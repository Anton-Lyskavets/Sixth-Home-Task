package com.example.sixth_home_task.di.module

import com.example.sixth_home_task.domain.usecases.GetListBankATMUseCase
import com.example.sixth_home_task.domain.usecases.GetListBankFilialUseCase
import com.example.sixth_home_task.domain.usecases.GetListBankInfoboxUseCase
import com.example.sixth_home_task.presentation.model.MapsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideMapsViewModelFactory(
        getListBankATMUseCase: GetListBankATMUseCase,
        getListBankInfoboxUseCase: GetListBankInfoboxUseCase,
        getListBankFilialUseCase: GetListBankFilialUseCase,
    ): MapsViewModelFactory {
        return MapsViewModelFactory(
            getListBankATMUseCase = getListBankATMUseCase,
            getListBankInfoboxUseCase = getListBankInfoboxUseCase,
            getListBankFilialUseCase = getListBankFilialUseCase
        )
    }
}