/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/22
 * ******************************************************************************
 */

package com.skeleton.faker.injection.components;

import android.content.Context;
import android.widget.Toast;

import com.skeleton.faker.AppApplication;
import com.skeleton.faker.injection.annotations.AppContext;
import com.skeleton.faker.injection.modules.ApplicationModule;
import com.skeleton.faker.injection.modules.ConverterModule;
import com.skeleton.faker.injection.modules.DatabaseModules;
import com.skeleton.faker.injection.modules.NetModule;
import com.skeleton.faker.managers.DataManager;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class, ConverterModule.class, DatabaseModules.class})
public interface ApplicationComponent {

    @AppContext
    Context context();

    Retrofit retrofit();

    DataManager dataManager();

    Toast toast();

    void inject(AppApplication appApplication);
}
