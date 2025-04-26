package com.example.automotiveapp.di

import com.example.automotiveapp.data.remote.ApiService
import com.example.automotiveapp.data.repository.RepositoryImpl
import com.example.automotiveapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun Repository(
        apiService: ApiService
    ): Repository {
        return RepositoryImpl(apiService)
    }
}
