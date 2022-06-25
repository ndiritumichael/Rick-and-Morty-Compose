package dev.mike.repository.pagingSourceTest

import dev.mike.repository.datasources.CharactersPagingSource
import dev.mike.repository.fake.FakeCharactersRepository
import org.junit.Before

class CharactersSourceTest {

    private lateinit var charactersSource: CharactersPagingSource

    @Before
    fun setUp() {

        charactersSource = CharactersPagingSource(repository = FakeCharactersRepository())
    }
}
