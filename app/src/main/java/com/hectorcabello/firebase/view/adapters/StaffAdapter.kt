package com.hectorcabello.firebase.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hectorcabello.firebase.R
import com.hectorcabello.firebase.databinding.CharacterElementBinding
import com.hectorcabello.firebase.model.StaffHP

class StaffAdapter(
    private var context: Context,
    private var staff: ArrayList<StaffHP>,
    private val clickListener: (StaffHP) -> Unit
) : RecyclerView.Adapter<StaffAdapter.ViewHolder>() {

    class ViewHolder(view: CharacterElementBinding) : RecyclerView.ViewHolder(view.root) {
        val ivStaff = view.ivThumbnail
        val tvName = view.tvCharacterName
        val tvActor = view.tvActorName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = staff.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val empty = context.getString(R.string.EMPTY)

        holder.tvName.text = if(!staff[position].name.isNullOrBlank()) staff[position].name else empty
        holder.tvActor.text = if(!staff[position].actor.isNullOrBlank()) staff[position].actor else empty

        if (!staff[position].image?.isEmpty()!!) {
            Glide.with(context)
                .load(staff[position].image)
                .into(holder.ivStaff)
        } else {
            holder.ivStaff.setImageResource(R.drawable.character)
        }

        holder.itemView.setOnClickListener {
            //Para programar los eventos click al elemento completo del ViewHolder
            clickListener(staff[position])
        }
    }
}