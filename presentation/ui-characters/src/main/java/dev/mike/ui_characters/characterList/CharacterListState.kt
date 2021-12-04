package dev.mike.ui_characters.characterList

import androidx.paging.PagingData
import dev.mike.domain.model.Character
import kotlinx.coroutines.flow.Flow

data class CharacterListState(
    var isLoading: Boolean = false,
    val dataList: Flow<PagingData<Character>>? = null,
    val errorMessage: String = ""
)
