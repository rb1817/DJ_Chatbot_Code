package net.n1books.dev2.crol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Search2 {

	public static void main(String[] args) throws IOException {
		List<String> TestList3 = new ArrayList<String>();
	
		String newtitleName;
        String url = "https://music.bugs.co.kr/chart/track/day/total";
        Document doc = Jsoup.connect(url).get();
        int value = (int) (Math.random()*3);
		Elements tbody = doc.select("tbody");
		Elements a = tbody.select("tr");
		for (Element t3 : a) {
	        	String artist= t3.select("p[class="+"title"+"]").text();
	        	String title= t3.select("p[class="+"artist"+"]").text();
	        	TestList3.add(title+"-"+artist);
	      
	    }
	System.out.println(TestList3.get((int) (Math.random()*20))); 
		newtitleName =TestList3.get((int) (Math.random()*20));
	}

}
