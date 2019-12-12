package uy.com.ideas.demente.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Al extender de WebSecurityConfigurerAdapter habilitamos la seguirdad HTTP
 * 
 * @author 1987diegog
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Se configura la seguirdad HTTP..
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// require all requests to be authenticated except for the resources
		http.authorizeRequests(). //
				antMatchers("/javax.faces.resource/**"). //
				permitAll().anyRequest().authenticated();

		// login
		http.formLogin().loginPage("/login.xhtml"). //
				permitAll().failureUrl("/login.xhtml?error=true");
		// logout
		http.logout().logoutSuccessUrl("/login.xhtml");

		// not needed as JSF 2.2 is implicitly protected against CSRF
		http.csrf().disable();
	}

	/**
	 * Configuracion basica en memoria para la autentificacion.
	 * 
	 * @param authentication
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {

		authentication. //
				inMemoryAuthentication() //
				.withUser("1987diegog").password("{noop}123456").roles("ADMIN").and() //
				.withUser("diegog").password("{noop}987654").roles("USER");
	}
}
