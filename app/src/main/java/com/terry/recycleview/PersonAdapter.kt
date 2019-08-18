package com.terry.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class PersonAdapter(private val mPersonList: ArrayList<Person>) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {


    class ViewHolder(var personView: View) : RecyclerView.ViewHolder(personView) {
        var personImage: ImageView = personView.findViewById(R.id.person_image)
        var personName: TextView = personView.findViewById(R.id.person_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_item, parent, false)
        val holder = ViewHolder(view)
        holder.personView.setOnClickListener { person ->
            val position = holder.adapterPosition
            val per = mPersonList[position]
            Toast.makeText(person.context, "点击了View" + per.getName(), Toast.LENGTH_SHORT).show()
        }
        holder.personImage.setOnClickListener { person ->
            val position = holder.adapterPosition
            val per = mPersonList[position]
            Toast.makeText(person.context, "点击了图片" + per.getName(), Toast.LENGTH_SHORT).show()
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = mPersonList[position]
        holder.personImage.setImageResource(person.getImageId())
        holder.personName.text = person.getName()
    }

    override fun getItemCount(): Int {
        return mPersonList.size
    }

}
