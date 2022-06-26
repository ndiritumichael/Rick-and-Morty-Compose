package dev.mike.ui_characters.characterList

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mike.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _characterListState: MutableState<CharacterListState> = mutableStateOf(
        CharacterListState()
    )
    private val handler = CoroutineExceptionHandler { _, exception ->
        _characterListState.value.isLoading = false
        _characterListState.value = CharacterListState(
            errorMessage = exception.message!!
        )
    }
    val characterListState: State<CharacterListState>
        get() = _characterListState
    init {
        getCharacters()
    }


     fun getCharacters() = viewModelScope.launch(handler) {

        val response = getCharactersUseCase().cachedIn(viewModelScope)



        _characterListState.value = CharacterListState(
            dataList = response
        )

    }
}
