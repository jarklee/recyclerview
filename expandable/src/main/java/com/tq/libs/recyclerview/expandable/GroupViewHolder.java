/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import android.view.View;

import com.tq.libs.recyclerview.core.SelfBindViewHolder;

public abstract class GroupViewHolder<DATA extends ExpandableGroup>
        extends SelfBindViewHolder<DATA> {

    public GroupViewHolder(View itemView) {
        super(itemView);
    }

    public void expandGroup() {

    }

    public void collapseGroup() {

    }
}
