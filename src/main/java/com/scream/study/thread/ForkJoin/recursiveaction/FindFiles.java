package com.scream.study.thread.forkjoin.recursiveaction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FindFiles extends RecursiveAction {
    private File file;

    public FindFiles(File file) {
        this.file = file;
    }

    @Override
    protected void compute() {
        File[] fileList = file.listFiles();
        List<FindFiles> taskList = new ArrayList<>();
        if (fileList != null) {
            for (File eachFIle : fileList) {
                if (eachFIle.isDirectory()) {
                    FindFiles eachTask = new FindFiles(eachFIle);
                    taskList.add(eachTask);
                } else {
                    System.out.println(eachFIle.getAbsolutePath());
                }
            }
        }
        if(!taskList.isEmpty()){
            for (FindFiles eachTask : taskList) {
//                eachTask.fork();
                eachTask.join();
            }
        }

    }

    public static void main(String[] args) {
        FindFiles task = new FindFiles(new File("D:\\腾讯"));
        ForkJoinPool poll = new ForkJoinPool();
        poll.execute(task);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始了");
        task.join();
        System.out.println("结束了");
    }
}
