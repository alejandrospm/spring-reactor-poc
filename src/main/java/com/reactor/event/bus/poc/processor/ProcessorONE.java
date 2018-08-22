package com.reactor.event.bus.poc.processor;

import com.reactor.event.bus.poc.event.Target;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.ReplayProcessor;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
public class ProcessorONE extends BaseSubscriber<Target> {


    private ReplayProcessor replayProcessor;


    @Autowired
    public ProcessorONE(final ReplayProcessor replayProcessor){

        Scheduler scheduler = Schedulers.newParallel("processorOne", 3);
        this.replayProcessor = replayProcessor;
        replayProcessor
                .subscribeOn(scheduler)
                .subscribe(this);
    }


    @Override
    protected void hookOnNext(final Target target) {

        if(target.getStatus().equals(Status.STEP_1)) {
            log.info("Event name:" + target.getName());
            ProcessorUtil.processTime();
            target.setStatus(Status.STEP_2);
            replayProcessor.onNext(target);
        }
    }

    @Override
    protected void hookOnSubscribe(Subscription subscription) {

        log.info("SUBSCRIBED!!!");
        requestUnbounded();
    }

}
