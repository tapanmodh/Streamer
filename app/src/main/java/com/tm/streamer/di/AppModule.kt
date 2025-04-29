package com.tm.streamer.di

import com.tm.streamer.data.model.StreamHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHandler(): StreamHandler {
        return StreamHandler()
    }
}