package will.of.d.sulsul.exception

class SulsulValidException(
    private val msg: String,
    private val e: Throwable? = null
) : RuntimeException(msg, e)
