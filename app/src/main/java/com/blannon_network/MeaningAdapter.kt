package com.blannon_network

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blannon_network.Respond.Meaning
import com.blannon_network.definitionhub.databinding.MeaningRecycleviewRowBinding

class MeaningAdapter(private var meaningList: List<Meaning>) : RecyclerView.Adapter<MeaningAdapter.meaningViewHolder>(){

    class meaningViewHolder(private val  binding: MeaningRecycleviewRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(meaning: Meaning) {
            // Bind all the
            binding.POSTv.text = meaning.partOfSpeech
            binding.definitionsTv.text = meaning.definitions.joinToString("\n\n") {
                var currentIndex = meaning.definitions.indexOf(it)
                (currentIndex + 1).toString() + " " + it.definition.toString()
            }
            if (meaning.synonyms.isEmpty()) {
                binding.synonymsTv.visibility = View.GONE
                binding.synonymsTv.visibility = View.GONE
            } else {
                binding.synonymsTv.visibility = View.VISIBLE
                binding.synonymsTv.visibility = View.VISIBLE
                binding.synonymsTv.text = meaning.synonyms.joinToString(", ")
            }

            if (meaning.antonyms.isEmpty()) {
                binding.antonymsTv.visibility = View.GONE
                binding.antonymsTv.visibility = View.GONE
            } else {
                binding.antonymsTv.visibility = View.VISIBLE
                binding.antonymsTv.visibility = View.VISIBLE
                binding.antonymsTv.text = meaning.antonyms.joinToString(", ")
            }
        }

    }

    fun updateNewData(newMeaningList: List<Meaning>){
        meaningList = newMeaningList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): meaningViewHolder {
    val binding = MeaningRecycleviewRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return meaningViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meaningList.size
    }

    override fun onBindViewHolder(holder: meaningViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }
}