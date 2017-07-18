package com.example.activitytest;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

public class MainApplication extends Application implements ReactApplication {

  private static MainApplication instance;


  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new CommunicatePackage()
      );
    }

    @Nullable
    @Override
    protected String getJSBundleFile() {
      File file = new File (FileConstant.JS_BUNDLE_LOCAL_PATH);
      if(file != null && file.exists()) {
        return FileConstant.JS_BUNDLE_LOCAL_PATH;
      } else {
        return super.getJSBundleFile();
      }
    }

    @Override
    protected String getJSMainModuleName() {
      return "index.android";
    }

    @Nullable
    @Override
    protected String getBundleAssetName() {
      return "index.android.bundle";
    }


  };

  /**
   *包名
   */
  public String getAppPackageName() {
    return this.getPackageName();
  }

  /**
   * 获取Application实例
   */
  public static MainApplication getInstance() {
    return instance;
  }

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    SoLoader.init(this, /* native exopackage */ false);
  }
}
