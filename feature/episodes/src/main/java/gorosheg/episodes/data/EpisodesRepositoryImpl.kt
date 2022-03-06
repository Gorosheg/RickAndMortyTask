package gorosheg.episodes.data

import gorosheg.myapplication.model.Episode
import gorosheg.network.NetworkDatasource
import io.reactivex.Single

class EpisodesRepositoryImpl(
    private val network: NetworkDatasource,
    private val CharacterId: Int
) : EpisodesRepository {

    override fun loadEpisodes(): Single<List<Episode>> {
        return network.getEpisodes(CharacterId)
    }
}