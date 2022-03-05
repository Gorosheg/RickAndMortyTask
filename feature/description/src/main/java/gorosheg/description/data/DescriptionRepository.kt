package gorosheg.description.data

import gorosheg.myapplication.model.Character
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Location
import io.reactivex.Single

internal interface DescriptionRepository {

    fun loadDescription(id:Int): Single<Description>
    fun loadLocation(id:Int): Single<Location>

}
