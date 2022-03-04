package gorosheg.characters.data

import gorosheg.network.NetworkDatasource

class CharactersRepositoryImpl(
    private val network: NetworkDatasource
) : CharactersRepository {
}