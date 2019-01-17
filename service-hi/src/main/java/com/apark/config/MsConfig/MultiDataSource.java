package com.apark.config.MsConfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据库操作
 * 双数据源切换
 * @author lubin
 */

public class MultiDataSource extends AbstractRoutingDataSource {
	
	private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();
	
    public  static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
    
    public static void clearDataSource() {
    	dataSourceKey.remove();
    }
}
