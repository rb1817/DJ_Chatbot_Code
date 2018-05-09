package net.n1books.dev2.ThreadTest;

public class ThreadTest2 implements Runnable{
	public static void main(String[] args) {
		Thread r = new Thread(new ThreadTest2());
		r.start();
	
		for(int i=1; i<5; i++) {
			System.out.println("main"+i);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("main 종료");
	}
	public void run() {
		for(int i=1; i<5; i++) {
			System.out.println("run"+i);
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
