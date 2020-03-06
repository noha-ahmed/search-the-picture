package eg.edu.alexu.csd.datastructure.iceHockey.cs;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

class PlayersFinderTest {

	@Test
	void test1() {
		PlayersFinder test = new PlayersFinder() ;
		String [] photo1 = {"33JUBU33"
				,"3U3O4433"
				,"O33P44NB"
				,"PO3NSDP3"
				,"VNDSD333"
				,"OINFD33X"};
		Point[] expected=new Point[3]; 
		expected[0]=new Point(4,5);
		expected[1]=new Point(13,9);
		expected[2]=new Point(14,2);
		Point[] actual = test.findPlayers(photo1 , 3 , 16 );
		assertArrayEquals( expected , actual ) ; 
	}
	@Test
	void test2() {
		PlayersFinder test = new PlayersFinder() ;
		String [] photo2= {"44444H44S4",
				"K444K4L444",
				"4LJ44T44XH",
				"444O4VIF44",
				"44C4D4U444",
				"4V4Y4KB4M4",
				"G4W4HP4O4W",
				"4444ZDQ4S4",
				"4BR4Y4A444",
				"4G4V4T4444"};
		Point[] expected=new Point[6]; 
		expected[0]=new Point(3,8);
		expected[1]=new Point(4,16);
		expected[2]=new Point(5,4);
		expected[3]=new Point(16,3);
		expected[4]=new Point(16,17);
		expected[5]=new Point(17,9);
		Point[] actual = test.findPlayers(photo2 , 4 , 16 );
		assertArrayEquals( expected , actual ) ; 
	}
	
	@Test
	void test3() {
		PlayersFinder test = new PlayersFinder() ;
		String[] photo3 = null;
		assertEquals(null,test.findPlayers(photo3,3, 4));
	}
	
	@Test
	void test4() {
		PlayersFinder test = new PlayersFinder () ;
		String[] photo4 = {"JJHDHB","77HHJK","IOJ7I7","XJKL7U","HJK7SS"};
		assertEquals(null,test.findPlayers(photo4,9,8));
	}

}
