package com.scream.study.thread.volatiletest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class AddThread implements Callable<List<Integer>> {
    private List<Integer> numList;

    public AddThread(List<Integer> numList) {
        this.numList = numList;
    }

    @Override
    public List<Integer> call() {
        try {
            synchronized (AddThread.class) {
                if(numList.size()>50000){
                    System.out.println("==================================" +(1/0));
                }
                for (int i = 0; i < 100000; i++) {
                    Random r = new Random();
                    int num = r.nextInt(10000);
                    numList.add(num);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numList;
    }
}
