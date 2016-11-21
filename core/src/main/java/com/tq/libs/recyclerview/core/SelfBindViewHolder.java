/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/21
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract class SelfBindViewHolder<DATA> extends RecyclerView.ViewHolder {

    public SelfBindViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(DATA item, int position);

    public void bindData(DATA item, int position, List<Object> payloads){
        bindData(item, position);
    }
}
