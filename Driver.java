import java.io.File;

public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,2,3,5};
		int [] e1 = {0, 1, 2, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {-2,1,-9};
		int [] e2 = {1, 2, 4};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		Polynomial t = p1.multiply(p2);
		t.saveToFile("file1.txt");
		File f = new File("file1.txt");
		Polynomial y = new Polynomial(f);
		y.saveToFile("file2.txt");
	}
}
