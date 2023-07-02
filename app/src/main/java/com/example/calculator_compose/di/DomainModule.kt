package com.example.calculator_compose.di

import com.example.calculator_compose.data.StoreHistoryCalculation
import com.example.calculator_compose.domain.ConstCalculationRow
import com.example.calculator_compose.domain.calculation.Result
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
        calculation: ConstCalculationRow.Base,
        result: Result.Base,
    ): MainInteractor = MainInteractor.Base(
        number = number,
        zero = zero,
        coma = coma,
        action = action,
        equal = equal,
        back = back,
        storeHistoryCalculation = store,
        constCalculationRow = calculation,
        result = result
    )

    @Provides
    fun provideAdditionalInteractor(
        number: NumberUseCase.Base,
        zero: ZeroUseCase.Base,
        coma: ComaUseCase.Base,
        action: ActionUseCase.Base,
        equal: EqualUseCase.Base,
        back: BackUseCase.Base,
        sqrt: SquareRootUseCase.Base,
        factorial: FactorialUseCase.Base,
        trigonometric: TrigonometricUseCase.Base,
        bracket: BracketUseCase.Base,
        pi: AdditionalNumberUseCase.AdditionalNumber,
        converting: DegreesToRadians.Base,
        change: TwoNDUseCase.Base,
        store: StoreHistoryCalculation,
        calculation: ConstCalculationRow.Base,
        result: Result.Base,
    ): AdditionalInteractor = AdditionalInteractor.Base(
        number = number,
        zero = zero,
        coma = coma,
        action = action,
        equal = equal,
        back = back,
        sqrt = sqrt,
        factorial = factorial,
        trigonometric = trigonometric,
        bracket = bracket,
        additionalNumber = pi,
        degreesToRadians = converting,
        twoND = change,
        storeHistoryCalculation = store,
        constCalculationRow = calculation,
        result = result
    )
}