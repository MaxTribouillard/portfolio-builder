package alt.portfolio.builder.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

import alt.portfolio.builder.services.DbUserService;
import alt.portfolio.builder.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
 
	@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                        (req) -> req.requestMatchers(
                                PathPatternRequestMatcher.withDefaults().matcher("/"),
                                PathPatternRequestMatcher.withDefaults().matcher("/css/**"),
                                PathPatternRequestMatcher.withDefaults().matcher("/js/**"),
                                PathPatternRequestMatcher.withDefaults().matcher("/img/**"),
                                PathPatternRequestMatcher.withDefaults().matcher("/users/register/**")
                                )
                                .permitAll()
                                .anyRequest()
                                .authenticated()).csrf(AbstractHttpConfigurer::disable).formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/users", true).permitAll());
        return http.build();
    }
	
	@Primary
    @Bean
    public UserDetailsService getUserDetailsService() {
        return new DbUserService();
    }
 
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider(userService);
        auth.setPasswordEncoder(getPasswordEncoder());
        return auth;
    }
	
}
