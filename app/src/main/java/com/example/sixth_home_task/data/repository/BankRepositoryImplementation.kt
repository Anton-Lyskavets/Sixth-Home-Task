package com.example.sixth_home_task.data.repository

import com.example.sixth_home_task.data.network.BankApi.retrofitService
import com.example.sixth_home_task.domain.models.BankATM
import com.example.sixth_home_task.domain.models.BankFilial
import com.example.sixth_home_task.domain.models.BankInfobox
import com.example.sixth_home_task.domain.repository.BankRepositoryInterface
import io.reactivex.Observable

private const val CITY = "Гомель"

class BankRepositoryImplementation(
) : BankRepositoryInterface {
    override fun getBankAtm(): Observable<List<BankATM>> {
        return retrofitService.getATM(CITY)
    }

    override fun getBankInfobox(): Observable<List<BankInfobox>> {
        return retrofitService.getInfobox(CITY)
    }

    override fun getBankFilial(): Observable<List<BankFilial>> {
        return retrofitService.getFilial(CITY)
    }
}