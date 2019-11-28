package com.scream.study.thread.forkjoin.recursivetask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumFiles extends RecursiveTask<Integer> {
    private File file;

    public SumFiles(File file) {
        this.file = file;
    }

    @Override
    protected Integer compute() {
        int count = 0;
        File[] fileList = file.listFiles();
        List<SumFiles> taskList = new ArrayList<>();
        if (fileList != null) {
            for (File eachFile : fileList) {
                if (eachFile.isDirectory()) {
                    taskList.add(new SumFiles(eachFile));
                } else {
                    count++;
                }
            }
        }
        if (!taskList.isEmpty()) {
            List<SumFiles> afterTaskList = (List<SumFiles>) invokeAll(taskList);
            for (SumFiles sumFiles : afterTaskList) {
//                sumFiles.fork();
                count += sumFiles.join();
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int result = 0;
        File file = new File("D:\\");
        Long start = System.currentTimeMillis();
        SumFiles sumFiles = new SumFiles(file);
        ForkJoinPool poll = new ForkJoinPool();
        poll.invoke(sumFiles);
        result = sumFiles.join();
        Long end = System.currentTimeMillis();
        System.out.println(result + "共用时" + (end - start) + "毫秒");


        start = System.currentTimeMillis();
        sum(file);
        end = System.currentTimeMillis();
        System.out.println(sum + "共用时" + (end - start) + "毫秒");
    }

    private static int sum = 0;

    private static void sum(File file) {
        int count = 0;
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
}
