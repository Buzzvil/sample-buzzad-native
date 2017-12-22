package com.buzzvil.nativead.sample;

import android.app.Application;
import android.content.Context;

import com.buzzvil.buzzad.sdk.BuzzSDK;


/**
 * SampleApplication.java
 *
 * @author Hovan Yoo
 */
public class SampleApplication extends Application {

	static final String TAG = SampleApplication.class.getSimpleName();

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}

	@Override
	public void onCreate() {
		super.onCreate();

		BuzzSDK.init(this);
	}
}
