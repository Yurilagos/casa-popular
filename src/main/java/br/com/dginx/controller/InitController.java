package br.com.dginx.controller;

import br.com.dginx.controller.spec.InitControllerSpec;
import br.com.dginx.service.InitService;
import br.com.dginx.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/init")
@RequiredArgsConstructor
public class InitController implements InitControllerSpec {

    private final InitService initService;

    @Override
    @GetMapping()
    public ResponseEntity<String> init() {
        initService.generateData();
        return ResponseEntity.ok().body("OK");
    }
}
