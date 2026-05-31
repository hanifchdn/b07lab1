import java.io.File;
import java.util.Scanner; 
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Polynomial {
	double coef[];
	int exp[];

	public Polynomial() {
		this.coef = new double[0];
		this.exp = new int[0];
	}

	public Polynomial(double coef[], int exp[]) {
		int len = coef.length;
		this.coef = new double[len];
		this.exp = new int[len];
		for (int i = 0; i < len; i++) {
			this.coef[i] = coef[i];
			this.exp[i] = exp[i];
		}
	}

	public Polynomial(File f) {

		try {
			Scanner input = new Scanner(f);
			String s = input.nextLine();

			if (s.isEmpty()) {
				this.coef = new double[0];
				this.exp = new int[0];
				return;
			}

			if (s.charAt(0) != '-') s = "\\+" + s; 
			
			String arr[] = s.split("[+-]");
			int id = 0, cnt = 1, len = arr.length;

			this.coef = new double[len - 1];
			this.exp = new int[len - 1];

			while (cnt < len) {
				while (s.charAt(id) != '+' && s.charAt(id) != '-') id++;
				String alg [] = arr[cnt].split("x"); 

				if (alg.length == 0) {
					this.coef[cnt - 1] = 1;
					this.exp[cnt - 1] = 1;
				}
				else if (alg.length == 2) {
					this.coef[cnt - 1] = Double.parseDouble(alg[0]);
					this.exp[cnt - 1] = Integer.parseInt(alg[1]);
				}
				else if (s.charAt(id + 1) == 'x') {
					this.coef[cnt - 1] = 1;
					this.exp[cnt - 1] = Integer.parseInt(alg[1]);
				} 
				else {
					this.coef[cnt - 1] = Double.parseDouble(alg[0]);
					if (alg[0].length() == arr[cnt].length()) this.exp[cnt - 1] = 0;
					else this.exp[cnt - 1] = 1;
				}
				if (s.charAt(id) == '-') this.coef[cnt - 1] *= -1;
				id++;
				cnt++;
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Error: File not found");
		}
	}

	public Polynomial add(Polynomial p) {
		double tmp[] = new double[300];
		Polynomial res = new Polynomial();
		int len1 = this.coef.length;
		int len2 = p.coef.length;

		for (int i = 0; i < len1; i++) {
			tmp[this.exp[i]] += this.coef[i];
		}
		for (int i = 0; i < len2; i++) {
			tmp[p.exp[i]] += p.coef[i];
		}

		int len = 0;
		for (int i = 0; i < 300; i++) {
			if (tmp[i] != 0) len++;
		}
		
		int cnt = 0;
		res.coef = new double[len];
		res.exp = new int[len];
		for (int i = 0; i < 300; i++) {
			if (tmp[i] != 0) {
				res.coef[cnt] = tmp[i];
				res.exp[cnt] = i;
				cnt++; 
			}
		}

		return res;
	}

	public Polynomial multiply(Polynomial p) {
		double tmp[] = new double[300];
		Polynomial res = new Polynomial();
		int len1 = this.coef.length;
		int len2 = p.coef.length;
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				tmp[this.exp[i] + p.exp[j]] += this.coef[i] * p.coef[j];
			}
		}

		int len = 0;
		for (int i = 0; i < 300; i++) {
			if (tmp[i] != 0) len++;
		}
		
		int cnt = 0;
		res.coef = new double[len];
		res.exp = new int[len];
		for (int i = 0; i < 300; i++) {
			if (tmp[i] != 0) {
				res.coef[cnt] = tmp[i];
				res.exp[cnt] = i;
				cnt++; 
			}
		}

		return res;
	}

	public double evaluate(double x) {
		int len = this.coef.length, cnt = 0;
		double res = 0, mult = 1;
		for (int i = 0; i < len; i++) {
			while (cnt < this.exp[i]) {
				mult *= x;
				cnt++;
			}
			res += mult * this.coef[i];
		}
		return res;
	}

	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0;
	}

	public void saveToFile(String s) {
		String res = "";
		for (int i = 0; i < this.coef.length; i++) {
			if (i != 0 && this.coef[i] >= 0) res += "+";
			if (this.exp[i] == 0) res += Double.toString(this.coef[i]);
			else if (this.exp[i] == 1) res += Double.toString(this.coef[i]) + "x";
			else {
				res += Double.toString(this.coef[i]) + "x" + Integer.toString(this.exp[i]);
			}
		}
		File f = new File(s);
		try {
			if (f.createNewFile()) {
				System.out.println("File Created");
			}
			else {
				System.out.println("File Already Exists");
			}
			PrintStream output = new PrintStream(s);
			output.println(res);
		}
		catch (IOException e) {
			System.out.println("Error Occured While Creating File");
		}
	}

}