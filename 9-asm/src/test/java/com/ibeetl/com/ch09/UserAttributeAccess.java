package com.ibeetl.com.ch09;

public class UserAttributeAccess {

    public Object value(Object bean, String attr) {
        int hash = attr.hashCode();
        User user = (User) bean;
        switch (hash) {
        case 1:
            return user.getName();
        case 2:
            return user.getAddress();
        case 3:
            return user.getNumbers();
        case 4:
            return user.getBirthDate();
        }
        throw new IllegalArgumentException("No such attribute : " + attr);

    }

}
