package com.courier.trackmonitor;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import com.courier.trackmonitor.utils.Logger;

/**
 * Created by basselchaitani on 2/13/19.
 */
public class App extends Application {

    private static App instance;

    private Thread.UncaughtExceptionHandler defaultUEH;

    public App() {
        instance = this;

        defaultUEH = Thread.getDefaultUncaughtExceptionHandler();

        // setup handler for uncaught exception
        Thread.UncaughtExceptionHandler _unCaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                Logger.e("unCaughtException", throwable.getMessage());

                // re-throw critical exception further to the os (important)
                defaultUEH.uncaughtException(thread, throwable);
            }
        };

        Thread.setDefaultUncaughtExceptionHandler(_unCaughtExceptionHandler);
    }

    public static Context getContext() {

        if (instance == null) {
            instance = new App();
        }

        return instance;
    }
}