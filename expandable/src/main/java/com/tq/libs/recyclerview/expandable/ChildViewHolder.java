/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/21
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ChildViewHolder<DATA> extends ExpandableViewHolder<DATA> {

    public ChildViewHolder(View itemView) {
        super(itemView);
    }

    public final void expandGroup() {
        BaseExpandableRecyclerViewModule module = _expandableModule;
        if (module == null) {
            return;
        }
        int position = getAdapterPosition();
        if (position == RecyclerView.NO_POSITION) {
            return;
        }
        _expandableModule.expandGroupAtPosition(position);
    }

    public final void collapseGroup() {
        BaseExpandableRecyclerViewModule module = _expandableModule;
        if (module == null) {
            return;
        }
        int position = getAdapterPosition();
        if (position == RecyclerView.NO_POSITION) {
            return;
        }
        _expandableModule.collapseGroupAtPosition(position);
    }
}
