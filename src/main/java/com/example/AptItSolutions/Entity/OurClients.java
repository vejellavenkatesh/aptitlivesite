package com.example.AptItSolutions.Entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class OurClients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Lob
    @Column(columnDefinition = "LongBlob")
    private byte[] clientLogo;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public byte[] getClientLogo() {
		return clientLogo;
	}
	public void setClientLogo(byte[] clientLogo) {
		this.clientLogo = clientLogo;
	}
	public OurClients(long id, byte[] clientLogo) {
		super();
		this.id = id;
		this.clientLogo = clientLogo;
	}
	@Override
	public String toString() {
		return "OurClients [id=" + id + ", clientLogo=" + Arrays.toString(clientLogo) + "]";
	}
	public OurClients() {
		super();
		// TODO Auto-generated constructor stub
	}


    
}
