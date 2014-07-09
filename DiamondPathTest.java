import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Mithilfe dieser Klasse können Sie ihre Implementierung testen. Ein
 * erfolgreiches bestehen aller Tests dieser Klasse garantiert aber noch keine
 * Punkte im Praktomaten, da dort eine andere Matrix getestet wird. Außerdem
 * wird im Praktomaten auch das Einhalten eines Zeitlimits überprüft.
 */
public class DiamondPathTest {
	
	@Test
	public void testCase0() {
		int[][] matrix = {
            {1}
		};
		int[][] arg = clone2D(matrix);
		DiamondPath p = new DiamondPath(arg);
		assertEquals(0, p.getPath().length());
		assertEquals(0, occurrences(p.getPath(), 'U'));
		assertEquals(0, occurrences(p.getPath(), 'R'));
		assertEquals(p.getSum(), cost(p.getPath(), matrix));
		
		assertEquals(1, p.getSum());
	}
	
	@Test
	public void testCase1() {
		int[][] matrix = {
            {1, 3},
            {2, 1}
		};
		int[][] arg = clone2D(matrix);
		DiamondPath p = new DiamondPath(arg);
		assertEquals(2, p.getPath().length());
		assertEquals(1, occurrences(p.getPath(), 'U'));
		assertEquals(1, occurrences(p.getPath(), 'R'));
		assertEquals(p.getSum(), cost(p.getPath(), matrix));
		
		assertEquals(5, p.getSum());
	}

	@Test
	public void testCase2() {
		int[][] matrix = {
            {0, 1, 1, 1, 1, 1},
            {1, 2, 1, 1, 6, 1},
            {1, 1, 1, 1, 1, 1}
		};
		int[][] arg = clone2D(matrix);
		DiamondPath p = new DiamondPath(arg);
		assertEquals(7, p.getPath().length());
		assertEquals(2, occurrences(p.getPath(), 'U'));
		assertEquals(5, occurrences(p.getPath(), 'R'));
		assertEquals(p.getSum(), cost(p.getPath(), matrix));
		
		assertEquals(13, p.getSum());
	}
	
	@Test
	public void testCase3() {
		int[][] matrix = {
            {2, -1, 10, -6, -5, 3, -1, 1, -8, -5},
            {-8, 9, 9, 3, 10, -1, -1, 8, -3, 7},
            {8, -3, -2, -1, -1, 0, -7, 2, 8, -5},
            {3, -2, -9, -3, -7, -10, 0, 10, -9, 1},
            {5, 5, 0, 4, 5, -6, 3, -6, 9, 10},
            {3, 8, -3, 3, 9, -9, -3, -4, 0, 10},
            {10, -9, 4, -7, -6, -7, 10, -9, -7, 10},
            {-4, 1, 8, -6, -8, 9, -1, 8, 8, -3},
            {-6, -10, 2, 8, 1, 3, 6, 4, 5, 2},
            {8, -4, 1, -5, 4, 8, -3, 2, -1, 2}
		};
				
		int[][] arg = clone2D(matrix);
		DiamondPath p = new DiamondPath(arg);
		assertEquals(18, p.getPath().length());
		assertEquals(9, occurrences(p.getPath(), 'U'));
		assertEquals(9, occurrences(p.getPath(), 'R'));
		assertEquals(p.getSum(), cost(p.getPath(), matrix));
		
		assertEquals(85, p.getSum());
	}
	
	private int occurrences(String target, char c) {
	    int count = 0;
	    for(int i = 0; i < target.length(); ++i)
	        if(target.charAt(i) == c)
                count++;
	    return count;
	}
	
	private int cost(String path, int[][] matrix) {
		int x = 0; int y = 0;
		int total = matrix[y][x];
	    for(int i = 0; i < path.length(); ++i) {
	        if(path.charAt(i) == 'U')
	            ++y;
	        else if(path.charAt(i) == 'R')
	        	++x;
	        total += matrix[y][x];
	    }
	    return total;
	}
	
	private int[][] clone2D(int[][] matrix) {
		if(matrix.length > 0) {
			int[][] result = new int[matrix.length][matrix[0].length];
			for(int i = 0; i < matrix.length; ++i) {
				result[i] = matrix[i].clone();
			}
			return result;
		} else {
			return new int[0][0];
		}
	}
}
