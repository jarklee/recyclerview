/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/25
 * ******************************************************************************
 */

package com.skeleton.faker.views.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;

import com.skeleton.faker.managers.DataManager;
import com.skeleton.faker.utils.FakerUtils;

import retrofit2.Retrofit;

public abstract class Presenter<V> {

    protected final DataManager mDataManager;

    protected final Retrofit mRetrofit;

    public Presenter(DataManager dataManager, Retrofit retrofit) {
        this.mDataManager = dataManager;
        this.mRetrofit = retrofit;
    }

    private V mView;

    private boolean isViewAttach;

    public final void attachView(V view) {
        this.mView = view;
        isViewAttach = true;
        onAttachView();
    }

    public final void detachView() {
        this.isViewAttach = false;
        onDetachView();
        this.mView = null;
    }

    public final V getMVPView() {
        return mView;
    }

    public final boolean isViewAttach() {
        return isViewAttach && mView != null;
    }

    @CallSuper
    protected void onAttachView() {
    }

    @CallSuper
    protected void onDetachView() {
    }

    @CallSuper
    public void onSaveState(Bundle outState) {

    }

    @CallSuper
    public void onRestoreState(Bundle outState) {

    }

    public final String getErrorMessage(Throwable throwable) {
        return FakerUtils.getError(throwable, mRetrofit);
    }
}
