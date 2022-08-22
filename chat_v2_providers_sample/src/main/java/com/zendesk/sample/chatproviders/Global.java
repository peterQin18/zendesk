package com.zendesk.sample.chatproviders;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import com.zendesk.logger.Logger;
import com.zendesk.util.StringUtils;

import zendesk.chat.Chat;

public class Global extends Application {

    private final static String LOG_TAG = "ChatApiSample";

    /**
     * Account config needed to initialize {@link Chat}
     * <p/>
     * Account key can be found in Zopim Dashboard at the <a href="https://dashboard.zopim.com/#widget/getting_started">Getting Started Page</a>
     */
    private final static String ACCOUNT_KEY = "xh7oF282oC2ztTkWkUJ8FeEmASGGShg3"; // NB: Replace this key with your Zopim account key
    private final static String PROJECT_ID = "786b2309211b0fe4c873d61a59074a1f0ee0d7b23c7c9a0d"; // Firebase console -> Project settings -> General -> Project Id
    private final static String API_KEY = "xh7oF282oC2ztTkWkUJ8FeEmASGGShg3"; // Firebase console -> Project settings -> Cloud messaging -> Server Key
    private final static String FCM_SENDER_ID = "446899691269267457"; // Firebase console -> Project settings -> Cloud messaging -> Sender ID

    private static boolean missingCredentials = false;

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable logging
        Logger.setLoggable(true);

        if (StringUtils.isEmpty(ACCOUNT_KEY)) {
            missingCredentials = true;
            return;
        }

        // Initialize Chat SDK
        Chat.INSTANCE.init(this, ACCOUNT_KEY);

        // Initialise Firebase app to receive push notifications
//        initFirebase();
    }

    private void initFirebase() {
        if (PROJECT_ID.isEmpty() || API_KEY.isEmpty() || FCM_SENDER_ID.isEmpty()) {
            missingCredentials = true;
            return;
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId(PROJECT_ID)
                .setApiKey(API_KEY)
                .setGcmSenderId(FCM_SENDER_ID)
                .build();

        FirebaseApp.initializeApp(this, options);

        try {
            String token = FirebaseInstanceId.getInstance().getToken();
            if (token != null) {
                Log.d(LOG_TAG, "Obtained FCM token");
                Chat.INSTANCE.providers().pushNotificationsProvider().registerPushToken(token);
            }
        } catch (IllegalStateException e) {
            Log.d(LOG_TAG, "Error requesting FCM token");
        }
    }

    static boolean isMissingCredentials() {
        return missingCredentials;
    }
}
