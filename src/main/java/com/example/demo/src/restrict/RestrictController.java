package com.example.demo.src.restrict;

import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restricts")
public class RestrictController {

    @Autowired
    private final RestrictProvider restrictProvider;
    @Autowired
    private final RestrictService restrictService;
    @Autowired
    private final JwtService jwtService;


}
