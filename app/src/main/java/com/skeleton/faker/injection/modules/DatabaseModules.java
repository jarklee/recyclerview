/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/22
 * ******************************************************************************
 */

package com.skeleton.faker.injection.modules;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.skeleton.faker.managers.AppDatabaseOpenHelper;
import com.skeleton.faker.models.DaoMaster;
import com.skeleton.faker.models.DaoSession;
import com.skeleton.faker.injection.annotations.AppContext;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModules {

    private final String _databaseName;

    public DatabaseModules(String databaseName) {
        _databaseName = databaseName;
    }

    @Provides
    @Singleton
    @Named("database_name")
    String provideDatabaseName() {
        return _databaseName;
    }

    @Provides
    @Singleton
    SQLiteOpenHelper provideSQLiteOpenHelper(@AppContext Context context,
                                             @Named("database_name") String databaseName) {
        return new AppDatabaseOpenHelper(context, databaseName);
    }

    @Provides
    @Singleton
    SQLiteDatabase provideSQLiteDatabase(SQLiteOpenHelper helper) {
        return helper.getWritableDatabase();
    }

    @Provides
    @Singleton
    DaoMaster provideDaoMaster(SQLiteDatabase database) {
        return new DaoMaster(database);
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(DaoMaster daoMaster) {
        return daoMaster.newSession();
    }

}
