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

import com.tq.libs.recyclerview.core.SelfBindViewHolder;

import java.util.List;

@SuppressLint("DefaultLocale")
public abstract class ExpandableRecyclerViewModule<PARENT extends GroupViewHolder,
        CHILD extends ChildViewHolder>
        extends InternalExpandableRecyclerViewModule<PARENT, CHILD, SelfBindViewHolder> {

    private final ExpandableList _expandableList;
    private final UnFlatGroupFlyweight _flatGroup;

    public ExpandableRecyclerViewModule() {
        this(null);
    }

    public ExpandableRecyclerViewModule(List<? extends ExpandableGroup> expandableGroups) {
        _flatGroup = new UnFlatGroupFlyweight();
        _expandableList = new ExpandableList(expandableGroups);
    }

    public ExpandableList getExpandableList() {
        return _expandableList;
    }

    @Override
    public SelfBindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isChildViewType(viewType)) {
            return onCreateChildViewHolder(parent, decodeViewType(viewType, true));
        }
        return onCreateGroupViewHolder(parent, viewType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(SelfBindViewHolder holder, int position) {
        Object data = getItemAtPosition(position);
        if (holder != null) {
            holder.bindData(data, position);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(SelfBindViewHolder holder, int position, List<Object> payloads) {
        Object data = getItemAtPosition(position);
        if (holder != null) {
            holder.bindData(data, position, payloads);
        }
    }

    @Override
    public int getItemCount() {
        return _expandableList.getVisibleItemCount();
    }

    public Object getItemAtPosition(int position) {
        UnFlatGroupFlyweight groupFlyweight = _flatGroup;
        _expandableList.unFlat(groupFlyweight, position);
        if (groupFlyweight.isChild()) {
            return _expandableList.get(groupFlyweight.getGroupPosition())
                    .get(groupFlyweight.getChildPosition());
        }
        return _expandableList.get(groupFlyweight.getGroupPosition());
    }

    @Override
    public int getItemViewType(int position) {
        UnFlatGroupFlyweight groupFlyweight = _flatGroup;
        _expandableList.unFlat(groupFlyweight, position);
        if (groupFlyweight.isChild()) {
            return getChildViewType(groupFlyweight.getChildPosition(), groupFlyweight.getGroupPosition());
        }
        return getGroupViewType(groupFlyweight.getGroupPosition());
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

    private int decodeViewType(int viewType, boolean isChild) {
        if (isChild) {
            return viewType - getGroupViewTypeCount();
        }
        return viewType;
    }

    private int encodeViewType(int viewType, boolean isChild) {
        if (isChild) {
            return viewType + getGroupViewTypeCount();
        }
        return viewType;
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
    public abstract int getChildViewType(int childPosition, int groupPosition);

    /**
     * @param groupPosition group position
     * @return view type for group view type
     * see method {@link #getGroupViewTypeCount()} getGroupViewTypeCount
     */
    public abstract int getGroupViewType(int groupPosition);

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
