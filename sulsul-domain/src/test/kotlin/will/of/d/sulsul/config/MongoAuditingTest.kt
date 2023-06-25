package will.of.d.sulsul.config

import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import will.of.d.sulsul.SharedContext
import java.time.LocalDateTime

class MongoAuditingTest(
    private val exampleRepository: ExampleRepository
) : SharedContext() {

    @AfterEach
    fun deleteAll() {
        exampleRepository.deleteAll()
    }

    @Test
    @DisplayName("MongoDB Auditing 확인")
    fun checkMongoDBAuditing() {
        // given
        var exampleDocument = ExampleDocument(name = "name1")

        // when
        exampleDocument = exampleRepository.save(exampleDocument)
        exampleDocument.name = "name2"

        exampleDocument = exampleRepository.save(exampleDocument)

        // then
        assertThat(exampleDocument.createdDate).isNotNull
        assertThat(exampleDocument.updatedDate).isNotNull
        assertThat(exampleDocument.createdDate).isNotEqualTo(exampleDocument.updatedDate)
    }
}

@Document
data class ExampleDocument(
    @Id
    val id: ObjectId? = null,
    var name: String,

    @CreatedDate
    val createdDate: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    val updatedDate: LocalDateTime = LocalDateTime.now()
)

interface ExampleRepository : MongoRepository<ExampleDocument, ObjectId>
