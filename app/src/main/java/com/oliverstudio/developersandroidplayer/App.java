package com.oliverstudio.developersandroidplayer;

import android.app.Application;

import com.oliverstudio.developersandroidplayer.di.AppComponent;
import com.oliverstudio.developersandroidplayer.di.DaggerAppComponent;
import com.oliverstudio.developersandroidplayer.di.modules.ContextModule;

public class App extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

}
