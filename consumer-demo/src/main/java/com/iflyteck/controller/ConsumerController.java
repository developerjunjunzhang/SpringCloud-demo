package com.iflyteck.controller;

import com.iflyteck.client.UserClient;
import com.iflyteck.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
// @DefaultProperties(defaultFallback = "defaultFallBack")
public class ConsumerController {
//    @Autowired
//    private RestTemplate restTemplate;

    /**
     * 比较原始的服务调用方式 使用RestTemplate发http请求
     * @param id
     * @return
     */
//    @GetMapping("/{id}")
//    public User queryById(@PathVariable("id") Long id) {
//        String url = "http://localhost:8081/user/8";
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 使用发现服务来调用服务
     */
//    @GetMapping("/{id}")
//    public User queryById(@PathVariable("id") Long id) {
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
//        ServiceInstance instance = instances.get(0);
//        String url = "http://" + instance.getHost() + ":" +instance.getPort() + "/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }

//    @Autowired
//    private RibbonLoadBalancerClient client;
    /**
     * 使用负载均衡找到服务提供者
     */
    // @GetMapping("/{id}")
    // @HystrixCommand(fallbackMethod = "queryByIdFallBack") // 开启服务的线程隔离和服务降级
    // @HystrixCommand(commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    //}) // 启用服务降级的作用  单个接口的调用超时时间的配置如上
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
//    }) // 以上配置熔断器的三个属性方便测试
//    public String queryById(@PathVariable("id") Long id) {
//        ServiceInstance instance = client.choose("user-service");
//        String url = "http://" + instance.getHost() + ":" +instance.getPort() + "/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
        // 也可以在RestTemplate上添加注解@LoadBalanced,可以直接实现负载均衡
//        if (id%2 == 0) {
//            throw new RuntimeException("服务异常！！！");
//        }
//        String url = "http://user-service/user/" + id;
//        // User user = restTemplate.getForObject(url, User.class);
//        String user = restTemplate.getForObject(url, String.class);
//        return user;
//    }

    // 容错的方法必须和原方法的返回值和参数列表保持一致
    public String queryByIdFallBack (Long id) {
        return "不好意思！服务正忙！";
    }

    // 类注释指定的默认方法 不能有参数
    public String defaultFallBack () {
        return "不好意思！服务正忙！";
    }

    @Autowired
    private UserClient userClient;
    /**
     * 使用Feign来调接口,底层使用了Ribbon来实现负载均衡
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
//        ServiceInstance instance = instances.get(0);
//        String url = "http://" + instance.getHost() + ":" +instance.getPort() + "/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
        return userClient.queryById(id);
    }
}
