package com.cb.io.inputoutputstream;

import com.cb.constant.FileConstant;

import java.io.*;

/**
 * @author ChenOT
 * @date 2019-01-25
 * @see
 * @since
 */
public class InputOutputStreamTest {

    public static void main(String[] args) throws IOException {
        try(
                InputStream inputStream = new FileInputStream(FileConstant.IN_PATH);
                OutputStream outputStream = new FileOutputStream(FileConstant.OUT_PATH,true);
        ){
            byte[] data = new byte[1];
            int index = inputStream.read(data);
//        while(-1 != data){
//            System.out.println((char)data);
//            data = inputStream.read();
//        }
            System.out.println("-----------------------------");
//        Reader reader = new FileReader(PATH);
//        Reader reader = new InputStreamReader(inputStream);

//        int index = reader.read(data);
            while(-1 != index){
                System.out.println((char)index);
                outputStream.write(data);
                outputStream.flush();
                index = inputStream.read(data);
            }
        }



    }
}
