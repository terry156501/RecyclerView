package com.terry.recycleview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Rich_01Activity:AppCompatActivity() {
    private var mButton:Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_03)
        mButton = findViewById(R.id.rich_01_button)
        mButton!!.setOnClickListener{
            val intent = Intent(this@Rich_01Activity,MainActivity::class.java)
            startActivity(intent)
        }
    }
}