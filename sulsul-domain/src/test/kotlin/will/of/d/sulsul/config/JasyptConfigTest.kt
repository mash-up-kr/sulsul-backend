package will.of.d.sulsul.config

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.jasypt.encryption.StringEncryptor
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import will.of.d.sulsul.SharedContext

/**
 * Thanks to muzily
 * Run > Edit Configurations > Configuration > Environment variables > 'JASYPT_ENCRYPTOR_PASSWORD={암호화키}' 입력
 */
@Disabled("암호화, 복호화 결과 확인을 위한 테스트이므로 비활성화")
class JasyptConfigTest(
    private val stringEncryptor: StringEncryptor
) : SharedContext() {

    private val plainText: String = "mongodb+srv://sulsul-dev:H6RSZEZkQNxvTyX9@sulsul-dev.aipo4xo.mongodb.net/test?retryWrites=true"

    @Test
    fun execute() {
        stringEncryptor.encrypt(plainText).let { encrypted ->
            println(encrypted)
            assertThat(stringEncryptor.decrypt(encrypted), `is`(plainText))
        }
    }
}
