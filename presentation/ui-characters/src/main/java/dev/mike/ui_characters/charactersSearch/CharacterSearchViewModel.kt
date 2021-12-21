package dev.mike.ui_characters.charactersSearch

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mike.domain.usecases.GetCharactersUseCase
import dev.mike.ui_characters.characterList.CharacterListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class CharacterSearchViewModel @Inject constructor(
    private val characterListUseCase: GetCharactersUseCase
): ViewModel() {
    private var _searchResult = mutableStateOf(CharacterListState())
    var searchResult = _searchResult

    private val _searchString = MutableStateFlow("")
    val searchString = _searchString.asStateFlow()
    @ExperimentalCoroutinesApi
    private val searchResponse = searchString.mapLatest { searchName ->
        delay(1000)

        characterListUseCase.invoke(searchName).cachedIn(viewModelScope)

    }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _searchResult.value = CharacterListState(isLoading = true)
            searchResponse.onEach { results ->
                _searchResult.value = CharacterListState(
                    dataList = results
                )


            }

        }



    }

    fun searchCharacter(name:String){
        _searchString.value = name


    }





}