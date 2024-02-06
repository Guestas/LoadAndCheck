package com.example.LoadAndCheck.service;

import java.util.Map;

public interface LoadFileThread {
    public void loadFileAndWait(String fileToLoad, int waitTime);

    public void loadFileWithoutWaiting(String fileToLoad, int waitTime);

    public Map<Long, String> getRunningTasks();
}
