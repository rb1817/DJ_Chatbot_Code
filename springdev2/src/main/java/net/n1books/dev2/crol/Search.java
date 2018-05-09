package net.n1books.dev2.crol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Search {

	public static void main(String[] args) throws IOException {
		String code;
		Scanner scanner = new Scanner(System.in);
		List<String> TestList1 = new ArrayList<String>();
		List<String> TestList2 = new ArrayList<String>();
		
		System.out.print("분위기를 입력하시오 . : ");
		code = scanner.nextLine(); 
		
        String url = "https://music.bugs.co.kr/search/esalbum?q="+code+"&page=1&sort=P";
        String album = "list listView musicPDAlbumList";
        String albumInfo = "musicPDAlbumInfo";
        String albumId = "es_albumid";
        String tracklist = "list trackList";
        String isection = "item-section";
        Document doc = Jsoup.connect(url).get();
        int value = (int) (Math.random()*3);
        String titleName;

        Elements ul= doc.select("ul[class="+album+"]");// li 태그의 attribute 중 class가 ah_item인 element를 모두 찾는다.
        

        Elements li = ul.select("li");
           // element의 내용을 출력한다.

        for (Element l : li) {
        	String figure= l.select("figure[class="+albumInfo+"]").attr(albumId);
        	TestList1.add(figure);
                
        	//System.out.println(figure);
        }
		//System.out.println(TestList1.get(value));
		Document doc2 = Jsoup.connect("https://music.bugs.co.kr/musicpd/albumview/"+TestList1.get(value)+"?wl_ref=list_mab_01").get();
		Elements table= doc2.select("table[class="+tracklist+"]");
		Elements tbody = table.select("tbody");
		Elements tr = tbody.select("tr");
		for (Element t : tr) {
	        	String artist= t.select("p[class="+"artist"+"]").text();
	        	String title= t.select("p[class="+"title"+"]").text();
	        	TestList2.add(artist+"-"+title);
	                
	        	//System.out.println(artist + title);
	    }
		//System.out.println(TestList2.get((int) (Math.random()*TestList2.size()))); 
		titleName =TestList2.get((int) (Math.random()*TestList2.size()));
		System.out.println(titleName);
		return;
	}

}
