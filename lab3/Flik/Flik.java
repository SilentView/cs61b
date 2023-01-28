/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        //since the input is seen as reference type Integer
        //we should its method to compare. Using == may result
        //in strange problem, which we needn't know
        return a.equals(b);
    }
}
