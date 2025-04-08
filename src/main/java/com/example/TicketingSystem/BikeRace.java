package com.example.TicketingSystem;

import java.util.*;

import java.util.concurrent.CountDownLatch;

class Biker extends Thread {
    private String name;
    private long startTime;
    private long endTime;
    private static final Random random = new Random();

    public Biker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            startTime = System.currentTimeMillis(); // Record start time
            Thread.sleep(1000 + random.nextInt(5000)); // Simulate race duration
            endTime = System.currentTimeMillis(); // Record end time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getTimeTaken() {
        return endTime - startTime;
    }

    public String getBikerName() {
        return name;
    }
}

public class BikeRace {
    public static void main(String[] args) throws InterruptedException {
        List<Biker> bikers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            bikers.add(new Biker("Biker " + i));
        }

        System.out.println("Race starts in...");
        for (int i = 10; i >= 0; i--) {
            System.out.println(i);
            Thread.sleep(1000);
        }

        System.out.println("Go!");
        for (Biker biker : bikers) {
            biker.start();
        }

        for (Biker biker : bikers) {
            biker.join();
        }

        bikers.sort(Comparator.comparingLong(Biker::getTimeTaken));

        System.out.println("\nRace Results:");
        System.out.printf("%-5s %-10s %-15s %-15s %-10s\n", "Rank", "Name", "Start Time", "End Time", "Time Taken (ms)");
        int rank = 1;
        for (Biker biker : bikers) {
            System.out.printf("%-5d %-10s %-15d %-15d %-10d\n", rank++, biker.getBikerName(), biker.getStartTime(), biker.getEndTime(), biker.getTimeTaken());
        }
    }
}
