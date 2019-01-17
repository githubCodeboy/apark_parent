package com.apark.common.Exception;

/**
 * spring事务检测sql执行异常类，想要回滚数据抛出此类异常，并使用@Transactional
 */
public class SqlRollBackException extends RuntimeException {

    public SqlRollBackException(String msg) {
        super(msg);
    }

    public SqlRollBackException(Throwable t) {
        super(t);
    }

    public SqlRollBackException(String msg,Throwable t) {
        super(msg,t);
    }
}
