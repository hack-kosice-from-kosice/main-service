package io.hackkosicefromkosice.mainservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class VersionController {

    @GetMapping("/version-info")
    public Map<String, Object> index() {
        return new HashMap<String, Object>() {{
           put("version", 1);
        }};
    }

}
