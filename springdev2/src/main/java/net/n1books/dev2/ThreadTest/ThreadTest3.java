package net.n1books.dev2.ThreadTest;

public class ThreadTest3 extends Thread{
	public static void main(String[] args) {
		Thread t1 = new  ThreadTest3("t1");
		Thread t2 = new  ThreadTest3("t2");
		Thread t3 = new  ThreadTest3("t3");
		Thread t4 = new  ThreadTest3("t4");
		Thread t5 = new  ThreadTest3("t5");
		Thread t6 = new  ThreadTest3("t6");
		Thread t7 = new  ThreadTest3("t7");
		Thread t8 = new  ThreadTest3("t8");
		Thread t9 = new  ThreadTest3("t9");
/*		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");*/
		
		t1.setPriority(9);
		t2.setPriority(8);
		t3.setPriority(7);
		t4.setPriority(6);
		t5.setPriority(5);
		t6.setPriority(4);
		t7.setPriority(3);
		t8.setPriority(2);
		t9.setPriority(1);
		//System.out.println(Thread.currentThread().getName());
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
	}
	public ThreadTest3(String name) {
		super(name);
	}
	public void run() {
		for(int i=1; i<5; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}System.out.println(Thread.currentThread().getName()+"종료");
	}

}
