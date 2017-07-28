/*
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Template pack-angular:src/main/java/config/SecurityConfiguration.java.p.vm
 */
package com.bpe.monitor.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import com.bpe.monitor.security.AjaxAuthenticationFailureHandler;
import com.bpe.monitor.security.AjaxAuthenticationSuccessHandler;
import com.bpe.monitor.security.AlwaysSendUnauthorized401AuthenticationEntryPoint;

/**
 * This is where security happens.
 *
 * @see http://www.baeldung.com/spring-security-authentication-with-a-database
 *
 * @see https://www.mkyong.com/spring-security/spring-security-password-hashing-example/
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Inject
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Inject
    private AlwaysSendUnauthorized401AuthenticationEntryPoint alwaysSendUnauthorized401AuthenticationEntryPoint;

    @Inject
    DataSource dataSource;

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Inject
    public void configAuthentication(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder()).usersByUsernameQuery("select email as principal, password as credentials, true from account where email = ?")
                .authoritiesByUsernameQuery("select email as principal, email as role from account where email = ?");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring(). //
                antMatchers(HttpMethod.OPTIONS, "/**"). //
                antMatchers("/"). //,
                antMatchers("/signup"). //
                antMatchers("/*.{js,html}"). //
                antMatchers("/img/**"). //
                antMatchers("/node_modules/**"). //
                antMatchers("/**/*.{js,html,css}");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http. //
                csrf().disable(). //
                formLogin(). //
                loginPage("/").//
                loginProcessingUrl("/api/login"). //
                defaultSuccessUrl("/", true). //
                successHandler(ajaxAuthenticationSuccessHandler). //
                failureHandler(ajaxAuthenticationFailureHandler). //
                usernameParameter("j_username"). //
                passwordParameter("j_password"). //
                permitAll(). //
                and(). //
                exceptionHandling(). //
                authenticationEntryPoint(alwaysSendUnauthorized401AuthenticationEntryPoint). //
                and(). //
                logout(). //
                logoutUrl("/api/logout"). //
                logoutSuccessUrl("/"). //
                deleteCookies("JSESSIONID"). //
                permitAll(). //
                and(). //
                authorizeRequests(). //
                antMatchers("/api/authenticated").permitAll().//
                antMatchers("/**").authenticated(). //
                antMatchers("/swagger-ui/index.html").hasAuthority("ROLE_ADMIN");
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}
