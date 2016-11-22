/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. aimesoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/22
 * ******************************************************************************
 */

package com.skeleton.faker.injection.modules;

import com.skeleton.faker.networks.FakerService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

@Module
public class NetModule {

    private final String _baseUrl;

    public NetModule(String baseUrl) {
        _baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    @Named("base_url")
    String provideBaseUrl() {
        return _baseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@Named("base_url") String baseUrl,
                             Converter.Factory converterFactory) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return builder.build();
    }

    @Provides
    @Singleton
    FakerService provideRemoteService(Retrofit retrofit) {
        return retrofit.create(FakerService.class);
    }
}
