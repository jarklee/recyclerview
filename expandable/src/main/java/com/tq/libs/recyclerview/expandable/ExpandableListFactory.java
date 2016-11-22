/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/21
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import java.util.List;

public class ExpandableListFactory {

    public static ExpandableList createList(String type, BaseExpandableRecyclerViewModule module,
                                            List<? extends ExpandableGroup> groups) {
        if ("calculate".equals(type)) {
            return new ExpandableCalculateBasedList(module, groups);
        }
        return new ExpandablePreCalculateList(module, groups);
    }
}
