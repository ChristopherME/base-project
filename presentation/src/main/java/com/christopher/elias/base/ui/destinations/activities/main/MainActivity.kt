package com.christopher.elias.base.ui.destinations.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.christopher.elias.base.R
import com.christopher.elias.base.ui.destinations.fragments.todo.list.TodoListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_main, TodoListFragment())
            .commit()
    }
}
