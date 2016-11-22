/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class GroupViewHolder<DATA extends ExpandableGroup>
        extends ExpandableViewHolder<DATA> implements View.OnClickListener {

    public GroupViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
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

    public final void expandOrCollapseGroup() {
        BaseExpandableRecyclerViewModule module = _expandableModule;
        if (module == null) {
            return;
        }
        int position = getAdapterPosition();
        if (position == RecyclerView.NO_POSITION) {
            return;
        }
        _expandableModule.expandOrCollapseGroupAtPosition(position);
    }

    @Override
    public void onClick(View v) {
        if (shouldInterceptGroupClick()) {
            onGroupClicked();
            return;
        }
        expandOrCollapseGroup();
    }

    /**
     * @return {@code true} if don't want to close or expand group by default, left derive class
     * handle group click action.
     */
    public boolean shouldInterceptGroupClick() {
        return false;
    }

    /**
     * get called when {@link #shouldInterceptGroupClick()} return {@code false} and group got
     * clicked.
     */
    public void onGroupClicked() {
        // do nothing
    }
}
