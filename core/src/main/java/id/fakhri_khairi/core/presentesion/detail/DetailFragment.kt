package id.fakhri_khairi.core.presentesion.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentDetailBinding
import id.fakhri_khairi.core.misc.convertToPrice
import id.fakhri_khairi.data.misc.Constants
import id.fakhri_khairi.domain.Product
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val viewModel by viewModels<DetailViewModel>()
    private var productId: Int? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentDetailBinding.setupEvent() {
        viewModel.event.collect {
            when(it) {
                is DetailEvent.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                }
                is DetailEvent.Success -> {
                    renderData(it.data)
                    renderLoading(false)
                }
                DetailEvent.Loading -> renderLoading()
            }
        }
    }

    override suspend fun FragmentDetailBinding.setupState() {}

    override fun FragmentDetailBinding.initBinding() {
        productId = arguments?.getInt(Constants.PRODUCT_ID)

        productId?.let {
            viewModel.getProductDetail(it)
        }
        initListener()
    }

    private fun FragmentDetailBinding.initListener() {
        cvBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun FragmentDetailBinding.renderData(data: Product) {
        ivProduct.load(data.image)
        tvTitleProduct.text = data.title
        tvPriceProduct.text = data.price.convertToPrice()
        tvCategoryProduct.text = data.category
        tvRateProduct.text = data.rating.rate.toString()
        tvDescProduct.text = data.description
    }

    private fun FragmentDetailBinding.renderLoading(isLoading: Boolean = true) {
        ivProduct.isVisible = !isLoading
        cvBack.isVisible = !isLoading
        tvTitleProduct.isVisible = !isLoading
        tvPriceProduct.isVisible = !isLoading
        tvCategoryProduct.isVisible = !isLoading
        tvTitleRateProduct.isVisible = !isLoading
        tvRateProduct.isVisible = !isLoading
        tvDescProduct.isVisible = !isLoading
        icSkeleton.root.isVisible = isLoading
    }
}