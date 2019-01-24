package com.cb.nio.filevisitor;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * 使用FileVisitor 进行文件处理
 */
public class FileVisitorTest {
    public static void main(String[] args) throws IOException {
        // 使用FileVisitor对目录进行遍历
        Files.walkFileTree(Paths.get("q:", "test"), new SimpleFileVisitor<Path>() {
            // 在访问子目录前触发该方法
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问" + dir + "目录");
                return FileVisitResult.CONTINUE;
                // 停止访问dir下的entries，该返回值，只在 preVisitDirectory 中有效，其它方法中等效于 CONTINUE
//                return FileVisitResult.SKIP_SUBTREE;
            }
            // 在访问文件时触发该方法
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问" + file + "文件");
                if (file.endsWith("test1.txt")) {
                    System.out.println("------已找到test.txt,文件内容-----");
                    List<String> list = Files.readAllLines(file);
                    // 打印出文件的内容
                    System.out.println(list);
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }
            // 在访问失败时触发该方法
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                // 写一些具体的业务逻辑
                System.out.println("没有找到要处理的文件");
                return super.visitFileFailed(file, exc);
            }

            // 在访问目录之后触发该方法
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                // 写一些具体的业务逻辑
                System.out.println("文件已经处理完成");
                return super.postVisitDirectory(dir, exc);
            }
        });
    }
}