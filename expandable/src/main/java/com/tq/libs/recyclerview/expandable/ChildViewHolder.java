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

public abstract class ChildViewHolder<DATA> extends SelfBindViewHolder<DATA> {

    public ChildViewHolder(View itemView) {
        super(itemView);
    }
}
