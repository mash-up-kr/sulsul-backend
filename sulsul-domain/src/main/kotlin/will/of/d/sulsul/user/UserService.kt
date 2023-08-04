package will.of.d.sulsul.user

import org.springframework.stereotype.Service
import will.of.d.sulsul.discord.DiscordService
import will.of.d.sulsul.log.Logger

@Service
class UserService(
    private val userRepository: UserRepository,
    private val discordService: DiscordService
) {

    companion object : Logger

    fun getUser(kakaoUserId: Long): User? {
        return userRepository.findByKakaoUserId(kakaoUserId)?.let { User.from(it) }
    }

    fun signup(user: User): User {
        return userRepository.save(UserEntity.from(user))
            .let {
                User.from(it)
            }.also {
                discordService.send("새로운 술친구(${user.kakaoUserId}}) 등장!! 벌써 ${userRepository.count()}명 쨰야 +_+")
            }
    }

    fun upsert(user: User): User {
        return userRepository.findByKakaoUserId(user.kakaoUserId)
            ?.let { entity ->
                userRepository.save(
                    entity.copy(
                        kakaoUserId = user.kakaoUserId,
                        kakaoNickname = user.kakaoNickname,
                        drinkingLimit = user.drinkingLimit,
                        title = user.title?.text
                    )
                )
            }?.toVO()
            ?: userRepository.save(UserEntity.from(user)).toVO()
    }
}
