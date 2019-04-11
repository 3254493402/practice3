package com.bigbigmall.xiamen.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Controller
@RequestMapping("/")
public class HomeController {

	/**
	 * 2019-3-20 判断两个数之间的所有素数
	 *
	 * @param minimum
	 * @param maximum
	 * @return
	 */
	@RequestMapping(value = "/ajaxJson")
	@ResponseBody
	public String ajaxJson(int minimum, int maximum) {
		String tm = "";
		System.out.println(minimum + "到" + maximum + "之间的素数：");
		for (int a = minimum; a <= maximum; a++) {
			if (isPrime(a)) {
				tm += a + "  ";
			}
		}
		System.out.println("222" + tm);
		return tm;
	}

	//素数
	public static boolean isPrime(int num) {
		boolean boo = true;
		if (num < 2) {
			return false;
		} else {
			for (int i2 = 2; i2 <= Math.sqrt(num); i2++) {
				if (num % i2 == 0) {
					boo = false;
					break;
				}
			}
		}
		return boo;
	}

	/**
	 * 2019-3-21 九九乘法表
	 *
	 * @return
	 */
	@RequestMapping(value = "/jsssss")
	@ResponseBody
	public String jiujiu() {
		JSONArray jSONArray = new JSONArray();
		for (int i = 2; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				JSONObject jSONObject = new JSONObject();
				if ((i * j) == 2 || (i * j) == 3 || (i * j) == 5 || (i * j) == 7) {
					jSONObject.put("name", i + "&#215;" + j + "=" + "<span style='color:red'>" + (i * j) + "</span>");
				} else {
					jSONObject.put("name", i + "&#215;" + j + "=" + (i * j));
				}
				jSONArray.put(jSONObject);
			}
			System.out.println("");
		}
		String toString = jSONArray.toString();
		return toString;
	}

	/**
	 * 2019-3-21 九九乘法表-xml
	 *
	 * @return
	 */
	@RequestMapping(value = "/jsssssXml")
	@ResponseBody
	public String jiuOne() {
		JSONArray jSONArray = new JSONArray();
		for (int i = 2; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				JSONObject jSONObject = new JSONObject();
				jSONObject.put("kOne", i);
				jSONObject.put("kTwo", j);
				jSONObject.put("kThr", (i * j));
				jSONArray.put(jSONObject);
			}
		}
		String toString = jSONArray.toString();
		return toString;
	}


	/*
		2019-3-25 九九乘法表转xml
	 */
	@RequestMapping(value = "/jiuXml")
	@ResponseBody
	public ModelAndView jiuXml(HttpServletResponse response) throws ParserConfigurationException, IOException, TransformerConfigurationException, TransformerException {
		//创建解析DOM解析器
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		//根元素
		Element documentElement = doc.createElement("document");
		doc.appendChild(documentElement);

		JSONArray jSONArrayW = new JSONArray(jiuOne());
		System.out.println(jSONArrayW);

		for (int i = 0; i < 18; i++) {
			if (i <= 8) {
				Element elementA = doc.createElement("a");
				JSONObject jSONObject = jSONArrayW.getJSONObject(i);
				Element jiuXsl = jiuXsl(doc, jSONObject);
				elementA.appendChild(jiuXsl);
				JSONObject jSONObject2 = jSONArrayW.getJSONObject(i + 9);
				Element jiuXs2 = jiuXsl(doc, jSONObject2);
				elementA.appendChild(jiuXs2);
				JSONObject jSONObject3 = jSONArrayW.getJSONObject(i + 18);
				Element jiuXs3 = jiuXsl(doc, jSONObject3);
				elementA.appendChild(jiuXs3);
				JSONObject jSONObject4 = jSONArrayW.getJSONObject(i + 27);
				Element jiuXs4 = jiuXsl(doc, jSONObject4);
				elementA.appendChild(jiuXs4);
				documentElement.appendChild(elementA);
			} else if (i > 8) {
				Element elementB = doc.createElement("a");
				JSONObject jSONObject = jSONArrayW.getJSONObject(i + 27);
				Element jiuXsl5 = jiuXsl(doc, jSONObject);
				elementB.appendChild(jiuXsl5);
				JSONObject jSONObject2 = jSONArrayW.getJSONObject(i + 36);
				Element jiuXsl6 = jiuXsl(doc, jSONObject2);
				elementB.appendChild(jiuXsl6);
				JSONObject jSONObject3 = jSONArrayW.getJSONObject(i + 45);
				Element jiuXsl7 = jiuXsl(doc, jSONObject3);
				elementB.appendChild(jiuXsl7);
				JSONObject jSONObject4 = jSONArrayW.getJSONObject(i + 54);
				Element jiuXsl8 = jiuXsl(doc, jSONObject4);
				elementB.appendChild(jiuXsl8);
				documentElement.appendChild(elementB);
			}
		}

		Source source = new DOMSource(doc);

		// adds the XML source file to the model so the XsltView can detect
		ModelAndView model = new ModelAndView("a");
		model.addObject("xmlSource", source);

		return model;
		//TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));
	}

	/**
	 * 2019-3-21 九九乘法表-xsl
	 *
	 * @return
	 */
	@RequestMapping(value = "/jiuJiuXsl")
	@ResponseBody
	public Element jiuXsl(Document doc, JSONObject jSONObject) {
		Element elementKey = doc.createElement("eKey");
		//乘数
		Element elementOne = doc.createElement("kOne");
		elementOne.appendChild(doc.createTextNode(jSONObject.get("kOne").toString()));
		elementKey.appendChild(elementOne);
		//被乘数
		Element elementTwo = doc.createElement("kTwo");
		elementTwo.appendChild(doc.createTextNode(jSONObject.get("kTwo").toString()));
		elementKey.appendChild(elementTwo);
		//积
		Element elementThr = doc.createElement("kThr");
		String toString = jSONObject.get("kThr").toString();
		elementThr.appendChild(doc.createTextNode(toString));
		elementKey.appendChild(elementThr);
		if ("2".equals(toString) || "3".equals(toString) || "5".equals(toString) || "7".equals(toString)) {
			elementThr.setAttribute("name", "");
		}

		return elementKey;
	}

	@RequestMapping("/lian")
	@ResponseBody
	public String getRecordSheet() {

		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("orderNumber", "A0001").put("orderDate", "2015-11-27 13:23:56").put("customer", "牛逼").
			put("purchaseGoods", "苹果").put("number", "11").put("unitPrice", "3").put("totalSum", "33").
			put("theDateOfIssuance", "2015-11-28 00:00:00").put("logistics", "邮政").put("freight", "0").
			put("deliveryAddress", "江南师大");
		array.put(obj);

		JSONObject obj2 = new JSONObject();
		obj2.put("orderNumber", "A0002").put("orderDate", "2015-11-28 08:11:56").put("customer", "牛一般").
			put("purchaseGoods", "兔子").put("number", "5").put("unitPrice", "100").put("totalSum", "500").
			put("theDateOfIssuance", "2015-11-29 00:00:00").put("logistics", "顺丰").put("freight", "0").
			put("deliveryAddress", "东北极地");
		array.put(obj2);

		JSONObject obj3 = new JSONObject();
		obj3.put("orderNumber", "A0003").put("orderDate", "2015-11-29 13:03:56").put("customer", "张飞").
			put("purchaseGoods", "香蕉").put("number", "15").put("unitPrice", "5").put("totalSum", "75").
			put("theDateOfIssuance", "2015-11-30 00:00:00").put("logistics", "邮政").put("freight", "0").
			put("deliveryAddress", "蜀汉白帝城");
		array.put(obj3);

		JSONObject obj4 = new JSONObject();
		obj4.put("orderNumber", "A0004").put("orderDate", "2015-11-30 11:22:56").put("customer", "刘备").
			put("purchaseGoods", "桃子").put("number", "20").put("unitPrice", "3").put("totalSum", "60").
			put("theDateOfIssuance", "2015-12-01 00:00:00").put("logistics", "申通").put("freight", "0").
			put("deliveryAddress", "蜀汉白帝城");
		array.put(obj4);

		JSONObject obj5 = new JSONObject();
		obj5.put("orderNumber", "A0005").put("orderDate", "2015-12-01 13:09:56").put("customer", "关羽").
			put("purchaseGoods", "母鸡").put("number", "8").put("unitPrice", "50").put("totalSum", "400").
			put("theDateOfIssuance", "2015-12-02 00:00:00").put("logistics", "邮政").put("freight", "0").
			put("deliveryAddress", "蜀汉白帝城");
		array.put(obj5);

		JSONObject obj6 = new JSONObject();
		obj6.put("orderNumber", "A0006").put("orderDate", "2015-12-02 13:05:56").put("customer", "曹操").
			put("purchaseGoods", "面具").put("number", "12").put("unitPrice", "20").put("totalSum", "240").
			put("theDateOfIssuance", "2015-12-03 00:00:00").put("logistics", "申通").put("freight", "0").
			put("deliveryAddress", "曹魏许昌");
		array.put(obj6);

		JSONObject obj7 = new JSONObject();
		obj7.put("orderNumber", "A0007").put("orderDate", "2015-12-03 03:09:56").put("customer", "公孙策").
			put("purchaseGoods", "枪").put("number", "3").put("unitPrice", "200").put("totalSum", "600").
			put("theDateOfIssuance", "2015-12-04 00:00:00").put("logistics", "顺丰").put("freight", "0").
			put("deliveryAddress", "开封府");
		array.put(obj7);

		JSONObject obj8 = new JSONObject();
		obj8.put("orderNumber", "A0008").put("orderDate", "2015-12-04 13:07:56").put("customer", "上官婉儿").
			put("purchaseGoods", "笔").put("number", "100").put("unitPrice", "50").put("totalSum", "5000").
			put("theDateOfIssuance", "2015-12-05 00:00:00").put("logistics", "邮政").put("freight", "0").
			put("deliveryAddress", "周皇宫");
		array.put(obj8);

		JSONObject obj9 = new JSONObject();
		obj9.put("orderNumber", "A0009").put("orderDate", "2015-12-05 12:06:56").put("customer", "亚瑟").
			put("purchaseGoods", "硬币").put("number", "30").put("unitPrice", "2").put("totalSum", "60").
			put("theDateOfIssuance", "2015-12-06 00:00:00").put("logistics", "顺丰").put("freight", "0").
			put("deliveryAddress", "大不列颠岛");
		array.put(obj9);

		JSONObject obj10 = new JSONObject();
		obj10.put("orderNumber", "A00010").put("orderDate", "2015-12-06 24:23:56").put("customer", "白起").
			put("purchaseGoods", "铠甲").put("number", "2").put("unitPrice", "340").put("totalSum", "680").
			put("theDateOfIssuance", "2015-12-07 00:00:00").put("logistics", "邮政").put("freight", "0").
			put("deliveryAddress", "秦国");
		array.put(obj10);

		return array.toString();
	}

	@RequestMapping("/lianOne")
	@ResponseBody
	public String lianOne() throws IOException {
		
		JSONArray arrAy = new JSONArray();
		JSONObject object = new JSONObject();
		object.put("id", "5ca172bd731295233cb42488").put("name", "北京市").put("nid", "0");
		arrAy.put(object);
		
		JSONObject objectOne = new JSONObject();
		objectOne.put("id", "5ca172bd731295233cb42488").put("name", "台湾").put("nid", "2");
		arrAy.put(objectOne);
		
		JSONObject objectTwo = new JSONObject();
		objectTwo.put("id", "5ca172e0731295233cb4248a").put("name", "福建").put("nid", "5");
		arrAy.put(objectTwo);		
		
		return arrAy.toString();

	}
	
	@RequestMapping("/personalPage")
	@ResponseBody
	public ModelAndView  get(HttpServletResponse response) throws Exception{
		//创建解析DOM解析器
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		//根元素
		Element documentElement = doc.createElement("document");
		doc.appendChild(documentElement);
		//响应数据
		CloseableHttpResponse ee = HttpClients.createDefault().execute(new HttpGet("https://redan-api.herokuapp.com/personnels/search/findOneById?id=3"));
		//返回获取实体
		HttpEntity entity = ee.getEntity();
		if (entity != null) {
			//字符集
			String str = EntityUtils.toString(entity, "UTF-8");

			//整个文档
			JSONObject jSONObjectW = new JSONObject(str);
//			System.out.println(jSONObjectW);
			Iterator<String> keyW = jSONObjectW.keys();
			for (int i = 0; i < jSONObjectW.length(); i++) {
				String key = keyW.next();
				String value = jSONObjectW.get(key).toString();
//				System.out.println( key + ":" + value);
//				if (key.equals("storyImage")) {
//					//如果第一层是json对象的情况下
//					JSONArray jsonArray1 = jSONObjectW.getJSONArray(key);
//					for(int j = 0; j < jsonArray1.length(); j++){
//						JSONObject jSONObject = jsonArray1.getJSONObject(j);
//						
//					}
//						
//				}
				//添加第一个节点，第一层
				Element element = doc.createElement(key);
				documentElement.appendChild(element);

				if (key.equals("nickname")) {
					System.out.println(value);
					element.setTextContent(value);
				}
				documentElement.appendChild(element);
				
				if(key.equals("coverImgUrl")){
					element.setTextContent(value);
					element.setAttribute("id",value);
				}
				documentElement.appendChild(element);
				
				if(key.equals("profileImgUrl")){
					element.setTextContent(value);
					element.setAttribute("id",value);
				}
				documentElement.appendChild(element);
				
				
				if (key.equals("userStory")) {
					JSONArray jsonArrayUser = jSONObjectW.getJSONArray(key);

					Element elementUs = doc.createElement(key);
					documentElement.appendChild(elementUs);

					for (int j = 0; j < jsonArrayUser.length(); j++) {
						JSONObject jSONObjectUser = jsonArrayUser.getJSONObject(j);
						Iterator<String> keyUser = jSONObjectUser.keys();
						while (keyUser.hasNext()) {
							//Json数组下的键storyImage、id
							String keyUsOne = keyUser.next();

							Element elementOne = doc.createElement(keyUsOne);
							elementUs.appendChild(elementOne);

							if (keyUsOne.equals("storyImage")) {
								//storyImage下的值
								String valueUsOne = jSONObjectUser.getJSONObject(keyUsOne).toString();

								JSONObject jSONObjectUsOne = jSONObjectUser.getJSONObject(keyUsOne);
								Iterator<String> keyUsIt = jSONObjectUsOne.keys();

								while (keyUsIt.hasNext()) {
									String keyUsItOne = keyUsIt.next();

									if (keyUsItOne.equals("imgUrl")) {
										String valueUsItOne = jSONObjectUsOne.getString(keyUsItOne);

										Element elementThr = doc.createElement(keyUsItOne);
										elementThr.setTextContent(valueUsItOne);
										elementThr.setAttribute("id",valueUsItOne);
										elementOne.appendChild(elementThr);
									}
								}

							}
//							
						}

					}

				}
				
			}
		}
		Source source = new DOMSource(doc);

		// adds the XML source file to the model so the XsltView can detect
		ModelAndView model = new ModelAndView("PersonalPage");
		model.addObject("xmlSource", source);

		return model;
		//TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));
	}

}
