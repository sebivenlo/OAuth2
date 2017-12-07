/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demooauthserver;

/**
 *
 * @author Gebruiker
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // TODO S2.3 Check that a user is authenticated in order to receive resources.
        // first pass "permitAll()" through the tokenKeyAccess
        // Then check if the user is authenticated with "isAuthenticated()" hin: checkTokenAccess
         security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()"); // UNCOMMENT
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // TODO S2.4 configure the OAUTH endpoint.
        // Add "ClientId" as client id hint: withc...
        // "secret" as secret
        // "authorization_code" as grant type
        // and "user_info" as the scope
        // then automaticly approve
           clients.inMemory().withClient("ClientId").secret("secret").authorizedGrantTypes("autherization_code").scopes("user_info").autoApprove(true); // UNCOMMENT
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.authenticationManager(authenticationManager);
    }
}
