package com.terry.recycleview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

@SuppressLint("Registered")
class MainActivityT : AppCompatActivity() {

    private val personList = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_t)
        for (i in 0..14) {
            val accessible = Person("残疾人", R.drawable.ic_accessible_black_24dp)
            personList.add(accessible)
            val accessibility = Person("健全人", R.drawable.ic_accessibility_black_24dp)
            personList.add(accessibility)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = PersonAdapter(personList)
        recyclerView.adapter = adapter
    }
}
