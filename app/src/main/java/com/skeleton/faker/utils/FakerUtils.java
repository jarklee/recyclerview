/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/25
 * ******************************************************************************
 */

package com.skeleton.faker.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.skeleton.faker.constants.Constants;
import com.skeleton.faker.models.ApiError;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.ConnectException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;

public class FakerUtils {

    public static String getError(Throwable e, Retrofit retrofit) {
        String errorMessage = Constants.GENERIC_ERROR;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ResponseBody body = httpException.response().errorBody();
            Converter<ResponseBody, ApiError> responseBodyObjectConverter
                    = retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
            try {
                ApiError error = responseBodyObjectConverter.convert(body);
                if (error != null) {
                    errorMessage = error.getMessage();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                errorMessage = httpException.message();
            }
        } else if (e instanceof ConnectException) {
            errorMessage = Constants.SERVER_ERROR;
        }
        return errorMessage;
    }

    public static boolean isConnectedToInternet(@NonNull Context context) {
        return false;
    }
}
