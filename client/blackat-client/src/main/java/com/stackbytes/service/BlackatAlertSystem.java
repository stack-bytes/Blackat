package com.stackbytes.service;

import com.stackbytes.abstracts.BlackatAlert;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Setter
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BlackatAlertSystem{

    private BlackatAlert globalBlackatAlert = new BlackatAlert() {
        @Override
        public void emmit(String params) {}
    };
    private BlackatAlertLevel globalBlackatAlertLevel = BlackatAlertLevel.NONE;

    /*
    Only display the current alert if it's severity is under the general reportable severity level
     */
    public void run(BlackatAlertLevel currentBlackatAlertLevel, String alertText) {
        if(currentBlackatAlertLevel.getLevel() <= globalBlackatAlertLevel.getLevel()){
           this.globalBlackatAlert.emmit(alertText);
        }
    }
}
