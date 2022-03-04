package gorosheg.network

import gorosheg.network.model.RickAndMortyResponse
import io.reactivex.Single

interface NetworkDatasource {
    //fun loadCharacters(): Single<RickAndMortyResponse>
    /* fun loadDescription(characterName: String): Single<RickAndMortyResponse>
     fun loadEpisodes(characterName: String): Single<RickAndMortyResponse>*/
}


internal class NetworkDatasourceImpl(private val api: CharactersApi) : NetworkDatasource {

  /*  override fun loadCharacters(): Single<RickAndMortyResponse> {

    }*/

}