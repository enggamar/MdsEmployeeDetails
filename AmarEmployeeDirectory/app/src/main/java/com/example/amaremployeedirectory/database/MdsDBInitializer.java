package com.example.amaremployeedirectory.database;


import com.example.amaremployeedirectory.database.table.EmployeeAuthTable;
import com.example.amaremployeedirectory.database.table.EmployeeDetailTable;

public class MdsDBInitializer extends BaseDBInitializer {
    private static String DB_NAME = "Mds_database";
    private static int DB_VERSION = 1;

    public MdsDBInitializer() {
        super(DB_NAME, DB_VERSION, CREATE_QUERIES, TABLE_NAMES);
    }

    protected static String[] TABLE_NAMES = new String[]
            {
//                    EmployeeAuthTable.TABLE_NAME,
//                    EmployeeDetailTable.TABLE_NAME
            };


    protected static String[] CREATE_QUERIES = new String[]
            {
                    EmployeeAuthTable.CREATE_USER_TABLE,
                    EmployeeDetailTable.CREATE_USER_TABLE
            };


}
