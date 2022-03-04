package gorosheg.characters.domain

import gorosheg.characters.data.CharactersRepository
import gorosheg.myapplication.Character
import io.reactivex.Single

class CharactersInteractorImpl(private val repository: CharactersRepository) :
    CharactersInteractor {

    override fun loadCharacters(): Single<List<Character>> {
        return repository.loadCharacters()
    }

}