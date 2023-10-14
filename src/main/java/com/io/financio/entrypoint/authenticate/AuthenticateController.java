package com.io.financio.entrypoint.authenticate;

import com.io.financio.domain.model.request.LoginUserRequest;
import com.io.financio.domain.model.response.AuthResponse;
import com.io.financio.domain.usecase.authenticate.AuthenticateUseCase;
import com.io.financio.entrypoint.AbstractRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.io.financio.entrypoint.constants.RestConstants.AUTHENTICATE_URL;

@RestController
@RequestMapping
public class AuthenticateController extends AbstractRestController {
    private final AuthenticateUseCase useCase;

    public AuthenticateController(AuthenticateUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(value = AUTHENTICATE_URL)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginUserRequest request) {
        var response = useCase.execute(request);

        return ResponseEntity
                .ok()
                .body(response);
    }
}
