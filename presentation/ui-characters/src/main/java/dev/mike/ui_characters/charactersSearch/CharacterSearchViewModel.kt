package dev.mike.ui_characters.charactersSearch

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mike.domain.model.Character
import dev.mike.domain.usecases.GetCharactersUseCase
import dev.mike.ui_characters.characterList.CharacterListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
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

    private var searchJob : Job? = null

    @ExperimentalCoroutinesApi
     val searchResponse = searchString.filter {
        it!=""
    }.flatMapLatest { searchName ->
      characterListUseCase.invoke(searchName).cachedIn(viewModelScope)
    }



    fun searchCharacterbyName(searchString: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if(searchString.length>3) delay(500)


            val response = characterListUseCase.invoke(searchString)
            _searchResult.value = CharacterListState(
                dataList = response
            )

        }
    }








    fun searchCharacter(name: String) {
        if (name == "") {
            _searchResult.value = CharacterListState(
                dataList = null
            )
        }
        _searchString.value = name
    }


}
