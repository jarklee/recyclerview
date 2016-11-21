/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;

@SuppressLint("DefaultLocale")
public abstract class ExpandableRecyclerViewModule<PARENT extends GroupViewHolder,
        CHILD extends ChildViewHolder>
        extends InternalExpandableRecyclerViewModule<PARENT, CHILD, ExpandableViewHolder> {

    protected final ExpandableList _expandableList;
    private final UnFlatGroupFlyweight _flatGroup;

    public ExpandableRecyclerViewModule() {
        this(null);
    }

    public ExpandableRecyclerViewModule(List<? extends ExpandableGroup> expandableGroups) {
        _flatGroup = new UnFlatGroupFlyweight();
        _expandableList = ExpandableListFactory.createList("calculate", this, expandableGroups);
    }

    public ExpandableList getExpandableList() {
        return _expandableList;
    }

    @Override
    public final ExpandableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ExpandableViewHolder holder;
        if (isChildViewType(viewType)) {
            holder = onCreateChildViewHolder(parent, decodeChildViewType(viewType));
        } else {
            holder = onCreateGroupViewHolder(parent, viewType);
        }
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

    public final void expandGroupContainChild(int position) {
        _expandableList.expandGroupContainChild(position);
    }

    public final void collapseGroupContainChild(int position) {
        _expandableList.collapseGroupContainChild(position);
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
        if (groupFlyweight.isChild()) {
            int childViewType = getChildViewType(groupFlyweight.getChildIndex(),
                    groupFlyweight.getGroupIndex());
            return encodeChildViewType(childViewType);
        }
        return getGroupViewType(groupFlyweight.getGroupIndex());
    }

    private Object getItemAtPosition(int position) {
        UnFlatGroupFlyweight groupFlyweight = _flatGroup;
        _expandableList.unFlat(groupFlyweight, position);
        if (groupFlyweight.isChild()) {
            return groupFlyweight.getGroup().get(groupFlyweight.getChildIndex());
        }
        return groupFlyweight.getGroup();
    }

    private boolean isChildViewType(int viewType) {
        if (viewType < 0) {
            throw new IllegalArgumentException(
                    String.format("unknown view type: view type could not be below zero, but %d", viewType));
        }
        int groupTypeCount = getGroupViewTypeCount();
        if (viewType < groupTypeCount) {
            return false;
        }
        int totalViewTypeCount = groupTypeCount + getChildViewTypeCount();
        if (viewType >= totalViewTypeCount) {
            throw new IllegalArgumentException(
                    String.format("unknown view type: view type could not be over total view type count %d, but %d", totalViewTypeCount, viewType));
        }
        return true;
    }

    private int decodeChildViewType(int viewType) {
        return viewType - getGroupViewTypeCount();
    }

    private int encodeChildViewType(int childViewType) {
        return childViewType + getGroupViewTypeCount();
    }

    /**
     * @return number of group view type, derive class must use group view type start at 0.
     * other view type must follow first view type by adding 1.
     * view type for group holder will be generate automatically.
     */
    public int getGroupViewTypeCount() {
        return 1;
    }

    /**
     * @return number of child view type, derive class must use child view type start at 0.
     * other view type must follow first view type by adding 1.
     * view type for child holder will be generate automatically by adding group view type count.
     */
    public int getChildViewTypeCount() {
        return 1;
    }

    /**
     * @param childPosition child position in group
     * @param groupPosition group position
     * @return view type for child view type in a group at that child position in group
     * see method {@link #getChildViewTypeCount()} getChildViewTypeCount
     */
    public int getChildViewType(int childPosition, int groupPosition) {
        return 0;
    }

    /**
     * @param groupPosition group position
     * @return view type for group view type
     * see method {@link #getGroupViewTypeCount()} getGroupViewTypeCount
     */
    public int getGroupViewType(int groupPosition) {
        return 0;
    }

    /**
     * @param parent        The ViewGroup into which the new View will be added after it is bound to
     *                      an adapter position.
     * @param groupViewType decode group view type, should be compare direct with registered view
     *                      type in derive class. See method {@link #getGroupViewTypeCount()}
     *                      getGroupViewTypeCount
     * @return Parent view holder for specific group type
     */
    public abstract PARENT onCreateGroupViewHolder(ViewGroup parent, int groupViewType);

    /**
     * @param parent        The ViewGroup into which the new View will be added after it is bound to
     *                      an adapter position.
     * @param childViewType decode child view type, should be compare direct with registered view
     *                      type in derive class. See method {@link #getChildViewTypeCount()}
     *                      getGroupViewTypeCount
     * @return Child view holder for specific group type
     */
    public abstract CHILD onCreateChildViewHolder(ViewGroup parent, int childViewType);
}
