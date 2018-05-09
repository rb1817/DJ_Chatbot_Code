package net.n1books.dev2.di;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="beaninit.xml")
public class MessageBeanJUnitTest {
	@Autowired
	ApplicationContext context;
	
	@Test
	public void testSayHello() {
		MessageBean mbean = (MessageBean) context.getBean("mbean");
		assertEquals("Hello~", mbean.sayhello());
	
	}
	@Test
	public void testJSONObject2json() { //json-simple
		JSONObject obj =new JSONObject();
		obj.put("name","lee");
		obj.put("nick","donggyu");
		assertEquals("{\"nick\":\"donggyu\",\"name\":\"lee\"}",obj.toJSONString());
	}
	@Test
	public void testjson2JSONObject() { //json->json-simple
		String str = "{\"name\":\"lee\",\"nick\":\"donggyu\"}";
		@SuppressWarnings("deprecation")
		JSONObject obj =  (JSONObject) JSONValue.parse(str);
		JSONObject source = new JSONObject();
		source.put("name","lee");
		source.put("nick","donggyu");
		assertEquals(source, obj);
	}
	@Test
	public void testJson2Gson() {
		String str = "{\"name\":\"lee\",\"nick\":\"donggyu\"}";
		JsonObject obj = new JsonParser().parse(str).getAsJsonObject();
		assertEquals("{\"name\":\"lee\",\"nick\":\"donggyu\"}", obj.toString());
		assertEquals("donggyu",obj.get("nick").getAsString());
		assertEquals("lee",obj.get("name").getAsString());
	}
	@Test
	public void testGson2Json() {
		JsonObject obj = new JsonObject();
		obj.addProperty("team", "spus");
		obj.addProperty("name", "son");
		
		assertEquals("{\"team\":\"spus\",\"name\":\"son\"}",obj.toString());
	}

}
