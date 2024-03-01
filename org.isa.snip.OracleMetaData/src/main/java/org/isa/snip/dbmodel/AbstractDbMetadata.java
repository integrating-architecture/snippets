/*Authored by www.integrating-architecture.de*/
package org.isa.snip.dbmodel;

import java.io.Serializable;

/**
 * Abstract db metadata base class.
 */
public abstract class AbstractDbMetadata implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	protected AbstractDbMetadata() {
	}

	/**
	 */
	protected AbstractDbMetadata(String pName) {
		setName(pName);
	}

	private String name = "";
	private String sql = "";
	private boolean isSysObject = false;

	/**
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * @return the isSysObject
	 */
	public boolean isSysObject() {
		return isSysObject;
	}

	/**
	 * @param isSysObject the isSysObject to set
	 */
	public void setSysObject(boolean isSysObject) {
		this.isSysObject = isSysObject;
	}
}
