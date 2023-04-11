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

    private var showDialogClickListener: ((DictionaryEntity) -> Unit)? = null
    fun showDialogClickListener(function: (DictionaryEntity) -> Unit) {
        showDialogClickListener = function
    }

    inner class MyViewHolder(private val binding: UzEnItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dictionary: DictionaryEntity) {
            binding.apply {
                textUzbek.text = dictionary.uzbek
            }

            binding.constraint.setOnClickListener {
                showDialogClickListener?.invoke(dictionary)
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