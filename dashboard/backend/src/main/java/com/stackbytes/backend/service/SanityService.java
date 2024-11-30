package com.stackbytes.backend.service;

import com.stackbytes.backend.components.SanityCheckingThread;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SanityService {

    private final SanityCheckingThread sanityCheckingThread;

    @Value("${blackat.debug.active-thread.active}")
    private boolean sanityActive;

    SanityService(SanityCheckingThread sanityCheckingThread) {
        this.sanityCheckingThread = sanityCheckingThread;

        if(sanityActive) { sanityCheckingThread.start(); }
    }
}
