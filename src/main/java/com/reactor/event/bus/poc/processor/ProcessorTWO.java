package com.reactor.event.bus.poc.processor;

import com.reactor.event.bus.poc.event.Target;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.ReplayProcessor;

@Slf4j
@Service
public class ProcessorTWO extends BaseSubscriber<Target> {


    private ReplayProcessor replayProcessor;


    @Autowired
    public ProcessorTWO(final ReplayProcessor replayProcessor){

        this.replayProcessor = replayProcessor;
        replayProcessor.subscribe(this);
    }


    @Override
    protected void hookOnNext(final Target target) {

        if(target.getStatus().equals(Status.STEP_2)) {
            log.info("Event name:" + target.getName());
            ProcessorUtil.processTime();
            target.setStatus(Status.STEP_3);
            replayProcessor.onNext(target);
        }
    }

    @Override
    protected void hookOnSubscribe(Subscription subscription) {

        log.info("SUBSCRIBED!!!");
        requestUnbounded();
    }

}
