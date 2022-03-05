package gorosheg.characters.data

import gorosheg.myapplication.model.Character
import io.reactivex.Single

internal interface CharactersRepository {

    fun loadCharacters(): Single<List<Character>>

}