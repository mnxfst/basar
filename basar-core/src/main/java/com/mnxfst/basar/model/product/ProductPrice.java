/**
 * basar - enhanced electronic marketplace
 * Copyright (C) 2013 Christian Kreutzfeldt
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mnxfst.basar.model.product;

import java.io.Serializable;
import java.util.Currency;

/**
 * Pricing details
 * @author mnxfst
 * @since 09.04.2013
 *
 * Revision Control Info $Id$
 */
public class ProductPrice implements Serializable {

	private static final long serialVersionUID = -5583644524163670515L;
	
	/** names the type of the pricing information */
	private ProductPriceType type = null;
	
	/** price value */
	private Double value = null;
	
	/** currency the price is given in */
	private Currency currency = null;
	
	/**
	 * Default constructor
	 */
	public ProductPrice() {		
	}
	
	/**
	 * Initializes the product price using the provided information
	 * @param type
	 * @param value
	 * @param currency
	 */
	public ProductPrice(ProductPriceType type, Double value, Currency currency) {
		this.type = type;
		this.value = value;
		this.currency = currency;
	}

	public ProductPriceType getType() {
		return type;
	}

	public void setType(ProductPriceType type) {
		this.type = type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductPrice other = (ProductPrice) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (type != other.type)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ProductPrice [type=" + type + ", value=" + value
				+ ", currency=" + currency + "]";
	}
	
}
