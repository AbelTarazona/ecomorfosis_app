package com.ecomorfosis.global.di

import com.ecomorfosis.global.retrofit.EcomorfosisRetrofit
import com.ecomorfosis.global.utils.KeyConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by AbelTarazona on 30/11/2020
 */
@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providerGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(KeyConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideEcomorfosisService(retrofit: Retrofit.Builder):EcomorfosisRetrofit {
        return retrofit.build().create(EcomorfosisRetrofit::class.java)
    }


}