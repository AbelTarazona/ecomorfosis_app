package com.ecomorfosis.global.di

import com.ecomorfosis.global.repository.MainRepository
import com.ecomorfosis.global.retrofit.EcomorfosisRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by AbelTarazona on 30/11/2020
 */
@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        retrofit: EcomorfosisRetrofit
    ): MainRepository {
        return MainRepository(retrofit)
    }

}