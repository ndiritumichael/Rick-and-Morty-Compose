package dev.mike.domain.usecases

import android.util.Log
import dev.mike.domain.repositories.CharacterDetailsRepository
import javax.inject.Inject

class GetCharacterDetailsUsecase @Inject constructor(
    private val characterDetailsRepository: CharacterDetailsRepository
) {

    suspend operator fun invoke(id: Int) = characterDetailsRepository.getCharacterDetails(id)
}
