package com.geeks.shopapp1.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.geeks.shopapp1.data.model.ProductDto
import com.geeks.shopapp1.databinding.ItemProductBinding


class ProductAdapter(
    private val onClick: (ProductDto) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var items: List <ProductDto>  = emptyList()

    fun submitList(list: List<ProductDto>){
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        //val product = productList[position]
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductDto) {

            with(binding){
                tvTitle.text = product.title
                tvPrice.text = "${product.price} ₸"
                tvCategory.text = product.category

                ivProduct.load(product.image){
                    crossfade(true)
                }

                root.setOnClickListener {
                    onClick(product)
                }

            }
            /*
            binding.tvTitle.text = product.title
            binding.tvPrice.text = "${product.price} ₸"
            binding.ivProduct.setImageResource(product.ivProduct)*/
        }
    }
}
