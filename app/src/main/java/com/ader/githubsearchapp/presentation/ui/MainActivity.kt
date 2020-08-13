package com.ader.githubsearchapp.presentation.ui

import android.os.Bundle
import com.ader.githubsearchapp.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity: DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}