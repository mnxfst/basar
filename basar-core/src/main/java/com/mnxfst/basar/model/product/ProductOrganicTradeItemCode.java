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
 * Defines the product composition concerning its organic ingredients
 * @author mnxfst
 * @since 09.04.2013
 *
 * Revision Control Info $Id$
 */
public enum ProductOrganicTradeItemCode implements Serializable {

	ORG_1, // 100% pure organics
	ORG_2, // organic
	ORG_3, // made with organic ingredients
	ORG_4, // some organic ingredients
	ORG_5, // not organic
	ORG_6 // unknown
	
}
