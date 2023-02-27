package com.kokoo.aurora;

import com.kokoo.aurora.service.SplitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SplitController {

    private final SplitService splitService;

    @GetMapping("/read")
    public Object read() {
        return splitService.read();
    }

    @GetMapping("/write")
    public Object write() {
        return splitService.write();
    }
}
