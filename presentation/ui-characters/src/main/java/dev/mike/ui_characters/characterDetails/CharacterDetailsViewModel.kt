package dev.mike.ui_characters.characterDetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mike.domain.usecases.GetCharacterDetailsUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUsecase: GetCharacterDetailsUsecase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _detailsState = mutableStateOf(CharacterDetailsState())
    val detailsState = _detailsState

    init {
        val id = savedStateHandle.get<Int>("characterId")
        if (id != null) {
            getCharacterbyId(id)
        }
        _detailsState.value = CharacterDetailsState(isLoading = true)

    }

    fun getCharacterbyId(id: Int) = viewModelScope.launch(Dispatchers.IO) {

        val result = getCharacterDetailsUsecase(id)

        result.onSuccess { details ->
            _detailsState.value = CharacterDetailsState(data = details)
        }
        result.onFailure {

            error ->
            _detailsState.value = CharacterDetailsState(errorMessage = error.message)
        }
    }
}
