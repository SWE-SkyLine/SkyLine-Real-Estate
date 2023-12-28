package com.example.SkyLine.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class SecTest {
    @GetMapping ("/sec")
    public String login(){
        return "hello security";
    }
}
