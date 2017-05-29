package com.erhan.onlinebilet.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "GIDERLER")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TUTAR")
	private BigDecimal price;
	
	@ManyToOne()
	@JoinColumn(name = "GIDER_TIPI_ID")
	@JsonBackReference
	@NotNull
	private ExpenseType type;
	
	@Column(name = "KAYIT_ZAMANI")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss")
	private Date registeredTime;
	
	@ManyToOne()
	@JoinColumn(name = "SEFER_ID")
	@JsonManagedReference
	@NotNull
	private Voyage voyage;
	
	public Expense() {
	
	}
	
	public Expense(BigDecimal price, ExpenseType type, Date registeredTime, Voyage voyage) {
		this.price = price;
		this.type = type;
		this.registeredTime = registeredTime;
		this.voyage = voyage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ExpenseType getType() {
		return type;
	}

	public void setType(ExpenseType type) {
		this.type = type;
	}


	public Date getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(Date registeredTime) {
		this.registeredTime = registeredTime;
	}

	public Voyage getVoyage() {
		return voyage;
	}

	public void setVoyage(Voyage voyage) {
		this.voyage = voyage;
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
		if (!(obj instanceof Expense)) {
			return false;
		}
		Expense other = (Expense) obj;
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
		return "Expense [id=" + id + ", price=" + price + ", type=" + type + ", registerTime=" + registeredTime + "]";
	}
}
