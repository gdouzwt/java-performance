package com.ibeetl.code.ch12;

import com.ibeetl.code.ch10.processor.annotation.JsonWriter;

@JsonWriter
public interface UserJsonMapper {
	public String write(User user);
}
