/*Authored by www.integrating-architecture.de*/
package org.isa.snip.dbmodel;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 */
public class DbTableMetadata extends AbstractDbMetadata {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private Map< String, DbColumnMetadata > columns = new LinkedHashMap<>();
	
	/**
	 */
	public DbTableMetadata(String pName) {
		super(pName);
	}

	/**
	 * @param pCol
	 */
	public void addColumn(DbColumnMetadata pCol) {
		columns.put(pCol.getName(), pCol);
	}

	/**
	 * @return the columns
	 */
	public Map<String, DbColumnMetadata> getColumns() {
		return columns;
	}
}
