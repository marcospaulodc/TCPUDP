package Trabalho5_Obsolteto;
/**
 * @author Marcos Paulo de Castro
 */

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorImpressaoMain 
{
	public static void main(String[] args) 
	{
		ServerSocket servidorSocket = null;
		
		try 
		{
			System.out.println("*** Servidor de Impressão iniciado com sucesso! ***\n");
			int portaDoServidor = 15800;
			servidorSocket = new ServerSocket(portaDoServidor);
			
			while (true) 
			{
				Socket clienteSocket = servidorSocket.accept();
				System.out.println("Conexao estabelecida com: " + clienteSocket.getInetAddress());
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
