package dev.mike.ui_characters.characterDetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mike.domain.usecases.GetCharacterDetailsUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUsecase: GetCharacterDetailsUsecase
) : ViewModel() {
    private val _detailsState = mutableStateOf(CharacterDetailsState())
    val detailsState = _detailsState

    fun getCharacterbyId(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        _detailsState.value = CharacterDetailsState(isLoading = true)

        getCharacterDetailsUsecase(id).onEach { result ->
            result.onSuccess { details ->
                _detailsState.value = CharacterDetailsState(data = details)
            }
            result.onFailure {

                error ->
                _detailsState.value = CharacterDetailsState(errorMessage = error.localizedMessage!!)
            }
        }
    }
}
