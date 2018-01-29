package it.dverrienti.demo.primefacesspringbootdemo;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.primefaces.util.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PrimefacesSpringBootDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PrimefacesSpringBootDemoApplication.class, args);
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PrimefacesSpringBootDemoApplication.class);
    }
     
    @Bean
    public FacesServlet facesServlet() {
        return new FacesServlet();
    }
     
    @Bean
    public ServletRegistrationBean facesServletRegistration() {
      ServletRegistrationBean registration = new   ServletRegistrationBean(facesServlet(), "*.xhtml");
      registration.setName("FacesServlet");
      return registration;
    }
    
	@Bean
	public ServletContextInitializer servletContextCustomizer() {
	    return new ServletContextInitializer() {

			@Override
			public void onStartup(ServletContext sc) throws ServletException {
                sc.setInitParameter(Constants.ContextParams.THEME, "bootstrap");	
			}
	    };
	}
}
