package com.algaworks.brewer.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.algaworks.brewer.controller.CervejasController;

import nz.net.ultraq.thymeleaf.LayoutDialect;



@Configuration // tal notação indica que trata-se de uma classe de configuração.
@ComponentScan(basePackageClasses = { CervejasController.class }) // tal notação indica ao Spring MVC onde serão localizados os controllers.
@EnableWebMvc // a notação habilita a classe para um projeto MVC
public class Webconfig extends WebMvcConfigurerAdapter implements ApplicationContextAware { // classe estendida oferece adaptadores para facilitar a configuração 

		private ApplicationContext applicationContext;	
		
		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			this.applicationContext = applicationContext;
		}
		
		@Bean
		public ViewResolver viewResolver() {
			ThymeleafViewResolver resolver = new ThymeleafViewResolver();
			resolver.setTemplateEngine(templateEngine());
			resolver.setCharacterEncoding("UTF-8");
			return resolver;
		}
		
		@Bean // A anotação bean deixa o TemplateEngine disponível para o contexto do Spring.
		public TemplateEngine templateEngine() { // Esse método recebe o objeto templateResolver, do método templateResolver e o processa num arquivo html.
			SpringTemplateEngine engine = new SpringTemplateEngine();
			engine.setEnableSpringELCompiler(true);
			engine.setTemplateResolver(templateResolver());
			
			engine.addDialect(new LayoutDialect());
			return engine;
		}
		
		private ITemplateResolver templateResolver() {
			SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
			resolver.setApplicationContext(applicationContext); // Obrigatório utilizar o método, pois, ele precisa receber como parâmetro o objeto applicationContext.
			resolver.setPrefix("classpath:/templates/"); // Informa a localização dos arquivos template html: "classpath:/templates/".
			resolver.setSuffix(".html"); // Mantem a extensão do arquivo a ser utilizado para que não seja necessário digitá-la no mapeamento.
			resolver.setTemplateMode(TemplateMode.HTML); // Determina que o template padrão é do tipo html.
			return resolver; 
	}

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		}
		
}
