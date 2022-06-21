package com.example.sixth_home_task.presentation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sixth_home_task.domain.usecases.GetListBankATMUseCase
import com.example.sixth_home_task.domain.usecases.GetListBankFilialUseCase
import com.example.sixth_home_task.domain.usecases.GetListBankInfoboxUseCase

class MapsViewModelFactory(
    val getListBankATMUseCase: GetListBankATMUseCase,
    val getListBankInfoboxUseCase: GetListBankInfoboxUseCase,
    val getListBankFilialUseCase: GetListBankFilialUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MapsViewModel(
            getListBankATMUseCase = getListBankATMUseCase,
            getListBankInfoboxUseCase = getListBankInfoboxUseCase,
            getListBankFilialUseCase = getListBankFilialUseCase
        ) as T
    }
}
