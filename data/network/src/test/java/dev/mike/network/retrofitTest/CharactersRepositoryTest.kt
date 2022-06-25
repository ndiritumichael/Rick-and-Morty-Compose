package dev.mike.network.retrofitTest

import dev.mike.network.BaseTest
import dev.mike.network.SampleData
import dev.mike.network.data.RemoteCharactersRepositoryImpl
import dev.mike.network.source.IRemoteCharactersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersRepositoryTest : BaseTest() {
    private lateinit var charactersRepository: IRemoteCharactersRepository

    @Before
    override fun setup() {
        super.setup()
        charactersRepository = RemoteCharactersRepositoryImpl(apiService)
    }

    @Test
    fun `given a valid page returns valid characters`() = runTest {
        val page = 1
        val actualCharacters = charactersRepository.getCharacters(page)

        val expectedCharacter = SampleData.page1CharacterDTO



        assertThat(actualCharacters.results, hasItem(expectedCharacter))
    }

    @Test
    fun `given an invalid page number returns an error`() = runTest {

        try {
            val actualresults = charactersRepository.getCharacters(1000)
        } catch (e: Throwable) {

            assertThat(e.message, containsString("HTTP 404"))
        }
    }

    @Test
    fun `given a valid character id returns character details`() = runTest {
        val actualcharacterdetails = charactersRepository.getCharacterDetails(1)

        assertThat(actualcharacterdetails.id, `is`(SampleData.page1CharacterDTO.id))
        assertThat(actualcharacterdetails.name, `is`(SampleData.page1CharacterDTO.name))
    }

    @Test
    fun `given an invalid character id returns an error`() = runTest {
        try {
            charactersRepository.getCharacterDetails(10000)
        } catch (e: Exception) {
            assertThat(e.message, containsString("HTTP 404"))

        }
    }


}
