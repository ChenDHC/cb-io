package com.cb.io.buffered;

import com.cb.constant.FileConstant;

import java.io.*;

/**
 * @author ChenOT
 * @date 2019-01-25
 * @see
 * @since
 */
public class BufferedTest {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(FileConstant.IN_PATH), 1024);
        int data = inputStream.read();
        System.out.println(data);
    }
}
