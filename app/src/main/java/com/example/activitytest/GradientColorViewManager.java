package com.example.activitytest;

/**
 * Created by xiongfeng on 2017/5/9.
 */

import javax.annotation.Nullable;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class GradientColorViewManager extends SimpleViewManager<GradientColorView> {

    // React-Native官方大多数自定义View都是用RCT开头，这里保持规范性
    private static final String REACT_CLASS = "RCTGradientColorView";

    @Override
    public String getName() {
        // 此处name在后面JS组件开发时会用到，需要统一命名
        return REACT_CLASS;
    }

    @Override
    protected GradientColorView createViewInstance(ThemedReactContext reactContext) {
        // GradientColorView的实例化
        return new GradientColorView(reactContext);
    }

    /**
     * ReactProp注解方法，React-Native底层会自动反射调用，
     * name字符串值与JS中配置的属性名保持一致
     * customType字符串值表示颜色转换，不配置会转换异常
     *
     * @param view GradientColorView实例
     * @param startColor 渐变起始值
     */
    @ReactProp(name = "startColor", customType = "Color")
    public void setStartColor(GradientColorView view, @Nullable Integer startColor) {
        view.setStartColor(startColor);
    }

    /**
     * ReactProp注解方法，React-Native底层会自动反射调用，
     * name字符串值与JS中配置的属性名保持一致
     * customType字符串值表示颜色转换，不配置会转换异常
     *
     * @param view GradientColorView实例
     * @param endColor 渐变结束值
     */
    @ReactProp(name = "endColor", customType = "Color")
    public void setEndColor(GradientColorView view, @Nullable Integer endColor) {
        view.setEndColor(endColor);
    }

}
