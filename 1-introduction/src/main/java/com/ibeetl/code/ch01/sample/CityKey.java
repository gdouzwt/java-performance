package com.ibeetl.code.ch01.sample;

import java.util.Objects;

public class CityKey {
    private Integer provinceId;
    private Integer cityId;

    private transient  int hashCode = -1;

    public CityKey(Integer provinceId, Integer cityId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        CityKey cityKey = (CityKey) o;
        return Objects.equals(provinceId, cityKey.provinceId) &&
                Objects.equals(cityId, cityKey.cityId);
    }

    @Override
    public int hashCode() {
        if(hashCode == -1){
            hashCode =   Objects.hash(provinceId, cityId);
        }
        return hashCode;
    }
}
