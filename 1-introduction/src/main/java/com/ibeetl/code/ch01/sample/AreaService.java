package com.ibeetl.code.ch01.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaService {
    /**
     *
     * @param areas  初始化地址列表
     * @return  key的格式省编号+“#“+市编号”
     */
    public Map<String,Area> buildArea(List<Area> areas){
        if(areas.isEmpty()){
            return new HashMap();
        }
        Map<String,Area> map = new HashMap<>();
        for(Area area:areas){
            String key = area.getProvinceId()+"#"+area.getCityId();
            map.put(key,area);
        }
        return map;
    }
}
