/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/11/21
 * ******************************************************************************
 */

package com.skeleton.faker.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tq.libs.recyclerview.expandable.ChildViewHolder;
import com.tq.libs.recyclerview.expandable.ExpandableGroup;
import com.tq.libs.recyclerview.expandable.ExpandableRecyclerViewModule;
import com.tq.libs.recyclerview.expandable.GroupViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainModule extends ExpandableRecyclerViewModule<MainModule.GroupView, MainModule.ChildView> {

    public MainModule(List<? extends ExpandableGroup> expandableGroups) {
        super(expandableGroups);
    }

    @Override
    public GroupView onCreateGroupViewHolder(ViewGroup viewGroup, int groupViewType) {
        return GroupView.create(viewGroup);
    }

    @Override
    public ChildView onCreateChildViewHolder(ViewGroup viewGroup, int childViewType) {
        return ChildView.create(viewGroup);
    }

    static class GroupView extends GroupViewHolder<MainGroup> {
        @BindView(android.R.id.text1)
        TextView tvText;

        public GroupView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindData(MainGroup mainGroup, int i) {
            tvText.setText(mainGroup.getTitle());
        }

        public static GroupView create(ViewGroup viewGroup) {
            return new GroupView(LayoutInflater.from(viewGroup.getContext())
                    .inflate(android.R.layout.simple_list_item_1, viewGroup, false));
        }
    }

    static class ChildView extends ChildViewHolder<String> {

        @BindView(android.R.id.text1)
        TextView tvText;

        public ChildView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindData(String s, int i) {
            tvText.setText(s);
        }

        public static ChildView create(ViewGroup viewGroup) {
            return new ChildView(LayoutInflater.from(viewGroup.getContext())
                    .inflate(android.R.layout.simple_list_item_1, viewGroup, false));
        }
    }
}
