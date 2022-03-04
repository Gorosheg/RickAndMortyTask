package gorosheg.characters.presentation

import androidx.lifecycle.ViewModel
import gorosheg.characters.domain.CharactersInteractor
import gorosheg.myapplication.Character
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharactersViewModel(private val interactor: CharactersInteractor) : ViewModel() {

    fun loadCharacters(): Single<List<Character>> {
        return interactor.loadCharacters()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

}