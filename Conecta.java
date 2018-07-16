package Trabalho5_Obsolteto;
/**
 * @author Marcos Paulo de Castro
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Conecta extends Thread 
{
	private DataInputStream entrada;
	private DataOutputStream saida;
	private Socket cSocket;
	private FluxoDeControle clienteFxControle;
	private int cOperacao;

	public Conecta(Socket socketCliente, FluxoDeControle fxcontrole) 
	{
		try 
		{
			this.cSocket = socketCliente;
			entrada = new DataInputStream(socketCliente.getInputStream());
			saida = new DataOutputStream(socketCliente.getOutputStream());
			this.clienteFxControle = fxcontrole;
		} 
		catch (IOException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void setOperacao(int operacao)
	{
		this.cOperacao = operacao;
	}
	
	public int getOperacao()
	{
		return this.cOperacao;
	}
	
	public void run()
	{
		while(!this.cSocket.isClosed()) 
		{
			// Operacao faz nada
			if (this.cOperacao == 0) 
			{
				
			}
			// Operacao para TOKEN
			if (this.cOperacao == 1) 
			{ 
				
			}
			// Operacao para CONEXAO
			if (this.cOperacao == 2) 
			{
				
			}
			// Operacao para SAIDA
			if (this.cOperacao == 3)
			{
				try 
				{
					this.cSocket.close();
				} 
				catch (IOException e) 
				{
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
}