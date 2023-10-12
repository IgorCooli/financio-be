package com.io.financio.entrypoint.hello;

import com.io.financio.dataprovider.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.io.financio.entrypoint.constants.RestConstants.BASE_API;
import static com.io.financio.entrypoint.constants.RestConstants.HELLO_URL;

@RestController
@RequestMapping(BASE_API)
public class HelloController {

    private final UserRepository repository;

    public HelloController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = HELLO_URL)
    public ResponseEntity<String> hello() {
//        var document = new UserDocument("teste", "igor", "123", Set.of("generic.scope"), LocalDateTime.now(), LocalDateTime.now(), UserStatus.ACTIVE);
//        repository.save(document);

        return ResponseEntity.ok("Hello World!");
    }
}
