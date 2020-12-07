package com.ibeetl.code.ch01.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreferAreaService {
    public Map<CityKey,Area> buildArea(List<Area> areas){
        if(areas.isEmpty()){
            return new HashMap<>();
        }
        Map<CityKey,Area> map = new HashMap<>(areas.size());
        for(Area area:areas){
            CityKey key = area.buildKey();
            map.put(key,area);
        }
        return map;
    }
}
