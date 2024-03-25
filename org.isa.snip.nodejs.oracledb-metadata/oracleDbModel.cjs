/*Authored by www.integrating-architecture.de*/

const modeldef = require('./dbMetadata.cjs');

/**
 */
class OracleDbModel {
    tables = new Map();
    columns = new Map();
    constraints = new Map();
    sysConstraints = new Map();
    indexes = new Map();
    sysIndexes = new Map();

    /**
     */
    loadTablesAndColumsFrom(result) {
        let tableDef = null;
        let columnDef = null;
        let tableName = "";
        let columnName = "";
        let columnKey = "";

        result.rows.forEach((row) => {
            tableName = row.TABLE_NAME;
            columnName = row.COLUMN_NAME;
            columnKey = tableName + "." + columnName;

            if (this.tables.has(tableName)) {
                tableDef = this.tables.get(tableName);
            } else {
                tableDef = new modeldef.DbTableMetadata(tableName);
                this.tables.set(tableName, tableDef);
                this.fillTableMetadataFrom(row, tableDef);
            }

            if (!this.columns.has(columnKey)) {
                columnDef = new modeldef.DbColumnMetadata(columnName);
                this.columns.set(columnKey, columnDef);
                this.fillColumnMetadataFrom(row, columnDef);
                tableDef.addColumn(columnDef);
            }
        });
    }

    /**
     */
    loadColumnConstraintsFrom(result) {
        let columnKey = "";
        let columnDef = null;
        let constraintDef = null;
        let tableName = "";
        let columnName = "";
        let constraintName = "";


        result.rows.forEach((row) => {
            constraintName = row.CONSTRAINT_NAME;
            tableName = row.TABLE_NAME;
            columnName = row.COLUMN_NAME;
            columnKey = tableName + "." + columnName;

            constraintDef = this.getConstraintDef(constraintName);
            if (constraintDef == null) {
                constraintDef = new modeldef.DbConstraintMetadata(constraintName);
                this.fillConstraintMetadataFrom(row, constraintDef)

                if (constraintDef.isSysObject()) {
                    this.sysConstraints.set(constraintName, constraintDef);
                } else {
                    this.constraints.set(constraintName, constraintDef);
                }
            }
        });
    }

    /**
     */
    loadIndexesFrom(result) {
        let columnKey = "";
        let columnDef = null;
        let indexDef = null;
        let tableName = "";
        let columnName = "";
        let indexName = "";


        result.rows.forEach((row) => {
            indexName = row.INDEX_NAME;
            tableName = row.TABLE_NAME;
            columnName = row.COLUMN_NAME;
            columnKey = tableName + "." + columnName;

            indexDef = this.getIndexDef(indexName);
            if (indexDef == null) {
                indexDef = new modeldef.DbIndexMetadata(indexName);
                this.fillIndexMetadataFrom(row, indexDef)

                if (indexDef.isSysObject()) {
                    this.sysIndexes.set(indexName, indexDef);
                } else {
                    this.indexes.set(indexName, indexDef);
                }
            }
        });
    }

    /**
     */
    getConstraintDef(name) {
        if (this.constraints.has(name)) {
            return this.constraints.get(name);
        } else if (this.sysConstraints.has(name)) {
            return this.sysConstraints.get(name);
        }
        return null;
    }

    /**
     */
    getIndexDef(name) {
        if (this.indexes.has(name)) {
            return this.indexes.get(name);
        } else if (this.sysIndexes.has(name)) {
            return this.sysIndexes.get(name);
        }
        return null;
    }

    /**
     */
    fillTableMetadataFrom(row, def) {
        def.temporary = row.TEMPORARY;
    }

    /**
     */
    fillColumnMetadataFrom(row, def) {
        def.tableName = row.TABLE_NAME;
        def.type = row.DATA_TYPE;
        def.length = row.DATA_LENGTH;
        def.charLength = row.CHAR_LENGTH;
        def.precision = row.DATA_PRECISION;
        def.nullable = row.NULLABLE;
    }

    /**
     */
    fillConstraintMetadataFrom(row, def) {
        def.type = row.CONSTRAINT_TYPE;
        def.tableName = row.TABLE_NAME;
        def.columnName = row.COLUMN_NAME;
        def.deleteRule = row.DELETE_RULE;
        def.searchCondition = row.SEARCH_CONDITION;
        def.relationConstraintName = row.R_CONSTRAINT_NAME;
        modeldef.enrichMetaData(def);
    }

    /**
     */
    fillIndexMetadataFrom(row, def) {
        def.tableName = row.TABLE_NAME;
        def.addColumnName(row.COLUMN_NAME);
        def.uniqueness = row.UNIQUENESS;
        modeldef.enrichMetaData(def);
    }
}


module.exports = {
    OracleDbModel: OracleDbModel
}
