package will.of.d.sulsul.exception

class UserNotFoundException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
}
