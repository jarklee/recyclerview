/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/22
 * ******************************************************************************
 */

package com.skeleton.faker;

import android.app.Application;

import com.skeleton.faker.constants.Constants;
import com.skeleton.faker.injection.components.ApplicationComponent;
import com.skeleton.faker.injection.components.DaggerApplicationComponent;
import com.skeleton.faker.injection.modules.ApplicationModule;
import com.skeleton.faker.injection.modules.ConverterModule;
import com.skeleton.faker.injection.modules.DatabaseModules;
import com.skeleton.faker.injection.modules.NetModule;

public class AppApplication extends Application {

    private ApplicationComponent _appComponent;

    public ApplicationComponent getAppComponent() {
        if (_appComponent == null) {
            _appComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .converterModule(new ConverterModule())
                    .netModule(new NetModule(Constants.BASE_URL))
                    .databaseModules(new DatabaseModules(Constants.DATABASE_NAME))
                    .build();
        }
        return _appComponent;
    }

}
