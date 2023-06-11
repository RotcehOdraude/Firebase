package com.hectorcabello.firebase.view.adapters

import android.content.Context
//import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hectorcabello.firebase.R
import com.hectorcabello.firebase.databinding.CharacterElementBinding
import com.hectorcabello.firebase.model.StudentHP
//import com.example.harrypotter.utils.Constants

class StudentsAdapter(
    private var context: Context,
    private var students: ArrayList<StudentHP>,
    private val clickListener: (StudentHP) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {
    class ViewHolder(view: CharacterElementBinding) : RecyclerView.ViewHolder(view.root) {
        val ivStudent = view.ivThumbnail
        val tvName = view.tvCharacterName
        val tvActor = view.tvActorName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = students.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val empty = context.getString(R.string.EMPTY)

        holder.tvName.text = if(!students[position].name.isNullOrBlank()) students[position].name else empty
        holder.tvActor.text = if(!students[position].actor.isNullOrBlank()) students[position].actor else empty

        if (!students[position].image?.isEmpty()!!) {
            Glide.with(context)
                .load(students[position].image)
                .into(holder.ivStudent)
        } else {
            holder.ivStudent.setImageResource(R.drawable.character)
        }


        holder.itemView.setOnClickListener {
            //Para programar los eventos click al elemento completo del ViewHolder
            clickListener(students[position])
        }
    }
}