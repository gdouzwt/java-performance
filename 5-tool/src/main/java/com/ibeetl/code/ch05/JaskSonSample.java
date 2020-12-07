package com.ibeetl.code.ch05;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ibeetl.code.ch05.model.JsonResult;
import com.ibeetl.code.ch05.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Jackson 作为很多工具的首选序列化工具，除了性能高超外，功能也很强大
 * @author 公众号 java系统优化
 */
public class JaskSonSample {

  static ObjectMapper objectMapper = null;
  public static void main(String[] args) throws Exception{
    initObjectMapper();
//    bindSample();
//    nodeSample();
//    streamSample();
//    customSerializer();
    collectionSmaple();

  }

  public static void initObjectMapper(){
    objectMapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule("SimpleModule",
      Version.unknownVersion());
    //JsonResult对象指定一个自定义的序列化
    simpleModule.addSerializer(JsonResult.class, new CustomJsonResultSerializer());
    objectMapper.registerModule(simpleModule);

  }

  public static void collectionSmaple() throws IOException{
    String jsonInput = "[{\"id\":2,\"name\":\"xiandafu\"},{\"id\":3,\"name\":\"lucy\"}]";
    List<Map> list = objectMapper.readValue(jsonInput, List.class);
    System.out.println("collectionSmaple map:"+list.get(0));
    JavaType type = getCollectionType(List.class,User.class);
    List<User> listUser = objectMapper.readValue(jsonInput, type);
    System.out.println("collectionSmaple user:"+listUser.get(0));

  }

  public static void streamSample() throws IOException{
    String json = "{\"name\":\"lijz\",\"age\":10}";
    JsonFactory f = objectMapper.getFactory();
    String key=null,value=null;
    JsonParser parser = f.createParser(json);
    // {，START_OBJECT,忽略第一个Token
    JsonToken token  = parser.nextToken();
    //"name",FIELD_NAME
    token = parser.nextToken();
    if(token==JsonToken.FIELD_NAME){
      key = parser.getCurrentName();
    }
    token = parser.nextToken();
    //"lijz"，VALUE_STRING
    value = parser.getValueAsString();

    System.out.println("streamSample "+"key="+key+" value="+value);
  }



  public static void nodeSample() throws IOException{
    String jsonStr = "{\"name\":\"lijz\",\"age\":10}";
    JsonNode node = objectMapper.readTree(jsonStr);

    String name = node.get("name").asText();
    int age = node.get("age").asInt();
    User user = new User();
    user.setAge(age);
    user.setName(name);
    System.out.println("nodeSample"+user);

  }


  public static void bindSample() throws IOException {
    bingSimple();
  }
  public static void bingSimple() throws IOException{
    String jsonStr = "{\"name\":\"lijz\",\"age\":10}";
    User user = objectMapper.readValue(jsonStr, User.class);
    System.out.println("bindSample:"+user);
  }


  public static void customSerializer() throws IOException{
    JsonResult result = new JsonResult();
    result.setSuccess(true);
    result.setMsg("操作成功");
    String json = objectMapper.writeValueAsString(result);
    System.out.println("customSerializer "+json);

  }

  private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
    return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
  }

  static class CustomJsonResultSerializer extends JsonSerializer<JsonResult> {
    @Override
    public void serialize(JsonResult value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      gen.writeStartObject();
      //将本该输出的success字段改成输出code，且用200和500标识是否成功
      if(value.isSuccess()) {
        gen.writeObjectField("code", "200");
      }else {
        gen.writeObjectField("code", "500");
      }
      gen.writeStringField("msg", value.getMsg());
      gen.writeEndObject();
    }

  }

}
