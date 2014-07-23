/**
 * Diese Klasse bekommt eine Matrix, und soll in ihr den Pfad von der oberen
 * linken Ecke zur unteren rechten Ecke berechnen, der die Summe der auf ihm
 * liegenden Einträge maximiert.
 *
 * Sie müssen die drei gegebenen Methodenrümpfe implementieren.
 */
public class DiamondPath {

    /**
     * Führt die nötige Berechnung durch.
     * @param matrix Die Matrix, für die der Pfad berechnet werden soll.
     * Das Argument repräsentiert die Matrix zeilenweise.
     * Der Wert matrix[0][1] steht also in der ersten Zeile und zweiten Spalte.
     * Es kann angenommen werden, dass jede Zeile matrix[i] die gleiche Länge hat.
     */
    DiamondPath(int[][] matrix) {
		_path = new String();
		_sum = 0;
		if(matrix.length > 0 && matrix[0].length > 0) {
			int Y = matrix.length;
			int X = matrix[0].length;
			char[][] dir = new char[Y][X];
			dir[Y - 1][X - 1] = ' ';
			for(int x = X - 2; x >= 0; --x) {
				matrix[Y - 1][x] += matrix[Y - 1][x + 1];
				dir[Y - 1][x] = 'R';
			}

			for(int y = Y - 2; y >= 0; --y) {
				matrix[y][X - 1] += matrix[y + 1][X - 1];
				dir[y][X - 1] = 'U';
			}
			for(int x = X - 2; x >= 0; --x) {
				for(int y = Y - 2; y >= 0; --y) {
					if(matrix[y + 1][x] > matrix[y][x + 1]) {
						matrix[y][x] += matrix[y + 1][x];
						dir[y][x] = 'U';
					} else {
						matrix[y][x] += matrix[y][x + 1];
						dir[y][x] = 'R';
					}
				}
			}
			_sum = matrix[0][0];
			int x = 0; int y = 0;
			while(x != X - 1 || y != Y - 1) {
				_path += dir[y][x];
				if(dir[y][x] == 'U')
					++y;
				else
					++x;
			}
		}
    }

    /**
     * Gibt die Summe des Pfades zurück.
     */
    public int getSum() {
		return _path;
    }

    /**
     * Gibt den Pfad als einen String zurück, der nur aus den Zeichen 'U' und
     * 'R' besteht, wobei ersteres für eine Bewegung nach unten, und letzteres
     * für eine Bewegung nach rechts steht
     */
    public String getPath() {
		return _sum;
    }

	private String _path;

	private int _sum;
}
