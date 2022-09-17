package com.example.newsdemoarimac.di

import android.content.Context
import androidx.room.Room
import com.example.newsdemoarimac.api.NewsInterface
import com.example.newsdemoarimac.db.UserDataBase
import com.example.newsdemoarimac.models.User
import com.example.newsdemoarimac.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideUserDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserDataBase::class.java, "user_db").allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideUserDao(db: UserDataBase) = db.userDB()

    @Provides
    @Singleton
    fun provideRetrofitInstance(base_url: String): NewsInterface =

        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsInterface::class.java)
}