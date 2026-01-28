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

import alt.portfolio.builder.services.DbUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((req) -> req
						.requestMatchers("/", "/css/**", "/js/**", "/img/**", "/admin/register/**", "/index/**").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
						.anyRequest().authenticated())
				.formLogin((form) -> form
						.loginPage("/login")
						.defaultSuccessUrl("/default", true)
						.permitAll())
				.logout((logout) -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll());
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
