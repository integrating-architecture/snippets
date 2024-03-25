/*Authored by www.integrating-architecture.de*/

/**
 *  Metadata base class.
 */
class AbstractDbMetadata {
    name = "";
    sysObject = false;

    constructor(name) {
        this.name = name;
    }

    isSysObject() {
        return this.sysObject;
    }

    getName() {
        return this.name;
    }

    setSysObject(flag) {
        this.sysObject = flag;
    }
}

/**
 */
class DbTableMetadata extends AbstractDbMetadata {
    temporary = "";

    constructor(name) {
        super(name);
    }

    addColumn(def){
        //TODO
    }
}

/**
 */
class DbColumnMetadata extends AbstractDbMetadata {
    tableName = "";
    type = "";
    length = "";
    charLength = "";
    precision = "";
    nullable = "";

    constructor(name) {
        super(name);
    }
}

/**
 */
class DbConstraintMetadata extends AbstractDbMetadata {
    type = "";
    tableName = "";
    columnName = "";
    deleteRule = "";
    searchCondition = "";
    relationConstraintName = "";

    constructor(name) {
        super(name);
    }
}

/**
 */
class DbIndexMetadata extends AbstractDbMetadata {
    tableName = "";
    uniqueness = "";
    columnNames = new Set();

    constructor(name) {
        super(name);
    }

    addColumnName(name) {
	    this.columnNames.add(name);
	}
}

/**
 */
enrichMetaData = (def) => {
    if (def.getName().toUpperCase().startsWith("SYS_")) {
        def.setSysObject(true);
    }
    return def;
};

module.exports = {
    DbTableMetadata: DbTableMetadata,
    DbColumnMetadata: DbColumnMetadata,
    DbConstraintMetadata : DbConstraintMetadata,
    DbIndexMetadata : DbIndexMetadata,
    enrichMetaData : enrichMetaData
}
