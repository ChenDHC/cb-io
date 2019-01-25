package com.cb.io.system;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * System.in System.out System.error
 * @author ChenOT
 * @date 2019-01-24
 * @see
 * @since
 */
public class SystemIoTest {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("我是老师");
        System.err.println("我是老师");
        OutputStream outputStream = new FileOutputStream("Q:\\test.txt");
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        System.out.println("我是老师");
    }
}
