package com.example.wordappdictionary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wordappdictionary.databinding.WordSearchItemBinding

// RecyclerView adapter for displaying search results
class SearchWordAdapter(val clickListener: WordListener) :
    ListAdapter<String, SearchWordAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)


        return WordViewHolder(
            WordSearchItemBinding.inflate(layoutInflater,parent,false)
        )

    }
    // Bind the data to the ViewHolder
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
    }

    class WordViewHolder(
        var binding: WordSearchItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        // Bind data to the ViewHolder
        fun bind(word: String?, clickListener: WordListener) {

            binding.searchResultTextView.text = word

            binding.clickListener = clickListener
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                clickListener.onClick(word!!)
            }

        }



    }
    // Comparator for efficiently calculating the differences between lists
    class WordsComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}


class WordListener(val clickListener: (word: String) -> Unit) {
    fun onClick(word: String) = clickListener(word)
}