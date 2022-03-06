package gorosheg.episodes.data

import gorosheg.myapplication.model.Episodes
import io.reactivex.Single

internal interface EpisodesRepository {

    fun loadEpisodes(): Single<List<Episodes>>

}