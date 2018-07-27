package kr.co.dwebss.child;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

//@SpringBootApplication
//@PropertySource("config.properties")
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
public class Application extends SpringBootServletInitializer {
	// public static void main(String[] args) {
	// SpringApplication.run(Application.class, args);
	// }

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class).properties(getProperties());
	}

	static Properties getProperties() {
		Properties props = new Properties();
		props.put("spring.config.location", "classpath:./");
		return props;
	}

	@Bean
	public TaskScheduler taskScheduler() {
		return new ConcurrentTaskScheduler();
	}

//	@Bean
//	public TaskScheduler taskScheduler() {
//		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//		taskScheduler.setPoolSize(10);
//		return taskScheduler;
//	}
	
	
//	@EnableScheduling을 명시하여 작업 스케쥴러를 활성화한다. 명시하지 않을 경우 애플리케이션은 시작과 동시에 종료된다. 애플리케이션은 대기 상태를 유지하며 정해진 일정대로 ScheduledThreadPoolExecutor에 의해 관리되는 Worker 쓰레드를 실행하여 스케쥴 작업을 처리한다.
//	작업 스케쥴러를 구현하려면 해당 스케쥴을 관리할 org.springframework.scheduling.TaskScheduler 빈이 등록되어 있어야 한다. 쓰레드 풀이 필요하다면 ThreadPoolTaskScheduler 빈을 등록하고 단일 쓰레드로 충분하다면 ConcurrentTaskScheduler 빈을 등록한다.
//	단일 쓰레드로 관리될 경우, 2개 이상의 작업을 같은 시간에 실행되도록 설정시 1개 작업을 먼저 실행하여 완료 후 다른 작업을 순차적으로 실행한다. 여러 작업이 동시에 실행되기를 원할 경우 쓰레드 풀의 크기를 2개 이상으로 설정하면 된다. 쓰레드 풀을 구성하지 않을 경우 작업 스케쥴의 의해 실행될 빈의 메써드에 @Async를 명시하는 것도 한 방법이다.



}
