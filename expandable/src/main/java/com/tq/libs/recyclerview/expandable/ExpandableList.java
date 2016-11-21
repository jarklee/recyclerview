/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressLint("DefaultLocale")
class ExpandableList {

    private final List<ExpandableGroup> _groups;
    private final ExpandableRecyclerViewModule _expandableModule;

    ExpandableList(ExpandableRecyclerViewModule expandableModule,
                   Collection<? extends ExpandableGroup> groups) {
        this._expandableModule = expandableModule;
        if (groups != null) {
            this._groups = new ArrayList<>(groups);
        } else {
            this._groups = new ArrayList<>();
        }
    }

    private int numberOfVisibleItemsInGroup(int group) {
        ExpandableGroup expandableGroup = _groups.get(group);
        if (expandableGroup == null) {
            return 0;
        }
        if (expandableGroup.isExpand()) {
            return expandableGroup.getItemCount() + 1;
        }
        return 1;
    }

    public int getVisibleItemCount() {
        int count = 0;
        for (int i = 0, size = _groups.size(); i < size; i++) {
            count += numberOfVisibleItemsInGroup(i);
        }
        return count;
    }

    public ExpandableGroup get(int position) {
        return _groups.get(position);
    }

    public void unFlat(UnFlatGroupFlyweight groupFlyweight, int position) {
        if (position < 0) {
            throw new IndexOutOfBoundsException("position could not be less than 0");
        }
        int totalCount = getVisibleItemCount();
        if (position >= totalCount) {
            throw new IndexOutOfBoundsException(
                    String.format("position could not be large than %d but: %d", totalCount, position));
        }
        int groupIndex = 0;
        int groupPosition = 0;
        List<ExpandableGroup> groupList = _groups;
        for (ExpandableGroup expandableGroup : groupList) {
            if (expandableGroup == null) {
                groupIndex++;
                continue;
            }
            int groupChildCount = expandableGroup.isExpand() ? expandableGroup.getItemCount() : 0;
            int upperGroupPositionLimit = groupPosition + groupChildCount;

            if (upperGroupPositionLimit < position) {
                groupIndex++;
                groupPosition = upperGroupPositionLimit + 1;
                continue;
            }
            groupFlyweight.setGroupIndex(groupIndex);
            groupFlyweight.setGroup(expandableGroup);
            if (position != groupPosition) {
                groupFlyweight.setChildIndex(position - groupPosition - 1);
            } else {
                groupFlyweight.setChildIndex(-1);
            }
            break;
        }
    }

    public void add(ExpandableGroup group) {
        if (group == null) {
            return;
        }
        int currentCount = getVisibleItemCount();
        group.setExpand(false);
        _groups.add(group);
        _expandableModule.notifyItemInserted(currentCount);
    }

    public void addAll(Collection<? extends ExpandableGroup> groups) {
        if (groups == null || groups.size() == 0) {
            return;
        }
        int currentCount = getVisibleItemCount();
        for (ExpandableGroup group : groups) {
            group.setExpand(false);
        }
        _groups.addAll(groups);
        _expandableModule.notifyItemRangeInserted(currentCount, groups.size());
    }

    public ExpandableGroup remove(int groupIndex) {
        int groupPosition = getGroupPositionForIndex(groupIndex);
        if (groupPosition != -1) {
            return null;
        }
        ExpandableGroup group = _groups.remove(groupIndex);
        if (group == null) {
            return null;
        }
        if (group.isExpand()) {
            _expandableModule.notifyItemRangeRemoved(groupPosition, group.getItemCount());
        } else {
            _expandableModule.notifyItemRemoved(groupPosition);
        }
        return group;
    }

    private int getGroupPositionForIndex(final int groupIndex) {
        List<ExpandableGroup> groupList = _groups;
        if (groupIndex < 0 || groupIndex >= groupList.size()) {
            return -1;
        }
        int countGroupIndex = 0;
        int groupPosition = 0;
        for (ExpandableGroup expandableGroup : groupList) {
            if (expandableGroup == null) {
                countGroupIndex++;
                continue;
            }
            if (countGroupIndex == groupIndex) {
                break;
            } else if (countGroupIndex > groupIndex) {
                return -1;
            }
            if (expandableGroup.isExpand()) {
                groupPosition += expandableGroup.getItemCount() + 1;
            } else {
                groupPosition++;
            }
            countGroupIndex++;
        }
        return groupPosition;
    }

    public void removeAll() {
        _groups.clear();
        _expandableModule.notifyDataSetChanged();
    }

    public void expandGroup(int groupIndex) {
        int groupPosition = getGroupPositionForIndex(groupIndex);
        if (groupPosition == -1) {
            return;
        }
        ExpandableGroup group = _groups.get(groupIndex);
        group.setExpand(true);
        _expandableModule.notifyItemChanged(groupPosition);
        _expandableModule.notifyItemRangeInserted(groupPosition + 1, group.getItemCount());
    }

    public void collapseGroup(int groupIndex) {
        int groupPosition = getGroupPositionForIndex(groupIndex);
        if (groupPosition == -1) {
            return;
        }
        ExpandableGroup group = _groups.get(groupIndex);
        group.setExpand(false);
        _expandableModule.notifyItemChanged(groupPosition);
        _expandableModule.notifyItemRangeRemoved(groupPosition + 1, group.getItemCount());
    }
}
