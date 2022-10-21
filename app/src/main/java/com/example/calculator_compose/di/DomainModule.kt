package com.example.calculator_compose.di

import com.example.calculator_compose.data.StoreHistoryCalculation
import com.example.calculator_compose.domain.CalculationRow
import com.example.calculator_compose.domain.interactor.AdditionalInteractor
import com.example.calculator_compose.domain.interactor.MainInteractor
import com.example.calculator_compose.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideMainInteractor(
        number: NumberUseCase.Base,
        zero: ZeroUseCase.Base,
        coma: ComaUseCase.Base,
        action: ActionUseCase.Base,
        equal: EqualUseCase.Base,
        back: BackUseCase.Base,
        store: StoreHistoryCalculation,
        calculation: CalculationRow.Base
    ): MainInteractor = MainInteractor.Base(
        number = number,
        zero = zero,
        coma = coma,
        action = action,
        equal = equal,
        back = back,
        storeHistoryCalculation = store,
        calculationRow = calculation
    )

    @Provides
    fun provideAdditionalInteractor(
        number: NumberUseCase.Base,
        zero: ZeroUseCase.Base,
        coma: ComaUseCase.Base,
        action: ActionUseCase.Base,
        equal: EqualUseCase.Base,
        back: BackUseCase.Base,
        store: StoreHistoryCalculation,
        calculation: CalculationRow.Base
    ): AdditionalInteractor = AdditionalInteractor.Base(
        number = number,
        zero = zero,
        coma = coma,
        action = action,
        equal = equal,
        back = back,
        storeHistoryCalculation = store,
        calculationRow = calculation
    )
}