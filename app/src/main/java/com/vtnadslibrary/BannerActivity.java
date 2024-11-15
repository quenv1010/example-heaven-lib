package com.vtnadslibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.nlbn.ads.adstype.AdNativeType;
import com.nlbn.ads.callback.NativeCallback;
import com.nlbn.ads.config.AdBannerConfig;
import com.nlbn.ads.adstype.AdBannerType;
import com.nlbn.ads.config.AdNativeConfig;
import com.nlbn.ads.util.Admob;
import com.nlbn.ads.util.BannerGravity;
import com.nlbn.ads.util.RemoteAdmob;

public class BannerActivity extends AppCompatActivity {

    AdBannerConfig adBannerConfig;
    AdNativeConfig adNativeConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        loadBanner();
        // loadBannerCollapseTop();
        // loadBannerCollapseBot();
        //loadNative();
        loadNative2();


    }

    public void loadBanner() {
       Admob.getInstance().loadBanner(BannerActivity.this, getString(R.string.ad_banner_id));
    }

    public void loadBannerCollapseTop() {
        adBannerConfig = new AdBannerConfig.Builder()
                .setKey(AdsConfig.key_ad_banner_id_collapse)
                .setBannerType(AdBannerType.BANNER_COLLAPSE)
                .setGravity(BannerGravity.top)
                .setView(findViewById(R.id.bannerTop))
                .build();
        RemoteAdmob.getInstance().loadBannerWithConfig(this, adBannerConfig);
    }

    public void loadBannerCollapseBot() {
        adBannerConfig = new AdBannerConfig.Builder()
                .setKey(AdsConfig.key_ad_banner_id_collapse)
                .setBannerType(AdBannerType.BANNER_COLLAPSE)
                .setGravity(BannerGravity.bottom)
                .setView(findViewById(R.id.bannerBot))
                .build();
        RemoteAdmob.getInstance().loadBannerWithConfig(this, adBannerConfig);
    }

    public void loadNative() {
        adNativeConfig = new AdNativeConfig.Builder()
                .setKey(AdsConfig.key_ad_native_id)
                .setNativeType(AdNativeType.NATIVE)
                .setLayout(R.layout.layout_native_custom)
                .setView(findViewById(R.id.native_ads))
                .build();
        RemoteAdmob.getInstance().loadNativeWithConfig(this, adNativeConfig, false);
    }

    public void loadNativeFloor() {
        adNativeConfig = new AdNativeConfig.Builder()
                .setKey(AdsConfig.KEY_AD_NATIVE_FLOOR_ID)
                .setNativeType(AdNativeType.NATIVE_FLOOR)
                .setLayout(R.layout.layout_native_custom)
                .setView(findViewById(R.id.native_ads))
                .build();
        RemoteAdmob.getInstance().loadNativeWithConfig(this, adNativeConfig, false);
    }

    private void loadNative2() {
        Admob.getInstance().loadNativeAd(this, getString(R.string.admod_native_id), new NativeCallback() {
            @Override
            public void onNativeAdLoaded(NativeAd nativeAd) {
                super.onNativeAdLoaded(nativeAd);
                NativeAdView normalView = (NativeAdView) LayoutInflater.from(BannerActivity.this).inflate(R.layout.layout_native_custom, null);
                FrameLayout adsContainer = findViewById(R.id.native_ads);
                adsContainer.removeAllViews();
                adsContainer.addView(normalView);
            }

            @Override
            public void onAdFailedToLoad() {
                super.onAdFailedToLoad();
            }
        });
    }
}
