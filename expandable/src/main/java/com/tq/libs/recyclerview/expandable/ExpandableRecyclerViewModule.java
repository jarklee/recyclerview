/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import android.annotation.SuppressLint;
import android.support.annotation.IntRange;
import android.view.ViewGroup;

import java.util.List;

@SuppressLint("DefaultLocale")
public abstract class ExpandableRecyclerViewModule<PARENT extends GroupViewHolder,
        CHILD extends ChildViewHolder>
        extends BaseExpandableRecyclerViewModule<PARENT, CHILD> {

    public ExpandableRecyclerViewModule() {
        this(null, "calculate");
    }

    public ExpandableRecyclerViewModule(String expandableListType) {
        this(null, expandableListType);
    }

    public ExpandableRecyclerViewModule(List<? extends ExpandableGroup> expandableGroups) {
        this(expandableGroups, "calculate");
    }

    public ExpandableRecyclerViewModule(List<? extends ExpandableGroup> expandableGroups,
                                        String expandableListType) {
        super(expandableGroups, expandableListType);
    }

    @Override
    public final ExpandableViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        if (isChildViewType(viewType)) {
            return onCreateChildViewHolder(parent, decodeViewTypeMark(viewType, true));
        }
        return onCreateGroupViewHolder(parent, decodeViewTypeMark(viewType, false));
    }

    @Override
    public final int getItemViewType(int flatPosition, int groupPosition,
                                     boolean isChild, int childPosition) {
        if (isChild) {
            return encodeViewTypeMark(getChildViewType(childPosition, groupPosition), true);
        } else {
            return encodeViewTypeMark(getGroupViewType(groupPosition), false);
        }
    }

    private boolean isChildViewType(int viewType) {
        return (viewType & 0x1) != 0;
    }

    private int decodeViewTypeMark(int viewType, boolean isChild) {
        if (isChild) {
            viewType = (viewType >> 13) & 0x3ffff;
        } else {
            viewType = (viewType >> 1) & 0xfff;
        }
        return viewType;
    }

    private int encodeViewTypeMark(int viewType, boolean isChild) {
        if (isChild) {
            viewType = ((viewType << 13) | 0x1fff) & 0x7fffffff;
        } else {
            viewType = (viewType << 1) & 0x1fff;
        }
        return viewType;
    }

    /**
     * @param groupPosition group position
     * @param childPosition child position in group
     * @return view type for child view type in range 0..262143
     */
    @IntRange(from = 0, to = 0x3ffff) // take up 18 bit mark
    public int getChildViewType(int groupPosition, int childPosition) {
        return 0;
    }

    /**
     * @param groupPosition group position
     * @return view type for group view type in range 0..4095
     */
    @IntRange(from = 0, to = 0xfff) // take up 12 bit mark
    public int getGroupViewType(int groupPosition) {
        return 0;
    }

    /**
     * @param parent        The ViewGroup into which the new View will be added after it is bound to
     *                      an adapter position.
     * @param groupViewType decode group view type, should be compare direct with registered view
     *                      type in derive class. Range 0..4095
     * @return Parent view holder for specific group type
     */
    public abstract PARENT onCreateGroupViewHolder(ViewGroup parent, int groupViewType);

    /**
     * @param parent        The ViewGroup into which the new View will be added after it is bound to
     *                      an adapter position.
     * @param childViewType decode child view type, should be compare direct with registered view
     *                      type in derive class. Range 0..262143
     * @return Child view holder for specific group type
     */
    public abstract CHILD onCreateChildViewHolder(ViewGroup parent, int childViewType);
}
