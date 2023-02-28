package id.fakhri_khairi.core.presentesion.home

import dagger.hilt.android.lifecycle.HiltViewModel
import id.fakhri_khairi.core.base.BaseViewModel
import id.fakhri_khairi.domain.Product
import id.fakhri_khairi.domain.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

sealed class HomeState {
    object Idle: HomeState()
}

sealed class HomeEvent {
    data class Success(val data: List<Product>) : HomeEvent()
    data class SuccessGetProductSearch(val data: List<Product>) : HomeEvent()
    data class Error(val message: String) : HomeEvent()
    object Loading : HomeEvent()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    coroutineContext: CoroutineContext,
    private val useCase: HomeUseCase
) : BaseViewModel<HomeState, HomeEvent>(
    HomeState.Idle,
    coroutineContext
) {
    fun getProductList(){
        adaptiveScope.launch {
            eventChannel.send(HomeEvent.Loading)

            when(val result = useCase.getProductList.execute()) {
                is Result.Error -> eventChannel.send(HomeEvent.Error(result.message))
                is Result.Success -> eventChannel.send(HomeEvent.Success(result.data))
            }
        }
    }

    fun getProductSearch(
        keyword: String,
        data: List<Product>
    ) {
        adaptiveScope.launch {
            eventChannel.send(HomeEvent.Loading)

            when(val result = useCase.getProductSearch.execute(keyword, data)) {
                is Result.Error -> eventChannel.send(HomeEvent.Error(result.message))
                is Result.Success -> eventChannel.send(HomeEvent.SuccessGetProductSearch(result.data))
            }
        }
    }
}