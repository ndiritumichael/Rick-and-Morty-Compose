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
    val characterId = mutableStateOf(0)

    init {
        val id = savedStateHandle.get<Int>("characterId")
        if (id != null) {
            characterId.value = id
            getCharacterbyId()
        }
        _detailsState.value = CharacterDetailsState(isLoading = true)

    }

    fun getCharacterbyId() = viewModelScope.launch {

        val result = getCharacterDetailsUsecase(characterId.value)

        result.onSuccess { details ->
            _detailsState.value = CharacterDetailsState(data = details)
        }
        result.onFailure {

            error ->
            _detailsState.value = CharacterDetailsState(errorMessage = error.message)
        }
    }
}
