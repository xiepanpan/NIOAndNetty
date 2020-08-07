package com.xiepanpan.nio.file;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: xiepanpan
 * @Date: 2020/8/4
 * @Description:  nio实现文件操作
 */
public class TestNIO {

    /**
     * 往本地文件中写数据
     */
    @Test
    public void test1() throws IOException {
        //1. 创建文件输出流
        File file;
        FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
        //2. 从流中得到一个通道
        FileChannel channel = fileOutputStream.getChannel();
        //3. 提供一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //4. 往缓冲区中存入数据
        String string = "hello,nio";
        byteBuffer.put(string.getBytes());
        //5. 翻转缓冲区
        byteBuffer.flip();
        //6. 把缓冲区写到通道中
        channel.write(byteBuffer);
        //7. 关闭流
        fileOutputStream.close();
    }

    /**
     * 从本地文件中读取数据
     * @throws FileNotFoundException
     */
    @Test
    public void test2() throws IOException {
        //1. 创建输入流
        File file = new File("test.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        //2. 得到一个通道
        FileChannel channel = fileInputStream.getChannel();
        //3. 准备一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //4. 从通道里读取数据并保存到缓冲区中
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        //5. 关闭
        fileInputStream.close();
    }

    /**
     * 使用NIO实现文件复制
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        //1. 创建两个流
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test.txt");

        //2. 得到两个通道
        FileChannel sourceFileChannel = fileInputStream.getChannel();
        FileChannel destFileChannel = fileOutputStream.getChannel();

        //3. 复制
        destFileChannel.transferFrom(sourceFileChannel,0,sourceFileChannel.size());

        //4. 关闭
        fileInputStream.close();
        fileOutputStream.close();
    }


}
