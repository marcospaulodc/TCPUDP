package Trabalho5_Obsolteto;
/**
 * @author Marcos Paulo de Castro
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP extends Thread 
{

	protected DatagramSocket sSocket = null;
	private FluxoDeControle sFluxoDeControle = null;

	public ServidorUDP(FluxoDeControle FluxoDeControle) 
	{
		this.sFluxoDeControle = FluxoDeControle;
	}

	public void run() 
	{
		try 
		{
			// Cria o DatagramSocket para recebimento de Mensagens
			sSocket = new DatagramSocket(sFluxoDeControle.cPortaUDP);
			// Prepara o buffer de recebimento
			byte[] msg = new byte[256];
			// Prepara o pacote de dados
			DatagramPacket pacote = new DatagramPacket(msg, msg.length);

			// Seta FluxoDeControle de execucao do Servidor
			this.sFluxoDeControle.isServerUDPRunning = true;

			while (this.sFluxoDeControle.isServerUDPRunning) 
			{
				// Recebimento de Mensagem
				sSocket.receive(pacote);
				// Passar mensagem recebida para FluxoDeControle
				this.sFluxoDeControle.msgUDP = new String(pacote.getData()).trim();
				System.out.println(this.sFluxoDeControle.msgUDP);
			}

		} 
		catch (SocketException e) 
		{
			// TODO: handle exception
			System.out.println("Erro Socket UDP: '" + e + "'");
		} 
		catch (IOException ex) 
		{
			// TODO: handle exception
			System.out.println("Erro IO UDP: '" + ex + "'");
		} 
		finally 
		{
			sSocket.close();
		}
	}
}
