package paquete;

public class Matriz {

	private static int marcos;
	private static int columnas;
	private static int[][] matriz;
	private static int ultimaIteracion;
	
	public Matriz(int marcosDePagina , int columnas)
	{
		this.marcos=marcosDePagina;
		this.columnas=columnas;
		matriz=new int[marcos][columnas];
		iniciarMatriz();
		ultimaIteracion=0;
	}

	public int retornarNumero(int fila, int columna)
	{
		return (matriz[fila][columna]);
	}
	
	public void modificarNumero(int fila, int columna, int valor)
	{
		matriz[fila][columna]=valor;
	}
	private static void iniciarMatriz() {
		for (int i = 0; i < marcos; i++) {
			for (int j = 0; j < columnas; j++) {
				matriz[i][j] = -1;
			}
		}
	}
	public void aumentarIteracion(){
		ultimaIteracion++;
	}
	public int darUltimaIteracion()
	{
		return ultimaIteracion;
	}
}
