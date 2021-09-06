package com.example.demo.src.restrict;

import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class RestrictProvider {

    @Autowired
    private final RestrictDao restrictDao;
    @Autowired
    private final JwtService jwtService;
}
