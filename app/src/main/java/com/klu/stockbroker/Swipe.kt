package com.example.stockbroker.di

import com.example.stockbroker.data.StockApi
import com.example.stockbroker.data.StockDao
import com.example.stockbroker.data.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockApi::class.java)
    }

    @Provides
    fun provideStockRepository(dao: StockDao, api: StockApi): StockRepository {
        return StockRepository(dao, api)
    }
}