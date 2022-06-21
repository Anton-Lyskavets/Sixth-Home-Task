package com.example.sixth_home_task.domain.usecases

import com.example.sixth_home_task.domain.models.BankFilial
import com.example.sixth_home_task.domain.repository.BankRepositoryInterface
import io.reactivex.Observable

class GetListBankFilialUseCase(private val bankRepositoryInterface: BankRepositoryInterface) {
    fun execute(): Observable<List<BankFilial>> {
        return bankRepositoryInterface.getBankFilial()
    }
}