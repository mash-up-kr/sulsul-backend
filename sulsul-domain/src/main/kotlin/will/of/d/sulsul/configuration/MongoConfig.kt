package will.of.d.sulsul.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["will.of.d.sulsul.persistence.mongo"])
class MongoConfig