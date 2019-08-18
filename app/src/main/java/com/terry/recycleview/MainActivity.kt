package com.terry.recycleview

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@SuppressLint("Registered")
class MainActivity : Activity() {

    private var list: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }

    private fun initData() {
        list = ArrayList()
        for (i in 1..100) {
            list!!.add("我是条目" + i.toString())
        }
    }

    @SuppressLint("WrongConstant")
    private fun initView() {
        val mRecyclerView = findViewById<RecyclerView>(R.id.RecycleView)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        // layoutManager
        mRecyclerView.layoutManager = layoutManager


        // animation
        mRecyclerView.itemAnimator = DefaultItemAnimator()

        // setAdapter
        val adapter = MyAdapter(this, list)
        mRecyclerView.adapter = adapter
        // itemClick
        adapter.setOnKotlinItemClickListener(object : MyAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
                Toast.makeText(applicationContext, list!![position], Toast.LENGTH_SHORT).show()
            }
        })

    }
}