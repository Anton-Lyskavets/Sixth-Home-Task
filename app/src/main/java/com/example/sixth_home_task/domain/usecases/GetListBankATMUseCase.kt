package com.example.sixth_home_task.domain.usecases

import com.example.sixth_home_task.domain.models.BankATM
import com.example.sixth_home_task.domain.repository.BankRepositoryInterface
import io.reactivex.Observable

class GetListBankATMUseCase(private val bankRepositoryInterface: BankRepositoryInterface) {
    fun execute(): Observable<List<BankATM>> {
        return bankRepositoryInterface.getBankAtm()
    }
}