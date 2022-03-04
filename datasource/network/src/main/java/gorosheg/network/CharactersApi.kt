package gorosheg.network

import gorosheg.network.model.RickAndMortyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CharactersApi {

    @GET("character/?")
    fun getAll(
        @Query("page") page: Int
    ): Single<RickAndMortyResponse>

    @GET("character/{id}")
    fun getCharacter(
        @Path("id") id: Int
    ): Single<RickAndMortyResponse>

    @GET("location/{id}")
    fun getLocation(
        @Path("id") id: Int
    ): Single<RickAndMortyResponse>

    @GET("character/?")
    fun getEpisodes(
        @Query("name") name: String
    ): Single<RickAndMortyResponse>
}