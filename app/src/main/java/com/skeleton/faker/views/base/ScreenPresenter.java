/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/25
 * ******************************************************************************
 */

package com.skeleton.faker.views.base;

import android.support.annotation.CallSuper;

import com.skeleton.faker.managers.DataManager;

import retrofit2.Retrofit;

public abstract class ScreenPresenter<V extends ScreenMVPView> extends Presenter<V> {

    public ScreenPresenter(DataManager dataManager, Retrofit retrofit) {
        super(dataManager, retrofit);
    }

    @CallSuper
    public void onStart() {

    }

    @CallSuper
    public void onResume() {

    }

    @CallSuper
    public void onPause() {

    }

    @CallSuper
    public void onStop() {

    }
}
