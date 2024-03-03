/*Authored by www.integrating-architecture.de*/
package org.isa.snip.dbmodel;

import java.sql.ResultSet;

/**
 */
public class DbColumnMetadata extends AbstractDbMetadata {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private String tablename = "";
	private String type = "";
	private String length = "";
	private String precision = "";
	private String scale = "";
	private String isnullable = "";
	private String charlength = "";
	private String comment = "";

	private String sqlType = "";
	private String sqlDBType = "";
	private String sqlPrimaryKey = "";
	private String sqlForeignKey = "";
	private boolean isPrimaryKey = false;
	private boolean isForeignKey = false;
	private boolean isNotNull = false;
	private boolean isUnique = false;
	private boolean isUserType = false;

	/**
	 */
	public DbColumnMetadata(String pName) {
		super(pName);
	}

	/**
	 */
	public DbColumnMetadata(String pTablename, String pName) {
		super(pName);
		tablename = pTablename;
	}

	/**
	 */
	public void fillFrom(ResultSet pRs) throws Exception {
		setTablename(pRs.getString(table_name));
		setType(pRs.getString(data_type));
		setLength(pRs.getString(data_length));
		setPrecision(pRs.getString(data_precision));
		setScale(pRs.getString(data_scale));
		
		setNullable(pRs.getString(nullable));
		setCharlength(pRs.getString(char_length));
	}

	/**
	 */
	public String getTablename() {
		return tablename;
	}

	/**
	 */
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	/**
	 */
	public String getType() {
		return type;
	}

	/**
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 */
	public String getLength() {
		return length;
	}

	/**
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 */
	public String getPrecision() {
		return precision;
	}

	/**
	 */
	public void setPrecision(String precision) {
		this.precision = precision;
	}

	/**
	 */
	public String getScale() {
		return scale;
	}

	/**
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}

	/**
	 */
	public String getNullable() {
		return isnullable;
	}

	/**
	 */
	public void setNullable(String nullable) {
		this.isnullable = nullable;
	}

	/**
	 */
	public String getCharlength() {
		return charlength;
	}

	/**
	 */
	public void setCharlength(String charlength) {
		this.charlength = charlength;
	}

	/**
	 */
	public String getComment() {
		return comment;
	}

	/**
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 */
	public String getSqlType() {
		return sqlType;
	}

	/**
	 */
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	/**
	 */
	public String getSqlDBType() {
		return sqlDBType;
	}

	/**
	 */
	public void setSqlDBType(String sqlDBType) {
		this.sqlDBType = sqlDBType;
	}

	/**
	 */
	public String getSqlPrimaryKey() {
		return sqlPrimaryKey;
	}

	/**
	 */
	public void setSqlPrimaryKey(String sqlPrimaryKey) {
		this.sqlPrimaryKey = sqlPrimaryKey;
	}

	/**
	 */
	public String getSqlForeignKey() {
		return sqlForeignKey;
	}

	/**
	 */
	public void setSqlForeignKey(String sqlForeignKey) {
		this.sqlForeignKey = sqlForeignKey;
	}

	/**
	 */
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	/**
	 */
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	/**
	 */
	public boolean isForeignKey() {
		return isForeignKey;
	}

	/**
	 */
	public void setForeignKey(boolean isForeignKey) {
		this.isForeignKey = isForeignKey;
	}

	/**
	 */
	public boolean isNotNull() {
		return isNotNull;
	}

	/**
	 */
	public void setNotNull(boolean isNotNull) {
		this.isNotNull = isNotNull;
	}

	/**
	 */
	public boolean isUnique() {
		return isUnique;
	}

	/**
	 */
	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	/**
	 */
	public boolean isUserType() {
		return isUserType;
	}

	/**
	 */
	public void setUserType(boolean isUserType) {
		this.isUserType = isUserType;
	}

}
