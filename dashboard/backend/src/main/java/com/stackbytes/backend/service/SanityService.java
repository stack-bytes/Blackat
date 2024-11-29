package com.stackbytes.backend.service;

import com.stackbytes.backend.components.SanityCheckingThread;
import org.springframework.stereotype.Service;

@Service
public class SanityService {

    private final SanityCheckingThread sanityCheckingThread;

    SanityService(SanityCheckingThread sanityCheckingThread) {
        this.sanityCheckingThread = sanityCheckingThread;
        sanityCheckingThread.start();
    }
}
