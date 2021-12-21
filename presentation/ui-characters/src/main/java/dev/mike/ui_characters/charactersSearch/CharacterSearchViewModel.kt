package dev.mike.ui_characters.charactersSearch

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mike.domain.usecases.GetCharactersUseCase
import dev.mike.ui_characters.characterList.CharacterListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CharacterSearchViewModel @Inject constructor(
    private val characterListUseCase: GetCharactersUseCase
) : ViewModel() {
    private var _searchResult = mutableStateOf(CharacterListState())
    var searchResult = _searchResult

    private val _searchString = MutableStateFlow("")
    val searchString = _searchString.asStateFlow()

    @ExperimentalCoroutinesApi
    private val searchResponse = searchString.flatMapLatest { searchName ->
        // delay(1000)

        characterListUseCase.invoke(searchName).cachedIn(viewModelScope)
    }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

    init {
        viewModelScope.launch {
          /*  searchString.flatMapLatest { name ->
                //searchCharacterbyName(name)


            }*/

            // _searchResult.value = CharacterListState(isLoading = true)
           /* searchResponse.onEach { results ->
                _searchResult.value = CharacterListState(
                    dataList = flow {
                        emit(results)
                    }
                )
            }*/
        }
    }

    fun searchCharacter(name: String) {
        _searchString.value = name
    }

    fun searchCharacterbyName(searchString: String) {
        viewModelScope.launch {
            _searchResult.value = CharacterListState(
                dataList = null
            )
            delay(1000)
            val response = characterListUseCase.invoke(searchString)
            _searchResult.value = CharacterListState(
                dataList = response
            )
        }
    }
}
