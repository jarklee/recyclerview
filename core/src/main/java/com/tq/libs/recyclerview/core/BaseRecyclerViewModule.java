/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.core;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerViewModule<VH extends RecyclerView.ViewHolder>
        implements RecyclerViewModule<VH> {

    private ModuleRecyclerViewAdapter<VH> adapter;

    @Override
    public void attachAdapter(ModuleRecyclerViewAdapter<VH> adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return RecyclerView.NO_ID;
    }

    public final void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public final void notifyItemChanged(int position) {
        if (adapter != null) {
            adapter.notifyItemChanged(position);
        }
    }

    public final void notifyItemChanged(int position, Object payload) {
        if (adapter != null) {
            adapter.notifyItemChanged(position, payload);
        }
    }

    public final void notifyItemRangeChanged(int positionStart, int itemCount) {
        if (adapter != null) {
            adapter.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    public final void notifyItemRangeChanged(int positionStart, int itemCount, Object payload) {
        if (adapter != null) {
            adapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }
    }

    public final void notifyItemInserted(int position) {
        if (adapter != null) {
            adapter.notifyItemInserted(position);
        }
    }

    public final void notifyItemMoved(int fromPosition, int toPosition) {
        if (adapter != null) {
            adapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    public final void notifyItemRangeInserted(int positionStart, int itemCount) {
        if (adapter != null) {
            adapter.notifyItemRangeInserted(positionStart, itemCount);
        }
    }

    public final void notifyItemRemoved(int position) {
        if (adapter != null) {
            adapter.notifyItemRemoved(position);
        }
    }

    public final void notifyItemRangeRemoved(int positionStart, int itemCount) {
        if (adapter != null) {
            adapter.notifyItemRangeRemoved(positionStart, itemCount);
        }
    }
}
