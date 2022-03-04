package gorosheg.characters.data

import gorosheg.myapplication.Character
import io.reactivex.Observable
import io.reactivex.Single

interface CharactersRepository {

    fun loadCharacters(): Single<List<Character>>

}