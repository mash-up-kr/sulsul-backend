package will.of.d.sulsul.user

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
data class UserEntity(
    @Id
    val id: ObjectId? = null,
    val kakaoUserId: Long,
    val kakaoNickname: String
) {
    companion object {
        fun from(user: User) = UserEntity(
            kakaoUserId = user.kakaoUserId,
            kakaoNickname = user.kakaoNickname
        )
    }
}
