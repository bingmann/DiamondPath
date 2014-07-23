import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.JUnitCore;

public class DiamondPathPrivateTest {

	@Test
	public void testMatrixPath1() {
		int[][] matrix = generateMatrix(600, 300, 14);
		System.out.println("----------------------------------------");
		System.out.println("1. Instanz");
		assertTrue(testMatrixPath(matrix, 103806, 100, 1));
		System.out.println("----------------------------------------");
	}

	@Test
	public void testMatrixPath2() {
		int[][] matrix = generateMatrix(6000, 3000, 42);
		System.out.println("----------------------------------------");
		System.out.println("2. Instanz");
		assertTrue(testMatrixPath(matrix, 1055141, 3000, 2));
		System.out.println("----------------------------------------");
	}

	private boolean testMatrixPath(int[][] matrix, int opt, int msTimeout, int num) {
		// Zeitmessung (Minimum von drei Durchläufen, alle Methoden einmal aufrufen)
		int foo = 0;
		long bestElapsed = Integer.MAX_VALUE;
		for(int i = 1; i <= 3; ++i) {
			int[][] copy = clone2D(matrix);
			long start = System.nanoTime();
			DiamondPath localPath = new DiamondPath(copy);
			foo += localPath.getPath().length();
			foo += localPath.getSum();
			long msElapsed = (System.nanoTime() - start) / 1000 / 1000;
			System.out.println("Im " + i + ". Durchlauf: " + msElapsed + "ms.");
			if(msElapsed < bestElapsed) bestElapsed = msElapsed;
		}

		if(foo == 1) System.out.print(" ");

		if(bestElapsed < msTimeout) {
			System.out.println("Du hast die Zeitvorgabe von " + msTimeout + "ms eingehalten.");
		} else {
			System.out.println("Du hast die Zeitvorgabe von " + msTimeout + "ms nicht eingehalten.");
			return false;
		}

		int[][] arg = clone2D(matrix);
		DiamondPath p = new DiamondPath(arg);
		// Vergleich mit Optimum (1 Punkt)
		if(opt == p.getSum()) {
			System.out.println("Die von dir berechnete Summe ist optimal. Dafür erhälst du einen Punkt");
		} else {
			System.out.println("Es gibt in dieser Instanz einen Pfad mit einer größeren als der von dir berechneten Summe.");
			return false;
		}

		// Oberflächliche Korrektheitstests
		if(matrix.length + matrix[0].length - 2 != p.getPath().length()) {
			System.out.println("Dein Pfad trifft nicht die untere rechte Ecke");
			return false;
		}
		if(matrix.length - 1 != occurrences(p.getPath(), 'U')) {
			System.out.println("Dein Pfad enthält die falsche Anzahl an 'U's.");
			return false;
		}
		if(matrix[0].length - 1 != occurrences(p.getPath(), 'R')) {
			System.out.println("Dein Pfad enthält die falsche Anzahl an 'R's.");
			return false;
		}
		if(p.getSum() != cost(p.getPath(), matrix)) {
			System.out.println("Die von dir berechnete Summe stimmt nicht mit der Summe deines Pfades überein.");
			return false;
		} else {
			System.out.println("Dein Pfad ist optimal. Du erhälst einen weiteren Punkt.");
			return true;
		}
	}

	private int[][] generateMatrix(int X, int Y, int seed) {
		Random rand = new Random(seed);
		int[][] matrix = new int[Y][X];
		for(int y = 0; y < Y; ++y)
			for(int x = 0; x < X; ++x)
				matrix[y][x] = rand.nextInt(500) - 250;
		return matrix;
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

	public static void main(String[] args) throws Exception {
		// mit JUnit ausfuehren:
		JUnitCore.main(DiamondPathPrivateTest.class.getName());
	}
}
