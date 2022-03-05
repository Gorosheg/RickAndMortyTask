package gorosheg.network

import gorosheg.myapplication.model.Character
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Location
import gorosheg.network.model.DescriptionResponse
import gorosheg.network.model.CharactersResponse
import gorosheg.network.model.LocationResponse
import io.reactivex.Single

interface NetworkDatasource {
    fun loadCharacters(): Single<List<Character>>

    fun getDescription(id: Int): Single<Description>

    fun getLocation(id: Int): Single<Location>

}

internal class NetworkDatasourceImpl(private val api: CharactersApi) : NetworkDatasource {

    override fun loadCharacters(): Single<List<Character>> {
        return api.getAll(1).map { it.toSimpleCharacter() }
    }

    override fun getDescription(id: Int): Single<Description> {
        return api.getCharacter(id).map { it.toSimpleDescription() }
    }

    override fun getLocation(id: Int): Single<Location> {
        return api.getLocation(id).map { it.toSimpleLocation() }
    }

    private fun CharactersResponse.toSimpleCharacter(): List<Character> {
        return info.map {
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

    private fun LocationResponse.toSimpleLocation(): Location {
        return Location(
            name = name,
            dimension = dimension
        )
    }
}