package com.redhat.examples.rest;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RestRouteBuilder extends RouteBuilder {

    public static final String TARGET_WITH_AUTH = "http://sapdgw.iff.com:8000/sap/opu/odata/sap/ZPLMIII_INFINITI_SRV/EndUses?authMethod=Basic&authUsername=infiniti&authPassword=sapsapif&bridgeEndpoint=true";
    @Override
    public void configure() throws Exception {

        restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
           .dataFormatProperty("prettyPrint", "true")
           .contextPath("camel-example-servlet-tomcat/rest").port(8080);
       
	//	rest("/provider").description("Provider rest service")
          //.consumes("application/json").produces("application/json")
	//	.get().description("MDR Service")
		from("rest:get:provider").to("https://ion-qas.iff.com/api/v1/users/?bridgeEndpoint=true");
		from("rest:get:sap").to(TARGET_WITH_AUTH);
          //  .get("/{id}").description("Find provider by id").outType(Provider.class)
            //    .to("bean:providerService?method=getProvider(${header.id})")

           // .put().description("Updates or create a provider").type(Provider.class)
           //     .to("bean:providerService?method=updateProvider")

           // .get().description("List all providers").outTypeList(Provider.class)
             //   .to("bean:providerService?method=listProviders")
                
        //	.get("/search").description("Search by Zip").outTypeList(Provider.class)
        //		.route().log("Incoming zip: ${header.zip}")
        //		.to("bean:providerService?method=searchByZip(${header.zip})").endRest();
	}

}
