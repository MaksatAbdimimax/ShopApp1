package com.geeks.shopapp1.ui.fragments.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.shopapp1.R

import com.geeks.shopapp1.databinding.FragmentListBinding
import com.geeks.shopapp1.ui.adapters.ProductAdapter

import com.geeks.shopapp1.ui.models.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by viewModel ()

    private val adapter = ProductAdapter { product ->
        val id = product.id
        if (id != null) {
            openDetail(id)
        } else {
            Toast.makeText(requireContext(), "ID продукта = null", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        observeState()
    }



    private fun setupRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    /*private fun loadProducts() {
        binding.progressBar.isVisible = true
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val products = RetrofitService.api.getAllProducts()
                adapter.submitList(products)
            }catch (e: Exception){

                Toast.makeText(requireContext(), "Oshibka: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                _binding?.progressBar?.isVisible = false
            }
        }
    }*/

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect { state ->
                    when(state){
                        is UiState.Loaging -> {
                            binding.progressBar.isVisible = true
                            binding.recyclerView.isVisible = false
                        }
                        is UiState.Succes -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerView.isVisible = true
                            adapter.submitList(state.data)
                        }
                        is UiState.Error -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerView.isVisible = false
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openDetail(productId: Int) {
        val bundle = Bundle().apply {
            putInt("product_id", productId)
        }


        /*findNavController().navigate(
            R.id.productDetailFragment,
            bundle
        )*/
        findNavController().navigate(
            R.id.action_to_detail,
            bundle
        )

    }


}