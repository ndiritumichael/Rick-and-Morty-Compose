package dev.mike.network.utils

import kotlinx.serialization.json.Json

const val BASE_URL = "https://rickandmortyapi.com/api/"
val json = Json { ignoreUnknownKeys = true }

const val CHARACTERSPATH = "character"

const val EPISODESPATH = "episode"
const val EPISODEID = "episode/"
