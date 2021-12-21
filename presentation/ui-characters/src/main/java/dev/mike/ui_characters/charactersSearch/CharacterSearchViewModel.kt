package dev.mike.ui_characters.charactersSearch

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mike.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharacterSearchViewModel @Inject constructor(
    private val characterListUseCase: GetCharactersUseCase
): ViewModel() {

    val _searchString = MutableStateFlow("")
    val searchString = _searchString.asStateFlow()
    val searchData = searchString.mapLatest { searchName ->
        characterListUseCase.invoke(searchName)

    }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)
    init {

    }





}