package com.io.financio.entrypoint;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
//TODO adicionar url do servidor do frontend
@CrossOrigin(origins = {"http://localhost:3000", "https://financio-fe-53b8b9d0c120.herokuapp.com/"})
public abstract class AbstractRestController {
}
