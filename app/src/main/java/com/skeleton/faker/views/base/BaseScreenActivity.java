/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/25
 * ******************************************************************************
 */

package com.skeleton.faker.views.base;

import android.os.Bundle;

import com.skeleton.faker.R;
import com.skeleton.faker.utils.FakerUtils;

public abstract class BaseScreenActivity extends BaseActivity implements ScreenMVPView {

    protected abstract ScreenPresenter getPresenter();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ScreenPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onSaveState(outState);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ScreenPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onRestoreState(savedInstanceState);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ScreenPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScreenPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ScreenPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        ScreenPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void notifyNetworkError() {
        showMessage(R.string.error_no_connection);
    }

    @Override
    public boolean isConnectedToInternet() {
        return FakerUtils.isConnectedToInternet(this);
    }
}
