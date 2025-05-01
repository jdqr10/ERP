package com.erp.ERP.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/protected")
@RequiredArgsConstructor
public class routeProtectedController {

    @PostMapping(value = "protect")
    public String welcome () {
        return "Welcome from protected endpoint";
    }

}