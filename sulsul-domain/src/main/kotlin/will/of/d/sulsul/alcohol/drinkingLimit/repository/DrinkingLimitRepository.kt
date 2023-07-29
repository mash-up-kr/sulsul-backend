package will.of.d.sulsul.alcohol.drinkingLimit.repository

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimitEntity

interface DrinkingLimitRepository : MongoRepository<DrinkingLimitEntity, ObjectId> {
    fun findFirstByKakaoUserIdOrderByCreatedAtDesc(kakaoUserId: Long): DrinkingLimitEntity?
}
