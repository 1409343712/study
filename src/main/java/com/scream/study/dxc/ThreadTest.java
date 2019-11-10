package com.scream.study.dxc;

import java.io.File;

public class ThreadTest extends Thread {

    private static int sum = 0;
    private static void sum(File file) {
        File[] fileList = file.listFiles();
        if (fileList != null) {
            for (File eachFile : fileList) {
                if (eachFile.isDirectory()) {
                    sum(eachFile);
                } else {
                    sum++;
                }
            }
        }
    }
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        File file = new File("D:\\");
        sum(file);
        System.out.println(sum);
        Long end = System.currentTimeMillis();
        System.out.println("耗时"+ (end-start));

    }
}
