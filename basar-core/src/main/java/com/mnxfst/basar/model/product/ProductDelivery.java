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

/**
 * Information on delivery types available for a product
 * @author mnxfst
 * @since 09.04.2013
 *
 * Revision Control Info $Id$
 */
public class ProductDelivery implements Serializable {

	private static final long serialVersionUID = -6885147118245191116L;

	/** selected transportation type */
	private ProductDeliveryType type = null;
	
	/** additional information on the selected transportation type */
	private String typeDescription = null;

	/** charge for selected delivery type */
	private Double charge = null;
	
	/** global location number of the carrier in charge of delivery */ 
	private String carrierGLN = null;
	
	/** ISO 3166 code of supported country */
	private String supportedCountry = null;
	
	/**
	 * Default constructor
	 */
	public ProductDelivery() {		
	}
	
	/**
	 * Initializes the delivery type using the provided data 
	 * @param type
	 * @param typeDescription
	 * @param charge
	 * @param carrierGLN
	 */
	public ProductDelivery(ProductDeliveryType type, String typeDescription, Double charge, String carrierGLN, String supportedCountry) {
		this.type = type;
		this.typeDescription = typeDescription;
		this.charge = charge;
		this.carrierGLN = carrierGLN;
		this.supportedCountry = supportedCountry;
	}

	public ProductDeliveryType getType() {
		return type;
	}

	public void setType(ProductDeliveryType type) {
		this.type = type;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public String getCarrierGLN() {
		return carrierGLN;
	}

	public void setCarrierGLN(String carrierGLN) {
		this.carrierGLN = carrierGLN;
	}

	public String getSupportedCountry() {
		return supportedCountry;
	}

	public void setSupportedCountry(String supportedCountry) {
		this.supportedCountry = supportedCountry;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carrierGLN == null) ? 0 : carrierGLN.hashCode());
		result = prime * result + ((charge == null) ? 0 : charge.hashCode());
		result = prime
				* result
				+ ((supportedCountry == null) ? 0 : supportedCountry.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((typeDescription == null) ? 0 : typeDescription.hashCode());
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
		ProductDelivery other = (ProductDelivery) obj;
		if (carrierGLN == null) {
			if (other.carrierGLN != null)
				return false;
		} else if (!carrierGLN.equals(other.carrierGLN))
			return false;
		if (charge == null) {
			if (other.charge != null)
				return false;
		} else if (!charge.equals(other.charge))
			return false;
		if (supportedCountry == null) {
			if (other.supportedCountry != null)
				return false;
		} else if (!supportedCountry.equals(other.supportedCountry))
			return false;
		if (type != other.type)
			return false;
		if (typeDescription == null) {
			if (other.typeDescription != null)
				return false;
		} else if (!typeDescription.equals(other.typeDescription))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ProductDelivery [type=" + type + ", typeDescription="
				+ typeDescription + ", charge=" + charge + ", carrierGLN="
				+ carrierGLN + ", supportedCountry=" + supportedCountry + "]";
	}

	
	
}
