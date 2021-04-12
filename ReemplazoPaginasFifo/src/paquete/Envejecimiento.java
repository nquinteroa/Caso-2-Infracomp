package paquete;

import java.util.ArrayList;

public class Envejecimiento extends Thread{

	private int[][] matriz;
	private String[] seguimiento;
	private ArrayList<Integer> ordenPaginas;
	private int marcosDePagina;
	private int numDePaginas;
	
	public Envejecimiento(int[][] matriz, String[] seguimiento, ArrayList<Integer> ordenPaginas, int marcos, int numPaginas)
	{
		this.matriz=matriz;
		this.seguimiento=seguimiento;
		this.ordenPaginas=ordenPaginas;
		this.marcosDePagina=marcos;
		this.numDePaginas=numPaginas;
	}
	
	
	
	
	//este método me devolverá el frame que fue el mas antiguo en ser liberado
	/*private int MayorDistancia(int paginaActual){
		int mayorDist=0;	
		for(int i=0;i<cantidadFrames;i++){
			for(int j=paginaActual;j>=0;j--){
				if(matriz[i][paginaActual]==paginas[j]){
					distancia[i]=paginaActual-j;
					break;
				}
			}
		}
		
		for(int i=0;i<cantidadFrames;i++){
			if(distancia[i]>distancia[mayorDist]){
				mayorDist=i;
			}
		}
		return mayorDist;
	}*/
	
}
