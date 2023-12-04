package com.example.firstservice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/first-service")
@RequiredArgsConstructor
public class FirstServiceController {

    private final Environment env;

    @GetMapping("/welcome") // develop
    public String welcome(){
        return "Welcome to the First service.";
    }

    @GetMapping("/message") // filter-java, filter-yaml
    public String message(@RequestHeader(name = "first-request", required = false) String header){
        log.info("header: {}", header);
        return "Welcome to the First service.";
    }

    @GetMapping("/check") // Custom Filter, Global Filter, lb
    public String check(HttpServletRequest request){
        // 포트 가져 오기 1
        log.info("Server port = {}", request.getServerPort());

        // 포트 가져 오기 2
        var port = env.getProperty("local.server.port");
        return String.format("Hi, there, This is a message from First Service on Port %s", port);
    }
}
