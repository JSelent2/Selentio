package de.lavie;

public class KontaktModel
{
	private String email;
	private String name;
	private String telefonnummer;
	private int id;
	private String vorname;

	public KontaktModel(String email, String name, String telefonnummer, int id, String vorname)
	{
		super();
		this.email = email;
		this.name = name;
		this.telefonnummer = telefonnummer;
		this.id = id;
		this.vorname = vorname;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTelefonnummer()
	{
		return telefonnummer;
	}

	public void setTelefonnummer(String telefonnummer)
	{
		this.telefonnummer = telefonnummer;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getVorname()
	{
		return vorname;
	}

	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
}
