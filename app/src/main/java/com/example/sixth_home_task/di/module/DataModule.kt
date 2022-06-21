package com.example.sixth_home_task.di.module

import com.example.sixth_home_task.data.repository.BankRepositoryImplementation
import com.example.sixth_home_task.domain.repository.BankRepositoryInterface
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideBankRepositoryInterface(): BankRepositoryInterface {
        return BankRepositoryImplementation()
    }
}