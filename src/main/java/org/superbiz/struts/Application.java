package org.superbiz.struts;

import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String... args) {

        SpringApplication.run(Application.class);
    }

    @Bean
    public FindUser findUser(UserService userService) {
        return new FindUser(userService);
    }

    @Bean
    public AddUser addUser(UserService userService) {
        return new AddUser(userService);
    }

    @Bean
    public ListAllUsers listAllUsers(UserService userService) {
        return new ListAllUsers(userService);
    }

    @Bean
    public FilterRegistrationBean struts2() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new org.apache.struts2.dispatcher.FilterDispatcher());
        Map<String, String> initMap = new HashMap<String, String>();
        initMap.put("actionPackages", "com.lq");
        filterRegistrationBean.setInitParameters(initMap);
        return filterRegistrationBean;

    }

    @Bean
    public FilterRegistrationBean struts_cleanup() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new org.apache.struts2.dispatcher.ActionContextCleanUp());
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean sitemesh() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new com.opensymphony.module.sitemesh.filter.PageFilter());
        return filterRegistrationBean;
    }


}
