package fr.ekito.coroutines;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ThreadsTest {

    static final int MAX_THREADS = 100;
    static final long WAIT = 1000L;

    @Test
    public void testThreads() throws InterruptedException {
        Long start = new Date().getTime();
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        List<ForkJoinTask> jobs = new ArrayList<>();
        for (int i = 0; i < MAX_THREADS; i++) {
            int val = i;
            jobs.add(forkJoinPool.submit(() -> {
                try {
                    Thread.sleep(WAIT);
                    System.out.print("[" + val + "]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        jobs.forEach(ForkJoinTask::join);

        Long end = new Date().getTime();
        Long delta = end - start;
        System.out.println("\ndone in " + delta);
    }

}
