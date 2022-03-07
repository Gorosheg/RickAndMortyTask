package gorosheg.characters.presentation

import androidx.lifecycle.ViewModel
import gorosheg.characters.domain.CharactersInteractor
import gorosheg.myapplication.model.Character
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

internal class CharactersViewModel(private val interactor: CharactersInteractor) : ViewModel() {

    private var disposable = CompositeDisposable()

    private val pageSubject = BehaviorSubject.create<Int>()

    private val _success = BehaviorSubject.create<List<Character>>()
    val success: Observable<List<Character>> = _success

    private val _error = PublishSubject.create<Throwable>()
    val error: Observable<Throwable> = _error

    init {
        disposable += pageSubject.distinctUntilChanged()
            .flatMap { page ->
                interactor.loadCharacters(page)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess {
                        if (page == 1) {
                            _success.onNext(it)
                        } else {
                            _success.onNext(_success.value!! + it)
                        }
                    }
                    .doOnError(_error::onNext)
                    .onErrorReturn { emptyList() }
                    .toObservable()
            }
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun loadCharacters(page: Int) {
        pageSubject.onNext(page)
    }
}