package com.example.sixth_home_task.presentation.model

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sixth_home_task.domain.models.Bank
import com.example.sixth_home_task.domain.usecases.GetListBankATMUseCase
import com.example.sixth_home_task.domain.usecases.GetListBankFilialUseCase
import com.example.sixth_home_task.domain.usecases.GetListBankInfoboxUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MapsViewModel(
    private val getListBankATMUseCase: GetListBankATMUseCase,
    private val getListBankInfoboxUseCase: GetListBankInfoboxUseCase,
    private val getListBankFilialUseCase: GetListBankFilialUseCase,
) : ViewModel() {
    private val _bankList = MutableLiveData<List<Bank>>()
    val bankList: LiveData<List<Bank>> = _bankList

    init {
        getBankList()
    }

    @SuppressLint("CheckResult")
    fun getBankList() {
        Observable
            .zip(
                getListBankATMUseCase.execute(),
                getListBankInfoboxUseCase.execute(),
                getListBankFilialUseCase.execute()
            ) { type1, type2, type3 ->
                type1 + type2 + type3
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _bankList.value = it
            }, { error ->
                error.printStackTrace()
            })
    }

}