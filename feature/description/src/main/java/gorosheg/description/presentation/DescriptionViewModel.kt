package gorosheg.description.presentation

import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.HttpException
import gorosheg.description.data.DescriptionRepository
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Location
import gorosheg.myapplication.model.NetworkExceptions
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

internal class DescriptionViewModel(private val repository: DescriptionRepository) : ViewModel() {

    private val _error = PublishSubject.create<NetworkExceptions>()
    val error: Observable<NetworkExceptions> = _error

    fun loadDescription(): Single<Description> {
        return repository.loadDescription()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(::handleError)
            .onErrorReturn { Description.empty() }
    }

    fun loadLocation(): Single<Location> {
        return repository.loadLocation()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(::handleError)
            .onErrorReturn { Location.empty() }
    }

    private fun handleError(throwable: Throwable) {
        _error.onNext(
            when (throwable) {
                is HttpException -> NetworkExceptions.NotFound
                else -> NetworkExceptions.Unknown
            }
        )
    }

}
