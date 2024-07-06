package com.example.demo.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableAsync
@Slf4j
public class AsyncThreadPoolConfig {
    
    
    private final int corePoolSize = 1000;

    private final int maxPoolSize = 1000;

    @Bean
    public Executor asyncTaskExecutor(){
        log.info("Creating an async task executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);

        executor.setThreadNamePrefix("asyncThread");
        executor.initialize();
        return executor;
    }
}
