package com.android.take_home.repository

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.android.take_home.data.local.FileCacheManager
import com.android.take_home.data.remote.RetrofitApiService
import com.android.take_home.data.repository.MatchesRepositoryImpl
import com.android.take_home.domain.model.match.Match
import com.android.take_home.domain.util.Resource
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import io.mockk.coEvery
import io.mockk.mockk
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.ListSerializer
import org.junit.Test
import java.io.File

class MatchesRepositoryTest {

    val sampleMatch = Match(
        id = 1,
        homeTeam = "Home",
        awayTeam = "Away",
        homeTeamAvatar = "",
        awayTeamAvatar = "",
        date = "2025-12-05 23:01",
        status = "PRE_MATCH",
        currentTime = null,
        result = null,
        sportId = 1,
        competitionId = 1
    )


    private fun createTempFileCache(initial: List<Match>? = null): FileCacheManager<List<Match>> {
        val tempFile = File(System.getProperty("java.io.tmpdir"), "matches_test.json")
        val fileCache = FileCacheManager(tempFile, ListSerializer(Match.serializer()))
        runBlocking {
            if (initial != null) fileCache.write(initial)
        }
        return fileCache
    }

    @Test
    fun `emits cached then remote data`() = runTest {
        // keširani podaci
        val cached = listOf(sampleMatch)
        // podaci sa API
        val remote = listOf(sampleMatch.copy(id = 2))

        val fakeCache = createTempFileCache(cached)
        val api = mockk<RetrofitApiService>()

        // Mock API poziv
        coEvery { api.getMatches() } returns remote

        val repo = MatchesRepositoryImpl(api, fakeCache)

        // Skupljamo sve emitove Flow-a
        val emissions = repo.fetchMatches().toList()

        // Prvi emit = keš
        assertThat(emissions[0]).isInstanceOf(Resource.Success::class.java)
        assertThat((emissions[0] as Resource.Success).data).isEqualTo(cached)

        // Drugi emit = remote podaci
        assertThat(emissions[1]).isInstanceOf(Resource.Success::class.java)
        assertThat((emissions[1] as Resource.Success).data).isEqualTo(remote)

        // Fake cache je ažuriran
        val cacheContent = runBlocking { fakeCache.read() }
        assertThat(cacheContent).isEqualTo(remote)
    }

    @Test
    fun `emits cached then error if remote fails`() = runTest {
        val cached = listOf(sampleMatch)
        val fakeCache = createTempFileCache(cached)
        val api = mockk<RetrofitApiService>()

        // Simuliramo grešku sa API-ja
        coEvery { api.getMatches() } throws RuntimeException("Internal Server Error")

        val repo = MatchesRepositoryImpl(api, fakeCache)

        val emissions = repo.fetchMatches().toList()

        // Prvi emit = keš
        assertThat(emissions[0]).isInstanceOf(Resource.Success::class.java)
        assertThat((emissions[0] as Resource.Success).data).isEqualTo(cached)

        // Drugi emit = error
        assertThat(emissions[1]).isInstanceOf(Resource.Error::class.java)
        assertThat((emissions[1] as Resource.Error).cached).isEqualTo(cached)
    }
}