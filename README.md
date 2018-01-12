### v.1.9.16: Migration Guide
If you use a BuzzTextView, It should be replaced with TextView.

### The Android SDK is supported on API 16 and above

### Prerequisite: Adding repositories and dependencies to build.gradle

```
repositories {
    maven { url "https://dl.bintray.com/buzzvil/maven/" }
    maven { url "http://dl.appnext.com/" }
    maven { url 'https://s3.amazonaws.com/moat-sdk-builds' }
}
...
dependencies {
    compile("com.buzzvil.buzzad:buzzad-sdk:1.+")
    compile('com.android.support:multidex:1.0.1')
}
```

### Step 1: Setting User Profile(Optional)

```
BuzzSDK.setUserProfile(new UserProfile.Builder()	// Optional
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
		public void onAdLoaded(List<Ad> ads) {
			SampleActivity.this.ads = ads;
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

### Step 3: Loading Ads

If you set the audience's demographic profile, it may have positive impact on the fill rate and ad revenue.
```
NativeAd nativeAd = new NativeAd(SampleActivity.this, "[YOUR_APP_KEY]");
			nativeAd.setAdListener(adListener);
			nativeAd.loadAds(count);
```
