public class Polynomial {
	double arr[];

	public Polynomial() {
		this.arr = new double[1];
		this.arr[0] = 0;
	}

	public Polynomial(double arr[]) {
		int len = arr.length;
		this.arr = new double[len];
		for (int i = 0; i < len; i++) {
			this.arr[i] = arr[i];
		}
	}

	public Polynomial add(Polynomial p) {
		Polynomial res = new Polynomial();
		int len1 = this.arr.length;
		int len2 = p.arr.length;
		if (len1 >= len2) {
			res.arr = new double[len1];
			for (int i = 0; i < len2; i++) {
				res.arr[i] = this.arr[i] + p.arr[i];
			}
			for (int i = len2; i < len1; i++) {
				res.arr[i] = this.arr[i];
			}
		}
		else {
			res.arr = new double[len2];
			for (int i = 0; i < len1; i++) {
				res.arr[i] = this.arr[i] + p.arr[i];
			}
			for (int i = len1; i < len2; i++) {
				res.arr[i] = p.arr[i];
			}
		}

		return res;
	}

	public double evaluate(double x) {
		int len = this.arr.length;
		double res = 0, mult = 1;
		for (int i = 0; i < len; i++) {
			res += mult * this.arr[i];
			mult *= x;
		}
		return res;
	}

	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0;
	}
}