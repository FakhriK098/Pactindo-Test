package id.fakhri_khairi.data.mapper

import id.fakhri_khairi.data.model.responses.ProductResponse
import id.fakhri_khairi.domain.Product

class ProductMapper : Mapper<ProductResponse, Product> {
    override fun convert(from: ProductResponse): Product {
        return Product(
            id = from.id,
            title = from.title,
            price = from.price,
            description = from.description,
            category = from.category,
            image = from.image,
            rating = Product.Rating(rate = from.rating.rate)
        )
    }
}