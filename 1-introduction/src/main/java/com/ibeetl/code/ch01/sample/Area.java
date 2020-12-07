package com.ibeetl.code.ch01.sample;

public class Area {
    private Integer provinceId;
    private Integer cityId;
    private Integer townId;

    public Area() {
    }

    public Area(Integer provinceId, Integer cityId) {
        this.provinceId = provinceId;
        this.cityId = cityId;
    }



    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    public CityKey buildKey(){
        return new CityKey(provinceId,cityId);
    }
}
