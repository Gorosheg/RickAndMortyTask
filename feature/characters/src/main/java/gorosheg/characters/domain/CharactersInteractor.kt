package gorosheg.characters.domain

import gorosheg.myapplication.model.Character
import io.reactivex.Single

interface CharactersInteractor {

    fun loadCharacters(page: Int): Single<List<Character>>

}