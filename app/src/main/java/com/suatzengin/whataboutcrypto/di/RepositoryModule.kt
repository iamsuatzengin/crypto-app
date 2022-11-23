package com.suatzengin.whataboutcrypto.di

import com.suatzengin.whataboutcrypto.data.remote.CoinRepositoryImpl
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoinRepository(coinRepositoryImpl: CoinRepositoryImpl): CoinRepository
}