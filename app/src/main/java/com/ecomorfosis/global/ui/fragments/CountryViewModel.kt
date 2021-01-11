package com.ecomorfosis.global.ui.fragments

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ecomorfosis.global.repository.EcomorfosisPreferences
import com.ecomorfosis.global.repository.MainRepository
import com.ecomorfosis.global.retrofit.User
import com.ecomorfosis.global.retrofit.UserRequest
import com.ecomorfosis.global.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Created by AbelTarazona on 5/12/2020
 */
@ExperimentalCoroutinesApi
class CountryViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<String>> = MutableLiveData()

    val dataState: LiveData<DataState<String>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent, request: UserRequest) {
        viewModelScope.launch {
            when (mainStateEvent) {
                MainStateEvent.CreateUser -> {
                    mainRepository.createUser(request)
                        .onEach {
                            _dataState.value = it
                        }.launchIn(viewModelScope)
                }
                MainStateEvent.None -> {
                }
            }
        }
    }

    suspend fun setUserPref(user: User, pref: EcomorfosisPreferences) {

        viewModelScope.async {
            pref.saveUser(user)
            pref.saveSession(true)
        }.await()


    }

}

sealed class MainStateEvent {
    object CreateUser : MainStateEvent()
    object None : MainStateEvent()
}