/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/22
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import android.view.ViewGroup;

import com.tq.libs.recyclerview.expandable.anotations.ListType;

import java.util.Collection;
import java.util.List;

public abstract class BaseExpandableRecyclerViewModule<PARENT extends GroupViewHolder,
        CHILD extends ChildViewHolder>
        extends InternalExpandableRecyclerViewModule<ExpandableViewHolder, PARENT, CHILD> {

    private final ExpandableList _expandableList;
    private final UnFlatGroupFlyweight _flatGroup;

    public BaseExpandableRecyclerViewModule(List<? extends ExpandableGroup> expandableGroups,
                                            @ListType String expandableListType) {
        _flatGroup = new UnFlatGroupFlyweight();
        _expandableList = ExpandableListFactory.createList(expandableListType, this, expandableGroups);
    }

    public ExpandableList getExpandableList() {
        return _expandableList;
    }

    @Override
    public final ExpandableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ExpandableViewHolder holder = onCreateItemViewHolder(parent, viewType);
        if (holder != null) {
            holder.setExpandableModule(this);
        }
        return holder;
    }

    @Override
    public boolean onFailedToRecycleView(ExpandableViewHolder holder) {
        if (holder != null) {
            holder.setExpandableModule(null);
            return false;
        }
        return super.onFailedToRecycleView(null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onBindViewHolder(ExpandableViewHolder holder, int position) {
        Object data = getItemAtPosition(position);
        if (holder != null) {
            holder.bindData(data, position);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onBindViewHolder(ExpandableViewHolder holder, int position, List<Object> payloads) {
        Object data = getItemAtPosition(position);
        if (holder != null) {
            holder.bindData(data, position, payloads);
        }
    }

    public final void expandGroup(int groupIndex) {
        _expandableList.expandGroup(groupIndex);
    }

    public final void collapseGroup(int groupIndex) {
        _expandableList.collapseGroup(groupIndex);
    }

    public void expandGroupAtPosition(int groupPosition) {
        _expandableList.expandGroupAtPosition(groupPosition);
    }

    public void collapseGroupAtPosition(int groupPosition) {
        _expandableList.collapseGroupAtPosition(groupPosition);
    }

    public void expandOrCollapseGroupAtPosition(int groupPosition) {
        _expandableList.expandOrCollapseGroupAtPosition(groupPosition);
    }

    public void addGroup(ExpandableGroup group) {
        _expandableList.add(group);
    }

    public void addGroups(Collection<? extends ExpandableGroup> groups) {
        _expandableList.addAll(groups);
    }

    public ExpandableGroup removeGroup(int groupPosition) {
        return _expandableList.remove(groupPosition);
    }

    public void removeAllGroups() {
        _expandableList.removeAll();
    }

    @Override
    public final int getItemCount() {
        return _expandableList.getVisibleItemCount();
    }

    @Override
    public final int getItemViewType(int position) {
        UnFlatGroupFlyweight groupFlyweight = _flatGroup;
        _expandableList.unFlat(groupFlyweight, position);
        return getItemViewType(position, groupFlyweight.getGroupIndex(),
                groupFlyweight.isChild(),
                groupFlyweight.getChildIndex());
    }

    private Object getItemAtPosition(int position) {
        UnFlatGroupFlyweight groupFlyweight = _flatGroup;
        _expandableList.unFlat(groupFlyweight, position);
        if (groupFlyweight.isChild()) {
            return groupFlyweight.getGroup().get(groupFlyweight.getChildIndex());
        }
        return groupFlyweight.getGroup();
    }

    /**
     * @param flatPosition  position of view in list.
     * @param groupPosition group in which contain this item be in group list only, not include
     *                      expanded items.
     * @param isChild       {@code true} if it is a child item, {@code false}
     *                      if it is a group item.
     * @param childPosition child position in group, -1 if it is a group item.
     * @return view type for this item at list position
     */
    public abstract int getItemViewType(int flatPosition, int groupPosition, boolean isChild, int childPosition);

    /**
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    public abstract ExpandableViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);
}
