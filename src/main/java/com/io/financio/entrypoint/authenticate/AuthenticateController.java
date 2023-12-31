package com.io.financio.entrypoint.authenticate;

import com.io.financio.domain.model.request.LoginUserRequest;
import com.io.financio.domain.usecase.authenticate.AuthenticateUseCase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.io.financio.entrypoint.constants.RestConstants.AUTHENTICATE_URL;

@RestController
@RequestMapping
public class AuthenticateController {
    private final AuthenticateUseCase useCase;

    public AuthenticateController(AuthenticateUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(AUTHENTICATE_URL)
    public ResponseEntity<String> login(@RequestBody LoginUserRequest request) {
        var token = useCase.execute(request);

        return ResponseEntity
                .ok()
                .headers(buildHeader(token))
                .build();
    }

    private HttpHeaders buildHeader(String token) {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, token);

        return headers;
    }
}
