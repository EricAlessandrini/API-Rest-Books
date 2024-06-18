package com.ega.books.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Book Journal - API",
                description = "API Rest to keep track of read books",
                contact = @Contact(
                        name = "Eric Alessandrini",
                        url = "www.linkedin.com/in/eric-alessandrini29",
                        email = "eric.alessandrini1@gmail.com"
                )
        )
)
public class SwaggerConfig {
}
