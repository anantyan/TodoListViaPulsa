package id.anantyan.todolistviapulsa.presentation.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import id.anantyan.todolistviapulsa.R
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import id.anantyan.todolistviapulsa.databinding.ItemListBinding
import java.util.Objects

/**
 * Created by Arya Rezza Anantya on 09/06/24.
 */
class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    private val asyncDiffer = object : DiffUtil.ItemCallback<ResponseModel>() {
        override fun areItemsTheSame(oldItem: ResponseModel, newItem: ResponseModel): Boolean {
            return Objects.equals(oldItem.id, newItem.id)
        }

        override fun areContentsTheSame(oldItem: ResponseModel, newItem: ResponseModel): Boolean {
            return Objects.equals(oldItem, newItem)
        }
    }

    private val asyncListDiffer = AsyncListDiffer(this, asyncDiffer)

    fun submitList(model: List<ResponseModel>) {
        asyncListDiffer.submitList(model)
    }

    inner class ListViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener?.onItemClick(bindingAdapterPosition, asyncListDiffer.currentList[bindingAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = asyncListDiffer.currentList[position]
        if (holder is ListViewHolder) {
            holder.binding.imageView.load(R.drawable.ic_account_circle) {
                size(ViewSizeResolver(holder.binding.imageView))
            }
            holder.binding.tvPersonName.text = item.personName
        }
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: ResponseModel)
    }
}