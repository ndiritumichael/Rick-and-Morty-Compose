package dev.mike.ui_characters.characterDetails

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mike.domain.model.episodes.Episode
import dev.mike.domain.usecases.GetCharacterDetailsUsecase
import dev.mike.domain.usecases.episodes.GetEpisodesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUsecase: GetCharacterDetailsUsecase,
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _detailsState = mutableStateOf(CharacterDetailsState())
    val detailsState = _detailsState
    val characterId = mutableStateOf(0)

    private val episodeIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())

    @ExperimentalCoroutinesApi
    val episodesList = episodeIds.filter { list ->
        Log.d("myflow","$list")
        list.isEmpty().not()

    }.flatMapLatest { idList ->
        val id = idList.joinToString()

    flow {
        getEpisodesUseCase(id).fold(
            onSuccess = {
                emit(
                    CharacterEpisodesState(
                        episodesDataList = it
                    )
                )

            },
            onFailure = {
                Log.d("myflow", it.localizedMessage)
                emit(
                CharacterEpisodesState(
                   errorMessage = it.localizedMessage?:"Something Went Wrong"
                ))

            }
        )
    }

    }

    init {
        val id = savedStateHandle.get<Int>("characterId")
        _detailsState.value = CharacterDetailsState(isLoading = true)
        if (id != null) {
            characterId.value = id
            getCharacterbyId()
        }


    }

    private fun getCharacterbyId() = viewModelScope.launch {

        val result = getCharacterDetailsUsecase(characterId.value)

        result.onSuccess { details ->
            _detailsState.value = CharacterDetailsState(data = details)
            episodeIds.value =details.episode
        }
        result.onFailure {

            error ->
            _detailsState.value = CharacterDetailsState(errorMessage = error.message)
        }
    }
}


data class CharacterEpisodesState(
    val isLoading: Boolean = false,
    val errorMessage:String = "",
    val episodesDataList :List<Episode> = emptyList()
)
