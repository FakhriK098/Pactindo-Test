package id.fakhri_khairi.core.presentesion.home

import id.fakhri_khairi.data.usecase.contract.GetProductList
import id.fakhri_khairi.data.usecase.contract.GetProductSearch
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    val getProductList: GetProductList,
    val getProductSearch: GetProductSearch
)