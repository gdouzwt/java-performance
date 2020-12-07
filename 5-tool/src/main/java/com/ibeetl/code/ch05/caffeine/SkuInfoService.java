package com.ibeetl.code.ch05.caffeine;

/**
 * 一个模拟类,实际调用来自于数据库或者Redis，或者其他微服务系统
 * 调用结果将放到caffeine里。
 * @author 公众号 java系统优化
 */
public class SkuInfoService {
        public SkuInfo query(String key){
            return new SkuInfo(key);
        }
}
