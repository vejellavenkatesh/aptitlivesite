package com.example.AptItSolutions.Entity;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class ProjectRegistrationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String mobile;
    private String collegename;
    private String collegeid;
    private String collegLocation;
    private String choosedomain;
    private String timeduration;
    private String anyquiries;
    
    @Lob
    @Column(columnDefinition = "LongBlob")
    private byte[] certificates;
    @Lob
    @Column(columnDefinition = "LongBlob")
    private byte[] anyattachments;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCollegename() {
		return collegename;
	}
	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}
	public String getCollegeid() {
		return collegeid;
	}
	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}
	public String getCollegLocation() {
		return collegLocation;
	}
	public void setCollegLocation(String collegLocation) {
		this.collegLocation = collegLocation;
	}
	public String getChoosedomain() {
		return choosedomain;
	}
	public void setChoosedomain(String choosedomain) {
		this.choosedomain = choosedomain;
	}
	public String getTimeduration() {
		return timeduration;
	}
	public void setTimeduration(String timeduration) {
		this.timeduration = timeduration;
	}
	public String getAnyquiries() {
		return anyquiries;
	}
	public void setAnyquiries(String anyquiries) {
		this.anyquiries = anyquiries;
	}
	public byte[] getCertificates() {
		return certificates;
	}
	public void setCertificates(byte[] certificates) {
		this.certificates = certificates;
	}
	public byte[] getAnyattachments() {
		return anyattachments;
	}
	public void setAnyattachments(byte[] anyattachments) {
		this.anyattachments = anyattachments;
	}
	public ProjectRegistrationForm(long id, String name, String email, String mobile, String collegename,
			String collegeid, String collegLocation, String choosedomain, String timeduration, String anyquiries,
			byte[] certificates, byte[] anyattachments) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.collegename = collegename;
		this.collegeid = collegeid;
		this.collegLocation = collegLocation;
		this.choosedomain = choosedomain;
		this.timeduration = timeduration;
		this.anyquiries = anyquiries;
		this.certificates = certificates;
		this.anyattachments = anyattachments;
	}
	@Override
	public String toString() {
		return "ProjectRegistrationForm [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", collegename=" + collegename + ", collegeid=" + collegeid + ", collegLocation=" + collegLocation
				+ ", choosedomain=" + choosedomain + ", timeduration=" + timeduration + ", anyquiries=" + anyquiries
				+ ", certificates=" + Arrays.toString(certificates) + ", anyattachments="
				+ Arrays.toString(anyattachments) + "]";
	}
	public ProjectRegistrationForm() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	
    
}
