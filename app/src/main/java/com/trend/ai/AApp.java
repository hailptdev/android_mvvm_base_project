package com.trend.ai;


import android.os.StrictMode;
import android.util.Log;
import com.trend.ai.di.DaggerAppComponent;
import com.twitter.sdk.android.core.*;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author ihsan on 11/29/17.
 */

public class AApp extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);

//        Log.d(TAG, "Setting up StrictMode policy checking.");
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))//enable logging when app is in debug mode
                .twitterAuthConfig(new TwitterAuthConfig(getResources().getString(R.string.CONSUMER_KEY), getResources().getString(R.string.CONSUMER_SECRET)))//pass the created app Consumer KEY and Secret also called API Key and Secret
                .debug(true)//enable debug mode
                .build();

        //finally initialize twitter with created configs
        Twitter.initialize(config);
    }
}
