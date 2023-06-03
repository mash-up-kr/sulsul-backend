package will.of.d.sulsul.alcohol.enroll.repository

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import will.of.d.sulsul.alcohol.enroll.domain.EnrollAlcohol

interface EnrollAlcoholRepository : MongoRepository<EnrollAlcohol, ObjectId>
