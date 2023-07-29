package will.of.d.sulsul.user

import will.of.d.sulsul.title.domain.Title

data class User(
    val kakaoUserId: Long,
    val kakaoNickname: String,
    val drinkingLimit: Int? = null,
    val title: Title? = null
) {
    companion object {
        fun from(entity: UserEntity): User = User(
            kakaoUserId = entity.kakaoUserId,
            kakaoNickname = entity.kakaoNickname,
            drinkingLimit = entity.drinkingLimit,
            title = entity.title?.let { Title.from(it) }
        )
    }
}
