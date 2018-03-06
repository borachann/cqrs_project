package com.project.parameter.config.webmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by p.ly on 3/1/2018.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/doc").setViewName(
      "forward:/index.html");
  }
}