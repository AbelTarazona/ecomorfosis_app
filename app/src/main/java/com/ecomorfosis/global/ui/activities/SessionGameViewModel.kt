package com.ecomorfosis.global.ui.activities

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ecomorfosis.global.models.Question
import com.ecomorfosis.global.repository.MainRepository
import com.ecomorfosis.global.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Created by AbelTarazona on 5/12/2020
 */
@ExperimentalCoroutinesApi
class SessionGameViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Question>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Question>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                MainStateEvent.GetQuestions -> {
                    mainRepository.getQuestions()
                        .onEach {
                            _dataState.value = it
                        }
                        .launchIn(viewModelScope)
                }
                MainStateEvent.None -> {
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetQuestions : MainStateEvent()
    object None : MainStateEvent()
}