package gorosheg.red_mad_robot_test_task

import org.koin.dsl.module

val appModule = module {
   // single<QewNavigator> { get<NavigatorImpl>() }
    single<MainNavigator> { get<NavigatorImpl>() }
    single { NavigatorImpl() }
}