package com.reactor.event.bus.poc;

import com.reactor.event.bus.poc.event.Target;
import com.reactor.event.bus.poc.processor.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.ReplayProcessor;

@Slf4j
@Configuration
public class InitializerData implements CommandLineRunner {

    @Autowired
    private ReplayProcessor<Target> rp;

    @Override
    public void run(String... args) throws Exception {

        for(int i=1; i <= 5; i++){
            rp.onNext(getNewTarget(i));
        }

        log.info("End of initializer");
    }

    private Target getNewTarget(int n){

        Target target = new Target();
        target.setName("Name " + n);
        target.setStatus(Status.STEP_1);
        return target;
    }
}
