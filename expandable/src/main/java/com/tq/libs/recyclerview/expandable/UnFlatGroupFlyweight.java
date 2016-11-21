/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/21
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

class UnFlatGroupFlyweight {

    private int groupPosition;

    private int childPosition;

    private boolean isChild;

    private ExpandableGroup group;

    UnFlatGroupFlyweight() {

    }

    public boolean isChild() {
        return childPosition >= 0;
    }

    public int getGroupPosition() {
        return groupPosition;
    }

    public void setGroupPosition(int groupPosition) {
        this.groupPosition = groupPosition;
    }

    public int getChildPosition() {
        return childPosition;
    }

    public void setChildPosition(int childPosition) {
        this.childPosition = childPosition;
    }

    public ExpandableGroup getGroup() {
        return group;
    }

    public void setGroup(ExpandableGroup group) {
        this.group = group;
    }
}
