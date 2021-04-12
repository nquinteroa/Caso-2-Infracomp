package paquete;

import java.util.ArrayList;

public class Envejecimiento extends Thread {

	private Matriz matriz;
	private String[] seguimiento;
	private ArrayList<Integer> ordenPaginas;
	private int marcosDePagina;
	private int iteracion;
	
	
	public Envejecimiento(Matriz matriz, String[] seguimiento, ArrayList<Integer> ordenPaginas, int marcos,
			int numPaginas, Referencia ref) {
		this.matriz = matriz;
		this.seguimiento = seguimiento;
		this.ordenPaginas = ordenPaginas;
		this.marcosDePagina = marcos;
	}

	public void run()
	{
		while(matriz.darUltimaIteracion()<ordenPaginas.size())
		{
			Envejecer();
		}
	}
	
	/**
	 * Agrega una nueva pagina a la tabla de paginas cuando esta llena.
	 * 
	 * @param columna
	 * @param matriz
	 * @param seguimiento
	 * @param paginas
	 * @param fila        // la fila de la referencias de la tabla de paginas
	 */
	public void Envejecer() {
		try {
			synchronized(matriz)
			{
				matriz.wait();	
				int columna=matriz.darUltimaIteracion();
				iteracion=columna;
				int menor = buscarMenor(columna); // Busca el valor menor a reemplazar de la tabla.
				matriz.modificarNumero(menor, columna, ordenPaginas.get(columna)); // Lo reemplaza.
				agregarBitsDeReferencia(matriz.retornarNumero(menor, columna)); // Se hace referencia binaria				
//				System.out.println(iteracion);
				matriz.notify();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void agregarBitsDeReferencia(int numeroPaginaReferenciada) {
		for (int i = 0; i < seguimiento.length; i++) {
			if (i == numeroPaginaReferenciada) {
				String paginaReferenciada = seguimiento[numeroPaginaReferenciada];
				paginaReferenciada = "1" + paginaReferenciada.substring(0, paginaReferenciada.length() - 1);
				seguimiento[numeroPaginaReferenciada] = paginaReferenciada;
			} else {
				String paginaActual = seguimiento[i];
				paginaActual = "0" + paginaActual.substring(0, paginaActual.length() - 1);
				seguimiento[i] = paginaActual;
			}
		}
	}

	/**
	 * Metodo encargado de econtrar el menor de la tabla de paginas
	 * 
	 * @return Retorna la FILA del valor menor de la columna asumiendo que la tabla
	 *         de paginas esta llena.
	 */
	public int buscarMenor(int columna) {
		int pagina = matriz.retornarNumero(0, columna);
		int fila = 0;
		String menor = seguimiento[pagina];
		for (int j = 1; j < marcosDePagina; j++) {
			int pagina2 = matriz.retornarNumero(j, columna);
			if (menor.compareTo(seguimiento[pagina2]) == 1) {
				menor = seguimiento[matriz.retornarNumero(j, columna)];
				fila = j;
			}
		}
		return fila;
	}

	// este método me devolverá el frame que fue el mas antiguo en ser liberado
	/*
	 * private int MayorDistancia(int paginaActual){ int mayorDist=0; for(int
	 * i=0;i<cantidadFrames;i++){ for(int j=paginaActual;j>=0;j--){
	 * if(matriz[i][paginaActual]==paginas[j]){ distancia[i]=paginaActual-j; break;
	 * } } }
	 * 
	 * for(int i=0;i<cantidadFrames;i++){ if(distancia[i]>distancia[mayorDist]){
	 * mayorDist=i; } } return mayorDist; }
	 */

}
