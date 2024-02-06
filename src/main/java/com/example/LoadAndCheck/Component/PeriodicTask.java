package com.example.LoadAndCheck.Component;

import com.example.LoadAndCheck.service.LoadFileThread;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PeriodicTask {

    private final LoadFileThread loadFileThread;

    public PeriodicTask(LoadFileThread loadFileThread) {
        this.loadFileThread = loadFileThread;
    }

    @Scheduled(fixedRate = 20_000)
    public void performPeriodicTask() {
        String mainFile = "MainFile.csv";
        List<String> files = Arrays.asList("First sub file","Second sub file","Third sub file");
        List<Integer> times = Arrays.asList(300,5_000,3_000);

        System.out.println("\n".repeat(4));
        loadFileThread.loadFileAndWait(mainFile, 500);

        for (String fileName: files){
            loadFileThread.loadFileWithoutWaiting(fileName, times.get(files.indexOf(fileName)));
        }
/*
        int size = 0;
        while (!loadFileThread.getRunningTasks().isEmpty()) {
            if (size != loadFileThread.getRunningTasks().size()) {
                size = loadFileThread.getRunningTasks().size();
                System.out.println(loadFileThread.getRunningTasks());
            }
        }
*/
    }

}
