package com.betrybe.agrix.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.stereotype.Component;

@Component
public class DocAuthCustomizer implements OpenApiCustomizer {

  public final String SCHEME_NAME = "Bearer Auth";

  @Override
  public void customise(OpenAPI openApi) {

    openApi.getComponents().addSecuritySchemes(SCHEME_NAME,
        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));
    openApi.addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME));

    openApi.info(new Info()
        .title("Agrix API")
        .version("1.0")
        .description("Documentação da API Agrix para gestão agrícola com autenticação via JWT."));
  }
}
