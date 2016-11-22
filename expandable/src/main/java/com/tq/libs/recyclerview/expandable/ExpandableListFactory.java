/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/21
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import android.support.annotation.NonNull;

import com.tq.libs.recyclerview.expandable.anotations.ListType;

import java.util.List;

class ExpandableListFactory {

    static ExpandableList createList(@ListType String type,
                                     @NonNull BaseExpandableRecyclerViewModule module,
                                     List<? extends ExpandableGroup> groups) {
        if ("calculate".equals(type)) {
            return new ExpandableCalculateBasedList(module, groups);
        }
        return new ExpandablePreCalculateList(module, groups);
    }
}
