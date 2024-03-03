/*Authored by www.integrating-architecture.de*/

package org.isa.snip.dbmodel;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.isa.snip.dbmodel.util.ExprString;
import org.isa.snip.dbmodel.util.Helper;
import org.isa.snip.dbmodel.util.JdbcConnection;

/**
 */
public class OracleDbModel extends AbstractDbModel {

	private static final Logger LOG = Helper.getLoggerFor(OracleDbModel.class);

	// Text file providing Metadata SQL Queries as flat "xml elements"
	protected static final String SQL_QUERIES_FILE = "/" + OracleDbModel.class.getPackageName().replaceAll("[.]", "/")
			+ "/oracle.metadata.queries.sql";

	protected static final String FILTER_MARK = "filter";
	protected static final String OWNER_MARK = "owner";

	protected static final String QUERY_ALL_TABLES_AND_COLUMNS = "all.tables.and.columns";
	protected static final String QUERY_USER_TABLES_AND_COLUMNS = "user.tables.and.columns";
	protected static final String QUERY_ALL_COLUMN_CONSTRAINTSS = "all.column.constraints";
	protected static final String QUERY_USER_COLUMN_CONSTRAINTSS = "user.column.constraints";
	protected static final String QUERY_ALL_INDEXES = "all.indexes";
	protected static final String QUERY_USER_INDEXES = "user.indexes";
	protected static final String QUERY_ALL_SEQUENCES = "all.sequences";
	protected static final String QUERY_USER_SEQUENCES = "user.sequences";
	protected static final String QUERY_ALL_TYPES = "all.types";
	protected static final String QUERY_USER_TYPES = "user.types";
	protected static final String QUERY_ALL_TABLE_COMMENTS = "all.table.comments";
	protected static final String QUERY_USER_TABLE_COMMENTS = "user.table.comments";
	protected static final String QUERY_ALL_COLUMN_COMMENTS = "all.column.comments";
	protected static final String QUERY_USER_COLUMN_COMMENTS = "user.column.comments";

	protected static Map<String, String> QueryStatements = null;

	protected JdbcConnection dbConnection;
	protected String schemaOwner = "TEST";

	/**
	 */
	public OracleDbModel() {
	}

	/**
	 */
	public void setDbConnection(JdbcConnection pConnection) {
		this.dbConnection = pConnection;
	}

	/**
	 */
	@Override
	public void loadMetaData() throws Exception {
		try {
			new TableAndColumnMetaDataLoader().run();
		} finally {
			dbConnection.close();
		}
	}

	/**
	 * Retrieve SQL Metadata Queries from the Queries File.
	 */
	protected ExprString getSqlStatement(String pName) throws Exception {
		String lSql = "";
		String lAllStatements = "";
		ExprString lQueryStatement;

		if (QueryStatements == null) {
			lAllStatements = Helper.readResourceFile(SQL_QUERIES_FILE);
			QueryStatements = new HashMap<>();
			QueryStatements.put(SQL_QUERIES_FILE, lAllStatements);
		}

		if (!QueryStatements.containsKey(pName)) {
			String lStart = "<" + pName + ">";
			String lEnd = "</" + pName + ">";
			lAllStatements = QueryStatements.get(SQL_QUERIES_FILE);
			lSql = lAllStatements.substring(lAllStatements.indexOf(lStart) + lStart.length(),
					lAllStatements.indexOf(lEnd));
			QueryStatements.put(pName, lSql);
		}
		lSql = QueryStatements.get(pName);

		lQueryStatement = ExprString.newFor(lSql.trim());
		lQueryStatement.put(OWNER_MARK, schemaOwner.toUpperCase());
		lQueryStatement.put(FILTER_MARK, "");

		return lQueryStatement;
	}

	/********************************************************************
	 * Internal Metadata Loader classes.
	 ********************************************************************/

	/**
	 * TableAndColumnMetaDataLoader.
	 */
	private class TableAndColumnMetaDataLoader extends AbstractMetaDataLoader {

		@Override
		protected void doLoad() throws Exception {
			ExprString lQuerySql = getSqlStatement(QUERY_ALL_TABLES_AND_COLUMNS);
			String lSql = lQuerySql.build();
			LOG.info("SQL: " + lSql);

			result = dbConnection.executeSql(lSql);
			boolean lWork = result.next();
			while (lWork) {
				lWork = loadFrom(result);
			}
		}

		/**
		 */
		private boolean loadFrom(ResultSet pRes) throws Exception {
			DbTableMetadata lTblDef = null;
			DbColumnMetadata lColDef = null;
			String lTableName = pRes.getString(table_name);
			String lColumnName = pRes.getString(column_name);

			if (!tables.containsKey(lTableName)) {
				lTblDef = new DbTableMetadata(lTableName);
				lTblDef.fillFrom(pRes);
				putToMap(tables, lTableName, lTblDef);
			} else {
				lTblDef = tables.get(lTableName);
			}

			String lColumnKey = new StringBuilder(lTableName).append(".").append(lColumnName).toString();

			if (!columns.containsKey(lColumnKey)) {
				lColDef = new DbColumnMetadata(lColumnName);
				lColDef.fillFrom(pRes);
				putToMap(columns, lColumnKey, lColDef);
				lTblDef.addColumn(lColDef);
			}

			return pRes.next();
		}
	}

	/**
	 * The loaders abstract base class.
	 */
	private abstract class AbstractMetaDataLoader implements DbQueryFieldNames{
		protected ResultSet result = null;

		/**
		 */
		protected void run() throws Exception {
			try {
				doBeforeLoad();
				doLoad();
				doAfterLoad();
			} finally {
				dbConnection.closeCurrentStatement();
			}
		}

		/**
		 */
		protected void doBeforeLoad() {
			LOG.info("[" + getClass().getSimpleName() + "] Start");
		}

		/**
		 */
		protected void doAfterLoad() throws Exception {
			if (result != null) {
				result.close();
			}
			dbConnection.closeCurrentStatement();
			LOG.info("[" + getClass().getSimpleName() + "] Done");
		}

		/**
		 */
		abstract protected void doLoad() throws Exception;
	}
}
