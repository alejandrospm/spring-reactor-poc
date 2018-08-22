package com.reactor.event.bus.poc.processor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ProcessorUtil {

    public static void processTime(){

        try {
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
            log.error("Error", e);
        }
    }
}
