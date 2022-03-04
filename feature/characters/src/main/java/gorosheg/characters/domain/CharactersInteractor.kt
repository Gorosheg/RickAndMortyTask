package gorosheg.characters.domain

import gorosheg.myapplication.Character
import io.reactivex.Observable
import io.reactivex.Single

interface CharactersInteractor {

    fun loadCharacters(): Single<List<Character>>

}