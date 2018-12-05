/**
 *
 * Author Created on
 * CTS    January 2013
 *
 * Copyright (c) 2005-2013 Cognizant Technology Solution. All rights reserved.
 *
 * This software is the confidential and proprietary information of Cognizant
 * Technology Solution. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of use.
 */
package com.cognizant.idw.nosqldb;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


//import org.apache.log4j.Logger;

public class DBServices 
{

    public static DBObject getData(String tableDocName, ArrayList<String> pkCompositeValues, String dataStoreDir) throws Exception {
        return DBEngine.getData (tableDocName, pkCompositeValues,dataStoreDir);
    }

    public static DBObject getData(String tableDocName, String pkValue, String dataStoreDir) throws Exception {
        return DBEngine.getData (tableDocName, pkValue,dataStoreDir);
    }

    public static boolean insertData(String tableDocName, ArrayList<String> pkCompositeValues, DBObject dataObj, String dataStoreDir) throws Exception {
        return DBEngine.insertData(tableDocName, pkCompositeValues, dataObj,dataStoreDir);
    }

    public static boolean insertData(String tableDocName, String pkValue, DBObject dataObj, String dataStoreDir) throws Exception {
        return DBEngine.insertData(tableDocName, pkValue, dataObj,dataStoreDir);
    }

    public static boolean updateData(String tableDocName, ArrayList<String> pkCompositeValues, DBObject dataObj, String dataStoreDir) throws Exception {
        return DBEngine.updateData(tableDocName, pkCompositeValues, dataObj,dataStoreDir);
    }

    public static boolean updateData(String tableDocName, String pkValue, DBObject dataObj, String dataStoreDir) throws Exception {
        return DBEngine.updateData(tableDocName, pkValue, dataObj,dataStoreDir);
    }

    public static boolean deleteData(String tableDocName, ArrayList<String> pkCompositeValues, String dataStoreDir) throws Exception {
        return DBEngine.deleteData(tableDocName, pkCompositeValues,dataStoreDir);
    }

    public static boolean deleteData(String tableDocName, String pkValue, String dataStoreDir) throws Exception {
        return DBEngine.deleteData(tableDocName, pkValue,dataStoreDir);
    }

    public static ArrayList<String> getKeyValuesList(String tableDocName, String dataStoreDir) throws Exception {
        return DBEngine.getKeyValuesList(tableDocName,dataStoreDir);
    }
}
