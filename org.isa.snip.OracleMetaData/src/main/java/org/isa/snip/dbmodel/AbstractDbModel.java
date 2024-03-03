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
		if(!pDestMap.containsKey(pKey)) {
			pDestMap.put(pKey, pData);
		}
	}
	
}
