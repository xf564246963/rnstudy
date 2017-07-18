package com.example.activitytest;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static android.provider.Telephony.Carriers.PASSWORD;
import static com.example.activitytest.R.xml.preferences;


public class Communicate extends ReactContextBaseJavaModule implements LifecycleEventListener {

	static ReactApplicationContext mReactContext;
	Timer timer;

	public Communicate(ReactApplicationContext reactContext) {
		super(reactContext);
		mReactContext = reactContext;

		//添加监听
		reactContext.addLifecycleEventListener(this);
	}

	@Override
	public String getName() {
		return "Communicate";
	}

	public static void sendEvent(String eventName, String params) {
		mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
				.emit(eventName, params);
	}

	@ReactMethod
	public void addUser(String userName, String password, Callback successCallback, Callback errorCallback) {
		//回调通信
		try {
			if (TextUtils.isEmpty(userName)) {
				errorCallback.invoke("user name is empty");
				return;
			}
			if (TextUtils.isEmpty(password)) {
				errorCallback.invoke("password is empty");
				return;
			}

			successCallback.invoke("add user success");
		} catch (Exception e) {
			e.printStackTrace();
			errorCallback.invoke(e.getMessage());
		}
	}

	@ReactMethod
	public void login(String userName, String password, Promise promise) {
		//promise通信
		promise.resolve("response:"+userName+'&'+password);
		ThirdActivity.actionStart(SecondActivity.mContext);
	}

	class T extends TimerTask {
		public void run(){
			Communicate.sendEvent("deviceready","reactActivity start");
			timer.cancel();
		}
	}


	/*
	* 监听reactnative生命周期，只支持这三个方法，且需写在注册模块里面
	* */
	@Override
	public void onHostResume() {
		Log.i("INFO", "onHostResume");
		timer = new Timer();
		timer.schedule(new T(),500);
	}

	@Override
	public void onHostPause() {
		Log.i("INFO", "onHostPause");

	}

	@Override
	public void onHostDestroy() {
		Log.i("INFO", "onHostDestroy");
	}

}
