package com.erhan.onlinebilet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ARAC_MODELLERI")
public class VehicleModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "MODEL_ADI")
	@NotNull
	private String modelName;
	
	@ManyToOne
	@JoinColumn(name = "MARKA_ID")
	@JsonManagedReference
	private VehicleBrand brand;
	
	@OneToMany(mappedBy="model", cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Vehicle> vehicleList = new ArrayList<Vehicle>(0);
	
	public VehicleModel() {
		
	}

	public VehicleModel(String modelName, VehicleBrand brand) {
		super();
		this.modelName = modelName;
		this.brand = brand;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public VehicleBrand getBrand() {
		return brand;
	}

	public void setBrand(VehicleBrand brand) {
		this.brand = brand;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
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
		if (!(obj instanceof VehicleModel)) {
			return false;
		}
		VehicleModel other = (VehicleModel) obj;
		if (brand == null) {
			if (other.brand != null) {
				return false;
			}
		} else if (!brand.equals(other.brand)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (modelName == null) {
			if (other.modelName != null) {
				return false;
			}
		} else if (!modelName.equals(other.modelName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "VehicleModel [id=" + id + ", modelName=" + modelName + "]";
	}
}