package com.erhan.onlinebilet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ARAC_MARKALARI")
public class VehicleBrand {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "MARKA_ADI")
	@NotNull
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "brand", fetch=FetchType.EAGER)
	@NotEmpty
	@JsonBackReference
	private List<VehicleModel> vehicleModelList = new ArrayList<VehicleModel>(0);
	
	public VehicleBrand() {
	}

	public VehicleBrand(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<VehicleModel> getVehicleModelList() {
		return vehicleModelList;
	}

	public void setVehicleModelList(List<VehicleModel> vehicleModelList) {
		this.vehicleModelList = vehicleModelList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof VehicleBrand)) {
			return false;
		}
		VehicleBrand other = (VehicleBrand) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "VehicleBrand [id=" + id + ", name=" + name + ", vehicleModelList=" + vehicleModelList + "]";
	}
}