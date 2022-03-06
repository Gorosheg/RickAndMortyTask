package gorosheg.episodes.domain

import gorosheg.myapplication.model.Character
import gorosheg.myapplication.model.Episodes
import io.reactivex.Single

interface EpisodesInteractor {

    fun getEpisodes(): Single<List<Episodes>>

}