package com.example.ui.di

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.ui.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object NavigationModule {

    @Provides
    fun providesNavController(activity: Activity): NavController =
        activity.findNavController(R.id.fragment_container)
}