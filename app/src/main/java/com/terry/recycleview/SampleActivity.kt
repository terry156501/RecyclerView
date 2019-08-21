package com.terry.recycleview

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.MultiTypeAdapter

@SuppressLint("Registered")
class SampleActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private lateinit var adapter: MultiTypeAdapter
    private lateinit var items: MutableList<Any>
    private lateinit var manager:GridLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }
    private fun initView() {

        val switchCompat = findViewById<SwitchCompat>(R.id.switch_count)
        switchCompat.setOnCheckedChangeListener(this)

        initRecyclerView()
    }
    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.RecycleView)

        //每行最多3个item
        manager = GridLayoutManager(this, 3)

        recyclerView.layoutManager = manager

        recyclerView.setHasFixedSize(true)

        items = ArrayList()

        adapter = MultiTypeAdapter(items)
        adapter.register(RichItemViewBinder())

        for (i in 0..19) {

            val richItem = RichItem("accessibility", R.drawable.img_00)
            items.add(richItem)
        }
        recyclerView.adapter = adapter

        //初始item权重为4-1
        changeShowItemCount(1)


        adapter.items = items
        adapter.notifyDataSetChanged()
    }
    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        val count = if (p1) 3 else 1
        changeShowItemCount(count)
    }
    private fun changeShowItemCount(count: Int) {

        //i为item权重4-count
        val i = 4 - count
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return i
            }
        }
        adapter.notifyDataSetChanged()
    }
}