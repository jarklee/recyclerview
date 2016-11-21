/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/21
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class ExpandablePreCalculateList implements ExpandableList {

    private final List<ExpandableGroup> _groups;
    private final ExpandableRecyclerViewModule _expandableModule;

    private final SparseArray<Object> _cachedObject;

    private final SparseIntArray _cacheChildIndex;

    private final SparseIntArray _cacheGroupIndex;

    private final SparseIntArray _cacheGroupPosition;

    private int _itemCount;

    ExpandablePreCalculateList(ExpandableRecyclerViewModule expandableModule,
                               List<? extends ExpandableGroup> groups) {
        this._expandableModule = expandableModule;
        if (groups != null) {
            this._groups = new ArrayList<>(groups);
        } else {
            this._groups = new ArrayList<>();
        }
        _cachedObject = new SparseArray<>(_groups.size());
        _cacheChildIndex = new SparseIntArray(_groups.size());
        _cacheGroupIndex = new SparseIntArray(_groups.size());
        _cacheGroupPosition = new SparseIntArray(_groups.size());
        updateObjects();
    }

    private void updateObjects() {
        int itemCount = 0;
        int currentIndex = 0;
        _cachedObject.clear();
        _cacheChildIndex.clear();
        _cacheGroupIndex.clear();
        _cacheGroupPosition.clear();
        int currentGroupIndex = 0;
        List<ExpandableGroup> groupList = _groups;
        for (ExpandableGroup group : groupList) {
            if (group == null) {
                currentGroupIndex++;
                continue;
            }
            itemCount++;
            if (group.isExpand()) {
                itemCount += group.getItemCount();
            }
            _cachedObject.append(currentIndex, group);
            _cacheGroupIndex.append(currentIndex, currentGroupIndex);
            _cacheChildIndex.append(currentIndex, -1);
            _cacheGroupPosition.append(currentGroupIndex, currentIndex);
            currentIndex++;
            int childCount = group.getItemCount();
            if (childCount <= 0) {
                currentGroupIndex++;
                continue;
            }
            for (int i = 0; i < childCount; i++) {
                _cachedObject.append(currentIndex, group.get(i));
                _cacheGroupIndex.append(currentIndex, currentGroupIndex);
                _cacheChildIndex.append(currentIndex, i);
                currentIndex++;
            }
            currentGroupIndex++;
        }
        _itemCount = itemCount;
    }

    @Override
    public int getVisibleItemCount() {
        return _itemCount;
    }

    @Override
    public ExpandableGroup get(int position) {
        return _groups.get(position);
    }

    @Override
    public void unFlat(UnFlatGroupFlyweight groupFlyweight, int position) {
        int groupIndex = _cacheGroupIndex.get(position);
        int childIndex = _cacheChildIndex.get(position);
        groupFlyweight.setChildIndex(childIndex);
        groupFlyweight.setGroupIndex(groupIndex);
        groupFlyweight.setGroup(_groups.get(groupIndex));
    }

    @Override
    public void add(ExpandableGroup group) {
        if (group == null) {
            return;
        }
        int currentCount = getVisibleItemCount();
        group.setExpand(false);
        _groups.add(group);
        updateObjects();
        _expandableModule.notifyItemInserted(currentCount);
    }

    @Override
    public void addAll(Collection<? extends ExpandableGroup> groups) {
        if (groups == null || groups.size() == 0) {
            return;
        }
        int currentCount = getVisibleItemCount();
        for (ExpandableGroup group : groups) {
            group.setExpand(false);
        }
        _groups.addAll(groups);
        updateObjects();
        _expandableModule.notifyItemRangeInserted(currentCount, groups.size());
    }

    @Override
    public ExpandableGroup remove(int groupIndex) {
        ExpandableGroup group = _groups.remove(groupIndex);
        if (group == null) {
            return null;
        }
        int groupPosition = _cacheGroupPosition.get(groupIndex, -1);
        updateObjects();
        if (groupPosition == -1) {
            _expandableModule.notifyDataSetChanged();
        } else {
            if (group.isExpand()) {
                _expandableModule.notifyItemRangeRemoved(groupPosition, group.getItemCount());
            } else {
                _expandableModule.notifyItemRemoved(groupPosition);
            }
        }
        return group;
    }

    @Override
    public void removeAll() {
        _groups.clear();
        updateObjects();
        _expandableModule.notifyDataSetChanged();
    }

    @Override
    public void expandGroup(int groupIndex) {
        ExpandableGroup group = _groups.get(groupIndex);
        if (group.isExpand()) {
            return;
        }
        int groupPosition = _cacheGroupPosition.get(groupIndex);
        group.setExpand(true);
        updateObjects();
        _expandableModule.notifyItemChanged(groupPosition);
        _expandableModule.notifyItemRangeInserted(groupPosition + 1, group.getItemCount());
    }

    @Override
    public void collapseGroup(int groupIndex) {
        ExpandableGroup group = _groups.get(groupIndex);
        if (!group.isExpand()) {
            return;
        }
        int groupPosition = _cacheGroupPosition.get(groupIndex);
        group.setExpand(false);
        updateObjects();
        _expandableModule.notifyItemChanged(groupPosition);
        _expandableModule.notifyItemRangeRemoved(groupPosition + 1, group.getItemCount());
    }

    @Override
    public void expandGroupContainChild(int position) {
        expandGroup(_cacheGroupIndex.get(position));
    }

    @Override
    public void collapseGroupContainChild(int position) {
        collapseGroup(_cacheGroupIndex.get(position));
    }
}
