/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import java.util.List;

public class ExpandableGroup<ITEM> {

    private String title;
    private List<ITEM> items;
    private boolean isExpand;

    public ExpandableGroup(String title, List<ITEM> items) {
        this.title = title;
        this.items = items;
    }

    public List<ITEM> getItems() {
        return items;
    }

    public String getTitle() {
        return title;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public ITEM get(int position) {
        return items == null ? null : items.get(position);
    }
}
