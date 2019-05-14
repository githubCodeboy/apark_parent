package com.apark.config.MsConfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据库操作
 * 双数据源切换
 * 或者多数据源切换
 * @author lubin
 */

public class MultiDataSource extends AbstractRoutingDataSource {
	
	private static final ThreadLocal<DataSourceKey> dataSourceKey = new ThreadLocal<DataSourceKey>();
	
    public  static void setDataSourceKey(DataSourceKey dataSource) {
        dataSourceKey.set(dataSource);
    }
    @Override
    protected Object determineCurrentLookupKey() {

        DataSourceKey key = dataSourceKey.get();
        return null==key?DataSourceKey.master:key;
    }
    
    public static void clearDataSource() {
    	dataSourceKey.remove();
    }
}
