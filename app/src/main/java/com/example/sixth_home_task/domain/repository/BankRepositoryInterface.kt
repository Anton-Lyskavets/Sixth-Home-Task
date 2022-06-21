package com.example.sixth_home_task.domain.repository

import com.example.sixth_home_task.domain.models.BankATM
import com.example.sixth_home_task.domain.models.BankFilial
import com.example.sixth_home_task.domain.models.BankInfobox
import io.reactivex.Observable

interface BankRepositoryInterface {
    fun getBankAtm(): Observable<List<BankATM>>
    fun getBankInfobox(): Observable<List<BankInfobox>>
    fun getBankFilial(): Observable<List<BankFilial>>
}