/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/11/1
 * ******************************************************************************
 */

package com.skeleton.faker.views.activities;

import com.skeleton.faker.views.base.MVPView;
import com.tq.libs.recyclerview.expandable.ExpandableRecyclerViewModule;

public interface MainMVPView extends MVPView {
    void updateView(ExpandableRecyclerViewModule module);
}
