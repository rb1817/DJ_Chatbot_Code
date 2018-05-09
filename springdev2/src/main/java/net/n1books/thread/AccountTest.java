package net.n1books.thread;


public class AccountTest {
	public static void main(String[] args) {
		Account account = new Account(2500);
		
		User user1 = new User(account, "유비");
		User user2 = new User(account, "관우");
		User user3 = new User(account, "장비");
		
		user1.start();
		user2.start();
		user3.start();
	}
}
