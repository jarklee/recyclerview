/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/21
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import android.view.View;

import com.tq.libs.recyclerview.core.SelfBindViewHolder;

public abstract class ExpandableViewHolder<DATA> extends SelfBindViewHolder<DATA> {

    protected BaseExpandableRecyclerViewModule _expandableModule;

    public ExpandableViewHolder(View itemView) {
        super(itemView);
    }

    void setExpandableModule(BaseExpandableRecyclerViewModule module) {
        _expandableModule = module;
    }

    public abstract void expandGroup();

    public abstract void collapseGroup();
}
