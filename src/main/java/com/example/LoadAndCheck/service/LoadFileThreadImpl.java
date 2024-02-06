package com.example.LoadAndCheck.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoadFileThreadImpl implements LoadFileThread{

    private final Map<Long, String> runningTasks = new ConcurrentHashMap<>();

    @Override
    public void loadFileAndWait(String fileToLoad, int waitTime) {
        runningTasks.put(Thread.currentThread().getId(),  Thread.currentThread().getName());
        try {
            Thread.sleep(waitTime);
            System.out.println("File: " + fileToLoad + " is loaded.");
        } catch (InterruptedException e) {
            System.out.println("Some error.");
        }
        runningTasks.remove(Thread.currentThread().getId());
    }

    @Override
    @Async
    public void loadFileWithoutWaiting(String fileToLoad, int waitTime) {
        runningTasks.put(Thread.currentThread().getId(),  Thread.currentThread().getName());
        try {
            if (waitTime == 100) {
                throw new RuntimeException("something goes wrong");
            }
            System.out.println("File to load: " + fileToLoad + " started.");
            Thread.sleep(waitTime);
            System.out.println("File: " + fileToLoad + " is loaded. " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println("Some error.");
            Thread.currentThread().interrupt();
        }
        runningTasks.remove(Thread.currentThread().getId());
    }

    @Override
    public Map<Long, String> getRunningTasks() {
        return new HashMap<>(runningTasks);
    }

}
