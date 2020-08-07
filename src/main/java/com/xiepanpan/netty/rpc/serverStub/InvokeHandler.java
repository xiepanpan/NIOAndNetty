package com.xiepanpan.netty.rpc.serverStub;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author: xiepanpan
 * @Date: 2020/8/7 0007
 * @Description: 服务端业务处理类
 */
public class InvokeHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ClassInfo classInfo = (ClassInfo) msg;
        Object clazz = Class.forName(getImplClassName(classInfo)).newInstance();
        Method method = clazz.getClass().getMethod(classInfo.getMethodName(), classInfo.getTypes());
        //通过反射调用实现类的方法
        Object result = method.invoke(clazz, classInfo.getObjects());
        ctx.writeAndFlush(result);
    }

    /**
     * 得到某接口下某个实现类的名字
     * @param classInfo
     * @return
     */
    private String getImplClassName(ClassInfo classInfo) throws ClassNotFoundException {
        String interfacePath ="com.xiepanpan.netty.rpc.server";
        int lastDot = classInfo.getClassName().lastIndexOf(".");
        String interfaceName = classInfo.getClassName().substring(lastDot);
        Class<?> superClass = Class.forName(interfacePath + interfaceName);
        Reflections reflections = new Reflections(interfacePath);
        //得到某接口下的所有实现类
        Set<Class<?>> implClassSet = (Set<Class<?>>) reflections.getSubTypesOf(superClass);
        if (implClassSet.size()==0) {
            System.out.println("未找到实现类");
            return null;
        }else if (implClassSet.size()>1) {
            System.out.println("找到多个实现类，未明确使用哪一个");
            return null;
        }else {
            Class[] classes = implClassSet.toArray(new Class[0]);
            return classes[0].getName();
        }
    }
}