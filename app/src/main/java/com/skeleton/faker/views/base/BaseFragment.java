/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/25
 * ******************************************************************************
 */

package com.skeleton.faker.views.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.skeleton.faker.AppApplication;
import com.skeleton.faker.R;
import com.skeleton.faker.injection.components.ActivityComponent;
import com.skeleton.faker.injection.components.ApplicationComponent;
import com.skeleton.faker.injection.components.DaggerActivityComponent;
import com.skeleton.faker.injection.modules.ActivityModule;
import com.tq.libs.fragment.AbsFragment;

import javax.inject.Inject;

public abstract class BaseFragment extends AbsFragment {

    private ActivityComponent _activityComponent;

    public ActivityComponent getActivityComponent() {
        if (_activityComponent == null) {
            _activityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(getAppComponent())
                    .activityModule(new ActivityModule(getActivity()))
                    .build();
        }
        return _activityComponent;
    }

    private ApplicationComponent getAppComponent() {
        return getApp().getAppComponent();
    }

    private AppApplication getApp() {
        return ((AppApplication) getContext().getApplicationContext());
    }

    @Inject
    Toast _toast;

    protected void showMessage(@StringRes int strRes) {
        showMessage(getString(strRes));
    }

    protected void showMessage(String message) {
        Toast toast = _toast;
        if (toast == null) {
            toast = Toast.makeText(getContext().getApplicationContext(), message, Toast.LENGTH_LONG);
        } else {
            toast.cancel();
            toast.setText(message);
        }
        toast.show();
    }

    protected void showAlert(String title, String message) {
        showAlert(title, message, R.string.action_ok);
    }

    protected void showAlert(String title, String message, @StringRes int closeTitle) {
        showAlert(title, message, getString(closeTitle));
    }

    protected void showAlert(String title, String message, String closeTitle) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(closeTitle, null)
                .show();
    }

}
