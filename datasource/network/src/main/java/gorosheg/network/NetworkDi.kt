package gorosheg.network

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<NetworkDatasource> {
        NetworkDatasourceImpl(get())
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/api/")
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .build()
            .create(CharactersApi::class.java)
    }

    single { RxJava2CallAdapterFactory.create() }

    single {
        OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }
}