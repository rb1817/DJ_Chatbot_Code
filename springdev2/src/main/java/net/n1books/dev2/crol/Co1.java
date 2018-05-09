package net.n1books.dev2.crol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class Co1 {

	public static void main(String[] args) throws IOException {
		String code;
		Scanner scanner = new Scanner(System.in);
		List<String> TestList = new ArrayList<String>();
		
		System.out.print("분위기를 입력하시오 . : ");
		code = scanner.nextLine(); 
		
        String url = "https://music.bugs.co.kr/search/esalbum?q="+code+"&page=1&sort=P";
        String album = "list listView musicPDAlbumList";
        String albumInfo = "musicPDAlbumInfo";
        String albumId = "es_albumid";
        Document doc = Jsoup.connect(url).get();
        int value = (int) (Math.random()*3);


        Elements ul= doc.select("ul[class="+album+"]");// li 태그의 attribute 중 class가 ah_item인 element를 모두 찾는다.
        

        Elements li = ul.select("li");
           // element의 내용을 출력한다.

        for (Element l : li) {
        	String figure= l.select("figure[class="+albumInfo+"]").attr(albumId);
        	TestList.add(figure);
                
        	System.out.println(figure);
        }
		System.out.println(TestList.get(value));

    }

}

