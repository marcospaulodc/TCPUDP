package Trabalho5_Obsolteto;
/**
 * @author Marcos Paulo de Castro
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteTCP extends Thread 
{
	protected Socket clienteSocket;
	private DataInputStream clienteIn = null;
	private DataOutputStream clienteOut = null;
	private FluxoDeControle clienteFxControle;

	public ClienteTCP(FluxoDeControle fxcontrole) 
	{
		this.clienteFxControle = fxcontrole;
	}

	public void mConectarNodo() 
	{
		try 
		{
			Nodo nodo = new Nodo();

			for (int i = 0; i < this.clienteFxControle.cNodos.size(); i++) 
			{
				if (this.clienteFxControle.cID == this.clienteFxControle.cNodos.get(i).getID()) 
				{
					// Nodo nao pode Conectar em si mesmo
				} 
				else 
				{
					// Tenta fazer conexao com outro Nodo
					nodo.setID(this.clienteFxControle.cNodos.get(i).getID());
					nodo.setHost(this.clienteFxControle.cNodos.get(i).getHost());
					nodo.setPorta(this.clienteFxControle.cNodos.get(i).getPorta());
					this.clienteSocket = new Socket(nodo.getHost(), nodo.getPorta());
					
					// Se conectei em alguem, saio do loop
					if (this.clienteSocket.isConnected()) 
					{
						break;
					}
				}
			}

			this.clienteIn = new DataInputStream(this.clienteSocket.getInputStream());
			this.clienteOut = new DataOutputStream(this.clienteSocket.getOutputStream());

			// Variavel de Conexao
			this.clienteFxControle.isClientConnected = true;
			
			// Recebo ID do Nodo que Estabeleceu a Conexao
			String idNodo = clienteIn.readUTF();
			
			System.out.println("Nodo conectado com Id: " + idNodo);
		} 
		catch (IOException e) 
		{
			this.clienteFxControle.isClientConnected = false;
			try 
			{
				Thread.sleep(500);
			} 
			catch (InterruptedException ex) 
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public void run() 
	{
		while (!this.clienteFxControle.isClientConnected) 
		{
			mConectarNodo();
		}
	}
}

