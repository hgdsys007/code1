package com.legaoyi;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration("appConfiguration")
@ImportResource(locations = {"classpath*:applicationContext*.xml"})
public class AppConfiguration {

}
