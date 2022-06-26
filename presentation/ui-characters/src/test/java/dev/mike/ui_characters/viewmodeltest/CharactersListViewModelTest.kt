package dev.mike.ui_characters.viewmodeltest

import androidx.paging.AsyncPagingDataDiffer
import com.dev.mike.core_testing.fake.CoreTestingSampleData
import dev.mike.repository.mappers.toCharacter
import dev.mike.ui_characters.characterList.CharactersListViewModel
import dev.mike.ui_characters.fakedata.fakeCharactersUseCase
import dev.mike.ui_characters.utils.CharacterDiff
import dev.mike.ui_characters.utils.CoroutineTestRule
import dev.mike.ui_characters.utils.noopListUpdateCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

class CharactersListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: CharactersListViewModel

    @Before
    fun setUp() {
        viewModel = CharactersListViewModel(fakeCharactersUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class, ExperimentalTime::class)
    @Test
    fun `calling get characters returns valid paging data`() = runTest {

        val actualItem = CoreTestingSampleData.page1CharactersDto.results.first().toCharacter()

        val differ = AsyncPagingDataDiffer(
            diffCallback = CharacterDiff,
            updateCallback = noopListUpdateCallback,
            mainDispatcher = Dispatchers.Main

        )
        advanceUntilIdle()

        viewModel.characterListState.value.dataList!!.onEach { data ->

            differ.submitData(data)

            assertThat(differ.snapshot().items, hasItem(actualItem))
        }
    }
}
