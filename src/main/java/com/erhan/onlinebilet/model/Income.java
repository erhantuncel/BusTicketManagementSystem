package com.erhan.onlinebilet.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "GELIRLER")
public class Income {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEFER_ID")
	private Voyage voyage;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Column(name = "KAYIT_ZAMANI")
	private Date registeredTime;
	
	@Column(name = "TUTAR")
	@NotNull
	private BigDecimal price;
	
	public Income() {
	
	}

	public Income(Voyage voyage, Date registeredTime, BigDecimal price) {
		this.voyage = voyage;
		this.registeredTime = registeredTime;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Voyage getVoyage() {
		return voyage;
	}

	public void setVoyage(Voyage voyage) {
		this.voyage = voyage;
	}

	public Date getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(Date registeredTime) {
		this.registeredTime = registeredTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((registeredTime == null) ? 0 : registeredTime.hashCode());
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
		if (!(obj instanceof Income)) {
			return false;
		}
		Income other = (Income) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (registeredTime == null) {
			if (other.registeredTime != null) {
				return false;
			}
		} else if (!registeredTime.equals(other.registeredTime)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Income [id=" + id + ", voyage=" + voyage + ", registeredTime=" + registeredTime + ", price=" + price
				+ "]";
	}
}
