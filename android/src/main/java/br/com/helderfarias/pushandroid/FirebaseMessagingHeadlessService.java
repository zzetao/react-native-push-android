package br.com.helderfarias.pushandroid;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;
import com.google.firebase.messaging.RemoteMessage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import android.util.Log;

import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public class FirebaseMessagingHeadlessService extends HeadlessJsTaskService {
    private static final String KEY_TOKEN = "token";
    private static final String KEY_COLLAPSE_KEY = "collapseKey";
    private static final String KEY_DATA = "data";
    private static final String KEY_FROM = "from";
    private static final String KEY_MESSAGE_ID = "messageId";
    private static final String KEY_MESSAGE_TYPE = "messageType";
    private static final String KEY_SENT_TIME = "sentTime";
    private static final String KEY_ERROR = "error";
    private static final String KEY_TO = "to";
    private static final String KEY_TTL = "ttl";

    @Override
    protected @Nullable
    HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) return null;
        RemoteMessage remoteMessage = intent.getParcelableExtra("message");

        Log.d("MessagingService", "HeadlessService getTaskConfig");

        return new HeadlessJsTaskConfig(
                "FCMBackgroundMessage",
                remoteMessageToWritableMap(remoteMessage),
                5000,
                false
        );
    }

    public WritableMap remoteMessageToWritableMap(RemoteMessage remoteMessage) {
        WritableMap messageMap = Arguments.createMap();
        WritableMap dataMap = Arguments.createMap();

        if (remoteMessage.getCollapseKey() != null) {
            messageMap.putString(KEY_COLLAPSE_KEY, remoteMessage.getCollapseKey());
        }

        if (remoteMessage.getFrom() != null) {
            messageMap.putString(KEY_FROM, remoteMessage.getFrom());
        }

        if (remoteMessage.getTo() != null) {
            messageMap.putString(KEY_TO, remoteMessage.getTo());
        }

        if (remoteMessage.getMessageId() != null) {
            messageMap.putString(KEY_MESSAGE_ID, remoteMessage.getMessageId());
        }

        if (remoteMessage.getMessageType() != null) {
            messageMap.putString(KEY_MESSAGE_TYPE, remoteMessage.getMessageType());
        }

        if (remoteMessage.getData().size() > 0) {
            Set<Map.Entry<String, String>> entries = remoteMessage.getData().entrySet();
            for (Map.Entry<String, String> entry : entries) {
                dataMap.putString(entry.getKey(), entry.getValue());
            }
        }

        messageMap.putMap(KEY_DATA, dataMap);
        messageMap.putDouble(KEY_TTL, remoteMessage.getTtl());
        messageMap.putDouble(KEY_SENT_TIME, remoteMessage.getSentTime());
        return messageMap;
    }
}