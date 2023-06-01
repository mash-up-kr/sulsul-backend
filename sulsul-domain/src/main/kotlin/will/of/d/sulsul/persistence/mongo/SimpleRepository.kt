package will.of.d.sulsul.persistence.mongo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

// TODO: 기능 개발 시작 후 지우기
interface SimpleRepository : MongoRepository<SimpleEntity, ObjectId>