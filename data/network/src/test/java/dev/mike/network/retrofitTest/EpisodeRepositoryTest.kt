package dev.mike.network.retrofitTest

import dev.mike.network.BaseTest
import dev.mike.network.SampleData
import dev.mike.network.data.RemoteEpisodesImpl
import dev.mike.network.source.IEpisodesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EpisodeRepositoryTest : BaseTest() {
    lateinit var episodesRepository: IEpisodesRepository

    @Before
    override fun setup() {
        super.setup()
        episodesRepository = RemoteEpisodesImpl(apiService)
    }
    @Test
    fun `given a valid episode number returns episodes`() = runTest {
        val actualepisode = episodesRepository.getoneEpisode("28")

        assertThat(actualepisode, `is`(SampleData.episode28))
    }
}
