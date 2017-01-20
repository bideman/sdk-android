package com.boss.stat;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by osx on 17/1/16.
 */

public class LoggerWatcher {
    private String appId = "";
    private String appKey = "";
    private String channelId = "_default_";

    private MessageSender messageSender = new MessageSender();

    public void startTracking(Context context, String appId, String key, String channelId) {
        messageSender.startup();

        // 需要判断是打开还是安装后首次打开
        if (!Utility.isInstalled(context)) {
            Utility.installed(context);

            try {
                JSONObject msg = new JSONObject();
                msg.put("who", "");
                msg.put("what", "install");
                msg.put("when", "" + System.currentTimeMillis());
                msg.put("where", "install");

                JSONObject content = new JSONObject();
                content.put("channelid", channelId);
                content.put("appid", appId);
                msg.put("content", content);

                msg.put("device", getDeviceInfo(context));

                messageSender.send(msg.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            JSONObject msg = new JSONObject();
            msg.put("who", "");
            msg.put("what", "startup");
            msg.put("when", "" + System.currentTimeMillis());
            msg.put("where", "startup");

            JSONObject content = new JSONObject();
            content.put("channelid", channelId);
            content.put("appid", appId);
            msg.put("content", content);

            msg.put("device", getDeviceInfo(context));

            messageSender.send(msg.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void trackRegistration(Context context, String userId) {
        try {
            JSONObject msg = new JSONObject();
            msg.put("who", userId);
            msg.put("what", "register");
            msg.put("when", "" + System.currentTimeMillis());
            msg.put("where", "register");

            JSONObject content = new JSONObject();
            content.put("channelid", channelId);
            content.put("appid", appId);
            msg.put("content", content);

            msg.put("device", getDeviceInfo(context));

            messageSender.send(msg.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void trackLogin(Context context, String userId) {
        try {
            JSONObject msg = new JSONObject();
            msg.put("who", userId);
            msg.put("what", "loggedin");
            msg.put("when", "" + System.currentTimeMillis());
            msg.put("where", "loggedin");

            JSONObject content = new JSONObject();
            content.put("channelid", channelId);
            content.put("appid", appId);
            msg.put("content", content);

            msg.put("device", getDeviceInfo(context));

            messageSender.send(msg.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void trackPurchase(Context context, String userId, String transactionId, String paymentType, String currency, String amount) {
        try {
            JSONObject msg = new JSONObject();
            msg.put("who", userId);
            msg.put("what", "payment");
            msg.put("when", "" + System.currentTimeMillis());
            msg.put("where", "payment");

            JSONObject content = new JSONObject();
            content.put("channelid", channelId);
            content.put("appid", appId);
            content.put("transactionid", transactionId);
            content.put("paymenttype", paymentType);
            content.put("currencytype", currency);
            content.put("amount", amount);

            msg.put("content", content);


            msg.put("device", getDeviceInfo(context));

            messageSender.send(msg.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void trackCustom(Context context, String eventId, String eventValue, String userId) {
        try {
            JSONObject msg = new JSONObject();
            msg.put("who", userId);
            msg.put("what", eventId);
            msg.put("when", "" + System.currentTimeMillis());
            msg.put("where", "event");

            JSONObject content = new JSONObject();
            content.put("channelid", channelId);
            content.put("appid", appId);
            content.put("value", eventValue);

            msg.put("content", content);


            msg.put("device", getDeviceInfo(context));

            messageSender.send(msg.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getDeviceInfo(Context context) {
        JSONObject content = new JSONObject();
        try {
            content.put("deviceid", Utility.getDeviceId(context));
            content.put("imei", Utility.getImei(context));
            content.put("imsi", Utility.getImsi(context));
            content.put("mac", Utility.getMacAddress(context));
            content.put("androidid", Utility.getAndroidId(context));
            content.put("board", Utility.getBoard());
            content.put("device", Utility.getDevice());
            content.put("brand", Utility.getBrand());
            content.put("manufacturer", Utility.getManufacturer());
            content.put("model", Utility.getModel());
            content.put("product", Utility.getProduct());
            content.put("systemversion", Utility.getSystemVersion());
            content.put("network", Utility.getNetworkType(context));
            content.put("width", Utility.getDisplayWidth(context));
            content.put("height", Utility.getDisplayHeight(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return content;
    }
}
