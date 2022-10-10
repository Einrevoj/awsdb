package jcano.awspractice.controller;

import jcano.awspractice.dto.PopularDTO;
import jcano.awspractice.model.PopularRequest;
import jcano.awspractice.service.PopularService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/popular")
@RequiredArgsConstructor
public class PopularController {

    private final PopularService popularService;

    @GetMapping("/getAll")
    public List<PopularDTO> getAllPopularProducts() {
        return popularService.getAllPopularProducts();
    }

    @PutMapping("/add")
    public List<PopularDTO> addPopular(@RequestBody PopularRequest popularRequest) {
        return popularService.addPopular(popularRequest);
    }

    @DeleteMapping("/delete/{productId}")
    public List<PopularDTO> deletePopular(@PathVariable UUID productId) {
        return popularService.deletePopular(productId);
    }

}
