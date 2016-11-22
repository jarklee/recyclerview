/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/22
 * ******************************************************************************
 */

package com.skeleton.faker.injection.components;

import com.skeleton.faker.injection.annotations.PerAndroidComponent;
import com.skeleton.faker.injection.modules.ActivityModule;
import com.skeleton.faker.views.activities.MainActivity;

import dagger.Component;

@PerAndroidComponent
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
