package com.scream.study.thread.volatiletest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Client {
    public static void main(String[] args) {
        List<Integer> numList = new ArrayList<>();
        List<FutureTask<List<Integer>>> resultList = new ArrayList<>();
        ExecutorService executorService = VolatilePooProvider.createExecutorService();
        for (int i = 0; i < 100; i++) {
            AddThread t = new AddThread(numList);
            FutureTask<List<Integer>> futureTask = (FutureTask<List<Integer>>) executorService.submit(t);
            resultList.add(futureTask);
        }
//        resultList.forEach(item -> {
//            try {
//                System.out.println(item.get().size());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        });
        ThreadPoolExecutor executor = (ThreadPoolExecutor)executorService;
        while(executor.getActiveCount()>=0){

            System.out.println(executor.getActiveCount());
            if(executor.getActiveCount() == 0){
                System.out.println("结束了");
                break;
            }
        }
        System.out.println(numList.size());
    }
}
