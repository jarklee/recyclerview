/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/22
 * ******************************************************************************
 */

package com.skeleton.faker.managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.skeleton.faker.constants.Constants;
import com.skeleton.faker.injection.annotations.AppContext;
import com.tq.libs.common.Preferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PrefManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    @Inject
    public PrefManager(@AppContext Context context) {
        pref = Preferences.preferences(context, Constants.APP_PREF);
        editor = pref.edit();
    }

    public void edit(@NonNull Preferences.EditorHandler handler) {
        handler.edit(editor);
        editor.apply();
    }
}
