package Trabalho5_Obsolteto;
/**
 * @author Marcos Paulo de Castro
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// Servidor para Comunicacao TCP
public class Servidor extends Thread 
{
	private ServerSocket sSocket = null;
	private FluxoDeControle servidorFxControle = null;
	protected ArrayList<Conecta> sConexoes = null;

	public Servidor(FluxoDeControle fxControle) 
	{
		this.servidorFxControle = fxControle;
		this.sConexoes = new ArrayList<Conecta>();
	}

	public void run() 
	{
		try 
		{
			while (true) 
			{
				// Socket do Cliente
				Socket clienteSocket = sSocket.accept();
				Conecta connection = new Conecta(clienteSocket, servidorFxControle);
				connection.start();
				
				// Conexao DIREITA
				if (this.sConexoes.size() == 0) 
				{
					this.sConexoes.add(0, connection);
				}
				// Conexao ESQUERDA
				if (this.sConexoes.size() == 1) 
				{
					this.sConexoes.add(1, connection);
				}
				// Conexao TEMPORARIA
				if (this.sConexoes.size() == 2) 
				{
					this.sConexoes.add(2, connection);
				}
			}
		} 
		catch (IOException e) 
		{
			// TODO: handle exception
			System.out.println("Erro de Conexao! '" + e + "'");
		}
	}
}
