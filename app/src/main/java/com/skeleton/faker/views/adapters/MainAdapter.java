/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/11/21
 * ******************************************************************************
 */

package com.skeleton.faker.views.adapters;

import com.tq.libs.recyclerview.core.RecyclerViewModuleAdapter;
import com.tq.libs.recyclerview.expandable.ExpandableViewHolder;

import javax.inject.Inject;

public class MainAdapter extends RecyclerViewModuleAdapter<ExpandableViewHolder> {

    @Inject
    public MainAdapter() {
        super(null);
    }
}
