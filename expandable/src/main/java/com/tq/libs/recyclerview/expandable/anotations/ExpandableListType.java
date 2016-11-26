/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/22
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable.anotations;

import android.support.annotation.StringDef;

public interface ExpandableListType {

    String PRE_CALC = "preCalc";
    String CALC = "calc";

    @StringDef(value = {PRE_CALC, CALC})
    @interface Constraints {

    }

}
