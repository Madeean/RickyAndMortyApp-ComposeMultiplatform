package com.madeean.rickandmortyapp

import KoinInitializer
import android.app.Application

class MyApp: Application() {

  override fun onCreate() {
    super.onCreate()
    KoinInitializer(applicationContext).init()
  }
}