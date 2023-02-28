package id.fakhri_khairi.core.presentesion.detail

import id.fakhri_khairi.data.usecase.contract.GetProductDetail
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    val getProductDetail: GetProductDetail
) {
}