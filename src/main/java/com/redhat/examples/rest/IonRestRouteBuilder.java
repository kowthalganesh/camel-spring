package com.redhat.examples.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class IonRestRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

	restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
            .contextPath("camel-example-servlet-tomcat/rest").port(8080);

          rest("/ionservice").description("ION rest service")
              .consumes("application/json").produces("application/json")
		
              .get().description("List all providers").outTypeList(UserProvider.class)
		 .route().log("Rest done")
                 .to("bean:userService?method=listProviders").endRest();
    }

}

