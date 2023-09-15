package com.io.financio.entrypoint.authenticate;

import com.io.financio.domain.model.request.LoginUserRequest;
import com.io.financio.domain.usecase.authenticate.AuthenticateUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.io.financio.entrypoint.constants.RestConstants.BASE_API;
import static com.io.financio.entrypoint.constants.RestConstants.LOGIN_URL;

@RestController
@RequestMapping(BASE_API)
public class AuthenticateController {

    private final AuthenticateUseCase useCase;

    public AuthenticateController(AuthenticateUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(LOGIN_URL)
    public ResponseEntity<String> login(@RequestBody LoginUserRequest request) {

        var response = useCase.execute(request);

        return ResponseEntity.ok(response);
    }

}
