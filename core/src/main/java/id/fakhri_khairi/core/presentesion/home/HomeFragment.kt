package id.fakhri_khairi.core.presentesion.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentHomeBinding
import id.fakhri_khairi.core.presentesion.adapter.ProductAdapter
import id.fakhri_khairi.data.misc.Constants
import id.fakhri_khairi.domain.Product
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by viewModels<HomeViewModel>()
    private var productAdapter = ProductAdapter()
    private val productList : MutableList<Product> = mutableListOf()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentHomeBinding.setupEvent() {
        viewModel.event.collect {
            when(it) {
                is HomeEvent.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                }
                is HomeEvent.Success -> {
                    renderData(it.data)
                    renderLoading(false)
                }
                HomeEvent.Loading -> renderLoading()
                is HomeEvent.SuccessGetProductSearch -> {
                    productAdapter.setItems(it.data)
                    renderLoading(false)
                }
            }
        }
    }

    override suspend fun FragmentHomeBinding.setupState() {}

    override fun FragmentHomeBinding.initBinding() {
        initAdapter()
        initListener()

        viewModel.getProductList()
    }

    private fun FragmentHomeBinding.initAdapter() {
        rvProduct.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productAdapter
            isNestedScrollingEnabled = false
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun FragmentHomeBinding.initListener() {
        productAdapter.setOnProductClicked { product ->
            val bundle = bundleOf(Constants.PRODUCT_ID to product.id)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }

        svProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val data = productList
                    viewModel.getProductSearch(it, data)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    viewModel.getProductList()
                }
                return false
            }

        })
    }

    private fun FragmentHomeBinding.renderData(data: List<Product>) {
        productList.clear()
        productList.addAll(data)
        productAdapter.setItems(productList)
    }

    private fun FragmentHomeBinding.renderLoading(isLoading: Boolean = true) {
        svProduct.isVisible = !isLoading
        rvProduct.isVisible = !isLoading
        icSkeleton.root.isVisible = isLoading
    }
}