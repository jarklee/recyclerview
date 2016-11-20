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

public interface RecyclerViewModule<VH extends RecyclerView.ViewHolder> {

    void attachAdapter(RecyclerViewModuleAdapter<VH> adapter);

    VH onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(VH holder, int position);

    void onBindViewHolder(VH holder, int position, List<Object> payloads);

    int getItemCount();

    int getItemViewType(int position);

    long getItemId(int position);

}
