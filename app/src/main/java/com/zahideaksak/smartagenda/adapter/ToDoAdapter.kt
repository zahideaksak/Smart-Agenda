package com.zahideaksak.smartagenda.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.zahideaksak.smartagenda.R
import com.zahideaksak.smartagenda.model.ToDoModel

class ToDoAdapter(options: FirestoreRecyclerOptions<ToDoModel>) :
    FirestoreRecyclerAdapter<ToDoModel, ToDoAdapter.MyViewHolder>(
        options
    ) {
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val noteText : TextView = itemView.findViewById(R.id.materialCheckBox)
        val dueText : TextView = itemView.findViewById(R.id.due_date_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_task, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: ToDoModel) {
        holder.noteText.text = model.task
    }
}