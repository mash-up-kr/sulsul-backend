package will.of.d.sulsul

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest(classes = arrayOf(SulsulApplication::class, SulsulDomainApplication::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SulsulApplicationTests {

    @Test
    fun contextLoads() {
    }
}
