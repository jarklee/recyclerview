/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/22
 * ******************************************************************************
 */

package com.skeleton.faker.managers;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {

    private final PrefManager prefManager;
    private final NetManager netManager;

    @Inject
    public DataManager(PrefManager prefManager, NetManager netManager) {
        this.prefManager = prefManager;
        this.netManager = netManager;
    }

    public PrefManager pref() {
        return prefManager;
    }

    public NetManager net() {
        return netManager;
    }
}
