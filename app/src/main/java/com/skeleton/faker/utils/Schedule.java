/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/22
 * ******************************************************************************
 */

package com.skeleton.faker.utils;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Schedule {

    public static <T> Observable<T> io(@NonNull Observable<T> observable) {
        return schedule(observable, Schedulers.io());
    }

    public static <T> Observable<T> compute(@NonNull Observable<T> observable) {
        return schedule(observable, Schedulers.computation());
    }

    public static <T> Observable<T> newThread(@NonNull Observable<T> observable) {
        return schedule(observable, Schedulers.newThread());
    }

    public static <T> Observable<T> executor(@NonNull Observable<T> observable,
                                             @NonNull Executor executor) {
        return schedule(observable, Schedulers.from(executor));
    }

    public static <T> Observable<T> schedule(@NonNull Observable<T> observable,
                                             @NonNull Scheduler scheduler) {
        return observable.subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
