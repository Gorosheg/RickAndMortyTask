package gorosheg.characters.data

import gorosheg.myapplication.model.Character
import io.reactivex.Single

internal interface CharactersRepository {

    fun loadCharacters(page: Int): Single<List<Character>>

}