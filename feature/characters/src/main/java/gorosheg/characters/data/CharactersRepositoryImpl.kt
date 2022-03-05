package gorosheg.characters.data

import gorosheg.myapplication.model.Character
import gorosheg.network.NetworkDatasource
import io.reactivex.Single

class CharactersRepositoryImpl(
    private val network: NetworkDatasource
) : CharactersRepository {

    override fun loadCharacters(): Single<List<Character>> {
        return network.loadCharacters()
    }
}