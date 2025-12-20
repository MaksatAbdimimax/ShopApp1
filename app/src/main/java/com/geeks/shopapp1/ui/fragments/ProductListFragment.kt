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
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.shopapp1.R
import com.geeks.shopapp1.data.api.RetrofitService
import com.geeks.shopapp1.databinding.FragmentListBinding
import com.geeks.shopapp1.ui.adapters.ProductAdapter
import kotlinx.coroutines.launch
import java.lang.Exception
import androidx.navigation.fragment.findNavController


class ProductListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter = ProductAdapter {product ->
        product.id?.let { id ->
            openDetail(id)
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
        loadProducts()
    }



    private fun setupRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun loadProducts() {
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