package will.of.d.sulsul.holder

import will.of.d.sulsul.user.User

object UserContextHolder {
    private val holder = ThreadLocal<User>()

    fun get(): User? = holder.get()
    fun set(user: User) = holder.set(user)
    fun remove() = holder.remove()
}
