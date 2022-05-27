package com.erhan.busticket.model;

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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ARACLAR")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PLAKA")
	@NotEmpty
	@Pattern(regexp="^[0-8][0-9][A-Z]{1,3}[0-9]{2,5}")
	private String plateCode;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MODEL_ID")
	@NotNull
	@JsonManagedReference
	private VehicleModel model;
	
	@Column(name = "URETIM_YILI")
	@NotEmpty
	@Pattern(regexp="[0-9]{4}")
	private String year;
	
	@Column(name = "KAT_EDILEN_MESAFE")
	@NotNull
	private Integer milage;
	
	@OneToMany(mappedBy="vehicle", cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Voyage> voyageList = new ArrayList<Voyage>(0);

	public Vehicle() {

	}

	public Vehicle(String plateCode, VehicleModel model, String year, Integer milage) {
		this.plateCode = plateCode;
		this.model = model;
		this.year = year;
		this.milage = milage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlateCode() {
		return plateCode;
	}

	public void setPlateCode(String plateCode) {
		this.plateCode = plateCode;
	}

	public VehicleModel getModel() {
		return model;
	}

	public void setModel(VehicleModel model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getMilage() {
		return milage;
	}

	public void setMilage(Integer milage) {
		this.milage = milage;
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
		result = prime * result + ((milage == null) ? 0 : milage.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((plateCode == null) ? 0 : plateCode.hashCode());
		result = prime * result + ((voyageList == null) ? 0 : voyageList.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (milage == null) {
			if (other.milage != null)
				return false;
		} else if (!milage.equals(other.milage))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (plateCode == null) {
			if (other.plateCode != null)
				return false;
		} else if (!plateCode.equals(other.plateCode))
			return false;
		if (voyageList == null) {
			if (other.voyageList != null)
				return false;
		} else if (!voyageList.equals(other.voyageList))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", plateCode=" + plateCode + ", model=" + model
				+ ", year=" + year + ", milage=" + milage + "]";
	}
}
