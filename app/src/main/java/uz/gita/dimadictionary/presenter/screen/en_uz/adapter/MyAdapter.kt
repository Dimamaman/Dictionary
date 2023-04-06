package uz.gita.dimadictionary.presenter.screen.en_uz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dimadictionary.R
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity
import uz.gita.dimadictionary.databinding.ItemLayoutBinding

class MyAdapter: ListAdapter<DictionaryEntity, MyAdapter.MyViewHolder>(DIFF_CALL_BACK) {

    class MyViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        private var isExpanded = false

        init {
            binding.apply {
                constraint.setOnClickListener {
                    isExpanded = !isExpanded
                    textTranscript.isVisible = isExpanded
                    textUzbek.isVisible = isExpanded


                    /*isExpanded = if (isExpanded) {
                        textTranscript.visible()
                        textUzbek.visible()
                        false
                    } else {
                        textTranscript.gone()
                        textUzbek.gone()
                        true
                    }*/
                }
            }
        }

        fun bind(dictionary: DictionaryEntity) {
            binding.apply {
                textEnglish.text = dictionary.english
                textType.text = dictionary.type
                textTranscript.text = dictionary.transcript
                textUzbek.text = dictionary.uzbek

                if (dictionary.favourite == 1) {
                    imFavorite.setImageResource(R.drawable.favorite)
                } else {
                    imFavorite.setImageResource(R.drawable.not_favorite)
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): MyViewHolder {
                val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return MyViewHolder(binding)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<DictionaryEntity>() {

            override fun areItemsTheSame(
                oldItem: DictionaryEntity,
                newItem: DictionaryEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DictionaryEntity,
                newItem: DictionaryEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}