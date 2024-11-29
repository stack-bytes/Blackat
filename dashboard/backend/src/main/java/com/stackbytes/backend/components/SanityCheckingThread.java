package com.stackbytes.backend.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SanityCheckingThread extends Thread{

    @Autowired
    ServiceRegistry serviceRegistry;


    //Bean factory
    @Bean
    public static SanityCheckingThread sanityCheckingThread(){
        return new SanityCheckingThread();
    }

    @Override
    public void run() {
        while(true){
            serviceRegistry.sanityEvict();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
