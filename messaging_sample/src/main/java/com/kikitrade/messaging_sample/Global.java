package com.kikitrade.messaging_sample;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;


import zendesk.android.FailureCallback;
import zendesk.android.SuccessCallback;
import zendesk.android.Zendesk;
import zendesk.logger.Logger;
import zendesk.messaging.android.DefaultMessagingFactory;


public class Global extends Application {

    private static final String SUBDOMAIN_URL = "";
    private static final String APPLICATION_ID = "";
    private static final String OAUTH_CLIENT_ID = "";
    private final static String CHAT_ACCOUNT_KEY = "";

    private static boolean missingCredentials = false;

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable logging
        Logger.setLoggable(true);


        Zendesk.initialize(this,
                "eyJzZXR0aW5nc191cmwiOiJodHRwczovL2tpa2l0cmFkZS56ZW5kZXNrLmNvbS9tb2JpbGVfc2RrX2FwaS9zZXR0aW5ncy8wMUc4WlJGSkhFR1ZETjRBODRGWkhTQlpWRy5qc29uIn0=",
                new SuccessCallback<Zendesk>() {
                    @Override
                    public void onSuccess(Zendesk zendesk) {
                        Log.d("Message"," 初始化成功");
                    }

                }, new FailureCallback<Throwable>() {
                    @Override
                    public void onFailure(@NonNull Throwable throwable) {
                        Log.d("Message"," 初始化失败");
                    }
                },new DefaultMessagingFactory());
    }

    static boolean isMissingCredentials() {
        return missingCredentials;
    }
}
