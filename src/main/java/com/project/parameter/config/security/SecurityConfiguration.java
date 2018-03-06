package com.project.parameter.config.security;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Chann Bora (b.chann@gl-f.com) on 9/14/2017.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.vaadin.spring.security.annotation.EnableVaadinSharedSecurity;
import org.vaadin.spring.security.config.VaadinSharedSecurityConfiguration;
import org.vaadin.spring.security.shared.VaadinLogoutHandler;
import org.vaadin.spring.security.shared.VaadinRedirectLogoutHandler;
import org.vaadin.spring.security.web.VaadinRedirectStrategy;

@KeycloakConfiguration
@EnableVaadinSharedSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {

    @Inject
    ObjectMapper objectMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        final SimpleAuthorityMapper grantedAuthoritiesMapper = new SimpleAuthorityMapper();
        grantedAuthoritiesMapper.setConvertToUpperCase(true);
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthoritiesMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.httpBasic().disable();
        http.formLogin().disable();
        http.anonymous().disable();
        http.csrf().disable();
        http.headers()
          .frameOptions().sameOrigin().and()
          .authorizeRequests()
          .antMatchers("/vaadinServlet/UIDL/**").permitAll()
          .antMatchers("/vaadinServlet/HEARTBEAT/**").permitAll()
          .antMatchers("/h2-console/**").permitAll()
          .antMatchers("/api/**").authenticated()
          .anyRequest().authenticated(); //.hasRole("ADMINISTRATOR");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/VAADIN/**");
    }

    @Bean
    public KeycloakConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
    }

    @Bean
    protected SessionRegistry buildSessionRegistry() {
        return new SessionRegistryImpl();
    }


    @Bean(name = VaadinSharedSecurityConfiguration.VAADIN_LOGOUT_HANDLER_BEAN)
    VaadinLogoutHandler vaadinLogoutHandler(VaadinRedirectStrategy vaadinRedirectStrategy) {
        VaadinRedirectLogoutHandler handler = new VaadinRedirectLogoutHandler(
          vaadinRedirectStrategy);
        handler.setLogoutUrl("/sso/logout");
        return handler;
    }

    @Bean
    public FilterRegistrationBean keycloakAuthenticationProcessingFilterRegistrationBean(
      KeycloakAuthenticationProcessingFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean keycloakPreAuthActionsFilterRegistrationBean(
      KeycloakPreAuthActionsFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }
}
