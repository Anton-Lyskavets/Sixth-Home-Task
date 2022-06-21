package com.example.sixth_home_task.domain.usecases

import com.example.sixth_home_task.domain.models.BankInfobox
import com.example.sixth_home_task.domain.repository.BankRepositoryInterface
import io.reactivex.Observable

class GetListBankInfoboxUseCase(private val bankRepositoryInterface: BankRepositoryInterface) {
    fun execute(): Observable<List<BankInfobox>> {
        return bankRepositoryInterface.getBankInfobox()
    }
}