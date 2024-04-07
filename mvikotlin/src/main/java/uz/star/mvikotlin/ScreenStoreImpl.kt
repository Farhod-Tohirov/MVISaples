package uz.star.mvikotlin

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import uz.star.mvikotlin.models.Intent
import uz.star.mvikotlin.models.Label

internal class ScreenStoreImpl(
    storeFactory: StoreFactory,
    executor: ScreenExecutor,
    reducer: ScreenReducer
) : ScreenStore, Store<Intent, ScreenState, Label> by storeFactory.create(
    name = "Store",
    initialState = ScreenState(),
    bootstrapper = SimpleBootstrapper(),
    executorFactory = { executor },
    reducer = reducer
)