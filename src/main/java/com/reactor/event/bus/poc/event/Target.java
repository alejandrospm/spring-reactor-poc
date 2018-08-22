package com.reactor.event.bus.poc.event;


import com.reactor.event.bus.poc.processor.Status;
import lombok.Data;

@Data
public class Target {

    private String processorExecuted;

    private String name;

    private Status status;
}
