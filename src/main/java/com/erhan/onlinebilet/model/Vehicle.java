package com.erhan.onlinebilet.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "ARACLAR")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PLAKA")
	@NotEmpty
	@Size(min = 7, max = 7)
	private String plateCode;
	
	@Column(name = "KOLTUK_SAYISI")
	@NotNull
	private Integer seatCount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MODEL_ID")
	@NotNull
	private VehicleModel model;
	
	@Column(name = "URETIM_YILI")
	@NotEmpty
	@Size(min = 4, max = 4)
	private String year;
	
	@Column(name = "KAT_EDILEN_MESAFE")
	@NotNull
	private Integer milage;

	public Vehicle() {

	}

	public Vehicle(String plateCode, Integer seatCount, VehicleModel model, String year, Integer milage) {
		this.plateCode = plateCode;
		this.seatCount = seatCount;
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

	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((milage == null) ? 0 : milage.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((plateCode == null) ? 0 : plateCode.hashCode());
		result = prime * result + ((seatCount == null) ? 0 : seatCount.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		if (!(obj instanceof Vehicle)) {
			return false;
		}
		Vehicle other = (Vehicle) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (milage == null) {
			if (other.milage != null) {
				return false;
			}
		} else if (!milage.equals(other.milage)) {
			return false;
		}
		if (model == null) {
			if (other.model != null) {
				return false;
			}
		} else if (!model.equals(other.model)) {
			return false;
		}
		if (plateCode == null) {
			if (other.plateCode != null) {
				return false;
			}
		} else if (!plateCode.equals(other.plateCode)) {
			return false;
		}
		if (seatCount == null) {
			if (other.seatCount != null) {
				return false;
			}
		} else if (!seatCount.equals(other.seatCount)) {
			return false;
		}
		if (year == null) {
			if (other.year != null) {
				return false;
			}
		} else if (!year.equals(other.year)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", plateCode=" + plateCode + ", seatCount=" + seatCount + ", model=" + model
				+ ", year=" + year + ", milage=" + milage + "]";
	}
}
