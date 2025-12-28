package com.geeks.shopapp1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.geeks.shopapp1.databinding.ItemCartBinding

import com.geeks.shopapp1.domain.models.CartItem
import com.geeks.shopapp1.domain.models.Product

class CartAdapter :
    ListAdapter<CartItem, CartAdapter.CartViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CartViewHolder(
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItem) = with(binding) {
            tvTitle.text = item.product.title
            tvPrice.text = item.product.price.toString()
            tvQuantity.text = "x${item.quantity}"
            ivProduct.load(item.product.image)
        }
    }

    class Diff : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(old: CartItem, new: CartItem) =
            old.product.id == new.product.id

        override fun areContentsTheSame(old: CartItem, new: CartItem) =
            old == new
    }
}
