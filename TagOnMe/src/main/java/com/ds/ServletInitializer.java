package com.ds;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override//goodddd
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TagOnMeApplication.class);
	}

}
