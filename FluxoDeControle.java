package Trabalho5_Obsolteto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Marcos Paulo de Castro
 */

public class FluxoDeControle 
{
	protected int cID;
	protected String cIP;
	protected int cPortaTCP;
	protected int cPortaUDP;
	protected ArrayList<Nodo> cNodos;

	// Variaveis de Servidor TCP
	protected boolean isServerConnected;

	// Variaveis de Cliente TCP
	protected boolean isClientConnected;

	// Variaveis de Servidor UDP
	protected String msgUDP = "";
	protected boolean isServerUDPRunning;

	// Servicos
	protected ServidorTCP serverT = null;
	protected ServidorUDP serverU = null;
	protected ClienteTCP clientComT = null;
	protected ClienteUDP clientComU = null;

	public FluxoDeControle() 
	{
		
	}

	public void mConfigurarArquivo(int ID) 
	{
		try 
		{
			// Recebe o ID do Nodo
			this.cID = ID;

			// Recebe o Arquivo de ConfiguraÃ§Ã£o
			String caminho = new File("arquivo2.txt").getAbsolutePath();
			Scanner arquivo = new Scanner(new FileReader(caminho));

			// Prepara ArrayList de Nodos
			this.cNodos = new ArrayList<Nodo>();

			while (arquivo.hasNext()) 
			{
				// Le a linha
				String linha = arquivo.nextLine();
				// Divide a Linha pelo Delimitador ";"
				String hosts[] = linha.split(";");
				// ID - Converte String em Integer
				Integer idHost = Integer.parseInt(hosts[0]);
				// IP
				String endHost = hosts[1];
				// Porta TCP - Converte String em Integer
				Integer portTCPHost = Integer.parseInt(hosts[2]);
				// Porta UDP - Converte String em Integer
				Integer portUDPHost = Integer.parseInt(hosts[3]);

				if (this.cID == idHost) 
				{
					this.cIP = endHost;
					this.cPortaTCP = portTCPHost;
					this.cPortaUDP = portUDPHost;
					Nodo novoNodo = new Nodo(cID, cIP, cPortaTCP, cPortaUDP);
					cNodos.add(novoNodo);
				} 
				else 
				{
					Nodo novoNodo = new Nodo(idHost, endHost, portTCPHost, portUDPHost);
					cNodos.add(novoNodo);
				}
			}
			arquivo.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO: handle exception
			System.out.println("Arquivo de Configuração não Encontrado!!");
		} 
		catch (IOException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void mReiniciarServidor() 
	{
		this.isClientConnected = false;
		this.isServerConnected = false;
		this.isServerUDPRunning = false;
	}

	public void mIniciarServidorTCP() 
	{
		// Instancia o Servidor e Cliente TCP
		this.serverT = new ServidorTCP(this);
		this.clientComT = new ClienteTCP(this);
		try 
		{
			// Inicia as Threads com 1s de Intervalo
			this.serverT.start();
			Thread.sleep(1000);
			this.clientComT.start();
		} 
		catch (InterruptedException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void mIniciarServidorUDP() 
	{
		// Instancia o Servidor e Cliente UDP
		this.serverU = new ServidorUDP(this);
		this.clientComU = new ClienteUDP(this);
		try 
		{
			// Inicia as Threads com 1s de Intervalo
			this.serverU.start();
			Thread.sleep(1000);
			this.clientComU.start();
		} 
		catch (InterruptedException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void mReiniciarConexao() 
	{
		if (this.msgUDP.contains("novoNodo")) 
		{
			try 
			{
				// Refaz as Conexoes
				// TCP
				this.serverT.sSocket.close();
				this.clientComT.clienteSocket.close();
				this.mIniciarServidorTCP();
				
				// UDP
				this.serverU.sSocket.close();
				this.serverU = new ServidorUDP(this);
				this.serverU.start();
				// Limpa a Mensagem escrita
				this.msgUDP = null;
			} 
			catch (IOException e) 
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		else 
		{

		}
	}
}
