package com.geeks.shopapp1.ui.fragments
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil3.load
import coil3.request.crossfade
import com.geeks.shopapp1.R
import com.geeks.shopapp1.data.api.RetrofitService
import com.geeks.shopapp1.data.model.ProductDto
import com.geeks.shopapp1.databinding.FragmentProductDetailBinding
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = requireArguments().getInt("product_id")
        loadProduct(productId)
    }

    private fun loadProduct(id: Int) {
        binding.progressBar.isVisible = true

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val product = RetrofitService.api.getProductById(id)
                showProduct(product)
            } catch (e: Exception) {
                
                Toast.makeText(requireContext(), "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.isVisible = false
            }
        }
    }

    private fun showProduct(product: ProductDto) {
        binding.tvTitle.text = product.title
        binding.tvPrice.text = product.price.toString()
        binding.tvCategory.text = product.description

        binding.ivProduct.load(product.image) {
            crossfade(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
