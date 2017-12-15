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
    public String helloAsService(HttpServletRequest request){
        return "hello as service";
    }
    private static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();

            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String ip1 : ips) {
                if (!("unknown".equalsIgnoreCase(ip1))) {
                    ip = ip1;
                    break;
                }
            }
        }
        System.err.println("最终获得的客户端ip是{}"+ip);
        System.err.println("测试远程提交");
        return ip;
    }
}
