package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@RestController
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
    @ApiOperation(value = "provideDocumentation", nickname = "provideDocumentation")

    @RequestMapping(method = RequestMethod.GET, path="/")

    public String provideDocumentation(){
    	return "Please fetch REST-Documentation under: .../swagger-ui.html";
    }  

	
    @ApiOperation(value = "sayHelloTo", nickname = "sayHelloTo")

    @RequestMapping(method = RequestMethod.GET, path="/hello")

    @ApiImplicitParams({

        @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query")

      })
    //@RequestMapping("/hello")
    public String sayHelloTo(@RequestParam(value="name") String name){
    	System.out.println(name);
    	return name==null?"Enter a name first, otherwise I can not greet you!":"Hello there " + name + "!";
    }    
    
    @Bean

    public Docket newsApi() {

        return new Docket(DocumentationType.SWAGGER_2)

                .apiInfo(apiInfo())

                .select()

                .paths(PathSelectors.any())

                .build();

    }

     

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()

                .title("Spring REST Sample with Swagger")

                .description("Spring REST Sample with Swagger")

                .contact("Finn Kunas")

                .version("1.0")

                .build();

    }

}