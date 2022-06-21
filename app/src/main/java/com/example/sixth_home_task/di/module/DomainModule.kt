package com.example.sixth_home_task.di.module

import com.example.sixth_home_task.domain.repository.BankRepositoryInterface
import com.example.sixth_home_task.domain.usecases.GetListBankATMUseCase
import com.example.sixth_home_task.domain.usecases.GetListBankFilialUseCase
import com.example.sixth_home_task.domain.usecases.GetListBankInfoboxUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetListBankATMUseCase(bankRepositoryInterface: BankRepositoryInterface):
            GetListBankATMUseCase {
        return GetListBankATMUseCase(bankRepositoryInterface)
    }

    @Provides
    fun provideGetListBankInfoboxUseCase(bankRepositoryInterface: BankRepositoryInterface):
            GetListBankInfoboxUseCase {
        return GetListBankInfoboxUseCase(bankRepositoryInterface)
    }

    @Provides
    fun provideGetListBankFilialUseCase(bankRepositoryInterface: BankRepositoryInterface):
            GetListBankFilialUseCase {
        return GetListBankFilialUseCase(bankRepositoryInterface)
    }
}
