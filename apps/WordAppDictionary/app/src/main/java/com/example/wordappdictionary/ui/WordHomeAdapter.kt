package com.example.wordappdictionary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wordappdictionary.database.Word
import com.example.wordappdictionary.databinding.WordHomeItemBinding

class WordHomeAdapter(val clickListener: DictionaryHomeListener) :
    //setUp list Adapter
    ListAdapter<Word, WordHomeAdapter.DictionaryViewHolder>(WordsComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : DictionaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)


        return DictionaryViewHolder(
            WordHomeItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
    }


    class DictionaryViewHolder(var binding: WordHomeItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            word: Word,
            clickListener: DictionaryHomeListener
        )  {

            binding.word = word
            binding.clickListener = clickListener
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                clickListener.onClick(word)
            }


        }



    }


    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }
    }


}

class DictionaryHomeListener(val clickListener: (word: Word) -> Unit) {
    fun onClick(word: Word) = clickListener(word)
}