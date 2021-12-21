package dev.mike.repository.datasources

import android.net.Uri
import androidx.paging.* // ktlint-disable no-wildcard-imports
import dev.mike.domain.model.Character
import dev.mike.domain.repositories.CharactersRepository
import dev.mike.network.ApiService
import dev.mike.repository.mappers.toCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersRepository {
    override suspend fun getAllCharacters(name: String?): Flow<PagingData<Character>> {

        return Pager(PagingConfig(pageSize = 20)) {
            CharactersPagingSource(apiService = apiService,name = name)
        }.flow
    }
}

class CharactersPagingSource(
    private val name:String? =null,
    private val apiService: ApiService
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorposition ->
            state.closestPageToPosition(anchorposition)?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val charactersResponse = apiService.getCharacters(pageNumber,name)
            val characters = charactersResponse.results.map { character ->
                character.toCharacter()
            }
            var nextPage: Int? = null
            if (charactersResponse.info.next != null) {
                val uri = Uri.parse(charactersResponse.info.next)
                nextPage = uri.getQueryParameter("page")?.toInt()
            }
            LoadResult.Page(
                data = characters,
                nextKey = nextPage,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
