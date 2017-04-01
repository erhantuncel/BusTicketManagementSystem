package com.erhan.onlinebilet.model;

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

@Entity
@Table(name = "ROTALAR")
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ROTA_ADI")
	@NotEmpty
	private String routeName;
	
	@OneToMany(mappedBy = "route", fetch=FetchType.EAGER)
	@OrderBy("duration")
	@JsonBackReference
	private Set<Stop> stops = new HashSet<Stop>(0);
	
	@OneToMany(mappedBy="route", cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Voyage> voyageList = new ArrayList<Voyage>(0);
	
	public Route() {
	
	}

	public Route(String routeName) {
		this.routeName = routeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Set<Stop> getStops() {
		return stops;
	}

	public void setStops(Set<Stop> stops) {
		this.stops = stops;
	}

	public List<Voyage> getVoyageList() {
		return voyageList;
	}

	public void setVoyageList(List<Voyage> voyageList) {
		this.voyageList = voyageList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((routeName == null) ? 0 : routeName.hashCode());
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
		if (!(obj instanceof Route)) {
			return false;
		}
		Route other = (Route) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (routeName == null) {
			if (other.routeName != null) {
				return false;
			}
		} else if (!routeName.equals(other.routeName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", routeName=" + routeName + ", stops=" + stops + "]";
	}
}
