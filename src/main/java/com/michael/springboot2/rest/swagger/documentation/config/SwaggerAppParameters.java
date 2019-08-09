package com.michael.springboot2.rest.swagger.documentation.config;

import springfox.documentation.service.Parameter;

import java.util.List;

public interface SwaggerAppParameters {

    List<Parameter> globalOperationParameters();
}
