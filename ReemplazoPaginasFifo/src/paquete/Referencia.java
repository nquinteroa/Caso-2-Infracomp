package paquete;

import java.util.ArrayList;

public class Referencia extends Thread{

	private int[][] matriz;
	private String[] seguimiento;
	private ArrayList<Integer> ordenPaginas;
	private int marcosDePagina;
	private int numDePaginas;
	
	public Referencia(int[][] matriz, String[] seguimiento, ArrayList<Integer> ordenPaginas, int marcos, int numPaginas)
	{
		this.matriz=matriz;
		this.seguimiento=seguimiento;
		this.ordenPaginas=ordenPaginas;
		this.marcosDePagina=marcos;
		this.numDePaginas=numPaginas;
	}
	
	public void cargarNuevaReferencia()
	{
		
	}
	public int buscarEspacio(int i2)
	{
		//Busca en que marco de la iteracion hay un espacio libre - i2 es la iteracion
		int marcoDisponible=-2;
		for(int j=0;j<numDePaginas;j++)
		{
			marcoDisponible=matriz[i2][j]==-1?j:-2;
		}
		return marcoDisponible;
	}
}
