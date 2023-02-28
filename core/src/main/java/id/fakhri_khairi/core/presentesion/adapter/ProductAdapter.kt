package id.fakhri_khairi.core.presentesion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.fakhri_khairi.core.base.BaseAdapter
import id.fakhri_khairi.core.databinding.CardProductBinding
import id.fakhri_khairi.core.misc.convertToPrice
import id.fakhri_khairi.data.misc.Constants
import id.fakhri_khairi.domain.Product

class ProductAdapter : BaseAdapter<Product>() {

    private var onProductClick : (Product) -> Unit = {_ ->}

    private class ProductViewHolder(
        val binding: CardProductBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private fun CardProductBinding.bind(product: Product) {
        ivProduct.load(product.image)
        tvTitleProduct.text = product.title
        tvPriceProduct.text = product.price.convertToPrice()

        root.setOnClickListener { onProductClick(product) }
    }

    fun setOnProductClicked(OnProductClick : (Product) -> Unit) {
        onProductClick = OnProductClick
    }

    override fun getViewType(position: Int): Int = Constants.BASE_VIEW_TYPE

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            CardProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            val product = items[position]
            product?.let { holder.binding.bind(it) }
        }
    }
}