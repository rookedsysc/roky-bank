package org.rookedsysc.accounts.accounts.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
        info = Info(
                title = "Accounts API",
                contact = Contact(
                        name = "RookedSysc",
                        email = "rookedsysc36@gmail.com"
                ),
                license = License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                ),
        )
)
class SwaggerConfig