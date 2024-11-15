package com.stackbytes.service;

import com.stackbytes.model.Alert;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * The purpose of this is to configure Blackat to send notifications to external systems in case of errors in config
 * Ex: Failing to get IP
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AlertSystem {

    private Alert globalAlert;


}
