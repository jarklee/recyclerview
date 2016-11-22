/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/11/21
 * ******************************************************************************
 */

package com.skeleton.faker.views.adapters;

import com.tq.libs.recyclerview.expandable.ExpandableGroup;

import java.util.List;

public class MainGroup extends ExpandableGroup<String> {

    public MainGroup(String title, List<String> strings) {
        super(title, strings);
    }
}
