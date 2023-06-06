package will.of.d.sulsul.user

data class User(
  val kakaoUserId: Long,
)  {
    companion object {
        fun from(entity: UserEntity) : User = User(
            kakaoUserId = entity.kakaoUserId,
        )
    }
}