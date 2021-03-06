package com.erhan.busticket.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.erhan.busticket.validator.TcNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "BILETLER")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "REZERVASYONMU")
	@NotNull
	private Boolean isReservation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEFER_ID")
	@NotNull
	@JsonManagedReference
	private Voyage voyage;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="KALKIS_ID")
	@NotNull
	@JsonManagedReference
	private City departure;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="VARIS_ID")
	@NotNull
	@JsonManagedReference
	private City arrival;
	
	@Column(name = "KOLTUK_NO")
	@NotNull
	private Byte seatNumber;
	
	@Column(name = "BILET_UCRETI")
	private BigDecimal price;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "KAYIT_ZAMANI")
	@NotNull
	@JsonFormat(shape=Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss", timezone="Europe/Istanbul")
	private Date registerTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "KALKIS_ZAMANI")
	@NotNull
	@JsonFormat(shape=Shape.STRING, pattern="dd.MM.yyyy HH:mm", timezone="Europe/Istanbul")
	private Date departureTime;
	
	@ManyToOne()
//	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "MUSTERI_ID")
	@JsonManagedReference
	private Customer customer;
	
	@Column(name = "YOLCU_TC_NO")
	@NotEmpty
	@TcNumber(message = "Ge??erli bir T.C. Kimlik numaras?? giriniz.")
	private String passangerTcNumber;
	
	@Column(name = "YOLCU_AD")
	@NotEmpty
	private String passangerName;
	
	@Column(name = "YOLCU_SOYAD")
	@NotEmpty
	private String passangerSurname;
	
	@Column(name = "YOLCU_CINSIYET")
	@NotNull
	private Gender passangerGender;
	
	@Column(name = "REZERVASYON_BITIS_ZAMANI", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss", timezone="Europe/Istanbul")
	private Date reservExpirationDate;

	public Ticket() {
	
	}

	public Ticket(Long id, Boolean isReservation, Voyage voyage, City departure, City arrival, Byte seatNumber,
			BigDecimal price, Date registerTime, Date departureTime, Customer customer, String passangerTcNumber,
			String passangerName, String passangerSurname, Gender passangerGender, Date reservExpirationDate) {
		this.id = id;
		this.isReservation = isReservation;
		this.voyage = voyage;
		this.departure = departure;
		this.arrival = arrival;
		this.seatNumber = seatNumber;
		this.price = price;
		this.registerTime = registerTime;
		this.departureTime = departureTime;
		this.customer = customer;
		this.passangerTcNumber = passangerTcNumber;
		this.passangerName = passangerName;
		this.passangerSurname = passangerSurname;
		this.passangerGender = passangerGender;
		this.reservExpirationDate = reservExpirationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsReservation() {
		return isReservation;
	}

	public void setIsReservation(Boolean isReservation) {
		this.isReservation = isReservation;
	}

	public Voyage getVoyage() {
		return voyage;
	}

	public void setVoyage(Voyage voyage) {
		this.voyage = voyage;
	}

	public City getDeparture() {
		return departure;
	}

	public void setDeparture(City departure) {
		this.departure = departure;
	}

	public City getArrival() {
		return arrival;
	}

	public void setArrival(City arrival) {
		this.arrival = arrival;
	}

	public Byte getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Byte seatNumber) {
		this.seatNumber = seatNumber;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPassangerTcNumber() {
		return passangerTcNumber;
	}

	public void setPassangerTcNumber(String passangerTcNumber) {
		this.passangerTcNumber = passangerTcNumber;
	}

	public String getPassangerName() {
		return passangerName;
	}

	public void setPassangerName(String passangerName) {
		this.passangerName = passangerName;
	}

	public String getPassangerSurname() {
		return passangerSurname;
	}

	public void setPassangerSurname(String passangerSurname) {
		this.passangerSurname = passangerSurname;
	}

	public Gender getPassangerGender() {
		return passangerGender;
	}

	public void setPassangerGender(Gender passangerGender) {
		this.passangerGender = passangerGender;
	}

	public Date getReservExpirationDate() {
		return reservExpirationDate;
	}

	public void setReservExpirationDate(Date reservExpirationDate) {
		this.reservExpirationDate = reservExpirationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrival == null) ? 0 : arrival.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((departure == null) ? 0 : departure.hashCode());
		result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isReservation == null) ? 0 : isReservation.hashCode());
		result = prime * result + ((passangerGender == null) ? 0 : passangerGender.hashCode());
		result = prime * result + ((passangerName == null) ? 0 : passangerName.hashCode());
		result = prime * result + ((passangerSurname == null) ? 0 : passangerSurname.hashCode());
		result = prime * result + ((passangerTcNumber == null) ? 0 : passangerTcNumber.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((registerTime == null) ? 0 : registerTime.hashCode());
		result = prime * result + ((reservExpirationDate == null) ? 0 : reservExpirationDate.hashCode());
		result = prime * result + ((seatNumber == null) ? 0 : seatNumber.hashCode());
		result = prime * result + ((voyage == null) ? 0 : voyage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (arrival == null) {
			if (other.arrival != null)
				return false;
		} else if (!arrival.equals(other.arrival))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isReservation == null) {
			if (other.isReservation != null)
				return false;
		} else if (!isReservation.equals(other.isReservation))
			return false;
		if (passangerGender != other.passangerGender)
			return false;
		if (passangerName == null) {
			if (other.passangerName != null)
				return false;
		} else if (!passangerName.equals(other.passangerName))
			return false;
		if (passangerSurname == null) {
			if (other.passangerSurname != null)
				return false;
		} else if (!passangerSurname.equals(other.passangerSurname))
			return false;
		if (passangerTcNumber == null) {
			if (other.passangerTcNumber != null)
				return false;
		} else if (!passangerTcNumber.equals(other.passangerTcNumber))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (registerTime == null) {
			if (other.registerTime != null)
				return false;
		} else if (!registerTime.equals(other.registerTime))
			return false;
		if (reservExpirationDate == null) {
			if (other.reservExpirationDate != null)
				return false;
		} else if (!reservExpirationDate.equals(other.reservExpirationDate))
			return false;
		if (seatNumber == null) {
			if (other.seatNumber != null)
				return false;
		} else if (!seatNumber.equals(other.seatNumber))
			return false;
		if (voyage == null) {
			if (other.voyage != null)
				return false;
		} else if (!voyage.equals(other.voyage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", isReservation=" + isReservation + ", voyage=" + voyage + ", departure="
				+ departure + ", arrival=" + arrival + ", seatNumber=" + seatNumber + ", price=" + price
				+ ", registerTime=" + registerTime + ", departureTime=" + departureTime + ", customer=" + customer
				+ ", passangerTcNumber=" + passangerTcNumber + ", passangerName=" + passangerName
				+ ", passangerSurname=" + passangerSurname + ", passangerGender=" + passangerGender
				+ ", reservExpirationDate=" + reservExpirationDate + "]";
	}
}
