package gorosheg.episodes.data

import gorosheg.myapplication.model.Episode
import io.reactivex.Single

internal interface EpisodesRepository {

    fun loadEpisodes(): Single<List<Episode>>

}