package com.erhan.onlinebilet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.erhan.onlinebilet.validator.TcNumber;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "MUSTERILER")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
//	@NotNull
	private Long id;
	
	@Column(name = "TC_NO")
	@NotEmpty
	@TcNumber(message = "Geçerli bir T.C. Kimlik numarası giriniz.")
	private String tcNumber;
	
	@Column(name = "AD")
	@NotEmpty
	private String name;
	
	@Column(name = "SOYAD")
	@NotEmpty
	private String surname;
	
	@Column(name = "CINSIYET")
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name = "DOGUM_TARIHI")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=Shape.STRING, pattern="dd.MM.yyyy")
	private Date dateOfBirth;
	
	@Column(name = "CEP_TEL")
	@NotEmpty
	@Size(min = 10, max = 10)
	@NumberFormat(style = Style.NUMBER)
	private String mobileNumber;
	
	@Column(name = "E_POSTA")
	@NotEmpty
	@Email(message = "Geçerli bir E-Posta adresi giriniz.")
	private String eMail;
	
	@Column(name = "SIFRE")
	@NotEmpty
//	@Size(min = 8, max = 15)
	@Length(min = 60)
	@Pattern.List({
		@Pattern(regexp = "(?=.*[0-9]).+$", message="Şifre en az bir adet nümerik karakter içermeli"),
		@Pattern(regexp = "(?=.*[a-z]).+$", message = "Şifre en az bir küçük harf içermeli.")
	})
	@JsonIgnore
	private String password;
	
	@Column(name = "KAYIT_ZAMANI")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss")
	private Date dateOfRegister;
	
	@Column(name = "SON_GIRIS_ZAMANI")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss")
	private Date timeOfLastOnline;
	
	@Column(name = "AKTIFMI")
	@NotNull
	private Boolean enabled;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade =CascadeType.ALL)
	@JsonBackReference
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonBackReference
	private List<Ticket> ticketList = new ArrayList<Ticket>(0);
	
	public Customer() {
		
	}

	public Customer(String tcNumber, String name, String surname, Gender gender, Date dateOfBirth, String mobileNumber,
			String eMail, String password, Date dateOfRegister, Date timeOfLastOnline, Boolean enabled) {
		this.tcNumber = tcNumber;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.eMail = eMail;
		this.password = password;
		this.dateOfRegister = dateOfRegister;
		this.timeOfLastOnline = timeOfLastOnline;
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTcNumber() {
		return tcNumber;
	}

	public void setTcNumber(String tcNumber) {
		this.tcNumber = tcNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfRegister() {
		return dateOfRegister;
	}

	public void setDateOfRegister(Date dateOfRegister) {
		this.dateOfRegister = dateOfRegister;
	}

	public Date getTimeOfLastOnline() {
		return timeOfLastOnline;
	}

	public void setTimeOfLastOnline(Date timeOfLastOnline) {
		this.timeOfLastOnline = timeOfLastOnline;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((dateOfRegister == null) ? 0 : dateOfRegister.hashCode());
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((tcNumber == null) ? 0 : tcNumber.hashCode());
		result = prime * result + ((timeOfLastOnline == null) ? 0 : timeOfLastOnline.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer other = (Customer) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null) {
				return false;
			}
		} else if (!dateOfBirth.equals(other.dateOfBirth)) {
			return false;
		}
		if (dateOfRegister == null) {
			if (other.dateOfRegister != null) {
				return false;
			}
		} else if (!dateOfRegister.equals(other.dateOfRegister)) {
			return false;
		}
		if (eMail == null) {
			if (other.eMail != null) {
				return false;
			}
		} else if (!eMail.equals(other.eMail)) {
			return false;
		}
		if (enabled == null) {
			if (other.enabled != null) {
				return false;
			}
		} else if (!enabled.equals(other.enabled)) {
			return false;
		}
		if (gender != other.gender) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (mobileNumber == null) {
			if (other.mobileNumber != null) {
				return false;
			}
		} else if (!mobileNumber.equals(other.mobileNumber)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (surname == null) {
			if (other.surname != null) {
				return false;
			}
		} else if (!surname.equals(other.surname)) {
			return false;
		}
		if (tcNumber == null) {
			if (other.tcNumber != null) {
				return false;
			}
		} else if (!tcNumber.equals(other.tcNumber)) {
			return false;
		}
		if (timeOfLastOnline == null) {
			if (other.timeOfLastOnline != null) {
				return false;
			}
		} else if (!timeOfLastOnline.equals(other.timeOfLastOnline)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", tcNumber=" + tcNumber + ", name=" + name + ", surname=" + surname + ", gender="
				+ gender + ", dateOfBirth=" + dateOfBirth + ", mobileNumber=" + mobileNumber + ", eMail=" + eMail
				+ ", password=" + password + ", dateOfRegister=" + dateOfRegister + ", timeOfLastOnline="
				+ timeOfLastOnline + ", enabled=" + enabled + ", userRole=" + userRole + "]";
	}	
}