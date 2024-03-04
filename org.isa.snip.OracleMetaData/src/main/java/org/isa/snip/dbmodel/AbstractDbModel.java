/*Authored by www.integrating-architecture.de*/

package org.isa.snip.dbmodel;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class AbstractDbModel {

	protected Map<String, DbTableMetadata> tables = new LinkedHashMap<>();
	protected Map<String, DbColumnMetadata> columns = new LinkedHashMap<>();
	protected Map<String, DbConstraintMetadata> constraints = new LinkedHashMap<>();
	protected Map<String, DbConstraintMetadata> sysConstraints = new LinkedHashMap<>();
	protected Map<String, DbIndexMetadata> indexes = new LinkedHashMap<>();
	protected Map<String, DbIndexMetadata> sysIndexes = new LinkedHashMap<>();
	protected Map<String, DbSequenceMetadata> sequences = new LinkedHashMap<>();
	protected Map<String, DbTypeMetadata> types = new LinkedHashMap<>();
	
	/**
	 */
	public AbstractDbModel() {
	}

	/**
	 */
	public abstract void loadMetaData() throws Exception;

	/**
	 */
	protected void putToMap(Map pDestMap, Object pKey, Object pData) {
		if (!pDestMap.containsKey(pKey)) {
			pDestMap.put(pKey, pData);
		}
	}

	/**
	 */
	protected DbConstraintMetadata getConstraintDef(String pName) {
		if (constraints.containsKey(pName)) {
			return constraints.get(pName);
		}
		return sysConstraints.get(pName);
	}

	/**
	 */
	protected DbIndexMetadata getIndexDef(String pName) {
		if (indexes.containsKey(pName)) {
			return indexes.get(pName);
		}
		return sysIndexes.get(pName);
	}

}
