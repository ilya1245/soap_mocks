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
		return new ServletRegistrationBean(servlet, "/mock/*");
	}

	@Bean(name = "countries")
	public DefaultWsdl11Definition countryWsdl11Definition(XsdSchema countriesSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CountryPort");
		wsdl11Definition.setLocationUri("/mock/country");
		wsdl11Definition.setTargetNamespace("http://spring.io/kingfisher/mock1");
		wsdl11Definition.setSchema(countriesSchema);
		return wsdl11Definition;
	}

	@Bean(name = "pfs")
	public DefaultWsdl11Definition pfsWsdl11Definition(XsdSchema pfsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PfsPort");
		wsdl11Definition.setLocationUri("/mock/pfs");
		wsdl11Definition.setTargetNamespace("http://www.kingfisher.com/oagis/9");
		wsdl11Definition.setSchema(pfsSchema);
		return wsdl11Definition;
	}

	@Bean(name = "carrier")
	public DefaultWsdl11Definition carrierWsdl11Definition(XsdSchema carrierSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CarrierPort");
		wsdl11Definition.setLocationUri("/mock/carrier");
		wsdl11Definition.setTargetNamespace("http://www.kingfisher.com/oagis/9");
		wsdl11Definition.setSchema(carrierSchema);
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

	@Bean
	public XsdSchema carrierSchema() {
		return new SimpleXsdSchema(new ClassPathResource("PFS/F5/com_kingfisher_oagis/9_5_1/Developer/BODs/GetCarrierBooking.xsd"));
	}
}
