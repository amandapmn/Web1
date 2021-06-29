package br.ufscar.dc.dsw.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.security.UsuarioDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UsuarioDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				http.csrf().disable().authorizeRequests()
				.antMatchers("/error", "/login/**", "/js/**", "/css/**", "/image/**", "/webjars/**").permitAll()
				.antMatchers("/listarProfissionais", "/home").permitAll()
				.antMatchers("/admin/clientes/**", "/admin/profissionais/**", "/admin/usuarios/**").hasAuthority("ADMIN")
				.antMatchers("/profissional/**").hasAuthority("PROFISSIONAL")
				.antMatchers("/cliente/**").hasAuthority("CLIENTE")
				.antMatchers("/clientes", "/profissionais", "/consultas").permitAll()
				.antMatchers("/clientes/{\\d+}", "/profissionais/{\\d+}").permitAll()
				.antMatchers("/consultas/{\\d+}").permitAll()
				.antMatchers("/profissionais/especialidades/{\\w+}").permitAll()
				.antMatchers("/consultas/clientes/{\\d+}").permitAll()
				.antMatchers("/consultas/profissionais/{\\d+}").permitAll()
				.anyRequest().authenticated()

			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/")
				.permitAll();
	}
}
