package cn.qdd.foods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;

/**
 * @author 东方曜
 * @date 2020/11/23 10:13
 */
@SpringBootApplication
@EnableScheduling
@MapperScan({ "cn.qdd.foods.mapper" })
public class QDDApplication {
    public static void main(String[] args) {
        SpringApplication.run(QDDApplication.class, args);
        System.out.println("start ok!");
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("Hostname: " + address.getHostName());
            System.out.println("IP Address: " + address.getHostAddress());
            System.out.println("Using IPv4: " + (address instanceof java.net.Inet4Address));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
