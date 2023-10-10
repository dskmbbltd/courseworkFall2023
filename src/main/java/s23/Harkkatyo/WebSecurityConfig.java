package s23.Harkkatyo;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import s23.Harkkatyo.web.UserDetailServiceImp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private UserDetailServiceImp userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(new
	BCryptPasswordEncoder());
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	http
	.authorizeHttpRequests( authorize -> authorize.requestMatchers(antMatcher("/rest/**")).permitAll() //postman testausta varten
	.requestMatchers(antMatcher("/add**")).hasAuthority("ADMIN")
	.requestMatchers(antMatcher("/edit**")).hasAuthority("ADMIN")
	.anyRequest().authenticated())
	.headers(headers -> headers.frameOptions(frameOptions -> frameOptions // h2
			.disable()
			)
	)
	.formLogin( formlogin -> formlogin
	.defaultSuccessUrl("/perfumelist", true)
	.permitAll()
	)
	.logout( logout -> logout
	.permitAll())
	.csrf(csrf->csrf.disable()); //postman ja h2
	
	return http.build();
	}

}


