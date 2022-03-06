package gorosheg.network

import gorosheg.myapplication.model.Character
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Episode
import gorosheg.myapplication.model.Location
import gorosheg.network.model.*
import io.reactivex.Single

interface NetworkDatasource {
    fun getCharacters(): Single<List<Character>>

    fun getDescription(id: Int): Single<Description>

    fun getLocation(id: Int): Single<Location>

    fun getEpisodes(id: Int): Single<List<Episode>>
}

internal class NetworkDatasourceImpl(private val api: CharactersApi) : NetworkDatasource {

    override fun getCharacters(): Single<List<Character>> {
        return api.getAll(1).map { it.toSimpleCharacter() }
    }

    override fun getDescription(id: Int): Single<Description> {
        return api.getCharacter(id).map { it.toSimpleDescription() }
    }

    override fun getLocation(id: Int): Single<Location> {
        return api.getLocation(id).map { it.toSimpleLocation() }
    }

    override fun getEpisodes(id: Int): Single<List<Episode>> {
        return api.getEpisodes().map {
            it.toSimpleEpisodes(id)
        }
    }

    private fun CharactersResponse.toSimpleCharacter(): List<Character> {
        return description.map {
            Character(it.id, it.name, it.image)
        }
    }

    private fun DescriptionResponse.toSimpleDescription(): Description {

        return Description(
            id = id,
            name = name,
            image = image,
            status = status,
            species = species
        )
    }

    private fun LocationResponse.toSimpleLocation() =
        Location(name = name, dimension = dimension)

    private fun EpisodesResponse.toSimpleEpisodes(id: Int): List<Episode> {
        return episodes.filterByCharacterId(id)
            .map { episodeResponse -> episodeResponse.toEpisode() }
    }

    private fun List<EpisodeResponse>.filterByCharacterId(id: Int): List<EpisodeResponse> {
        return filter { episodeResponse ->
            episodeResponse.characters.containsCharacterId(id)
        }
    }

    private fun List<String>.containsCharacterId(id: Int): Boolean {
        return any { characterId ->
            characterId.getCharacterId() == id
        }
    }

    private fun String.getCharacterId(): Int {
        return substringAfter("https://rickandmortyapi.com/api/character/").toInt()
    }

    private fun EpisodeResponse.toEpisode(): Episode {
        return Episode(
            name = name,
            airDate = airDate,
        )
    }
}
