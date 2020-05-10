package com.somvelisetty.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("JJMl8afJe3JKXTgvpUYfFPcneGmbJlMN6tD43WtQ")
                // if defined
                .clientKey("jGNnlkKQkNzfO4xJMJvVqybSrl8bfia161SSa1Y9")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
