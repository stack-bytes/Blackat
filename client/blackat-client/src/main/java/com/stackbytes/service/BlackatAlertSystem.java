package com.stackbytes.service;

import com.stackbytes.abstracts.Alert;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Setter
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BlackatAlertSystem{

    private Alert globalAlert = new Alert() {
        @Override
        public void emmit(String params) {}
    };
    private AlertLevel globalAlertLevel = AlertLevel.NONE;

    /*
    Only display the current alert if it's severity is under the general reportable severity level
     */
    protected void run(AlertLevel currentAlertLevel, String alertText) {
        if(currentAlertLevel.getLevel() <= globalAlertLevel.getLevel()){
           this.globalAlert.emmit(alertText);
        }
    }
}
