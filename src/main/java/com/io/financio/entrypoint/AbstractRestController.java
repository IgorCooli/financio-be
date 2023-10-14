package com.io.financio.entrypoint;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
//TODO adicionar url do servidor do frontend
@CrossOrigin(origins = {"http://localhost:3000", "https://financio-fe-9626ae645fb0.herokuapp.com/"})
public abstract class AbstractRestController {
}
