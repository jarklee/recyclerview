/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.core;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerViewModuleAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private RecyclerViewModule<VH> recyclerViewModule;

    public RecyclerViewModuleAdapter(RecyclerViewModule<VH> recyclerViewModule) {
        attachRecyclerViewModule(recyclerViewModule);
    }

    public void setRecyclerViewModule(RecyclerViewModule<VH> recyclerViewModule) {
        attachRecyclerViewModule(recyclerViewModule);
        notifyDataSetChanged();
    }

    private void attachRecyclerViewModule(RecyclerViewModule<VH> recyclerViewModule) {
        RecyclerViewModule<VH> oldModule = this.recyclerViewModule;
        if (oldModule != null) {
            oldModule.attachAdapter(null);
        }
        if (recyclerViewModule != null) {
            recyclerViewModule.attachAdapter(this);
        }
        this.recyclerViewModule = recyclerViewModule;
    }

    public RecyclerViewModule<VH> getRecyclerViewModule() {
        return recyclerViewModule;
    }

    @Override
    public void onViewRecycled(VH holder) {
        if (recyclerViewModule != null) {
            recyclerViewModule.onViewRecycled(holder);
        }
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        return recyclerViewModule == null ? super.onFailedToRecycleView(holder)
                : recyclerViewModule.onFailedToRecycleView(holder);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return recyclerViewModule == null ? null : recyclerViewModule.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (recyclerViewModule != null) {
            recyclerViewModule.onBindViewHolder(holder, position);
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        if (recyclerViewModule != null) {
            recyclerViewModule.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public int getItemCount() {
        return recyclerViewModule == null ? 0 : recyclerViewModule.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return recyclerViewModule == null ? super.getItemViewType(position)
                : recyclerViewModule.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return recyclerViewModule == null ? super.getItemId(position)
                : recyclerViewModule.getItemId(position);
    }
}
