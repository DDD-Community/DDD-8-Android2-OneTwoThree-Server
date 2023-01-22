package com.ddd.onetwothree.controller

import com.ddd.onetwothree.controller.response.ResponseEntity
import com.ddd.onetwothree.controller.response.StretchingStartResponse
import com.ddd.onetwothree.type.StretchingType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stretching")
class StretchingController {

    @GetMapping("/start")
    fun startStretching(): ResponseEntity<StretchingStartResponse> {
        val resp = StretchingStartResponse(StretchingType.values().random())
        return ResponseEntity.ok(resp)
    }

    @GetMapping
    fun findAllStretching(): ResponseEntity<List<StretchingType>> {
        return ResponseEntity.ok(StretchingType.values().toList())
    }

}