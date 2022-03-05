package gorosheg.myapplication.model

sealed class NetworkExceptions {

    object NotFound : NetworkExceptions()
    object Unknown : NetworkExceptions()

}
