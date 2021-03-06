package person.andy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: AndyCui
 * @Date: 2017/10/10 17:57
 * @Description:
 */
@RestController
public class HelloConsumerController {
    @Resource
    private RestTemplate restTemplate;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String getHello(){
        System.err.println("hello consumer copy");
        return restTemplate.getForEntity("http://hello-service/hello/hello",String.class).getBody();
    }
    @RequestMapping(value = "/serviceHello",method = RequestMethod.GET)
    public String helloAsService(){
        return "hello as service";
    }
}
