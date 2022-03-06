package gorosheg.episodes.presentation

import androidx.lifecycle.ViewModel
import gorosheg.episodes.domain.EpisodesInteractor
import gorosheg.myapplication.model.Episodes
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class EpisodesViewModel(private val interactor: EpisodesInteractor) : ViewModel() {

    private val _error = PublishSubject.create<Throwable>()
    val error: Observable<Throwable> = _error

    fun getEpisodes(): Single<List<Episodes>> {
        return interactor.getEpisodes()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(_error::onNext)
            .onErrorReturn { emptyList() }
    }
}