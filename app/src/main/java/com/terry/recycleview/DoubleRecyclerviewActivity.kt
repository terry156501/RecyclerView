package com.terry.recycleview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import java.util.ArrayList
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

@SuppressLint("Registered")
class DoubleRecyclerviewActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private var manager: GridLayoutManager? = null
    private var adapter: PersonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_t02)

        initView()
    }

    private fun initView() {

        val switchCompat = findViewById<View>(R.id.switch_count) as SwitchCompat
        switchCompat.setOnCheckedChangeListener(this)

        initRecyclerView()
    }

    private fun initRecyclerView() {

        val recyclerView = findViewById<View>(R.id.rv_count_switch) as RecyclerView

        manager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)

        val urls = ArrayList<Person>()
        for (i in 0..14) {
            val accessible = Person("残疾人", R.drawable.ic_accessible_black_24dp)
            urls.add(accessible)
            val accessibility = Person("健全人", R.drawable.ic_accessibility_black_24dp)
            urls.add(accessibility)
        }
        adapter = PersonAdapter(urls)

        recyclerView.adapter = adapter

        changeShowItemCount(2)
    }

    override fun onCheckedChanged(compoundButton: CompoundButton, b: Boolean) {

        val count = if (b) 3 else 2
        changeShowItemCount(count)
    }

    /**
     * 更改每行显示数目
     */
    private fun changeShowItemCount(count: Int) {

        val i = 4 - count
        manager!!.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return i
            }
        }
        adapter!!.notifyDataSetChanged()
    }
}
