/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/22
 * ******************************************************************************
 */

package com.skeleton.faker.injection.modules;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.skeleton.faker.injection.annotations.AppContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application getApplication() {
        return application;
    }

    @Provides
    @Singleton
    @AppContext
    Context getContext() {
        return application;
    }

    @Provides
    @Singleton
    Toast provideToast(@AppContext Context context) {
        return Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }
}
