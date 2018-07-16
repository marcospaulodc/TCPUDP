package Trabalho5_Obsolteto;
/**
 * @author Marcos Paulo de Castro
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

// Servidor para Comunicação TCP
public class ServidorTCP extends Thread 
{
	private FluxoDeControle servidorFxControle = null;
	protected ServerSocket sSocket = null;
	private DataInputStream sEntrada = null;
	private DataOutputStream sSaida = null;

	public ServidorTCP(FluxoDeControle FluxoDeControle) 
	{
		this.servidorFxControle = FluxoDeControle;
	}

	public void run() 
	{
		Socket cSocket = null;
		try 
		{
			// Cria ServerSocket com a Porta do Nodo
			this.sSocket = new ServerSocket(this.servidorFxControle.cPortaTCP);
			// Socket estabelecer Conexao
			cSocket = sSocket.accept();
			// Timeout para o Socket
			cSocket.setSoTimeout(8000);
			// Set FluxoDeControle de Funcionamento do Servidor TCP
			this.servidorFxControle.isServerConnected = true;

			sEntrada = new DataInputStream(cSocket.getInputStream());
			sSaida = new DataOutputStream(cSocket.getOutputStream());

			String idNodo = String.valueOf(this.servidorFxControle.cID);
			sSaida.writeUTF(idNodo);

			// Metodos para receber o token
			while (this.servidorFxControle.isServerConnected) 
			{
				String TOKEN = sEntrada.readUTF();
			}

		} 
		catch (IOException e) 
		{
			// TODO: handle exception
			System.out.println("Erro de Conexao Server TCP! '" + e + "'");
		} 
	}
}