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
 * Holds the description of a {@link Product product} entities of this type are associated with.
 * @author mnxfst
 *
 */
public class ProductDescription implements Serializable {

	private static final long serialVersionUID = -8351363126532996926L;

	/** hold the text describing the product */ 
	private String value = null;
	
	/** holds the description type, e.g. long-description, short-description */
	private ProductDescriptionType type = null;
	
	/** holds the ISO code of the language the provided text is written in */
	private String language = null;
	
	/**
	 * Default constructor
	 */
	public ProductDescription() {		
	}
	
	/**
	 * Initializes the entity using the provided information
	 * @param type
	 * @param value
	 * @param language
	 */
	public ProductDescription(ProductDescriptionType type, String value, String language) {
		this.type = type;
		this.value = value;
		this.language = language;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ProductDescriptionType getType() {
		return type;
	}

	public void setType(ProductDescriptionType type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ProductDescription other = (ProductDescription) obj;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
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

	@Override
	public String toString() {
		return "ProductDescription [value=" + value + ", type=" + type
				+ ", language=" + language + "]";
	}
	
	
}
