/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/11/20
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.recyclerview.expandable;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private ExpandableRecyclerViewModule module;

    private ExpandableList list;

    @Before
    public void setup() {
        module = new ExpandableRecyclerViewModule() {
            @Override
            public GroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
                return null;
            }

            @Override
            public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int childViewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        };
        list = ExpandableListFactory.createList("calc", module, null);
    }

    @After
    public void tearDown() {
        module = null;
        list = null;
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.tq.libs.recyclerview.expandable.test", appContext.getPackageName());
    }

    @Test
    public void addGroup() {

    }
}
