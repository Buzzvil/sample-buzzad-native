### Latest version: 2.0.1

### The Android SDK is supported on API 16 and above

### Prerequisite: Adding versions to build.gradle
```
ext {
    playServicesVersion = '11.8.0'
    androidSupportVersion = '25.3.1'
}
...
dependencies {
    compile("com.google.android.gms:play-services-ads:$playServicesVersion")
    compile("com.google.android.gms:play-services-basement:$playServicesVersion")
    compile("com.google.android.gms:play-services-location:$playServicesVersion")
    compile("com.android.support:support-v4:$androidSupportVersion")
    compile("com.android.support:appcompat-v7:$androidSupportVersion")
    compile("com.android.support:recyclerview-v7:$androidSupportVersion")
    compile("com.android.support:customtabs:$androidSupportVersion")
}
```

### Step 1: Setting User Profile(Optional)

```
BuzzSDK.setUserProfile(new UserProfile.Builder()
				.setBirthday("1990-12-31")
				.setGender(UserProfile.USER_GENDER_MALE)
				.build());
	}
```

### Step 2: Impl. AdListener

```
AdListener adListener = new AdListener() {
		@Override
		public void onError(BuzzAdError error) {
		}

		@Override
		public void onAdLoaded(Ad ad) {
			SampleActivity.this.ad = ad;
			// Do somethins with `Ad` objects.
		}

		@Override
		public void onClicked(Ad ad) {
		}
		
		@Override
		public void onLanding(Ad ad, boolean success) {
		}
	};
```

### Step 3: Loading Ad

If you set the audience's demographic profile, it may have positive impact on the fill rate and ad revenue.
```
NativeAd nativeAd = new NativeAd(SampleActivity.this, "[YOUR_APP_KEY]");
			nativeAd.setAdListener(adListener);
			nativeAd.loadAd();
```

### Step 4: Draw Ad

Refer to SampleAdView.java
```
ad.getCoverImage().loadIntoView(imageCover);
ad.getIconImage().loadIntoView(imageIcon);
textTitle.setText(ad.getTitle());
textDescription.setText(ad.getContent());
if (false == TextUtils.isEmpty(ad.getCallToAction())) {
    vGroupCallToAction.setVisibility(View.VISIBLE);
    textCallToAction.setText(ad.getCallToAction());
}
ad.registerViewForInteraction(vGroupNativeAdFrame);
```
