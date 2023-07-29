package will.of.d.sulsul.user

data class User(
    val kakaoUserId: Long,
    val kakaoNickname: String
) {
    companion object {
        fun from(entity: UserEntity): User = User(
            kakaoUserId = entity.kakaoUserId,
            kakaoNickname = entity.kakaoNickname
        )
    }
}
