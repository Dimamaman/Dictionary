package uz.gita.dimadictionary.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dimadictionary.R
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity
import uz.gita.dimadictionary.databinding.UzEnItemLayoutBinding

class MyUzEnAdapter: ListAdapter<DictionaryEntity, MyUzEnAdapter.MyViewHolder>(DIFF_CALL_BACK) {

    private var favoriteClickListener: ((DictionaryEntity) -> Unit)? = null
    fun setFavoriteClickListener(block: (DictionaryEntity) -> Unit) {
        favoriteClickListener = block
    }

    private var speakClickListener: ((String) -> Unit)? = null
    fun setSpeakClickListener(block: (String) -> Unit) {
        speakClickListener = block
    }

    private var copyClickListener: ((DictionaryEntity) -> Unit)? = null
    fun setCopyClickListener(block: (DictionaryEntity) -> Unit) {
        copyClickListener = block
    }

    inner class MyViewHolder(private val binding: UzEnItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        private var isExpanded = false

        init {
            binding.apply {
                constraint.setOnClickListener {
                    isExpanded = !isExpanded
                    textTranscript.isVisible = isExpanded
                    textEnglish.isVisible = isExpanded
                    textType.isVisible = isExpanded
                    sound.isVisible = isExpanded
                    imCopy.isVisible = isExpanded
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

                imFavorite.setOnClickListener {
                    if (dictionary.favourite == 0) {
                        dictionary.favourite = 1
                        imFavorite.setImageResource(R.drawable.favorite)
                        favoriteClickListener?.invoke(dictionary)
                    } else {
                        imFavorite.setImageResource(R.drawable.not_favorite)
                        dictionary.favourite = 0
                        favoriteClickListener?.invoke(dictionary)
                    }
                }

                sound.setOnClickListener {
                    speakClickListener?.invoke(dictionary.english)
                }

                imCopy.setOnClickListener {
                    copyClickListener?.invoke(dictionary)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UzEnItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
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