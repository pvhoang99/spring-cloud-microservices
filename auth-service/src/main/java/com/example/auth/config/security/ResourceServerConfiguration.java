package com.example.auth.config.security;

import com.example.auth.config.security.expression.PermissionEvaluatorImpl;
import com.example.auth.config.security.expression.SecurityService;
import com.example.auth.config.security.expression.SecurityServiceImpl;
import com.example.grpc.auth.AuthServiceGrpc;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.security.authentication.BearerAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.CompositeGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.check.AccessPredicate;
import net.devh.boot.grpc.server.security.check.AccessPredicateVoter;
import net.devh.boot.grpc.server.security.check.GrpcSecurityMetadataSource;
import net.devh.boot.grpc.server.security.check.ManualGrpcSecurityMetadataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final CorsConfigurationSource corsConfigurationSource;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId("auth-server");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().configurationSource(corsConfigurationSource);
        http.httpBasic().disable();

        http.requestMatchers()
            .antMatchers("/v1/**")
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/v1/users", "/v1/auth/*").permitAll()
            .expressionHandler(webExpressionHandler())
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(new OAuth2AccessDeniedHandler())
            .authenticationEntryPoint(new OAuth2AuthenticationEntryPoint());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
        defaultWebSecurityExpressionHandler.setPermissionEvaluator(new PermissionEvaluatorImpl());
        return defaultWebSecurityExpressionHandler;
    }

    /*
     * metadataSource --get attribute (antMatchers(....).hasAnyRole(...) --> AccessDecisionManager ---> AccessDecisionVoter ----> RoleHierarchyVoter
     * */
    @Bean
    public RoleHierarchyVoter roleHierarchyVoter() {
        return new RoleHierarchyVoter(roleHierarchy());
    }

    @Bean
    public HttpSessionSecurityContextRepository contextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    /*
    * Advanced SpEL expressions
    * @Query("select u from User u where u.emailAddress = ?#{principal.emailAddress}")
      List<User> findCurrentUserWithCustomQuery();
    * */
    @Bean
    public EvaluationContextExtension securityExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean("securityService")
    public SecurityService securityService() {
        return new SecurityServiceImpl();
    }

    @Bean
    public GrpcAuthenticationReader authenticationReader() {
        final List<GrpcAuthenticationReader> readers = new ArrayList<>();
        // The actual token class is dependent on your spring-security library (OAuth2/JWT/...)
        readers.add(new BearerAuthenticationReader(BearerTokenAuthenticationToken::new));
        return new CompositeGrpcAuthenticationReader(readers);
    }

    @Bean
    public GrpcSecurityMetadataSource grpcSecurityMetadataSource() {
        final ManualGrpcSecurityMetadataSource source = new ManualGrpcSecurityMetadataSource();
        source.set(AuthServiceGrpc.getLoginMethod(), AccessPredicate.permitAll());
        source.setDefault(AccessPredicate.denyAll());
        return source;
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        final List<AccessDecisionVoter<?>> voters = new ArrayList<>();
        voters.add(new AccessPredicateVoter());
        return new UnanimousBased(voters);
    }

}
