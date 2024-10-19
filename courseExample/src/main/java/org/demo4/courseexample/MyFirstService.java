package org.demo4.courseexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

@Service
@PropertySources({
                @PropertySource("classpath:custom.property"),
                @PropertySource("classpath:custom-2.property")
})

public class MyFirstService {

    private MyFirstClass myFirstClass;
    @Value("${my.prop}")
    private String customProperty;
    @Value("${my.prop.2}")
    private String customProperty2;
    /* private Environment environment;*/

    @Autowired
    public void setMyFirstClass(
            @Qualifier("mySecondBean") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    public String tellAStory() {
        return "The dependency is saying : " + myFirstClass.sayHello();
    }

    public String getCustomProperty() {
        return customProperty;
    }

    public String getCustomProperty2() {
        return customProperty2;
    }

    /*public String getJavaVersion() {
        return environment.getProperty("java.version");
    }

    public String readProperty() {
        return environment.getProperty("spring.application.name");
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }*/
}
