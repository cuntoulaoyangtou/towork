package com.java.toworkservice.config;

import com.java.toworkservice.exception.BizException;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassNameServerConfig
 * @Description
 * @Author 村头老杨头
 * @Date 2020/3/22 0022 22:46
 * @Version V1.0
 **/

@Component
public class ServerConfig  implements ApplicationListener<WebServerInitializedEvent> {
    private int serverPort;

    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new BizException("未知主机异常");
        }
        return "http://" + address.getHostAddress() + ":" + this.serverPort;
    }
    public String getHost(){
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new BizException("未知主机异常");
        }
        return address.getHostAddress() + ":" + this.serverPort;
    }
    public int getPort(){
       return this.serverPort;

    }
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

}
