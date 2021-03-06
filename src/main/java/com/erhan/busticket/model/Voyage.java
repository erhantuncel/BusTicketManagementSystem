package com.erhan.busticket.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


@Entity
@Table(name = "SEFERLER")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Voyage implements Comparable<Voyage> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ARAC_ID")
	@JsonManagedReference
	@NotNull
	@Valid
	private Vehicle vehicle;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ROTA_ID")
	@JsonManagedReference
	@NotNull
	@Valid
	private Route route;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "KALKIS_ZAMANI")
	@JsonFormat(shape=Shape.STRING, pattern="dd.MM.yyyy HH:mm", timezone="Europe/Istanbul")
	@NotNull
 	private Date departureTime; 
 	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "KAYIT_ZAMANI")
	@JsonFormat(shape=Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss", timezone="Europe/Istanbul")
 	private Date registerTime;
	
	@OneToMany(mappedBy="voyage", fetch=FetchType.EAGER, orphanRemoval=true)
	@Cascade(CascadeType.ALL)
	@Column(nullable = true)
	@JsonBackReference
	private Set<Expense> expenseList = new HashSet<>();
	
	@OneToMany(mappedBy="voyage")
	@Cascade(CascadeType.ALL)
	@JsonBackReference
	private List<Ticket> ticketList = new ArrayList<Ticket>(0);
	
	public Voyage() {
	
	}
	
	public Voyage(Vehicle vehicle, Route route, Date departureTime, Date registerTime) {
		this.vehicle = vehicle;
		this.route = route;
		this.departureTime = departureTime;
		this.registerTime = registerTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Set<Expense> getExpenseList() {
		return expenseList;
	}
	
	public void setExpenseList(Set<Expense> expenseList) {
		this.expenseList = expenseList;
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
		result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + ((expenseList == null) ? 0 : expenseList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((registerTime == null) ? 0 : registerTime.hashCode());
		result = prime * result + ((route == null) ? 0 : route.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
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
		Voyage other = (Voyage) obj;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (expenseList == null) {
			if (other.expenseList != null)
				return false;
		} else if (!expenseList.equals(other.expenseList))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (registerTime == null) {
			if (other.registerTime != null)
				return false;
		} else if (!registerTime.equals(other.registerTime))
			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Voyage [id=" + id + ", vehicle=" + vehicle + ", route=" + route + ", departureTime=" + departureTime
				+ ", registerTime=" + registerTime + ", expenseList=" + expenseList + "]";
	}

	@Override
	public int compareTo(Voyage voyage) {
		Date departureTime = voyage.getDepartureTime();
		return this.departureTime.compareTo(departureTime);
	} 	
}