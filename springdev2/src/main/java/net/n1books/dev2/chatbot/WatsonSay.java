package net.n1books.dev2.chatbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.conversation.v1.model.OutputData;

@RestController
@RequestMapping("chatbot")
public class WatsonSay {
	private static final Logger logger = LoggerFactory.getLogger(WatsonSay.class);

	@RequestMapping("watsonsay")
	public MessageResponse watsonsay(String isay, HttpSession session) throws IOException {
		logger.info("user input : " + isay);

		Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setUsernameAndPassword("username", "password");

		MessageOptions options = null;
		if (!isay.equals("")) {
			options = new MessageOptions.Builder("d678f765-98e9-40df-91ad-f21f8ecc85ea")
					.input(new InputData.Builder(isay).build()).context((Context) session.getAttribute("context"))
					.build();
		} else {
			options = new MessageOptions.Builder("d678f765-98e9-40df-91ad-f21f8ecc85ea")
					.input(new InputData.Builder(isay).build()).build();
		}

		MessageResponse response = service.message(options).execute();
		session.setAttribute("context", response.getContext());
		logger.info(response.toString());
		session.invalidate();
		if ((response.getContext().get("title") != null && response.getIntents().get(0).getIntent().equals("기분"))
				|| (response.getContext().get("title") != null && response.getIntents().get(0).getIntent().equals("장르"))
				|| (response.getContext().get("title") != null && response.getIntents().get(0).getIntent().equals("테마"))
				|| (response.getContext().get("title") != null && response.getIntents().get(0).getIntent().equals("시대"))) { // context의 값이 title 이고,intent값이 기분
																						// 일때만!

			List<String> TestList1 = new ArrayList<String>();
			List<String> TestList2 = new ArrayList<String>();

			String url = "https://music.bugs.co.kr/search/esalbum?q=" + isay + "&page=1&sort=P";
			String album = "list listView musicPDAlbumList";
			String albumInfo = "musicPDAlbumInfo";
			String albumId = "es_albumid";
			String tracklist = "list trackList";
			Document doc = Jsoup.connect(url).get();
			int value = (int) (Math.random() * 3);
			String titleName;

			Elements ul = doc.select("ul[class=" + album + "]");// ul 태그의 attribute 중 class가 album인 element를 모두 찾는다.

			Elements li = ul.select("li");
			// element의 내용을 출력한다.

			for (Element l : li) {
				String figure = l.select("figure[class=" + albumInfo + "]").attr(albumId);
				TestList1.add(figure);

				// System.out.println(figure);
			}
			// System.out.println(TestList1.get(value));
			Document doc2 = Jsoup.connect(
					"https://music.bugs.co.kr/musicpd/albumview/" + TestList1.get(value) + "?wl_ref=list_mab_01").get();
			Elements table = doc2.select("table[class=" + tracklist + "]");
			Elements tbody = table.select("tbody");
			Elements tr = tbody.select("tr");
			for (Element t : tr) {
				String artist = t.select("p[class=" + "artist" + "]").text();
				String title = t.select("p[class=" + "title" + "]").text();
				TestList2.add(artist + "-" + title);

				// System.out.println(artist + title);
			}
			// System.out.println(TestList2.get((int) (Math.random()*TestList2.size())));
			titleName = TestList2.get((int) (Math.random() * TestList2.size()));
			OutputData op = new OutputData(); // output 인스턴스 선언

			List<String> list = new ArrayList<String>();
			list.add(titleName);
			op.setText(list); // 리스트에서 text를 가져와서 op에 넣는다.

			response.setOutput(op);// 리스폰스의 아웃풋을 op로.
			System.out.println(response.getIntents().get(0));
			return response;
		} else if ((response.getContext().get("title") != null
				&& response.getIntents().get(0).getIntent().equals("최신"))) {
			List<String> TestList3 = new ArrayList<String>();
			String newtitleName;
			String url = "https://music.bugs.co.kr/chart/track/day/total";
			Document doc = Jsoup.connect(url).get();
			Elements tbody = doc.select("tbody");
			Elements a = tbody.select("tr");
			for (Element t3 : a) {
				String artist = t3.select("p[class=" + "title" + "]").text();
				String title = t3.select("p[class=" + "artist" + "]").text();
				TestList3.add(title + "-" + artist);

			}
			System.out.println(TestList3.get((int) (Math.random() * 20)));
			newtitleName = TestList3.get((int) (Math.random() * 20));
			OutputData op = new OutputData(); // output 인스턴스 선언
			List<String> list = new ArrayList<String>();
			list.add(newtitleName); // 리스트 내의 저장
			op.setText(list); // 리스트에서 text를 가져와서 op에 넣는다.
			response.setOutput(op);// 리스폰스의 아웃풋을 op로.
			return response;
		} else {
			return response;
		}

	}
}
