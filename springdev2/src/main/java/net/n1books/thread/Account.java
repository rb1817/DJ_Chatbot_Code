package net.n1books.thread;

public class Account {
	private int money;

	public Account(int money) {
		this.money = money;
	}

	public String getMoney() {
		return "잔액은 " + money + "만원 입니다.";
	}

	public void deposit(int money, String name) {
		this.money += money;
		System.out.println(name + "님이 " + money + "만원을 입금하였습니다.");
		System.out.println(getMoney());
	}

	public void withdraw(int money, String name) {
		if (this.money >= money) {
			Thread.yield();
			this.money -= money;
			System.out.println(name + "님이 " + money + "만원을 출금하였습니다.");

		} else {
			System.out.println(name + "님이 " + money + "만원을 출금하려 했으나 잔액이 부족합니다.");
		}
		System.out.println(getMoney());
	}
}
