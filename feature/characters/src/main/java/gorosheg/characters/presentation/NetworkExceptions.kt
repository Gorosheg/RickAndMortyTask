package gorosheg.characters.presentation

internal sealed class NetworkExceptions {

    object NotFound : NetworkExceptions()
    object Unknown : NetworkExceptions()

}
