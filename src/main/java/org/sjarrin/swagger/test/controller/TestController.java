package org.sjarrin.swagger.test.controller;

import org.sjarrin.swagger.test.annotations.AuthLevel2;
import org.sjarrin.swagger.test.annotations.AuthLevel3;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {

    @GetMapping("check")
    @AuthLevel2
    public String check() {
        return "OK";
    }

    @GetMapping("check2")
    @AuthLevel2(issuers = {"issuer1", "issuer2"})
    @AuthLevel3(issuers = {"issuer4", "issuer5"})
    public String check2() {
        return "OK";
    }
}
