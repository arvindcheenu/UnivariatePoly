package arvindcheenu.math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnivariatePoly {
    private Node first = new Node(0, 0);
    private Node last  = first;
    private static class Node {
        int coef;
        int exp;
        Node next;
        Node(int coef, int exp) {
            this.coef = coef;
            this.exp  = exp;
        }
    }
    private UnivariatePoly() { }
    public UnivariatePoly(int coef, int exp) {
        last.next = new Node(coef, exp);
        last = last.next;
    }
    public int getCoef (int exp) {
    		UnivariatePoly a = this;
    		int coeff = 0;
        Node x = a.first.next;
        while (x != null) {
        		if (x.exp == exp) {
        			coeff = x.coef;
        		}
        		else {
        			x = x.next;
        		}
        }
    		return coeff;
    }
    
    public UnivariatePoly setCoef (int exp, int coef) {
		UnivariatePoly a = this;
		Node x = a.first.next;
		while (x != null) {
	    		if (x.exp == exp) {
	    			x.coef = coef;
	    		}
	    		else {
	    			x = x.next;
	    		}
		}
		return this;
    }
    
    public boolean isZero() {
    		return (this.last.exp == 0 && this.last.coef == 0) ? true : false;
    }
    
    public boolean isMonomial() {
		return (this.first.next == null) ? true : false;
    }
    
    public boolean hasDegree (int n) {
    		return (this.degree() == n) ? true : false; 
    }
    
    public UnivariatePoly plus(UnivariatePoly b) {
        UnivariatePoly a = this;
        UnivariatePoly c = new UnivariatePoly();
        Node x = a.first.next;
        Node y = b.first.next;
        while (x != null || y != null) {
            Node t = null;
            if      (x == null)     { t = new Node(y.coef, y.exp);  y = y.next; }
            else if (y == null)     { t = new Node(x.coef, x.exp);  x = x.next; }
            else if (x.exp > y.exp) { t = new Node(x.coef, x.exp);  x = x.next; } 
            else if (x.exp < y.exp) { t = new Node(y.coef, y.exp);  y = y.next; } 
            else {
                int coef = x.coef + y.coef;
                int exp  = x.exp;
                x = x.next;
                y = y.next;
                if (coef == 0) continue;
                t = new Node(coef, exp);
            }
            c.last.next = t;
            c.last = c.last.next;
        }
        return c;
    }
    
    public UnivariatePoly plus(int number) {
		return this.plus(new UnivariatePoly(number,0));
    }
    
    public UnivariatePoly minus(UnivariatePoly b) {
        UnivariatePoly a = this;
        UnivariatePoly c = new UnivariatePoly();
        Node x = a.first.next;
        Node y = b.first.next;
        while (x != null || y != null) {
            Node t = null;
            if      (x == null)     { t = new Node(y.coef, y.exp);  y = y.next; }
            else if (y == null)     { t = new Node(x.coef, x.exp);  x = x.next; }
            else if (x.exp > y.exp) { t = new Node(x.coef, x.exp);  x = x.next; } 
            else if (x.exp < y.exp) { t = new Node(y.coef, y.exp);  y = y.next; } 
            else {
                int coef = x.coef - y.coef;
                int exp  = x.exp;
                x = x.next;
                y = y.next;
                if (coef == 0) continue;
                t = new Node(coef, exp);
            }
            c.last.next = t;
            c.last = c.last.next;
        }
        return c;
    }
    
    public UnivariatePoly minus(int number) {
		return this.minus(new UnivariatePoly(number,0));
    }
    
    public UnivariatePoly times(UnivariatePoly b) {
        UnivariatePoly a = this;
        UnivariatePoly c = new UnivariatePoly();
        for (Node x = a.first.next; x!= null; x = x.next) {
            UnivariatePoly temp = new UnivariatePoly();
            for (Node y = b.first.next; y!= null; y = y.next) {
                temp.last.next = new Node(x.coef * y.coef, x.exp + y.exp);
                temp.last = temp.last.next;
            }
            c = c.plus(temp);
        }
        return c;
    }
    
    public UnivariatePoly times (int number) {
    		return this.times(new UnivariatePoly(number,0));
    }
    
    public int evaluate(int x) {
    		int result = 0;
        for (Node i = first.next; i != null; i = i.next) {
        		result += i.coef * (Math.pow(x,i.exp));
        }
    		return result;
    }
    
    public UnivariatePoly differential() { 
		UnivariatePoly a = this;
        for (Node i = first.next; i != null; i = i.next) {
        		i.coef *= i.exp;
        		i.exp -= 1;
        }
    		return a;
    }
    
    public UnivariatePoly differentiate() { 
		UnivariatePoly b = new UnivariatePoly();
		Node x = b.last;
        for (Node i = first.next; i != null; i = i.next) {
        		x.next = new Node (i.coef * i.exp, i.exp - 1);
        		x = x.next;
        }
    		return b;
    }
    
    public Vector<Integer> toVector () {
    		int p_array[] = this.toArray();
    		Vector<Integer> result = new Vector<Integer> (this.degree());
    		for (int i = 0; i < p_array.length; i++) {
    			result.add(i, p_array[i]);
    		}
    		return result;
    }
    
    public Vector<Integer> toVector (int n) {
		int p_array[] = this.toArray();
		Vector<Integer> result = initZero(n);
		for (int i = 0; i < p_array.length; i++) {
			result.set(i, p_array[i]);
		}
		return result;
    }
    
    public static int degree (Vector<Integer> A) {
    		int degree = 0;
    		for (int i= A.size()-1; i >= 0; i--) {
    			if (A.elementAt(i) != 0) {
    				degree = i;
    				break;
    			}
    			else {
    				continue;
    			}
    		}
    		return degree;
    }
    private static Vector<Integer> initZero (int n){
		Vector<Integer> q = new Vector<Integer> ();
		for (int i = 0; i < n; i++) q.add(i, 0);
		return q;
    }
    private static Vector<Integer> multiply (Vector<Integer> V, int scalar) {
    		for (int i = 0; i < V.size(); i++) {
    			V.set(i, V.get(i) * scalar);
    		}
    		return V;
    }
    private static Vector<Integer> subtract (Vector<Integer> A, Vector<Integer> B) {
    		for (int i = 0; i < A.size(); i++) {
    			A.set(i,A.get(i)-B.get(i));
		}
		return A;
    }
    private static Vector<Integer> shiftRight (Vector<Integer> input, int amount) {
        if (input.isEmpty())
            return new Vector<Integer>();
        int shift = (amount % input.size() + input.size()) % input.size();
        if (shift == 0)
            return new Vector<Integer>(input);
        Vector<Integer> v = new Vector<Integer>(input.size());
        v.addAll(input.subList(input.size() - shift, input.size()));
        v.addAll(input.subList(0, input.size() - shift));
        return v;
    }
    
    public Vector<UnivariatePoly> divmod (UnivariatePoly b) {
    		UnivariatePoly a = this;
    		Vector<Integer> N = b.toVector();
    		Vector<Integer> D = a.toVector(N.size());
    		Vector<Integer> q = initZero(N.size());
    		Vector<Integer> r = initZero(N.size());
    		Vector<Integer> d = initZero(N.size());
    		if (degree(D) < 0) {
    			throw new ArithmeticException("Division By Zero");
    		}
    		while (degree(N) >= degree(D)) {
    			d = shiftRight(D,degree(N) - degree(D));
    			q.set((degree(N) - degree(D)), N.get(degree(N)) / d.get(degree(d)));
    			d = multiply(d,q.get(degree(N) - degree(D)));
    			subtract(N,d);
    		}
    		r = N;
    		Vector<UnivariatePoly> array = new Vector<UnivariatePoly> (2);
    		array.add(toUnivariatePoly(q));
    		array.add(toUnivariatePoly(r));
        return array;
    }
    
    public UnivariatePoly divides (UnivariatePoly B) {
    		return this.divmod(B).get(0);
    }
    
    public UnivariatePoly mod (UnivariatePoly B) {
		return B.divmod(this).get(1);
    }
    
	public int degree() {
		int exp = 0;
		for (Node i = this.first.next; i != null; i = i.next) 
			if (i.exp > exp)
				exp = i.exp;
		return exp;
	}
	public static UnivariatePoly toUnivariatePoly(String output) {
		UnivariatePoly p = new UnivariatePoly();
		Node x = p.last;
		List<String> allmatches = new ArrayList<String>();
		Matcher m = Pattern.compile("(\\d+)").matcher(output);
		while (m.find()) allmatches.add(m.group());
		String[] array = allmatches.toArray(new String[0]);
		for (int i = 0; i < array.length; i+= 2) {
			x.next = new Node (Integer.parseInt(array[i]),Integer.parseInt(array[i+1]));
			x = x.next;
		}
		return p;
	}
	
	public static UnivariatePoly toUnivariatePoly(Vector<Integer> array) {
		UnivariatePoly p = new UnivariatePoly();
		Node x = p.last;
        for (int i = array.size()-1; i >=0 ; i--) {
        		x.next = new Node (array.get(i), i);
    			x = x.next;
        }
		return p; 
	}
	
	public static UnivariatePoly toUnivariatePoly(int[] array) {
		UnivariatePoly p = new UnivariatePoly();
		Node x = p.last;
        for (int i = array.length-1; i >=0 ; i--) {
        		if (array[i] != 0)
        			x.next = new Node (array[i],i);
    			x = x.next;
        }
		return p; 
	}
	
	public int[] toArray() {
		int terms = this.degree() + 1;
		int[] poly = new int [terms];
		for (int i = 0; i < terms; i++ ) poly[i] = 0;
		for (Node i = first.next; i != null; i = i.next) {
    			poly[i.exp] = i.coef;
		}
		return poly;
	}
	
	public String toArrayString() {
		return Arrays.toString(this.toArray());
	}

	public String toString() {
        String s = "";
        for (Node x = first.next; x != null; x = x.next) {
            if      (x.coef > 0) s = s + " + " +   x.coef  + "x^" + x.exp;
            else if (x.coef < 0) s = s + " - " + (-x.coef) + "x^" + x.exp;
        }
        return s;
    }
}