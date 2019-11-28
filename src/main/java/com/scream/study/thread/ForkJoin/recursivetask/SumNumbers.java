package com.scream.study.thread.forkjoin.recursivetask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumNumbers extends RecursiveTask<Integer> {
    private Integer start;

    private Integer end;

    private Integer step;

    public SumNumbers(Integer start, Integer end, Integer step) {
        this.start = start;
        this.end = end;
        this.step = step;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        List<SumNumbers> tastList = new ArrayList<>();
        if (end - start <= step) {
            for (int i = start; i <= end; i++) {
                result += i;
            }
        } else {
//            System.out.println("走了else");
            int middle = (start + end) / 2;
            SumNumbers before = new SumNumbers(start, middle, step);
            SumNumbers after = new SumNumbers(middle + 1, end, step);
            tastList.add(before);
            tastList.add(after);
        }
        if (tastList.size() > 0) {
            tastList = (List<SumNumbers>) invokeAll(tastList);
            for (SumNumbers task : tastList) {
//                task.fork();
                result += task.join();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int time = 10000000;
        Long start = System.currentTimeMillis();
        SumNumbers sumNumber = new SumNumbers(1, time, 1);
        ForkJoinPool poll = new ForkJoinPool();
        poll.invoke(sumNumber);
        System.out.println(sumNumber.join());
        Long end = System.currentTimeMillis();
        System.out.println("共用时" + (end - start) + "毫秒");

        start = System.currentTimeMillis();
        System.out.println(sum(1, time));
        end = System.currentTimeMillis();
        System.out.println("共用时" + (end - start) + "毫秒");
    }

    private static int sum(int begin, int end) {
        int result = 0;
        for (int i = begin; i <= end; i++) {
            result+=i;
        }
        return result;
    }
}
