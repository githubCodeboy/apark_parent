package com.apark.common.constant;

public class ApiConstants {

    // Twitter 雪花id 生成器
    private static  SnowflakeIdWorker idWorker ;

    static {
        idWorker = new SnowflakeIdWorker(3, 1);
    }
    /**
     * // 获取 SnowflakeId 雪花id
     * @return
     */
    public  static  long getSnowflakeIdWorker_id(){
        return  idWorker.nextId();
    }




}
