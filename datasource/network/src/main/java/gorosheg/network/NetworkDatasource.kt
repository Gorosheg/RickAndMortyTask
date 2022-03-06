package gorosheg.network

import gorosheg.myapplication.model.Character
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Episodes
import gorosheg.myapplication.model.Location
import gorosheg.network.model.*
import io.reactivex.Single

interface NetworkDatasource {
    fun getCharacters(): Single<List<Character>>

    fun getDescription(id: Int): Single<Description>

    fun getLocation(id: Int): Single<Location>

    fun getEpisodes(listEpisodes: List<Int>): Single<List<Episodes>>
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

    override fun getEpisodes(listEpisodes: List<Int>): Single<List<Episodes>> {
        return api.getEpisodes(listEpisodes).map {
            it.toSimpleEpisodes()
        }
    }

    private fun CharactersResponse.toSimpleCharacter(): List<Character> {
        return description.map {
            Character(it.id, it.name, it.image)
        }
    }

    private fun DescriptionResponse.toSimpleDescription(): Description {
        val episodesList = episodes.map {
            it.substringAfter("https://rickandmortyapi.com/api/episode/").toInt()
        }

        return Description(
            id = id,
            name = name,
            image = image,
            status = status,
            species = species,
            episodes = episodesList
        )
    }


    private fun LocationResponse.toSimpleLocation() =
        Location(name = name, dimension = dimension)

    private fun Episode.toSimpleEpisodes(): List<Episodes> {
        return results.map {
            Episodes(name = it.name, airDate = it.airDate)
        }
    }
}
