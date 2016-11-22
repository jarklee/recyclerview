/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/22
 * ******************************************************************************
 */

package com.skeleton.faker.injection.modules;

import android.app.Activity;
import android.content.Context;

import com.skeleton.faker.injection.annotations.ActivityContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    Activity getActivity() {
        return activity;
    }

    @Provides
    @Singleton
    @ActivityContext
    Context getContext() {
        return activity;
    }
}
