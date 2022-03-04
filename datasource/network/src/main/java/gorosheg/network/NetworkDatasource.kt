package gorosheg.network

import gorosheg.myapplication.Character
import gorosheg.network.model.RickAndMortyResponse
import io.reactivex.Single

interface NetworkDatasource {
    fun loadCharacters(): Single<List<Character>>
}

internal class NetworkDatasourceImpl(private val api: CharactersApi) : NetworkDatasource {

    override fun loadCharacters(): Single<List<Character>> {
        return api.getAll(1).map { it.toSimpleCharacter() }
    }

    private fun RickAndMortyResponse.toSimpleCharacter(): List<Character> {
        return info.map {
            Character(it.id, it.name, it.image)
        }
    }
}