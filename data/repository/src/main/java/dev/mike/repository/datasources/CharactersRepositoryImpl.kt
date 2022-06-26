package dev.mike.repository.datasources

import androidx.paging.* // ktlint-disable no-wildcard-imports
import dev.mike.domain.model.Character
import dev.mike.domain.repositories.CharactersRepository
import dev.mike.network.source.IRemoteCharactersRepository
import dev.mike.repository.mappers.toCharacter
import dev.mike.repository.utils.getPageIntFromUrl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val repository: IRemoteCharactersRepository
) : CharactersRepository {
    override suspend fun getAllCharacters(name: String?): Flow<PagingData<Character>> {

        return Pager(PagingConfig(pageSize = 20)) {
            CharactersPagingSource(repository = repository, name = name)
        }.flow
    }
}

class CharactersPagingSource(
    private val name: String? = null,
    private val repository: IRemoteCharactersRepository
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorposition ->
            state.closestPageToPosition(anchorposition)?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val charactersResponse = repository.getCharacters(pageNumber, name)
            val characters = charactersResponse.results.map { characterDto ->
                characterDto.toCharacter()
            }
            var nextPage: Int? = null
            if (charactersResponse.info.next != null) {

                /** fetching next Page wworks with production code but fails on unit tests due to android depedency
                 * val uri = Uri.parse(charactersResponse.info.next)
                 * nextpage = uri.getQueryParameter("page")?.toInt()
                 */

                nextPage = getPageIntFromUrl(charactersResponse.info.next!!)
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
