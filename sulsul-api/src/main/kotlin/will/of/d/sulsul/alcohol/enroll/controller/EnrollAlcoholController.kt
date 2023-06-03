package will.of.d.sulsul.alcohol.enroll.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.alcohol.enroll.dto.request.EnrollAlcoholSaveDto
import will.of.d.sulsul.alcohol.enroll.dto.response.EnrollAlcoholResponseDto
import will.of.d.sulsul.alcohol.enroll.service.EnrollAlcoholService

@Tag(name = "주량등록 컨트롤러")
@RestController
@RequestMapping("/api/enroll")
class EnrollAlcoholController(
    private val enrollAlcoholService: EnrollAlcoholService
) {

    @Operation(summary = "주량 등록 API", description = "로그인 시, 주량을 등록할 때 호출하는 API")
    @PostMapping("")
    fun saveEnrollAlcohol(@RequestBody saveDto: EnrollAlcoholSaveDto): EnrollAlcoholResponseDto {
        return enrollAlcoholService.save(saveDto)
    }
}
