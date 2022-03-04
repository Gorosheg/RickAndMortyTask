package gorosheg.red_mad_robot_test_task

import android.app.Application
import gorosheg.characters.di.charactersModule
import gorosheg.network.networkModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule, charactersModule, networkModule)
        }
    }
}