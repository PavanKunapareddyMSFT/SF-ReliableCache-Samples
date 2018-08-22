package com.fabrikam.directory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import microsoft.servicefabric.services.client.ServicePartitionKey;
import microsoft.servicefabric.simplemodel.springboot.PartitionResolutionHandler;

/**
 * Configuration related to Web MVC.
 */
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
         * The employee directory service is designed as a partitioned micro service where each partition could handle a portion of the employee requests. Each partition here has its own cache for lookups and talks to the backend DB for cache-miss. This is one way in which the load is effectively distributed across different partitions of the micro service. 

         * The SF PartitionResolutionHandler interceptor allows user to register partition computation logic which will be used to determine if the webrequest has landed on the partition, if not, the request is routed to the correct partition.
         * The compute function gets the id of the employee from the http request and computes modulo using the number of partitions available, and the returns the partition key.
         * This key will then be used by the interceptor to determine if the request has to be forwarded to local web server or a remote one. 
         */
        registry.addInterceptor(new PartitionResolutionHandler((request, numOfPartitions) -> {
            long id = Long.parseLong(request.getParameter("id"));
            new ServicePartitionKey(id % numOfPartitions);
        }));
    }

}