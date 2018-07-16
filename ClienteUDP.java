package Trabalho5_Obsolteto;
/**
 * @author Marcos Paulo de Castro
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP extends Thread 
{
	private DatagramSocket cSocket = null;
	private FluxoDeControle clienteFxControle;

	public ClienteUDP(FluxoDeControle fxControle) 
	{
		try 
		{
			this.clienteFxControle = fxControle;
			this.cSocket = new DatagramSocket();
		} 
		catch (SocketException e) 
		{
			// TODO: handle exception
			System.out.println("Erro de Socket UDPClient: '" + e + "'");
		}
	}

	public void mIniciaNodo() 
	{
		String mensagem = "novoNodo";
		byte[] msg = mensagem.getBytes();
		// Percorre os Nodos Enviando as Mensagens
		try 
		{
			for (int i = 0; i < this.clienteFxControle.cNodos.size(); i++) 
			{
				if (this.clienteFxControle.cID == this.clienteFxControle.cNodos.get(i).getID()) 
				{
					// Nao mandar mensagem para o proprio Nodo
				} 
				else 
				{
					InetAddress ip = InetAddress.getByName(this.clienteFxControle.cNodos.get(i).getHost());
					Integer porta = this.clienteFxControle.cNodos.get(i).getPortaUDP();
					// Monta o pacote para transmissao
					DatagramPacket pacote = new DatagramPacket(msg, msg.length, ip, porta);
					// Envia o Pacote
					this.cSocket.send(pacote);
				}
			}
		} 
		catch (UnknownHostException e) 
		{
			// TODO: handle exception
			System.out.println(e);
		} 
		catch (IOException ex) 
		{
			// TODO: handle exception
			System.out.println(ex);
		}
	}

	public void mEleicaoNodo() 
	{
		String mensagem = "novaEleicao";
		byte[] msg = mensagem.getBytes();
		
		// Percorre os Nodos Enviando as Mensagens
		try 
		{
			for (int i = 0; i < this.clienteFxControle.cNodos.size(); i++) 
			{
				if (this.clienteFxControle.cID == this.clienteFxControle.cNodos.get(i).getID()) 
				{
					// Nao mandar mensagem para o proprio Nodo
				} 
				else 
				{
					// Pega o IP do Nodo
					InetAddress ip = InetAddress.getByName(this.clienteFxControle.cNodos.get(i).getHost());
					
					// Pega a Porta do Servidor UDP do Nodo
					Integer porta = this.clienteFxControle.cNodos.get(i).getPortaUDP();
					
					// Monta o pacote para transmissao
					DatagramPacket pacote = new DatagramPacket(msg, msg.length, ip, porta);
					
					// Envia o Pacote
					this.cSocket.send(pacote);
				}
			}
		} 
		catch (UnknownHostException e) 
		{
			// TODO: handle exception
			System.out.println(e);
		} 
		catch (IOException ex) 
		{
			// TODO: handle exception
			System.out.println(ex);
		}
	}

	public void run() 
	{
		this.mIniciaNodo();
		this.mEleicaoNodo();
	}
}
