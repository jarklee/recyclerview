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

import com.tq.libs.recyclerview.expandable.anotations.ExpandableListType;

import java.util.List;

@SuppressLint("DefaultLocale")
public abstract class ExpandableRecyclerViewModule<PARENT extends GroupViewHolder,
        CHILD extends ChildViewHolder>
        extends BaseExpandableRecyclerViewModule<PARENT, CHILD> {

    public ExpandableRecyclerViewModule() {
        this(null, ExpandableListType.CALC);
    }

    public ExpandableRecyclerViewModule(@ExpandableListType.Constraints String expandableListType) {
        this(null, expandableListType);
    }

    public ExpandableRecyclerViewModule(List<? extends ExpandableGroup> expandableGroups) {
        this(expandableGroups, ExpandableListType.CALC);
    }

    public ExpandableRecyclerViewModule(List<? extends ExpandableGroup> expandableGroups,
                                        @ExpandableListType.Constraints String expandableListType) {
        super(expandableGroups, expandableListType);
    }

    @Override
    public final ExpandableViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        if (isChildViewType(viewType)) {
            return onCreateChildViewHolder(parent, decodeViewTypeMark(viewType));
        }
        return onCreateGroupViewHolder(parent, decodeViewTypeMark(viewType));
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

    private int decodeViewTypeMark(int viewType) {
        return (viewType >> 1) & 0x3fffffff;
    }

    private int encodeViewTypeMark(int viewType, boolean isChild) {
        if (isChild) {
            return ((viewType << 1) | 0x1) & 0x7fffffff;
        } else {
            return (viewType << 1) & 0x7fffffff;
        }
    }

    /**
     * @param groupPosition group position
     * @param childPosition child position in group
     * @return view type for child view type in range 0..1073741823
     */
    @IntRange(from = 0, to = 0x3fffffff)
    public int getChildViewType(int groupPosition, int childPosition) {
        return 0;
    }

    /**
     * @param groupPosition group position
     * @return view type for group view type in range 0..1073741823
     */
    @IntRange(from = 0, to = 0x3fffffff)
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
