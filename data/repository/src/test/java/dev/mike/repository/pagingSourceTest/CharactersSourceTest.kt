package dev.mike.repository.pagingSourceTest

import androidx.paging.PagingSource
import dev.mike.repository.datasources.CharactersPagingSource
import dev.mike.repository.fake.FakeCharactersRepository
import dev.mike.repository.fake.NotFoundException
import dev.mike.repository.fake.SampleData
import dev.mike.repository.mappers.toCharacter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersSourceTest {

    private lateinit var charactersSource: CharactersPagingSource

    @Before
    fun setUp() {

        charactersSource = CharactersPagingSource(repository = FakeCharactersRepository())
    }

    @Test
    fun `characters paging source source refresh is success`() = runTest {
        // given
        val actualResult = charactersSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        // when
        val expectedResult = PagingSource.LoadResult.Page(
            data = SampleData.page1CharactersDto.results.map { it.toCharacter() },
            prevKey = null,
            nextKey = 2
        )
        // then

        print(actualResult)
        assertThat(
            actualResult, `is`(expectedResult)
        )
    }

    @Test
    fun `characters  load next page is a success`() = runTest {

        // given
        val actualResult = charactersSource.load(
            PagingSource.LoadParams.Append(
                key = 2,
                loadSize = 20,
                placeholdersEnabled = true
            )
        )
        // when
        val expectResult = PagingSource.LoadResult.Page(
            data = SampleData.page2CharactersDto.results.map { it.toCharacter() },
            prevKey = null,
            nextKey = 3
        )

        // then

        assertThat(actualResult, `is`(expectResult))
    }

    @Test
    fun `characters page with invalid page returns error`() = runTest {

        // given
        val actualResult = charactersSource.load(
            PagingSource.LoadParams.Refresh(
                key = 60,
                loadSize = 20,
                placeholdersEnabled = true
            )
        )



        // when
        val expectedResult = PagingSource.LoadResult.Error<Int, dev.mike.domain.model.Character>(NotFoundException())

        assertThat(actualResult.toString(), `is`(expectedResult.toString()))
    }
}
