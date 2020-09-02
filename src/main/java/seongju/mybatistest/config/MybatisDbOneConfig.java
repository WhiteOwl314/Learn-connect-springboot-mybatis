package seongju.mybatistest.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = "seongju.mybatistest.dao",
        //복수의 db 운영 위해
        sqlSessionFactoryRef = "oracleOneSqlSessionFactory"
)
public class MybatisDbOneConfig {

    @Bean(name="oracleOneDatasource")
    //yml 앞글자 통일
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource oracleOneDatasource(){
        return DataSourceBuilder.create()
                .type(HikariDataSource.class).build();
    }

    @Bean(name="oracleOneSqlSessionFactory")
    public SqlSessionFactory oracleOneSqlSessionFactory(
            @Qualifier(value = "oracleOneDatasource")
            DataSource oracleOneDatasource
    )throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(oracleOneDatasource);
        PathMatchingResourcePatternResolver resolver =
                new PathMatchingResourcePatternResolver();
        //oracle mapper 설정
        bean.setMapperLocations(resolver.getResources(
                "classpath:seongju/mybatistest/dao/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name="oracleOneSqlSessionTemplate")
    public SqlSessionTemplate oracleOneSqlSessionTemplate(
            @Qualifier(value = "oracleOneSqlSessionFactory")
            SqlSessionFactory oracleOneSqlSessionFactory
    ) throws Exception{
        return new SqlSessionTemplate(oracleOneSqlSessionFactory);
    }
}
