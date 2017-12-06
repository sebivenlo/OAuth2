package com.example;

import java.security.Principal;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//TODO 1.1 Remove the default EnableOauth2 annotation and setup the project as an Oauth client
@EnableOAuth2Sso
@RestController
public class SocialApplication extends WebSecurityConfigurerAdapter {
    
    /**
     * the client context is The OAuth 2 security context (for a specific user or client or combination thereof).
     */
    // @Autowired
    // private //TODO 1.2 Create a OAuth2 client context
    
    @RequestMapping("/user")
    public Principal user(Principal principal) {
            return principal;
    }
    
    //TODO 1.6 add the filter to your security configuration
    // hint: the filterbefore also needs an authicaton filter class(see imports)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
// @formatter:off
        http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
            .authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            //.and().addFilterBefore
            ;
// @formatter:on
    }

    public static void main(String[] args) {
            SpringApplication.run(SocialApplication.class, args);
    }
    
    /*
    * Spring Security provides a number of filters by default, and most of the time, these are enough.
    * But of course sometimes it’s necessary to implement new functionality with createing a new filter to use in the chain.
    */
    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/facebook");
        //TODO 1.5 Setup the facebook filter
        
        
        
        
        return facebookFilter;
    }
    
    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        //TODO 1.9 We need to explicitly support the redirects from our app to facebook. 
        //         This is handles in Spring Oauth2 with a servlet filter
        //         Also handle the order of in which it will be assesed 
        return bean;
    }
    
    
    //TODO 1.3 Create a bean for the client registration with facebook
    //         and annotate it with the ConfigurationProperties annotation
    
    
    //TODO 1.4 Create a bean to complete the authentication 
    //         since it needs to know where the user info endpoint is in facebook
    //         and annotate it with the ConfigurationProperties annotation
}
