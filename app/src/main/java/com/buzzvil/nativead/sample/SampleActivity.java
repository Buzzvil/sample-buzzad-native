package com.buzzvil.nativead.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.buzzvil.buzzad.BuzzAdError;
import com.buzzvil.buzzad.BuzzSDK;
import com.buzzvil.buzzad.UserProfile;
import com.buzzvil.buzzad.nativead.Ad;
import com.buzzvil.buzzad.nativead.AdListener;
import com.buzzvil.buzzad.nativead.NativeAd;

import java.util.List;

public class SampleActivity extends Activity {
	private View vGroupEditAppKey;
	private View buttonLoadAd, buttonShowAd, buttonClearAd;
	private EditText editAppKey;
	private TextView textConsole;

	private SampleAdView currentAdView;
	private NativeAd nativeAd;
	private Ad ad = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);

		vGroupEditAppKey = findViewById(R.id.vGroupEditAppKey);
		editAppKey = (EditText) findViewById(R.id.editAppKey);
		buttonLoadAd = findViewById(R.id.buttonLoadAd);
		buttonShowAd = findViewById(R.id.buttonShowAd);
		buttonClearAd = findViewById(R.id.buttonClearAd);

		textConsole = (TextView) findViewById(R.id.textConsole);

		buttonLoadAd.setOnClickListener(loadAd);
		buttonClearAd.setOnClickListener(clearAd);
		buttonShowAd.setOnClickListener(showAd);

		BuzzSDK.setUserProfile(new UserProfile.Builder()	// Optional
				.setBirthday("1990-12-31")
				.setGender(UserProfile.USER_GENDER_MALE)
				.build());
	}

	View.OnClickListener loadAd = new View.OnClickListener() {
		@Override
		public void onClick(View v)  {
			textConsole.setText(String.format("Requesting"));

			if (nativeAd != null) {
				nativeAd.destroy();
			}

			nativeAd = new NativeAd(SampleActivity.this, "[YOUR_APP_KEY]");
			nativeAd.setAdListener(adListener);
			nativeAd.loadAd(true);
		}
	};

	private View.OnClickListener clearAd = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (nativeAd != null) {
				nativeAd.destroy();
			}
			ad = null;
			textConsole.setText("");
		}
	};

	private View.OnClickListener showAd = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (ad == null) {
				return;
			}

			FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
					FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);

			currentAdView = new SampleAdView(SampleActivity.this, null);
			currentAdView.setAd(ad);
			currentAdView.setOnCloseListener(new SampleAdView.OnCloseListener() {
				@Override
				public void onClose(View view) {
					nativeAd.destroy();
				}
			});

			((ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content)).addView(
					currentAdView, -1, layoutParams);
			textConsole.setText(String.format("Ad size: %d", ad == null? 0 : 1));
		}
	};

	AdListener adListener = new AdListener() {
		@Override
		public void onError(BuzzAdError error) {
			Toast.makeText(getApplicationContext(), "Ad Failed:" + error, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onAdLoaded(Ad ad) {
			Toast.makeText(getApplicationContext(), "Ad loaded", Toast.LENGTH_SHORT).show();
			SampleActivity.this.ad = ad;
			textConsole.setText(String.format("Ad size: 1"));
		}

		@Override
		public void onClicked(Ad ad) {
			Toast.makeText(getApplicationContext(), "Ad Clicked", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onLanding(Ad ad, boolean success) {
			Toast.makeText(getApplicationContext(), "Ad Landing:" + success, Toast.LENGTH_SHORT).show();
		}
	};
}
