package com.algaworks.brewer.config.init;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.algaworks.brewer.config.JPAConfig;
import com.algaworks.brewer.config.Webconfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer { // classe estendida para auxiliar a configuração do Front controller do Spring - dispatcher servlet.

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { JPAConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { Webconfig.class }; // método retorna o nome da classe que vai indicar ao Spring onde localizar os controllers.
	}

	@Override
	protected String[] getServletMappings() { 
		return new String[] { "/" }; // método retorna a url digitada após o sinal de barra e que será exibida no navegador.
	}

	@Override
		protected Filter[] getServletFilters() {
			CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
			characterEncodingFilter.setEncoding("UTF-8");
			characterEncodingFilter.setForceEncoding(true);
			return new Filter[] { characterEncodingFilter };
			
		}
	
}
