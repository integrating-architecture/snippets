/*Authored by www.integrating-architecture.de*/
package org.isa.snip.dbmodel;

import java.sql.ResultSet;

/**
 */
public class DbTableMetadata extends AbstractDbMetadata {

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private String istemporary = "";
	
	/**
	 */
	public DbTableMetadata(String pName) {
		super(pName);
	}

	/**
	 */
	public void fillFrom(ResultSet pRs) throws Exception {
		setIstemporary(pRs.getString(temporary));
	}

	/**
	 */
	public void addColumn(DbColumnMetadata pDef) {
		
	}

	/**
	 */
	public String getIstemporary() {
		return istemporary;
	}

	/**
	 */
	public void setIstemporary(String istemporary) {
		this.istemporary = istemporary;
	}
}
