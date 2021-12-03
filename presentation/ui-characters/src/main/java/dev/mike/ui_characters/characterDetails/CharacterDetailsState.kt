package dev.mike.ui_characters.characterDetails

import dev.mike.domain.model.CharacterDetails

data class CharacterDetailsState(
    val isLoading: Boolean = false,
    val data: CharacterDetails? = null,
    val errorMessage: String = ""
)
