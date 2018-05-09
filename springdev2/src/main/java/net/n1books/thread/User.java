package net.n1books.thread;

public class User extends Thread {
	boolean flag = false;
	// false -> 출금, true -> 입금
	
	Account account;

	public User(Account account, String name) {
		this.account = account;
		setName(name);
	}
	
	@Override
	public void run() {
		for(int i=1; i <=2; i++, flag = !flag) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (flag) {	//입금
				account.deposit(
						(int)(Math.random() * 20 + 6)*100, getName());
			} else {	//출금
				account.withdraw(
						(int)(Math.random() * 20 + 6)*100, getName());
			}
		}
	}
}

