import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        int a = 1;
        int b = 2;
        int c = 3;
        assertFalse(Flik.isSameNumber(a, b));
        assertTrue(Flik.isSameNumber(a, 1));
        assertFalse(Flik.isSameNumber(a, c));
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
