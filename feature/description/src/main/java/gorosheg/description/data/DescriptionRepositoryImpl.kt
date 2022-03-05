package gorosheg.description.data

import gorosheg.myapplication.model.Character
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Location
import gorosheg.network.NetworkDatasource
import io.reactivex.Single

class DescriptionRepositoryImpl(
    private val network: NetworkDatasource
) : DescriptionRepository {

    override fun loadDescription(id:Int): Single<Description> {
        return network.getDescription(id)
    }

    override fun loadLocation(id: Int): Single<Location> {
        return network.getLocation(id)
    }
}