/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/11/1
 * ******************************************************************************
 */

package com.skeleton.faker.views.activities;

import com.skeleton.faker.injection.annotations.PerAndroidComponent;
import com.skeleton.faker.managers.DataManager;
import com.skeleton.faker.views.adapters.MainGroup;
import com.skeleton.faker.views.adapters.MainModule;
import com.skeleton.faker.views.base.Presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

@PerAndroidComponent
public class MainPresenter extends Presenter<MainMVPView> {

    @Inject
    public MainPresenter(DataManager dataManager, Retrofit retrofit) {
        super(dataManager, retrofit);
    }

    public void loadData() {
        List<String> groupItems1 = new ArrayList<>();
        groupItems1.add("item1");
        groupItems1.add("item2");
        groupItems1.add("item3");
        groupItems1.add("item4");
        MainGroup group1 = new MainGroup("group1", groupItems1);
        group1.setExpand(true);

        List<String> groupItems2 = new ArrayList<>();
        groupItems2.add("item1");
        groupItems2.add("item2");
        groupItems2.add("item3");
        groupItems2.add("item4");
        groupItems2.add("item5");
        MainGroup group2 = new MainGroup("group2", groupItems2);

        List<MainGroup> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);

        MainModule mainModule = new MainModule(groups);
        getMVPView().updateView(mainModule);
    }
}
