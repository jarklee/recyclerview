/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/25
 * ******************************************************************************
 */

package com.skeleton.faker.views.base;

import android.os.Bundle;

import com.skeleton.faker.R;

public abstract class BaseScreenFragment extends BaseFragment implements ScreenMVPView {

    protected abstract ScreenPresenter getPresenter();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ScreenPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onSaveState(outState);
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        ScreenPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onRestoreState(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ScreenPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ScreenPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        ScreenPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    public void onStop() {
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
        return false;
    }
}
