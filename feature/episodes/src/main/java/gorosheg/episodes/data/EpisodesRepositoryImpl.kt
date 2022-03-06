package gorosheg.episodes.data

import gorosheg.myapplication.model.Episodes
import gorosheg.network.NetworkDatasource
import io.reactivex.Single

class EpisodesRepositoryImpl(
    private val network: NetworkDatasource,
    private val listEpisodes: List<String>
) : EpisodesRepository {

    override fun loadEpisodes(): Single<List<Episodes>> {
        return network.getEpisodes(listEpisodes)
    }
}