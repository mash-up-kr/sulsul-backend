package will.of.d.sulsul.alcohol.enroll.repository

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import will.of.d.sulsul.alcohol.enroll.domain.EnrollAlcohol

@Repository
interface EnrollAlcoholRepository : MongoRepository<EnrollAlcohol, ObjectId>
