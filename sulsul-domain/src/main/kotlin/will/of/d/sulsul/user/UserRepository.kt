package will.of.d.sulsul.user

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<UserEntity, ObjectId> {
    fun findByKakaoUserId(kakaoUserId: Long) : UserEntity?
}