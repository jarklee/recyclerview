/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/21
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

class UnFlatGroupFlyweight {

    private int groupIndex;

    private int childIndex;

    private ExpandableGroup group;

    public boolean isChild() {
        return childIndex >= 0;
    }

    public int getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }

    public int getChildIndex() {
        return childIndex;
    }

    public void setChildIndex(int childIndex) {
        this.childIndex = childIndex;
    }

    public ExpandableGroup getGroup() {
        return group;
    }

    public void setGroup(ExpandableGroup group) {
        this.group = group;
    }
}
