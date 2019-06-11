package jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jackson.model.Company;
import jackson.model.User;

import java.util.ArrayList;

public class learnSerializer {
    
    public static void main(String[] args) throws Exception{

        Company company = new Company().setId("8888").setName("King");
        User user = new User().setId("123").setName("张三").setCountry(null).setAge(100).setCompany(company).setObject(new ArrayList<>());

        ObjectMapper mapper = new ObjectMapper();
        ObjectMapper mapper2 = new ObjectMapper();
        mapper2.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper2.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        mapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper2.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);
        /**
         * jackson 序列化
         */
        String userString = mapper.writeValueAsString(user);
        System.out.println(userString);
        System.out.println(user.toString());
        System.out.println(mapper2.writeValueAsString(user));

        /**
         * jackson 反序列
         */
        String json = "{\"id\":\"123\",\"name\":\"张三\",\"age\":100,\"company\":{\"id\":\"8888\",\"name\":\"King\"}}";
        String json2 = "[\"jackson.model.User\",{\"id\":\"123\",\"name\":\"张三\",\"age\":100,\"company\":[\"jackson.model.Company\",{\"id\":\"8888\",\"name\":\"King\"}]}]";

        User user2 = mapper.readValue(json,User.class);
//            User user2 = mapper2.readValue(json,User.class);  //会报错
        User user3 = mapper2.readValue(json2,User.class);
        System.out.println("-------反序列化------");
        System.out.println(user2.toString());
        System.out.println(user3.toString());

        /**
         * 构建json对象
         */
        ObjectNode jsonObject = mapper.createObjectNode();
        jsonObject.put("key1","123");
        jsonObject.putPOJO("key2",new Object());
        // 注意：jsonObject.toString()是有问题的
        System.out.println(jsonObject.toString());
        // 空对象new Object()会报错，还要配置一下
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        System.out.println(mapper.writeValueAsString(jsonObject));
    }
}
