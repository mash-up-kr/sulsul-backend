package will.of.d.sulsul.user

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import will.of.d.sulsul.title.domain.Title

@Document("user")
data class UserEntity(
    @Id
    val id: ObjectId? = null,
    val kakaoUserId: Long,
    val kakaoNickname: String,
    val drinkingLimit: Int? = null, // alcohol
    val title: String? = null // title.text
) {
    companion object {
        fun from(user: User) = UserEntity(
            kakaoUserId = user.kakaoUserId,
            kakaoNickname = user.kakaoNickname,
            drinkingLimit = user.drinkingLimit,
            title = user.title?.text
        )
    }

    fun toVO() = User(
        kakaoUserId = kakaoUserId,
        kakaoNickname = kakaoNickname,
        drinkingLimit = drinkingLimit,
        title = title?.let { Title.from(it) }
    )
}
