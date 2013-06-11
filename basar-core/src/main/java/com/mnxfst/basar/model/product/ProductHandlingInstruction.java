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
 * Handling instructions
 * @author mnxfst
 * @since 09.04.2013
 *
 * Revision Control Info $Id$
 */
public enum ProductHandlingInstruction implements Serializable {

	HESENS, // heat sensitive
	STDRY, // store in dry environment
	STKED, // stacked
	AVI, // live animals
	BIG, // out-sized
	CRU, // crushable
	DAE, // dangerous article
	DCE, // delivery via distribution center
	DDE, // direct delivery
	DES, // destroy
	DWS, // product is dish washer safe 
	EAT, // food stuff
	FRO, // frozen
	FTD, // frost danger
	HEA, // heavy cargo
	HGA, // hanging garment
	HWC, // handle with care
	LAB, // label
	LYG, // lying
	MF, // multiple facings
	MOV, // must be moved according to instructions
	NES, // nestable
	NWP, // newspaper
	OHG, // overhang item
	PAC, // to be packed according to instructions
	PER, // perishable good
	PSC, // pest controlling
	RCY, // recyclable packaging
	RFG, // flammable compressed gas
	RFL, // flammable liquid
	RFS, // flammable solid
	RPB, // poison
	SLT, // sensitive to light
	UPR, // upright standing
	DRY, // dry cleaning
	HWSH, // hand washing
	MWSN, // machine washing - normal
	MWSC, // machine washing - cold (30C)
	MWSW, // machine washing - warm (40C)
	MWSH, // machine washing - hot (50C - 95C)
	NWSH, // do not wash
	BLCH, // bleach when needed
	NCHLR, // no chlorine bleach when needed
	NBLCH, // no bleaching
	TMBDR, // tumble dry
	TMBLH, // tumble dry, low heat
	TMBMH, // tumble dry, medium heat
	TMBHH, // tumble dry, high heat
	TMBNH, // tumble dry, no heat
	NTMB, // no tumbling
	LNDRY, // line dry
	NWRNG, // no wringing
	IRN, // iron, any temperature
	IRNL, // iron, low temperature
	IRNM, // iron, medium temperature
	IRNH, // iron, high temperature
	NIRN, // no ironing
	NSTM // no steam
	
	
	
	
}
