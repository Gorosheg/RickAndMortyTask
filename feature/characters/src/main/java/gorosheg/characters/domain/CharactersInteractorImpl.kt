package gorosheg.characters.domain

import gorosheg.characters.data.CharactersRepository
import gorosheg.myapplication.model.Character
import io.reactivex.Single

internal class CharactersInteractorImpl(private val repository: CharactersRepository) : CharactersInteractor {

    override fun loadCharacters(page: Int): Single<List<Character>> {
        return repository.loadCharacters(page)
    }

}