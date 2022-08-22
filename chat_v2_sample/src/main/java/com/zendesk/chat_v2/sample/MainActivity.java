package com.zendesk.chat_v2.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import zendesk.chat.ChatConfiguration;
import zendesk.chat.ChatEngine;
import zendesk.messaging.MessagingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Global.isMissingCredentials()) {
            setContentView(R.layout.missing_credentials);
            return;
        }

//        ChatConfiguration chatConfiguration = ChatConfiguration.builder().build();

        MessagingActivity.builder()
                .withEngines(ChatEngine.engine())
                .show(this);
    }
}