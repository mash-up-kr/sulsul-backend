package will.of.d.sulsul.title.domain

import org.springframework.stereotype.Service
import will.of.d.sulsul.log.Logger

@Service
class TitleService {

    companion object : Logger

    fun getTitles(): List<Title> {
        return Title.values().toList().also { log.debug("Return titles") }
    }
}
