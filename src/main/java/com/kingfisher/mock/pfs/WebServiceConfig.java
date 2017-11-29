package com.kingfisher.mock.pfs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/pfsws/*");
	}

	@Bean(name = "countries")
	public DefaultWsdl11Definition countryWsdl11Definition(XsdSchema countriesSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PfsPort");
		wsdl11Definition.setLocationUri("/pfsws/country");
		wsdl11Definition.setTargetNamespace("http://spring.io/kingfisher/mock");
		wsdl11Definition.setSchema(countriesSchema);
		return wsdl11Definition;
	}

	@Bean(name = "pfs")
	public DefaultWsdl11Definition pfsWsdl11Definition(XsdSchema pfsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PfsPort");
		wsdl11Definition.setLocationUri("/pfsws/pfs");
		wsdl11Definition.setTargetNamespace("http://spring.io/kingfisher/mock");
		wsdl11Definition.setSchema(pfsSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema countriesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("countries/countries.xsd"));
	}

	@Bean
	public XsdSchema pfsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("PFS/F5/com_kingfisher_oagis/9_5_1/Developer/BODs/ProcessFulfilmentSource.xsd"));
	}
}
