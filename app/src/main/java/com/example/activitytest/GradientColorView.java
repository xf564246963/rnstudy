package com.example.activitytest;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class GradientColorView extends View {

	private GradientDrawable mDrawable;

	private int mStartColor;
	private int mEndColor;

	public GradientColorView(Context context) {
		super(context);
		mDrawable = new GradientDrawable();
		mDrawable.setOrientation(Orientation.LEFT_RIGHT);
	}

	public void setStartColor(int startColor) {
		this.mStartColor = startColor;
	}

	public void setEndColor(int endColor) {
		this.mEndColor = endColor;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mDrawable.setBounds(0, 0, getWidth(), getHeight());
		mDrawable.setColors(new int[]{mStartColor, mEndColor});
		mDrawable.draw(canvas);

		WritableMap event = Arguments.createMap();
		event.putString("message","myMessage");
		ReactContext reactContext = (ReactContext)getContext();
		reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(),"topChange",event);

	}

}