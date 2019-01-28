package com.cb.io.inputoutputstream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author ChenOT
 * @date 2019-01-25
 * @see
 * @since
 */
public class RandomAccessFileTest {
    private static final String IN_PATH = "Q:\\in.txt";
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(IN_PATH, "rw");
        // 从第2个位置开始写
        randomAccessFile.seek(1);
        long pointer = randomAccessFile.getFilePointer();
        System.out.println(pointer);
        int aByte = randomAccessFile.read();
        System.out.println(aByte);
        // 写数据会直接替换该位原有的内容
        randomAccessFile.write("hello".getBytes());
//        randomAccessFile.write("j".getBytes());
        randomAccessFile.close();
    }
}
