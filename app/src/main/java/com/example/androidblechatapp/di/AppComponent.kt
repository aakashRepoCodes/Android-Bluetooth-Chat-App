package com.example.androidblechatapp.di

import android.content.Context
import com.example.androidblechatapp.AndroidBleChatApp
import com.example.androidblechatapp.domain.AppBluetoothController
import com.example.androidblechatapp.domain.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {

    @Provides
    fun providesBluetoothController(@ApplicationContext context: Context): BluetoothController = AppBluetoothController(context)


}