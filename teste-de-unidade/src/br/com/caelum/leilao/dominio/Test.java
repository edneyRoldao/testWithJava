package br.com.caelum.leilao.dominio;

public class Test {

	public static void main(String[] args) {

		callWithTry();
		System.out.println("-----------------------------");
		withdrawTry();

	}

	public static void callWithTry() {
		for (int i = 0; i < 10; i++) {
			try {
				int a = 10;
				if (i == 5)
					a = a / 0;
				System.out.println("int i = " + i);
			} catch (Exception e) {

			}
		}
	}

	public static void withdrawTry() {
		for (int i = 0; i < 10; i++) {
			int a = 10;
			if (i == 5)
				a = a / 0;
			System.out.println("int i = " + i);
		}
	}

}
