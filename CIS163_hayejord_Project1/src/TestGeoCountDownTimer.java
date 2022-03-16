
import org.junit.Test;
import static org.junit.Assert.*;


/**********************************************************************
 * CIS163 Project 1: Count Down Timer
 * Testing Class: The following are simple JUnit test cases.
 * @author Jordan Hayes
 * @version 1
 **********************************************************************/

public class TestGeoCountDownTimer {


    @Test
    public void testConstructor1() {
        GeoCountDownTimer s = new GeoCountDownTimer(2121, 5, 10);
        assertEquals(s.toDateString(), "5/10/2121");

        s = new GeoCountDownTimer(2121, 1, 11);
        assertEquals(s.toDateString(), "1/11/2121");

        GeoCountDownTimer s2 = new GeoCountDownTimer(2121, 12,29);
        assertEquals(s2.toDateString(), "12/29/2121");
    }


    @Test
    public void testConstructor2() {
        GeoCountDownTimer s = new GeoCountDownTimer("5/10/2121");
        assertTrue(s.toDateString().equals("5/10/2121"));

    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructor3() {
        GeoCountDownTimer s = new GeoCountDownTimer("2/29/2121");
        assertTrue(s.toDateString().equals("2/29/2020"));

    }


    @Test
    public void testAddMethod() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 5, 10);
        s1.inc(365);
        assertTrue(s1.toDateString().equals("5/10/2122"));

        s1 = new GeoCountDownTimer(2120, 1, 10);

        for (int i = 0; i < 366; i++)
            s1.inc();
        assertTrue(s1.toDateString().equals("1/10/2121"));

        s1 = new GeoCountDownTimer(2022, 5, 10);

        for (int i = 0; i < 31665; i++)
            s1.inc();

        for (int i = 0; i < 31665; i++)
            s1.dec();

        assertTrue(s1.toDateString().equals("5/10/2022"));



        s1 = new GeoCountDownTimer(2024, 4, 10);

        s1.dec(366);
        assertTrue(s1.toDateString().equals("4/10/2023"));
    }


    // must have a separate test for each Exception
    @Test(expected = IllegalArgumentException.class)
    public void testContuctor1() {
        new GeoCountDownTimer(2, -3, -3);
    }

    //test less than min year
    @Test(expected = IllegalArgumentException.class)
    public void testContuctor1_1() {
        new GeoCountDownTimer(2017, 3, 3); }

    //test less than 0 in day
    @Test(expected = IllegalArgumentException.class)
    public void testContuctor1_2() {
        new GeoCountDownTimer(2222, 3, -3); }

    //test less than 0 in month
    @Test(expected = IllegalArgumentException.class)
    public void testContuctor1_3() {
        new GeoCountDownTimer(2222, -3, 3); }

    //test Feb 29 in not a leap year
    @Test(expected = IllegalArgumentException.class)
    public void testContuctor1_4() {
        new GeoCountDownTimer(2222, 2, 29); }

    //test not valid data
    @Test(expected = IllegalArgumentException.class)
    public void testContuctor2() {
        new GeoCountDownTimer("2,-3/-3");
    }

    //test not valid data
    @Test(expected = IllegalArgumentException.class)
    public void testContuctor2_1() {
        new GeoCountDownTimer("2$$$-3 -3");
    }

    //test not valid data
    @Test(expected = IllegalArgumentException.class)
    public void testContuctor2_2() {
        new GeoCountDownTimer("222@#$$%");
    }

    //test nothing inside parsing array
    @Test(expected = IllegalArgumentException.class)
    public void testContuctor2_3() {
        new GeoCountDownTimer("////");
    }

    //letters between slashes
    @Test(expected = IllegalArgumentException.class)
    public void testContuctor2_4() {
        new GeoCountDownTimer("mm/dd/yyy");
    }

    //test error for trying to increment or decrement a negative number
    @Test(expected = IllegalArgumentException.class)
    public void testAddMethod_error() {

        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 5, 10);
         s1.inc(-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractMethod_error() {

        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 5, 10);
        s1.dec(-3);
    }

    @Test
    public void testEqual() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(3000, 5, 9);
        GeoCountDownTimer s2 = new GeoCountDownTimer(2121, 6, 1);
        GeoCountDownTimer s3 = new GeoCountDownTimer(2121, 5, 5);
        GeoCountDownTimer s4 = new GeoCountDownTimer(3000, 5, 9);
        GeoCountDownTimer s5 = new GeoCountDownTimer(3000, 5, 9);

        assertEquals(s4, s5);
        assertFalse(s1.equals(s2));
        assertTrue(s1.equals(s4));


    }

    @Test
    public void testCompareTo() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 5, 9);
        GeoCountDownTimer s2 = new GeoCountDownTimer(2121, 6, 1);
        GeoCountDownTimer s3 = new GeoCountDownTimer(2121, 5, 8);
        GeoCountDownTimer s4 = new GeoCountDownTimer(2121, 5, 9);

        assertTrue(s2.compareTo(s1) > 0);
        assertTrue(s3.compareTo(s1) < 0);
        assertTrue(s1.compareTo(s4) == 0);

    }

    @Test
    public void testLoadSave() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 9, 1);
        GeoCountDownTimer s2 = new GeoCountDownTimer(2121, 9, 1);

        s1.save("file1");
        s1 = new GeoCountDownTimer(2111, 1, 1);  // resets
        s1.load("file1");
        assertTrue(s2.equals(s1));

    }

    @Test
    public void testDaysToGo() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 2, 9);
        assertTrue(s1.daysToGo("2/1/2121") == 8);
        assertTrue(s1.daysToGo("2/8/2121") == 1);
        assertTrue(s1.daysToGo("2/9/2121") == 0);
        assertTrue(-(5) == s1.daysToGo("2/14/2121"));

    }

    @Test
    public void testDaysInFuture() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 12,9);
        GeoCountDownTimer s2 = new GeoCountDownTimer(2121, 12,19);

        assertTrue (s1.daysInFuture(10).equals(s2));
    }

    @Test
    public void testDaysInFuture_1() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 12,9);
        GeoCountDownTimer s2 = new GeoCountDownTimer(2121, 12,29);

        assertTrue (s1.daysInFuture(20).equals(s2));
    }

    @Test
    public void testDaysInFuture_2() {
        GeoCountDownTimer s1 = new GeoCountDownTimer(2121, 12,9);
        GeoCountDownTimer s2 = new GeoCountDownTimer(2122, 1,30);

        assertTrue (s1.daysInFuture(52).equals(s2));
    }

}
