package com.example.android.homeowrk.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.homeowrk.R
import com.example.android.homeowrk.data.Flower

class FlowerListAdapter(private val dataSet: List<Flower>) :
    RecyclerView.Adapter<FlowerListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView
        val descriptionTextView: TextView
        val thumbnailImageView: ImageView

        init {
            nameTextView = view.findViewById(R.id.text_flower_name)
            descriptionTextView = view.findViewById(R.id.text_flower_description)
            thumbnailImageView = view.findViewById(R.id.image_flower_thumb)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.flower_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.descriptionTextView.text = dataSet[position].description
        viewHolder.nameTextView.text = dataSet[position].name
        viewHolder.thumbnailImageView.load(dataSet[position].image) {
            placeholder(R.drawable.ic_launcher_foreground)
        }
    }

    override fun getItemCount(): Int = dataSet.size
}