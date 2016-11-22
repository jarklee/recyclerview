/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/25
 * ******************************************************************************
 */

package com.skeleton.faker.managers;

import com.skeleton.faker.networks.FakerService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NetManager {

    private final FakerService mRemoteService;

    @Inject
    public NetManager(FakerService remoteService) {
        mRemoteService = remoteService;
    }
}
