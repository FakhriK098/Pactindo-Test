package id.fakhri_khairi.core.presentesion.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import id.fakhri_khairi.core.base.BaseViewModel
import id.fakhri_khairi.domain.Product
import id.fakhri_khairi.domain.Result
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

sealed class DetailState {
    object Idle: DetailState()
}

sealed class DetailEvent {
    data class Success(val data: Product) : DetailEvent()
    data class Error(val message: String) : DetailEvent()
    object Loading : DetailEvent()
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    coroutineContext: CoroutineContext,
    private val useCase: DetailUseCase
) : BaseViewModel<DetailState, DetailEvent>(
    DetailState.Idle,
    coroutineContext
) {
    fun getProductDetail(id: Int) {
        adaptiveScope.launch {
            eventChannel.send(DetailEvent.Loading)
            when(val result = useCase.getProductDetail.execute(id)) {
                is Result.Error -> eventChannel.send(DetailEvent.Error(result.message))
                is Result.Success -> eventChannel.send(DetailEvent.Success(result.data))
            }
        }
    }
}