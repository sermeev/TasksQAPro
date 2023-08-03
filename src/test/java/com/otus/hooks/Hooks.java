package com.otus.hooks;

import com.google.inject.Inject;
import com.otus.bdd.support.GuiceScoped;
import io.cucumber.java.After;

public class Hooks {
    @Inject
    private GuiceScoped guiceScoped;

    @After
    public void close(){
        if(guiceScoped.driver!=null){
            guiceScoped.driver.close();
            guiceScoped.driver.quit();
        }
    }
}
