package uz.star.mvikotlin

import com.arkivanov.mvikotlin.core.store.Store
import uz.star.mvikotlin.models.Intent
import uz.star.mvikotlin.models.Label

interface ScreenStore : Store<Intent, ScreenState, Label>