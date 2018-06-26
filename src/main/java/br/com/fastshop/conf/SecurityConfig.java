package br.com.fastshop.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.fastshop.security.UserDetailsServiceImplementacao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private UserDetailsServiceImplementacao userDetailsImplementacao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		
		.antMatchers("/home").permitAll() // Permito todo mundo acessar /inicio
	//		.antMatchers("/usuario/listar").hasRole("ADMIN")
	//		.antMatchers("/usuario/excluir").hasRole("ADMIN") // /pessoa/listar todo mundo pode acessar
		.antMatchers("/usuario/**").permitAll() //Somente pessoa com papel "USER" acessa /pessoa/formulario
		.antMatchers("/produto/**").permitAll()
		.antMatchers("/carrinho/**").permitAll()
		.antMatchers("/img/**").permitAll()
		.antMatchers("/images/**").permitAll()
		
		.anyRequest().authenticated() // o resto precisa está autenticado
		
		.and().formLogin().loginPage("/usuario/login").permitAll() 
//		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		//.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		.and()
		.logout()
		.logoutSuccessUrl("/usuario/login?logout") // logout sucesso
		.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsImplementacao).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/js/**","/images/**"); // ignora e permite uri's com esses arquivos
	}

}