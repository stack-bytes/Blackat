package com.stackbytes.service;

import lombok.Getter; /**
 * The purpose of this is to configure Blackat to send notifications to external systems in case of errors in config
 * Ex: Failing to get IP
 */

@Getter
public enum AlertLevel{
    NONE(0) ,LOW(1), MEDIUM(2), HIGH(3), ULTRA(4);

    private Integer level;
    private AlertLevel(int level) {
        this.level = level;
    }

}
