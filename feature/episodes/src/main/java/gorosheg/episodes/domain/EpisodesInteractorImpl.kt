package gorosheg.episodes.domain

import gorosheg.episodes.data.EpisodesRepository
import gorosheg.myapplication.model.Episodes
import io.reactivex.Single

internal class EpisodesInteractorImpl(private val repository: EpisodesRepository) : EpisodesInteractor {

    override fun getEpisodes(): Single<List<Episodes>> {
        return repository.loadEpisodes()
    }

}