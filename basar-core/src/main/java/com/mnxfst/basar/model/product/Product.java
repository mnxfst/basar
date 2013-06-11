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
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;

/**
 * Describes a product entity 
 * @author mnxfst
 */ 

public class Product implements Serializable {

	private static final long serialVersionUID = -1086862291264948153L;
	
	///////////////////////////////////////////////////////////////////////////////////
	// list of attributes to be used for looking up a product
	public static final String QUERY_ATTRIBUTE_PRODUCT_ID = "productId"; 
	
	///////////////////////////////////////////////////////////////////////////////////
	// identifiers required for product lookups	
	/** unique identifier required by mongo database to reference the entity */
	private ObjectId id = null;
	
	/** unique product identifier used to reference the product within the marketplace space */  
	private String productId = null;

	/** product supplier */
	private String supplierId = null;
	
	/** product identifier in supplier space */
	private String supplierProductId = null;
	
	/** global trade item number: UPC, EAN, ISBN, JAN - must contain a prefix followed by a colon. EAN:3001234567892 */
	private String gtin = null;	
	///////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////
	// unit, order quantities	
	/** minimum order quantity */
	private Integer minOrderQuantity = null;
	
	/** maximum order quantity */
	private Integer maxOrderQuantity = null;
	
	/** default order quantity */
	private Integer defaultOrderQuantity = null;
		
	///////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////
	// product description, additional product information	
	/** holds textual information describing the product */
	private List<ProductDescription> descriptions = new ArrayList<>();
	
	/** holds a list of tags describing the product with a single or two-word expression; used for searching etc. */
	private Set<String> tags = new HashSet<>();
	
	/** list of colors the product shows */
	private Set<String> colors = new HashSet<>();
	
	/** list of product measures */
	private Map<ProductMeasurementUnit, String> measures = new HashMap<>();
	
	/** set of materials used for building the product */	
	private Set<ProductMaterial> materials = new HashSet<>();
	
	/** links to external product information */
	private Map<ProductExternalLinkType, String> externalLinks = new HashMap<>();
	
	/** ISO 3166 two alpha code of origin country */
	private String countryOfOrigin = null;

	/** name of the product manufacturer */
	private String manufacturer = null;
	
	/** the manufacturers safety data sheet number */ 
	private String safetySheetNumber = null;
	
	/** marks the product as being recycable */
	private Boolean recyclable = Boolean.FALSE;	
	
	/** brand the product belongs to, e.g. manufacturer = Levi's, brand = 501 */
	private String brand = null;
	
	/** variation type, e.g. special summer edition */
	private String variation = null;
	
	/** names the gender the product is dedicated to */
	private ProductTargetGender targetGender = ProductTargetGender.NEUTRAL;
	
	/** age limit */
	private int ageLimit = 0;
		
	/** warranty information */
	private String warranty = null;	
	////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////////////
	// pricing information, payment terms and allowed destination markets	
	/** lists the default prices of a product if nothing else is provided */
	private Set<ProductPrice> defaultPrices = new HashSet<>();
	
	/** names the base unit for selling a product: square meter */
	private ProductSellingUnit sellingBaseUnit = null;
	
	/** base amount */
	private Double sellingBaseAmount = null;
	
	/** price per base unit: 100EUR per 10 square meters */
	private ProductPrice sellingBasePrice = null;

	/** names the taxes to be applied for this product TODO what about the collecting party */ 
	private Map<ProductTaxType, Double> taxes = new HashMap<>();
	
	/** list of allowed target markets, provided as ISO 3166 alpha two codes */
	private Set<String> targetMarkets = new HashSet<>();
	
	/** due date for payment as days after receiving the invoice */
	private int paymentWithinDays = 0;
	
	/** names the currency payments must be placed in */
	private Currency paymentCurrency = null;
	
	/** names the first date at which this product might be ordered */
	private Date firstOrderingDate = null;
	
	/** names the last date at which this product might be ordered */
	private Date lastOrderingDate = null;
	
	///////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////////////
	// transportation / delivery
	
	/** lists the available delivery options for this product */
	private Set<ProductDelivery> deliveryConditions = new HashSet<>();
	
	/** list of handling instructions */
	private Set<ProductHandlingInstruction> handlingInstructions = new HashSet<>();
	
	///////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////
	// lifecycle methods - to be handled by the application when persisting
	/** creation date */
	private Date created = null;
	
	/** last modification */
	private Date modified = null;
	
	/** entity version */
	private long version = 0;
	///////////////////////////////////////////////////////////////////////////////////

	public Product() {		
	}
	
	public void addTag(String tag) {
		if(this.tags == null)
			this.tags = new HashSet<>();
		this.tags.add(tag);
	}
	
	public void removeTag(String tag) {
		if(this.tags != null)
			this.tags.remove(tag);
	}
	
	public void addColor(String color) {
		if(this.colors == null)
			this.colors = new HashSet<>();
		this.colors.add(color);
	}
	
	public void removeColor(String color) {
		if(this.colors != null)
			this.colors.remove(color);
	}
	
	public void addMeasure(ProductMeasurementUnit measureUnit, String value) {
		if(this.measures == null)
			this.measures = new HashMap<>();
		this.measures.put(measureUnit, value);
	}
	
	public void removeMeasure(ProductMeasurementUnit measureUnit) {
		if(this.measures != null)
			this.measures.remove(measureUnit);
	}
	
	public void addMaterial(ProductMaterial material) {
		if(this.materials == null)
			this.materials = new HashSet<>();
		this.materials.add(material);
	}
	
	public void removeMaterial(ProductMaterial material) {
		if(this.materials != null)
			this.materials.remove(material);
	}
	
	public void addExternalLink(ProductExternalLinkType linkType, String link) {
		if(this.externalLinks == null)
			this.externalLinks = new HashMap<>();
		this.externalLinks.put(linkType, link);
	}
	
	public void removeExternalLink(ProductExternalLinkType linkType) {
		if(this.externalLinks != null)
			this.externalLinks.remove(linkType);
	}
	
	public void addDefaultPrice(ProductPrice price) {
		if(this.defaultPrices == null)
			this.defaultPrices = new HashSet<>();
		this.defaultPrices.add(price);
	}
	
	public void removeDefaultPrice(ProductPrice price) {
		if(this.defaultPrices != null)
			this.defaultPrices.remove(price);
	}
	
	public void addTax(ProductTaxType type, Double value) {
		if(this.taxes == null)
			this.taxes = new HashMap<>();
		this.taxes.put(type, value);
	}
	
	public void removeTax(ProductTaxType type) {
		if(this.taxes != null)
			this.taxes.remove(type);
	}
	
	public void addTargetMarket(String targetMarketISO3166) {
		if(this.targetMarkets == null)
			this.targetMarkets = new HashSet<>();
		this.targetMarkets.add(targetMarketISO3166);
	}
	
	public void removeTargetMarket(String targetMarketISO3166) {
		if(this.targetMarkets != null)
			this.targetMarkets.remove(targetMarketISO3166);
	}

	public void addDeliveryCondition(ProductDelivery deliveryCondition) {
		if(this.deliveryConditions == null)
			this.deliveryConditions = new HashSet<>();
		this.deliveryConditions.add(deliveryCondition);
	}
	
	public void removeDeliveryCondition(ProductDelivery deliveryCondition) {
		if(this.deliveryConditions != null)
			this.deliveryConditions.remove(deliveryCondition);
	}
	
	public void addHandlingInstruction(ProductHandlingInstruction handlingInstruction) {
		if(this.handlingInstructions == null) 
			this.handlingInstructions = new HashSet<>();
		this.handlingInstructions.add(handlingInstruction);
	}
	
	public void removeHandlingInstruction(ProductHandlingInstruction handlingInstruction) {
		if(this.handlingInstructions != null)
			this.handlingInstructions.remove(handlingInstruction);
	}
	
	public void addDescription(ProductDescription description) {
		if(this.descriptions == null)
			this.descriptions = new ArrayList<>();
		this.descriptions.add(description);
	}
	
	public void removeDescription(ProductDescription description) {
		if(this.descriptions != null)
			this.descriptions.remove(description);
	}
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierProductId() {
		return supplierProductId;
	}

	public void setSupplierProductId(String supplierProductId) {
		this.supplierProductId = supplierProductId;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public Integer getMinOrderQuantity() {
		return minOrderQuantity;
	}

	public void setMinOrderQuantity(Integer minOrderQuantity) {
		this.minOrderQuantity = minOrderQuantity;
	}

	public Integer getMaxOrderQuantity() {
		return maxOrderQuantity;
	}

	public void setMaxOrderQuantity(Integer maxOrderQuantity) {
		this.maxOrderQuantity = maxOrderQuantity;
	}

	public Integer getDefaultOrderQuantity() {
		return defaultOrderQuantity;
	}

	public void setDefaultOrderQuantity(Integer defaultOrderQuantity) {
		this.defaultOrderQuantity = defaultOrderQuantity;
	}

	public List<ProductDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<ProductDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Set<String> getColors() {
		return colors;
	}

	public void setColors(Set<String> colors) {
		this.colors = colors;
	}

	public Map<ProductMeasurementUnit, String> getMeasures() {
		return measures;
	}

	public void setMeasures(Map<ProductMeasurementUnit, String> measures) {
		this.measures = measures;
	}

	public Set<ProductMaterial> getMaterials() {
		return materials;
	}

	public void setMaterials(Set<ProductMaterial> materials) {
		this.materials = materials;
	}

	public Map<ProductExternalLinkType, String> getExternalLinks() {
		return externalLinks;
	}

	public void setExternalLinks(Map<ProductExternalLinkType, String> externalLinks) {
		this.externalLinks = externalLinks;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSafetySheetNumber() {
		return safetySheetNumber;
	}

	public void setSafetySheetNumber(String safetySheetNumber) {
		this.safetySheetNumber = safetySheetNumber;
	}

	public Boolean getRecyclable() {
		return recyclable;
	}

	public void setRecyclable(Boolean recyclable) {
		this.recyclable = recyclable;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getVariation() {
		return variation;
	}

	public void setVariation(String variation) {
		this.variation = variation;
	}

	public ProductTargetGender getTargetGender() {
		return targetGender;
	}

	public void setTargetGender(ProductTargetGender targetGender) {
		this.targetGender = targetGender;
	}

	public int getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(int ageLimit) {
		this.ageLimit = ageLimit;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public Set<ProductPrice> getDefaultPrices() {
		return defaultPrices;
	}

	public void setDefaultPrices(Set<ProductPrice> defaultPrices) {
		this.defaultPrices = defaultPrices;
	}

	public ProductSellingUnit getSellingBaseUnit() {
		return sellingBaseUnit;
	}

	public void setSellingBaseUnit(ProductSellingUnit sellingBaseUnit) {
		this.sellingBaseUnit = sellingBaseUnit;
	}

	public Double getSellingBaseAmount() {
		return sellingBaseAmount;
	}

	public void setSellingBaseAmount(Double sellingBaseAmount) {
		this.sellingBaseAmount = sellingBaseAmount;
	}

	public ProductPrice getSellingBasePrice() {
		return sellingBasePrice;
	}

	public void setSellingBasePrice(ProductPrice sellingBasePrice) {
		this.sellingBasePrice = sellingBasePrice;
	}

	public Map<ProductTaxType, Double> getTaxes() {
		return taxes;
	}

	public void setTaxes(Map<ProductTaxType, Double> taxes) {
		this.taxes = taxes;
	}

	public Set<String> getTargetMarkets() {
		return targetMarkets;
	}

	public void setTargetMarkets(Set<String> targetMarkets) {
		this.targetMarkets = targetMarkets;
	}

	public int getPaymentWithinDays() {
		return paymentWithinDays;
	}

	public void setPaymentWithinDays(int paymentWithinDays) {
		this.paymentWithinDays = paymentWithinDays;
	}

	public Currency getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(Currency paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public Date getFirstOrderingDate() {
		return firstOrderingDate;
	}

	public void setFirstOrderingDate(Date firstOrderingDate) {
		this.firstOrderingDate = firstOrderingDate;
	}

	public Date getLastOrderingDate() {
		return lastOrderingDate;
	}

	public void setLastOrderingDate(Date lastOrderingDate) {
		this.lastOrderingDate = lastOrderingDate;
	}

	public Set<ProductDelivery> getDeliveryConditions() {
		return deliveryConditions;
	}

	public void setDeliveryConditions(Set<ProductDelivery> deliveryConditions) {
		this.deliveryConditions = deliveryConditions;
	}

	public Set<ProductHandlingInstruction> getHandlingInstructions() {
		return handlingInstructions;
	}

	public void setHandlingInstructions(
			Set<ProductHandlingInstruction> handlingInstructions) {
		this.handlingInstructions = handlingInstructions;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ageLimit;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((colors == null) ? 0 : colors.hashCode());
		result = prime * result
				+ ((countryOfOrigin == null) ? 0 : countryOfOrigin.hashCode());
		result = prime
				* result
				+ ((defaultOrderQuantity == null) ? 0 : defaultOrderQuantity
						.hashCode());
		result = prime * result
				+ ((defaultPrices == null) ? 0 : defaultPrices.hashCode());
		result = prime
				* result
				+ ((deliveryConditions == null) ? 0 : deliveryConditions
						.hashCode());
		result = prime * result
				+ ((descriptions == null) ? 0 : descriptions.hashCode());
		result = prime * result
				+ ((externalLinks == null) ? 0 : externalLinks.hashCode());
		result = prime
				* result
				+ ((firstOrderingDate == null) ? 0 : firstOrderingDate
						.hashCode());
		result = prime * result + ((gtin == null) ? 0 : gtin.hashCode());
		result = prime
				* result
				+ ((handlingInstructions == null) ? 0 : handlingInstructions
						.hashCode());
		result = prime
				* result
				+ ((lastOrderingDate == null) ? 0 : lastOrderingDate.hashCode());
		result = prime * result
				+ ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result
				+ ((materials == null) ? 0 : materials.hashCode());
		result = prime
				* result
				+ ((maxOrderQuantity == null) ? 0 : maxOrderQuantity.hashCode());
		result = prime * result
				+ ((measures == null) ? 0 : measures.hashCode());
		result = prime
				* result
				+ ((minOrderQuantity == null) ? 0 : minOrderQuantity.hashCode());
		result = prime * result
				+ ((paymentCurrency == null) ? 0 : paymentCurrency.hashCode());
		result = prime * result + paymentWithinDays;
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result
				+ ((recyclable == null) ? 0 : recyclable.hashCode());
		result = prime
				* result
				+ ((safetySheetNumber == null) ? 0 : safetySheetNumber
						.hashCode());
		result = prime
				* result
				+ ((sellingBaseAmount == null) ? 0 : sellingBaseAmount
						.hashCode());
		result = prime
				* result
				+ ((sellingBasePrice == null) ? 0 : sellingBasePrice.hashCode());
		result = prime * result
				+ ((sellingBaseUnit == null) ? 0 : sellingBaseUnit.hashCode());
		result = prime * result
				+ ((supplierId == null) ? 0 : supplierId.hashCode());
		result = prime
				* result
				+ ((supplierProductId == null) ? 0 : supplierProductId
						.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result
				+ ((targetGender == null) ? 0 : targetGender.hashCode());
		result = prime * result
				+ ((targetMarkets == null) ? 0 : targetMarkets.hashCode());
		result = prime * result + ((taxes == null) ? 0 : taxes.hashCode());
		result = prime * result
				+ ((variation == null) ? 0 : variation.hashCode());
		result = prime * result
				+ ((warranty == null) ? 0 : warranty.hashCode());
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
		Product other = (Product) obj;
		if (ageLimit != other.ageLimit)
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (colors == null) {
			if (other.colors != null)
				return false;
		} else if (!colors.equals(other.colors))
			return false;
		if (countryOfOrigin == null) {
			if (other.countryOfOrigin != null)
				return false;
		} else if (!countryOfOrigin.equals(other.countryOfOrigin))
			return false;
		if (defaultOrderQuantity == null) {
			if (other.defaultOrderQuantity != null)
				return false;
		} else if (!defaultOrderQuantity.equals(other.defaultOrderQuantity))
			return false;
		if (defaultPrices == null) {
			if (other.defaultPrices != null)
				return false;
		} else if (!defaultPrices.equals(other.defaultPrices))
			return false;
		if (deliveryConditions == null) {
			if (other.deliveryConditions != null)
				return false;
		} else if (!deliveryConditions.equals(other.deliveryConditions))
			return false;
		if (descriptions == null) {
			if (other.descriptions != null)
				return false;
		} else if (!descriptions.equals(other.descriptions))
			return false;
		if (externalLinks == null) {
			if (other.externalLinks != null)
				return false;
		} else if (!externalLinks.equals(other.externalLinks))
			return false;
		if (firstOrderingDate == null) {
			if (other.firstOrderingDate != null)
				return false;
		} else if (!firstOrderingDate.equals(other.firstOrderingDate))
			return false;
		if (gtin == null) {
			if (other.gtin != null)
				return false;
		} else if (!gtin.equals(other.gtin))
			return false;
		if (handlingInstructions == null) {
			if (other.handlingInstructions != null)
				return false;
		} else if (!handlingInstructions.equals(other.handlingInstructions))
			return false;
		if (lastOrderingDate == null) {
			if (other.lastOrderingDate != null)
				return false;
		} else if (!lastOrderingDate.equals(other.lastOrderingDate))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (materials == null) {
			if (other.materials != null)
				return false;
		} else if (!materials.equals(other.materials))
			return false;
		if (maxOrderQuantity == null) {
			if (other.maxOrderQuantity != null)
				return false;
		} else if (!maxOrderQuantity.equals(other.maxOrderQuantity))
			return false;
		if (measures == null) {
			if (other.measures != null)
				return false;
		} else if (!measures.equals(other.measures))
			return false;
		if (minOrderQuantity == null) {
			if (other.minOrderQuantity != null)
				return false;
		} else if (!minOrderQuantity.equals(other.minOrderQuantity))
			return false;
		if (paymentCurrency == null) {
			if (other.paymentCurrency != null)
				return false;
		} else if (!paymentCurrency.equals(other.paymentCurrency))
			return false;
		if (paymentWithinDays != other.paymentWithinDays)
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (recyclable == null) {
			if (other.recyclable != null)
				return false;
		} else if (!recyclable.equals(other.recyclable))
			return false;
		if (safetySheetNumber == null) {
			if (other.safetySheetNumber != null)
				return false;
		} else if (!safetySheetNumber.equals(other.safetySheetNumber))
			return false;
		if (sellingBaseAmount == null) {
			if (other.sellingBaseAmount != null)
				return false;
		} else if (!sellingBaseAmount.equals(other.sellingBaseAmount))
			return false;
		if (sellingBasePrice == null) {
			if (other.sellingBasePrice != null)
				return false;
		} else if (!sellingBasePrice.equals(other.sellingBasePrice))
			return false;
		if (sellingBaseUnit != other.sellingBaseUnit)
			return false;
		if (supplierId == null) {
			if (other.supplierId != null)
				return false;
		} else if (!supplierId.equals(other.supplierId))
			return false;
		if (supplierProductId == null) {
			if (other.supplierProductId != null)
				return false;
		} else if (!supplierProductId.equals(other.supplierProductId))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (targetGender != other.targetGender)
			return false;
		if (targetMarkets == null) {
			if (other.targetMarkets != null)
				return false;
		} else if (!targetMarkets.equals(other.targetMarkets))
			return false;
		if (taxes == null) {
			if (other.taxes != null)
				return false;
		} else if (!taxes.equals(other.taxes))
			return false;
		if (variation == null) {
			if (other.variation != null)
				return false;
		} else if (!variation.equals(other.variation))
			return false;
		if (warranty == null) {
			if (other.warranty != null)
				return false;
		} else if (!warranty.equals(other.warranty))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Product [id=" + id + ", productId=" + productId
				+ ", supplierId=" + supplierId + ", supplierProductId="
				+ supplierProductId + ", gtin=" + gtin + ", minOrderQuantity="
				+ minOrderQuantity + ", maxOrderQuantity=" + maxOrderQuantity
				+ ", defaultOrderQuantity=" + defaultOrderQuantity
				+ ", descriptions=" + descriptions + ", tags=" + tags
				+ ", colors=" + colors + ", measures=" + measures
				+ ", materials=" + materials + ", externalLinks="
				+ externalLinks + ", countryOfOrigin=" + countryOfOrigin
				+ ", manufacturer=" + manufacturer + ", safetySheetNumber="
				+ safetySheetNumber + ", recyclable=" + recyclable + ", brand="
				+ brand + ", variation=" + variation + ", targetGender="
				+ targetGender + ", ageLimit=" + ageLimit + ", warranty="
				+ warranty + ", defaultPrices=" + defaultPrices
				+ ", sellingBaseUnit=" + sellingBaseUnit
				+ ", sellingBaseAmount=" + sellingBaseAmount
				+ ", sellingBasePrice=" + sellingBasePrice + ", taxes=" + taxes
				+ ", targetMarkets=" + targetMarkets + ", paymentWithinDays="
				+ paymentWithinDays + ", paymentCurrency=" + paymentCurrency
				+ ", firstOrderingDate=" + firstOrderingDate
				+ ", lastOrderingDate=" + lastOrderingDate
				+ ", deliveryConditions=" + deliveryConditions
				+ ", handlingInstructions=" + handlingInstructions
				+ ", created=" + created + ", modified=" + modified
				+ ", version=" + version + "]";
	}
	
	
}
