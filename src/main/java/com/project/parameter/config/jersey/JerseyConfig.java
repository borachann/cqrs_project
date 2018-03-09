package com.project.parameter.config.jersey;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

/**
 * Created by p.ly on 3/2/2018.
 */
@Configuration
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

  @Value("${spring.jersey.application-path}")
  private String apiPath;

  @PostConstruct
  public void init() {
    this.configureSwagger();
  }

  public JerseyConfig() {

    this.registerEndpoints();
    register(JacksonFeature.class);
    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(
            false);
    scanner.addIncludeFilter(new AnnotationTypeFilter(Provider.class));
    scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
    this.registerClasses(
            scanner.findCandidateComponents("com.project.parameter.resource").stream()
                    .map(beanDefinition -> ClassUtils.resolveClassName(
                            beanDefinition.getBeanClassName(),
                            this.getClassLoader()))
                    .collect(Collectors.toSet()));
  }

  private void registerEndpoints() {
    this.register(WadlResource.class);
  }

  private void configureSwagger() {
    this.register(ApiListingResource.class);
    this.register(SwaggerSerializers.class);

    BeanConfig config = new BeanConfig();
    config.setConfigId("com-project-parameter");
    config.setTitle("Parameter");
    config.setVersion("v1");
    config.setSchemes(new String[]{"http", "https"});
    config.setBasePath(apiPath);
    config.setResourcePackage("com.project.parameter.resource");
    config.setPrettyPrint(true);
    config.setScan(true);
  }
}