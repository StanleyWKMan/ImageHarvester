package application;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import handler.RequestHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.vaadin.artur.helpers.LaunchUtil;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
@EnableVaadin("view")
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Main.class));
    }
}
