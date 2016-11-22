/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/22
 * ******************************************************************************
 */

package com.skeleton.faker.managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.skeleton.faker.models.DaoMaster;

public class AppDatabaseOpenHelper extends DaoMaster.OpenHelper {

    public AppDatabaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public AppDatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}
