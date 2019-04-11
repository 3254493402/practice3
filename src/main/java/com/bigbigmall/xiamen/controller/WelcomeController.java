package com.bigbigmall.xiamen.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author P-C Lin (a.k.a 高科技黑手)
 */
@Controller
@RequestMapping("/aaa")
public class WelcomeController {

	@RequestMapping("/")
	@ResponseBody
	void index(HttpServletResponse response) throws ParserConfigurationException, TransformerConfigurationException, IOException, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

		//节点document（最高）
		Element documentElement = document.createElement("document");
		document.appendChild(documentElement);
		documentElement.setAttribute("user", "me");

		//创建stories（第二）
		Element stories = document.createElement("stories");
		documentElement.appendChild(stories);
		stories.setAttribute("status", "200");
		
		
		
		
		//第一个story（第三级）
		Element storyOne = document.createElement("story");
		stories.appendChild(storyOne);
		
		//创建story下一级第一个comments
		Element comments1 = document.createElement("comments");
		storyOne.appendChild(comments1);
		
		//创建第comments下一级comment
		Element comment1 = document.createElement("comment");
		comments1.appendChild(comment1);
		//id（同级）
		Element comment1id = document.createElement("id");
		comment1.appendChild(comment1id);
		comment1id.setTextContent("defad05a-0eb1-4545-b4aa-39460f174fbc");
		//content（同级）
		Element content1 = document.createElement("content");
		comment1.appendChild(content1);
		content1.setTextContent("這是一篇好文章阿");
		
		//who（同级）
		Element who1 = document.createElement("who");
		comment1.appendChild(who1);
		//who下一级nickname
		Element nickname1 = document.createElement("nickname");
		who1.appendChild(nickname1);
		nickname1.setTextContent("pc");
		//who下一级id
		Element whoid = document.createElement("id");
		who1.appendChild(whoid);
		whoid.setTextContent("7f84cc84-bb50-4cdf-8578-ef2827d35726");
		
		
		
		
		//创建story下一级第二个comments
		Element comments2 = document.createElement("comments");
		storyOne.appendChild(comments2);
		
		//创建第comments下一级comment
		Element comment2 = document.createElement("comment");
		comments2.appendChild(comment2);
		//id（同级）
		Element comment2id = document.createElement("id");
		comment2.appendChild(comment2id);
		comment2id.setTextContent("8ee5d256-a4e9-4c67-a5d0-0699d9b0cbd7");
		//content（同级）
		Element content2 = document.createElement("content");
		comment2.appendChild(content2);
		content2.setTextContent("家惠什麼時候要開始減肥呀");
		
		//who（同级）
		Element who2 = document.createElement("who");
		comment2.appendChild(who2);
		//who下一级nickname
		Element nickname2 = document.createElement("nickname");
		who2.appendChild(nickname2);
		nickname2.setTextContent("pc");
		//who下一级id
		Element whoid2 = document.createElement("id");
		who2.appendChild(whoid2);
		whoid2.setTextContent("7f84cc84-bb50-4cdf-8578-ef2827d35726");
		
		
		
		//与comments同级的emotions
		Element emotions1 = document.createElement("emotions");
		storyOne.appendChild(emotions1);
		emotions1.setTextContent("0");
		
		//与comments同级的postedAt
		Element postedAt1 = document.createElement("postedAt");
		storyOne.appendChild(postedAt1);
		postedAt1.setTextContent("2014-02-11T00:00:00+08:00");
		
		//与comments同级的author
		Element storyOneAuthor = document.createElement("author");
		storyOne.appendChild(storyOneAuthor);
		//author下的nickname
		Element authornickname1 = document.createElement("nickname");
		storyOneAuthor.appendChild(authornickname1);
		authornickname1.setTextContent("redan");
		//author下的id
		Element authorid1 = document.createElement("id");
		storyOneAuthor.appendChild(authorid1);
		authorid1.setTextContent("e4b6a337-6647-4521-ac9a-c4e0a3853626");
		
		//与comments同级的id
		Element storyOneId = document.createElement("id");
		storyOne.appendChild(storyOneId);
		storyOneId.setTextContent("190cc757-7a0e-4712-97c9-bf5f77fd5a8e");
		
		//与comments同级的content
		Element storyOneContent = document.createElement("content");
		storyOne.appendChild(storyOneContent);
		storyOneContent.setTextContent("this is content 這是一篇文章");
		
		
		
		//第二个story
		Element storyTwo = document.createElement("story");
		stories.appendChild(storyTwo);
		
		//创建emotions
		Element emotions2 = document.createElement("emotions");
		storyTwo.appendChild(emotions2);
		emotions2.setTextContent("0");
		
		//创建与emotions同级的postedAt
		Element postedAt2 = document.createElement("postedAt");
		storyTwo.appendChild(postedAt2);
		postedAt2.setTextContent("2019-01-30 03:08:30.556067");
		
		//author
		Element storyTwoAuthor = document.createElement("author");
		storyTwo.appendChild(storyTwoAuthor);
		//author下的nickname
		Element authornickname2 = document.createElement("nickname");
		storyTwoAuthor.appendChild(authornickname2);
		authornickname2.setTextContent("阿惠");
		//author下的id
		Element authorid2 = document.createElement("id");
		storyTwoAuthor.appendChild(authorid2);
		authorid2.setTextContent("fdb53388-243b-11e9-b2d7-23d188ab349e");
		
		//第二个story下的id
		Element storyTwoId = document.createElement("id");
		storyTwo.appendChild(storyTwoId);
		storyTwoId.setTextContent("72ff2337-1b0b-473c-8120-6ff028632806");
		
		//第二个story下的content
		Element storyTwoContent = document.createElement("content");
		storyTwo.appendChild(storyTwoContent);
		storyTwoContent.setTextContent("路，就是一條直直的。認同請按讚，不認同請分享");
		
		
		
		//第三个story
		Element storyStr = document.createElement("story");
		stories.appendChild(storyStr);
		
		//创建emotions
		Element emotions3 = document.createElement("emotions");
		storyStr.appendChild(emotions3);
		emotions3.setTextContent("0");
		
		//创建与emotions同级的postedAt
		Element postedAt3 = document.createElement("postedAt");
		storyStr.appendChild(postedAt3);
		postedAt3.setTextContent("2019-01-30 03:08:54.812136");
		
		//author
		Element storyStrAuthor = document.createElement("author");
		storyStr.appendChild(storyStrAuthor);
		//author下的nickname
		Element authornickname3 = document.createElement("nickname");
		storyStrAuthor.appendChild(authornickname3);
		authornickname3.setTextContent("生仔");
		//author下的id
		Element authorid3 = document.createElement("id");
		storyStrAuthor.appendChild(authorid3);
		authorid3.setTextContent("f45f0aa2-243b-11e9-b2d7-7339b94259f1");
		
		//第三个story下的id
		Element storyStrId = document.createElement("id");
		storyStr.appendChild(storyStrId);
		storyStrId.setTextContent("5bc82c97-14e7-44ad-91ea-8941a12eae43");
		
		//第三个story下的content
		Element storyStrContent = document.createElement("content");
		storyStr.appendChild(storyStrContent);
		storyStrContent.setTextContent("累、累、累！");
		
		TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(response.getOutputStream()));
	}
	
	
	
	
}
