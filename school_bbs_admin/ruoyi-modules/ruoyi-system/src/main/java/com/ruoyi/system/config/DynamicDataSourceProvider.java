package com.ruoyi.system.config;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 加载所有的数据源
 */
public interface DynamicDataSourceProvider {
    String DEFAULT_DATASOURCE = "master";
    /**
     * 加载所有的数据源
     * @return
     */
    Map<String, DataSource> loadDataSources();
}
