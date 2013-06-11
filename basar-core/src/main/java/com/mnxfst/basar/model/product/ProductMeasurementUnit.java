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
 * Lists all supported measure types
 * @author mnxfst
 * @since 06.04.2013
 *
 * Revision Control Info $Id$
 */
public enum ProductMeasurementUnit implements Serializable {

	AAA, // unit net weight -- values must be provided as grams
	AAB, // unit gross weight -- values must be provided in grams
	AAJ, // number of units per pallet
	AAK, // fat content	
	AAO, // humidity
	AAP, // voltage
	AAQ, // power consumption
	AAR, // heat dissipation
	AAU, // operative temperature
	AAW, // gross volume
	AAX, // net volume
	AAY, // water content
	ABF, // item width when unrolled
	ABH, // item area when unrolled	
	ABI, // original wort
	ABJ, // volume
	ABQ, // percentage of alcohol
	ACG, // chargeable weight
	ACN, // estimated gross weight
	ACP, // estimated volume
	ACV, // loading meters
	ADJ, // surface
	AEY, // juice weight per 100grams
	AEZ, // fruit skin color
	AEI, // drained weight -- values must be provided as grams
	AEV, // acidity of juice
	AFG, // percentage fat in dry matter
	AFI, // hydrate content of an alcoholic product after bottling
	AFJ, // anhydrous content -- non-water contents
	AFT, // color depth
	AFU, // color depth maximum
	AFV, // image resoultion
	AFW, // device resolution, maximum
	AFX, // acoustic absorption coefficient
	BNU, // peg hole number
	BNV, // number of inner packs
	CT, // content of package
	DBX, // degree BRIX -- rate of sugar	
	DI, // diameter -- values must be provided as millimeters
	DLL, // diluted liquid
	DN, // density
	DP, // depth -- values must be provided as millimeters
	ENE, // energy efficiency 
	G, // gross weight	
	HT, // height dimension -- values must be provided as millimeters
	ID, // inside diameter
	LN, // length
	OD, // outside diameter
	RA, // relative humidity
	RJ, // rockwell c scale
	SAA, // product strength
	SCC, // saccharomatic content of an alcoholic product
	SPV, // product strength basis
	SSZ, // step size used for selling units of a products: 100mtr wood to be sold in 2mtr steps
	T, // tare weight
	TC, // temperature -- values must be provided as celsius values
	TH, // thickness
	TN, // time period
	UCO, // units per package
	WD, // width dimension -- values must be provided as millimeters
	WRM, // weight per running meter
	WSM, // weight per square meter
	X01, // acidity of meat (1 hr after slaughtering)
	X02, // acidity of meat (12hrs after slaughtering)
	X03, // acidity of meat (24hrs after slaughtering)
	X04, // acidity of meat (36hrs after slaughtering)
	X05, // acidity of meat (48hrs after slaughtering)
	X13, // acidity of meat
	X16, // slaughtering weight
	X8E, // peg hole horizontal distance from package leftmost edge -- values must be provided as millimeters
	X9E, // peg hole vertical distance from top -- values must be provided as millimeters
	ZWA // waste content	
	
	
}
