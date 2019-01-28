package com.cb.io.file;

import java.io.File;

/**
 * @author ChenOT
 * @date 2019-01-25
 * @see
 * @since
 */
public class FileTest {
    private static final String IN_PATH = "Q:\\in.txt";
    public static void main(String[] args) {
        File file = new File(IN_PATH);
        // 检查文件是否存在
        boolean fileExists = file.exists();
        // 获取文件长度
        long length = file.length();
        // 重命名或移动文件
        boolean success = file.renameTo(new File("c:\\data\\new-file.txt"));
        // 删除文件
        boolean success1 = file.delete();
        // 检测某个路径是文件还是目录
        boolean isDirectory = file.isDirectory();
        // 读取目录中的文件列表
        String[] fileNames = file.list();
        File[] files = file.listFiles();
    }
}
