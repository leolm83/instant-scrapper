package com.leolm.instantgamingscrapper.scrapper.services;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WaitService {
    private WaitService() {
    }

    public static Long getRandomWaitTime() {
        long min = 1;
        long max = 10;
        Random random = new Random();
        long randomLong = min + (long) (random.nextDouble() * (max - min));
        return randomLong;
    }

    public static void waitARandomInterval() throws InterruptedException {
        Long randomWaitTime = WaitService.getRandomWaitTime();
        System.out.println("AGUARDANDO " + randomWaitTime + " SEGUNDOS");
        TimeUnit.SECONDS.sleep(randomWaitTime);
    }
}
