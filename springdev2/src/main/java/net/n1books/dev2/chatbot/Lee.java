package net.n1books.dev2.chatbot;

	import java.util.Scanner;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

	public class Lee { 
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			String iam="";
			String say = "";
			Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
			service.setUsernameAndPassword("username", "password");
			
			for(;;) {
				InputData input = new InputData.Builder(iam).build();
				MessageOptions options = new MessageOptions.Builder("d678f765-98e9-40df-91ad-f21f8ecc85ea").input(input).build();
				MessageResponse response = service.message(options).execute();
				say = response.getOutput().getText().get(0);
				System.out.println("he : " + say);
				System.out.print("she : ");
				iam = sc.nextLine();
			}
		}
	}
