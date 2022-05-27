package com.erhan.onlineticket.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "SEHIRLER")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "SEHIR")
	@NotEmpty
	private String cityName;
	
	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	@OrderBy("duration")
	@JsonBackReference
	private Set<Stop> stops = new HashSet<Stop>();
	
//	@OneToMany(mappedBy = "departure", fetch = FetchType.LAZY)
//	private Set<CityDistance> cityDistances = new HashSet<CityDistance>();
	
	@OneToMany(mappedBy="arrival", cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Ticket> ticketListArrival = new ArrayList<Ticket>(0);
	
	@OneToMany(mappedBy="departure", cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Ticket> ticketListDeparture = new ArrayList<Ticket>(0);
	
	public City() {
		
	}

	public City(Long id, Integer cityCode, String cityName, Set<Stop> stops) {
		this.cityName = cityName;
		this.stops = stops;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Set<Stop> getStops() {
		return stops;
	}

	public void setStops(Set<Stop> stops) {
		this.stops = stops;
	}

	public List<Ticket> getTicketListArrival() {
		return ticketListArrival;
	}

	public void setTicketListArrival(List<Ticket> ticketListArrival) {
		this.ticketListArrival = ticketListArrival;
	}

	public List<Ticket> getTicketListDeparture() {
		return ticketListDeparture;
	}

	public void setTicketListDeparture(List<Ticket> ticketListDeparture) {
		this.ticketListDeparture = ticketListDeparture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof City)) {
			return false;
		}
		City other = (City) obj;
		if (cityName == null) {
			if (other.cityName != null) {
				return false;
			}
		} else if (!cityName.equals(other.cityName)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + ", stops=" + stops + "]";
	}
}