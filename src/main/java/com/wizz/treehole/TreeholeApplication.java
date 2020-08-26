package com.wizz.treehole;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  //启动缓存
@ServletComponentScan(basePackages = "com.yxr.treehole.filter") //过滤器生效位置
@MapperScan("com/wizz/treehole/mapper")
public class TreeholeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreeholeApplication.class, args);
    }

}
