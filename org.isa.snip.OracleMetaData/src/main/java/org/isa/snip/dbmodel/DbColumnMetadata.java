/*Authored by www.integrating-architecture.de*/
package org.isa.snip.dbmodel;

/**
 */
public class DbColumnMetadata extends AbstractDbMetadata {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private String tableName = "";
	private String qualifiedName = "";
	
	/**
	 */
	public DbColumnMetadata(String pTableName, String pName) {
		super(pName);
		tableName = pTableName;
		qualifiedName = tableName+"."+pName;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @return the qualifiedName
	 */
	public String getQualifiedName() {
		return qualifiedName;
	}

}
