package Trabalho5_Obsolteto;
/**
 * @author Marcos Paulo de Castro
 */

public class Nodo 
{
	private Integer nID;
	private String nHost;
	private Integer nPorta;
	private Integer nPortaUDP;

	public Nodo() 
	{
		
	}
	
	public Nodo(Integer ID, String Host, Integer PortaTCP, Integer PortaUDP) 
	{
		this.nID = ID;
		this.nHost = Host;
		this.nPorta = PortaTCP;
		this.nPortaUDP = PortaUDP;
	}

	public Integer getID() 
	{
		return nID;
	}

	public String getHost() 
	{
		return nHost;
	}

	public Integer getPorta() 
	{
		return nPorta;
	}

	public Integer getPortaUDP() 
	{
		return this.nPortaUDP;
	}

	public void setID(Integer nID) 
	{
		this.nID = nID;
	}

	public void setHost(String nHost) 
	{
		this.nHost = nHost;
	}

	public void setPorta(Integer nPorta) 
	{
		this.nPorta = nPorta;
	}

	public void setPortaUDP(Integer nPortaUDP) 
	{
		this.nPortaUDP = nPortaUDP;
	}
}
