/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/21
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import java.util.Collection;

public interface ExpandableList {
    int getVisibleItemCount();

    ExpandableGroup get(int position);

    void unFlat(UnFlatGroupFlyweight groupFlyweight, int position);

    void add(ExpandableGroup group);

    void addAll(Collection<? extends ExpandableGroup> groups);

    ExpandableGroup remove(int groupIndex);

    void removeAll();

    void expandGroup(int groupIndex);

    void collapseGroup(int groupIndex);

    void expandGroupContainChild(int position);

    void collapseGroupContainChild(int position);
}
