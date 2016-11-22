/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ExpandableGroup<ITEM> {

    private String title;
    private final List<ITEM> items;
    private boolean isExpand;
    private final List<GroupDataChangedListener> observers;

    public ExpandableGroup(String title) {
        this(title, (List<ITEM>) null);
    }

    public ExpandableGroup(String title, ITEM[] items) {
        observers = new LinkedList<>();
        this.title = title;
        if (items != null) {
            this.items = new ArrayList<>(items.length);
            Collections.addAll(this.items, items);
        } else {
            this.items = new ArrayList<>();
        }
    }

    public ExpandableGroup(String title, List<ITEM> items) {
        observers = new LinkedList<>();
        this.title = title;
        if (items != null) {
            this.items = new ArrayList<>(items);
        } else {
            this.items = new ArrayList<>();
        }
    }

    /**
     * @return Title for this group.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title Title for this group.
     *              See {@link #setTitle(String, boolean)}
     */
    public void setTitle(String title) {
        setTitle(title, true);
    }

    /**
     * @param title  Title for this group.
     * @param notify Notify data changed to subscriber. {@code true} if notify, {@code false}
     *               otherwise.
     */
    public void setTitle(String title, boolean notify) {
        this.title = title;
        if (notify) {
            notifyDataChanged();
        }
    }

    /**
     * @return {@code true} if this group is expanding, {@code false} otherwise.
     */
    public boolean isExpand() {
        return isExpand;
    }

    /**
     * @param expand Set group is expand or not.
     *               See {@link #setExpand(boolean, boolean)}
     */
    public void setExpand(boolean expand) {
        setExpand(expand, true);
    }

    /**
     * @param expand Set group is expand or not.
     * @param notify Notify data changed to subscriber. {@code true} if notify, {@code false}
     *               otherwise.
     */
    public void setExpand(boolean expand, boolean notify) {
        isExpand = expand;
        if (notify) {
            notifyDataChanged();
        }
    }

    /**
     * @return Total child item in group
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * @param position Position of child item in group.
     * @return Item at group position
     */
    public ITEM get(int position) {
        return items.get(position);
    }

    /**
     * @param item Item in which will be add to group.
     *             See {@link #add(Object, boolean)}
     */
    public void add(ITEM item) {
        add(item, true);
    }

    /**
     * @param item   Item in which will be add to group
     * @param notify Notify data changed to subscriber. {@code true} if notify, {@code false}
     *               otherwise.
     */
    public void add(ITEM item, boolean notify) {
        items.add(item);
        if (notify) {
            notifyDataChanged();
        }
    }

    void registerDataChangedListener(GroupDataChangedListener listener) {
        if (listener == null) {
            return;
        }
        if (!observers.contains(listener)) {
            observers.add(listener);
        }
    }

    void unregisterDataChangedListener(GroupDataChangedListener listener) {
        if (listener == null) {
            return;
        }
        observers.remove(listener);
    }

    protected void notifyDataChanged() {
        List<GroupDataChangedListener> listeners = observers;
        for (GroupDataChangedListener listener : listeners) {
            listener.onDataChanged();
        }
    }
}
