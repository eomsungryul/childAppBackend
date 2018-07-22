package kr.co.dwebss.child.configurer;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static kr.co.dwebss.child.core.ProjectConstant.*;

import java.util.Properties;

/**
 * Mybatis & Mapper & PageHelper 구성
 */
@Configuration
public class MybatisConfigurer {

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage(MODEL_PACKAGE);

        //페이징 플러그인을 구성, 자세한 내용은 공식 설명서를 참조하십시오.
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("pageSizeZero", "true");// 더 긴 페이징 0을 수행하는 모든 기록을 조회하는 페이지 크기
        properties.setProperty("reasonable", "true");//마지막 페이지 쿼리 페이지의 페이지 <= 0 첫 페이지를 조회, 페이지 번호는> = 총 수
        properties.setProperty("supportMethodsArguments", "true");//지원 페이징 매개 변수는 인터페이스 매개 변수 매퍼 통과
        pageHelper.setProperties(properties);

        //플러그인 추가
        factory.setPlugins(new Interceptor[]{pageHelper});

        // XML 디렉토리 추가
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return factory.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage(MAPPER_PACKAGE);

        //일반 매퍼를 구성하십시오. 자세한 내용은 공식 문서를 참조하십시오.
        Properties properties = new Properties();
        properties.setProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        properties.setProperty("notEmpty", "false");// INSERT, 갱신 문자열 타입 = '즉 테스트 = "STR = NULL을!"의 경우 발현 및 STR = 첨가!'의 여부를 판정!
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);

        return mapperScannerConfigurer;
    }

}

