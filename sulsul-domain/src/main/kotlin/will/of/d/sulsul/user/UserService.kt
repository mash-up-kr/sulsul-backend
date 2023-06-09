package will.of.d.sulsul.user

import org.springframework.stereotype.Service
import will.of.d.sulsul.log.Logger

@Service
class UserService(
    private val userRepository: UserRepository
) {

    companion object : Logger

    fun getUser(kakaoUserId: Long): User? {
        return userRepository.findByKakaoUserId(kakaoUserId)?.let { User.from(it) }
    }

    fun signup(kakaoUserId: Long): User {
        // TODO: 회원가입 시 Discord bot 알림
        return userRepository.save(UserEntity(kakaoUserId = kakaoUserId))
            .let {
                User.from(it)
            }.also {
                log.info("signup $kakaoUserId")
            }
    }
}
