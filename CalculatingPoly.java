import arvindcheenu.math.UnivariatePoly;
public class CalculatingPoly {
	/* +--------------+
     * | DRIVER TESTS |
     * +--------------+
     */
    public static void main(String[] args) { 
        UnivariatePoly zero = new UnivariatePoly(0, 0);
        UnivariatePoly p1   = new UnivariatePoly(1, 3);
        UnivariatePoly p2   = new UnivariatePoly(-12, 2);
        UnivariatePoly p3   = new UnivariatePoly(0, 1);
        UnivariatePoly p4   = new UnivariatePoly(-42, 0);
        UnivariatePoly p    = p1.plus(p2).plus(p3).plus(p4);  	 								
        UnivariatePoly q1   = new UnivariatePoly(1, 1);
        UnivariatePoly q2   = new UnivariatePoly(-3, 0);
        UnivariatePoly q    = q1.plus(q2);														
        System.out.println("zero(x)			| " + zero);												
        System.out.println("zero(x).isZero()		|  " + zero.isZero());												
        System.out.println("p(x) 			| " + p);
        System.out.println("p(x).isMonomial()	|  " + p.isMonomial());												
        System.out.println("p(x).hasDegree(3)	|  " + p.hasDegree(3));												
        System.out.println("p(x).toArrayString	|  " + p.toArrayString());												
        System.out.println("q(x) 			| " + q);												
        System.out.println("q(x) + 20		| " + q.plus(20));									
        System.out.println("3 * q(x) 		| " + q.times(3));												
        System.out.println("q(x).toArrayString	|  " + q.toArrayString());							
        System.out.println("q(x).degree		|    " + q.degree());							
        System.out.println("p(x) + q(x)		| " + p.plus(q));												
        System.out.println("p(x) - q(x)		| " + p.minus(q));												
        System.out.println("p(x) * q(x)		| " + p.times(q));												
        System.out.println("p(x) / q(x)		| " + q.divides(p));	
        System.out.println("p(x) / q(x)		| " + p.mod(q));											
        System.out.println("0 - p(x)			| " + zero.minus(p));									
        System.out.println("p''(x)			| " + p.differentiate().differentiate().toString());	
        System.out.println("p(3)			|    " + p.evaluate(3));										
        System.out.println("p'(x)			| " + p.differential().toString());						
        System.out.println("p(3)			|    " + p.evaluate(3));										
        System.out.println("p''(x)			| " + p.differential().toString());						
   }
}