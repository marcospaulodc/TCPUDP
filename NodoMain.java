package Trabalho5_Obsolteto;
/**
 * @author Marcos Paulo de Castro
 */

import java.io.IOException;
import java.util.Scanner;

public class NodoMain 
{
	public static void main(String[] args) 
	{
		FluxoDeControle fxControle = new FluxoDeControle();
		System.out.print("Aten��o!!! Esse main dever� ser executado proporcionalmente ao n�mero de Nodos do Arquivo.txt \n");
		System.out.print("Digite o ID do Nodo do Arquivo.txt: ");
		
		Scanner teclado = new Scanner(System.in);
		int ID = teclado.nextInt();
		System.out.println("*** Nodo " + ID + " ***");

		// Recebe Configuracao dos Nodos
		fxControle.mConfigurarArquivo(ID);

		// Inicia os Servidores e Clientes
		fxControle.mIniciarServidorTCP();
		fxControle.mIniciarServidorUDP();

		try 
		{
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fxControle.mReiniciarConexao();
	}
}
