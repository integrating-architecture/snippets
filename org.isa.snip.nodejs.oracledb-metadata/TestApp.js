
/*Authored by www.integrating-architecture.de*/

const oracledb = require('oracledb');
const model = require('./oracleDbModel.cjs');
const queries = require('./oracleDbMetadataQueries.cjs');

oracledb.outFormat = oracledb.OUT_FORMAT_OBJECT;

function run(){
    loadMetadata();
}

async function loadMetadata() {
    console.time("loadMetadata");

    let owner = "TEST";
    let query = "";
    let result = null;
    let dbModel = new model.OracleDbModel();

    const connection = await oracledb.getConnection ({
        user          : "test",
        password      : "testpwd",
        connectString : "localhost/XEPDB1"
    });

    query = queries.getAllTablesAndColumsSql(owner);
    result = await connection.execute(query);
    dbModel.loadTablesAndColumsFrom(result);

    query = queries.getAllColumnConstraintsSql(owner);
    result = await connection.execute(query);
    dbModel.loadColumnConstraintsFrom(result);

    query = queries.getAllIndexesSql(owner);
    result = await connection.execute(query);
    dbModel.loadIndexesFrom(result);
 
    await connection.close();

    //console.log(dbModel);
    console.timeEnd("loadMetadata");
}

run();


