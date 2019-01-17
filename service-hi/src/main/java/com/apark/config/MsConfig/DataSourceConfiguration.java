package com.apark.config.MsConfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.apark.utils.SpringContextUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库源配置
 *
 * @author Jfei
 */
@Data

@Configuration
public class DataSourceConfiguration    {

    private static Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${mysql.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Value("${mysql.datasource.write.url}")
    private String dbUrl ;
    @Value("${mysql.datasource.write.username}")
    private String username ;
     @Value("${mysql.datasource.write.password}")
    private String password ;

    @Value("${mysql.datasource.read01.url}")
    private String read_dbUrl ;
    @Value("${mysql.datasource.read01.username}")
    private String read_username ;
    @Value("${mysql.datasource.read01.password}")
    private String read_password ;

   // String driverClassName = "com.mysql.jdbc.Driver";

   //String connectionProperties = "druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500";

    @Value("${mysql.datasource.write.driverClassName}")
    private String driverClassName;
    @Value("${mysql.datasource.write.initialSize}")
    private int initialSize;
    @Value("${mysql.datasource.write.minIdle}")
    private int minIdle;
    @Value("${mysql.datasource.write.maxActive}")
    private int maxActive;
    @Value("${mysql.datasource.write.maxWait}")
    private int maxWait;
    @Value("${mysql.datasource.write.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${mysql.datasource.write.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${mysql.datasource.write.validationQuery}")
    private String validationQuery;
    @Value("${mysql.datasource.write.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${mysql.datasource.write.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${mysql.datasource.write.testOnReturn}")
    private boolean testOnReturn;
    @Value("${mysql.datasource.write.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${mysql.datasource.write.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${mysql.datasource.write.filters}")
    private String filters;
    @Value("${mysql.datasource.write.connectionProperties}")
    private String connectionProperties;
    @Value("${mysql.datasource.write.useGlobalDataSourceStat}")
    private boolean useGlobalDataSourceStat;

    /**
     * 写库 数据源配置
     *
     * @return
     */
    @Bean(name = "writeDataSource")
    @Primary
    public DataSource writeDataSource() {
        log.info("-------------------- writeDataSource init ---------------------");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setDriverClassName(driverClassName);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setUseGlobalDataSourceStat(true);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter 出错 ++: "+ e);
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }

    /**
     * 有多少个从库就要配置多少个
     *
     * @return
     */
    @Bean(name = "readDataSource01")
    public DataSource readDataSourceOne() {
        log.info("-------------------- read01 DataSourceOne init ---------------------");
        // return DataSourceBuilder.create().type(dataSourceType).build();

        log.info("-------------------- writeDataSource init ---------------------");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

       //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter 出错 ++: "+ e);
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }




    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        DataSourceConfiguration.log = log;
    }

    public Class<? extends DataSource> getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(Class<? extends DataSource> dataSourceType) {
        this.dataSourceType = dataSourceType;
    }




}
