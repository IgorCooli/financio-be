package com.io.financio.entrypoint.registeruser;

import com.io.financio.domain.model.request.RegisterUserRequest;
import com.io.financio.domain.usecase.registeruser.RegisterUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.io.financio.entrypoint.constants.RestConstants.REGISTER_URL;

@RestController
@RequestMapping
public class RegisterUserController {

    private final RegisterUserUseCase useCase;

    public RegisterUserController(RegisterUserUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(REGISTER_URL)
    public ResponseEntity<Void> execute(@RequestBody RegisterUserRequest request) {
        useCase.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
