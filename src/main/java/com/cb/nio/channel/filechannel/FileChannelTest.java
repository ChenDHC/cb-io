package com.cb.nio.channel.filechannel;

import com.cb.constant.FileConstant;
import com.cb.constant.Utils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenOT
 * @date 2019-01-28
 * @see
 * @since
 */
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        long time1 = System.currentTimeMillis();
        for(int i=0; i<1000; i++){
//            List<String> list = readFileToList();
            List<String> list = Utils.readFileToList(FileConstant.IN_PATH);
//            System.out.println(list.size());
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);

//        for(String str:list){
//            System.out.println(str);
//        }
//        writeFile();
    }
    private static List<String> readFileToList() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FileConstant.IN_PATH);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(1024);

        int byteRead = fileChannel.read(byteBuffer);
        StringBuffer stringBuffer = new StringBuffer();
        List<String> list = new ArrayList<>();
        while (byteRead != -1){
            byteBuffer.flip();
            decoder.decode(byteBuffer, charBuffer, false);
            charBuffer.flip();
            while(charBuffer.hasRemaining()){
                char b = charBuffer.get();
                stringBuffer.append(b);
                if(b == 13 || b==10){
                    String temp = stringBuffer.toString().trim();
                    if(StringUtils.isNotBlank(temp)){
                        list.add(stringBuffer.toString().trim());
                    }
                    stringBuffer.setLength(0);
                }
            }
            byteBuffer.clear();
            charBuffer.clear();
            byteRead = fileChannel.read(byteBuffer);
        }
        fileInputStream.close();
        fileChannel.close();

        if(stringBuffer.toString().trim().length()>0){
            list.add(stringBuffer.toString().trim());
        }
        return  list;
    }
    private static void readFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(FileConstant.IN_PATH, "rw");
        FileChannel fileChannel = raf.getChannel();
        // 创建48个字节的buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        // 数据从channel写入buffer
        int byteRead = fileChannel.read(byteBuffer);
        while (byteRead != -1) {
            // 切换buffer写模式为读模式（改变position、limit的设置）
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
            // 准备，向buffer写入新的数据（读模式切换为写模式）
            byteBuffer.clear();
            byteRead = fileChannel.read(byteBuffer);
        }
        raf.close();
    }
    private static void writeFile() throws IOException {
        FileOutputStream is = new FileOutputStream(FileConstant.OUT_PATH);
        FileChannel fileChannel = is.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);
        String content = "hello word";
//        byteBuffer.clear();
        byteBuffer.put(content.getBytes());
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            fileChannel.write(byteBuffer);
        }
        fileChannel.close();
        is.close();
    }
}
