package com.geeks.shopapp1.ui.fragments.product.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil3.load
import coil3.request.crossfade
import com.geeks.shopapp1.data.api.RetrofitService
import com.geeks.shopapp1.data.model.ProductDto
import com.geeks.shopapp1.databinding.FragmentProductDetailBinding
import com.geeks.shopapp1.ui.models.UiState
import kotlinx.coroutines.launch
import androidx.fragment.app.viewModels

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

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

        val productId = arguments?.getInt("product_id") ?: return
        viewModel.loadProduct(productId)


        observeViewModel()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is UiState.Loaging -> {
                        binding.progressBar.isVisible = true
                    }
                    is UiState.Succes -> {
                        binding.progressBar.isVisible = false
                        showProduct(state.data)
                    }
                    is UiState.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                }
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
