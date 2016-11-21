/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import java.util.ArrayList;
import java.util.List;

class ExpandableList {

    private final List<? extends ExpandableGroup> _groups;

    ExpandableList(List<? extends ExpandableGroup> groups) {
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

    }
}
