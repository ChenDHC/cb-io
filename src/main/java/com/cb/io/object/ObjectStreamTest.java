package com.cb.io.object;

import com.cb.constant.FileConstant;

import java.io.*;

/**
 * @author ChenOT
 * @date 2019-01-25
 * @see
 * @since
 */
public class ObjectStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileConstant.IN_PATH));
        City city = new City();
        city.setCode("010");
        city.setName("北");
        oos.writeObject(city);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FileConstant.IN_PATH));
        City city1 = (City)ois.readObject();
        System.out.println(city1);
        ois.close();

    }
}
