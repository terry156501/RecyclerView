package com.terry.recycleview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.MultiTypeAdapter
import java.util.ArrayList

@SuppressLint("Registered")
class MainActivity : AppCompatActivity() {

    private lateinit var multiTypeAdapter: MultiTypeAdapter
    private lateinit var items: MutableList<Any>
    private val spacing:Int = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_t)
        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerView_t)


        val gridLayoutManager = GridLayoutManager(this, 6)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(i: Int): Int {
                return when {
                    items[i] is ImageItem -> 6/3
                    items[i] is TextItem -> 6/1
                    items[i] is RichItem -> 6/2
                    else -> 6
                }
            }
        }
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView.addItemDecoration(SpaceDecoration(gridLayoutManager.spanCount,spacing,false))
        items = ArrayList()
        multiTypeAdapter = MultiTypeAdapter(items)
        multiTypeAdapter.register(ImageItemViewBinder())
        multiTypeAdapter.register(TextItemViewBinder())
        multiTypeAdapter.register(RichItemViewBinder())

        recyclerView?.adapter = multiTypeAdapter
        requestData()

    }

    private fun requestData() {
        items.clear()
        for (i in 0..2) {
            items.add(ImageItem(R.drawable.img_00))
        }
        for (i in 0..4){
            items.add(TextItem("Hello!"))
        }
        for(i in 0..9){
            items.add(RichItem("Bye",R.mipmap.ic_launcher))
        }
        multiTypeAdapter.notifyDataSetChanged()
    }
}