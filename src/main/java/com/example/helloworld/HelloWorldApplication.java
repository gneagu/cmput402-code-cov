package com.example.helloworld;

import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.resources.MathAdditionResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
  public static void main(String[] args) throws Exception {
    new HelloWorldApplication().run(args);
  }

  @Override
  public String getName() {
    return "hello-world";
  }

  @Override
  public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    // nothing to do yet
  }

  @Override
  public void run(HelloWorldConfiguration configuration,
                  Environment environment) {
    // Hello world resource
    final HelloWorldResource helloWorldResource = new HelloWorldResource(
        configuration.getTemplate(),
        configuration.getDefaultName()
    );

    // Math addition resource
    final MathAdditionResource mathAdditionResource = new MathAdditionResource();

    // Healthchecks
    final TemplateHealthCheck healthCheck =
        new TemplateHealthCheck(configuration.getTemplate());
    environment.healthChecks().register("template", healthCheck);

    // Resources (paths)
    environment.jersey().register(helloWorldResource);
    environment.jersey().register(mathAdditionResource);
  }


}