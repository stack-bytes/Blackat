package com.stackbytes.abstracts;

/**
 * Alerts are simple programmable interfaces for internal errors/warning of Blackat
 */

// TODO: might wanna have return types in the future for custom alert stack-trace
public abstract class Alert {
    public abstract void emmit(String params);
}
