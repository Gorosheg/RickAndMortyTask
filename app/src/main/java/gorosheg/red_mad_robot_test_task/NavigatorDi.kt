package gorosheg.red_mad_robot_test_task

import gorosheg.myapplication.CharacterNavigator
import org.koin.dsl.module

val appModule = module {
    single<MainNavigator> { get<NavigatorImpl>() }
    single<CharacterNavigator> { get<NavigatorImpl>() }
    single { NavigatorImpl() }
}