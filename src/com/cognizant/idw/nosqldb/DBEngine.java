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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;






//import org.apache.log4j.Logger;

public class DBEngine {

    public static final int CONCURRENCY_TYPE_SEQUENTIAL = 1;
    public static final int CONCURRENCY_TYPE_PARALLEL = 2;

    private static final int TRANS_TYPE_INSERT = 1;
    private static final int TRANS_TYPE_UPDATE = 2;
    private static final int TRANS_TYPE_DELETE = 3;

     //private static Logger logger = Logger.getLogger(DBEngine.class);

    //private static String dataStoreDir = System.getProperty("user.dir") + "\\NoSQLDBDataStore\\";
    
   // private static String dataStoreDir = Common.getNoSQLDBPath();
    
    private static String tableNameStoreDir = System.getProperty("user.dir") + "\\NoSQLDBTblNameStore\\";
    private static String keyStoreDir = System.getProperty("user.dir") + "\\NoSQLDBKeyStore\\";

    private static int concurrencyType = CONCURRENCY_TYPE_PARALLEL;

    public static boolean setConcurrencyStrategy (int type)
    {
    	if ((type == CONCURRENCY_TYPE_SEQUENTIAL) || (type == CONCURRENCY_TYPE_PARALLEL))
    	{
    		concurrencyType = type;
    		return true;
    	}

    	return false;
    }

    public static DBObject getData(String tableDocName, ArrayList<String> pkCompositeValues,String dataStoreDir) throws Exception
    {
    	String pkValue = getNoDBCompositeKeyValue(pkCompositeValues);

    	return getData(tableDocName, pkValue,dataStoreDir);
    }

    public static boolean insertData(String tableDocName, ArrayList<String> pkCompositeValues, DBObject dataObj,String dataStoreDir) throws Exception
    {
    	String pkValue = getNoDBCompositeKeyValue(pkCompositeValues);

        return insertData(tableDocName, pkValue, dataObj,dataStoreDir);
    }

    public static boolean updateData (String tableDocName, ArrayList<String> pkCompositeValues, DBObject dataObj,String dataStoreDir) throws Exception
    {
    	String pkValue = getNoDBCompositeKeyValue(pkCompositeValues);

    	return updateData(tableDocName, pkValue, dataObj,dataStoreDir);
	}

    public static boolean deleteData (String tableDocName, ArrayList<String> pkCompositeValues,String dataStoreDir) throws Exception
    {
    	String pkValue = getNoDBCompositeKeyValue(pkCompositeValues);

    	return deleteData(tableDocName, pkValue,dataStoreDir);
	}

    public static DBObject getData(String tableDocName, String pkValue,String dataStoreDir) throws Exception
    {
    	//System.out.println("pkValue---"+pkValue+"::::"+tableDocName);
    	File dataFile = getDataFile (tableDocName, pkValue,dataStoreDir);
    	//System.out.println("dataFile---"+dataFile);
    	if (dataFile == null)
    		return null;

		DBObject dbObj = null;
        try
        {
        	
        	
            FileInputStream fileInStrem = new FileInputStream(dataFile);
            ObjectInputStream objIn = new ObjectInputStream(fileInStrem);
            Object obj = objIn.readObject();
            objIn.close();
            fileInStrem.close();
            if (obj instanceof DBObject)
            {
                dbObj = (DBObject) obj;
                return dbObj;
            }
        }
        catch (Exception ex)
        {
           // logger.error("UserID:" + keyInfo.getUserId() + " ProjectID:" + tableDocName + " Error Message:" + ex.getMessage());
            //logger.error("UserID:" + keyInfo.getUserId() + " ProjectID:" + tableDocName + " Error Message:" + "No More Connection Details Found");
            //throw ex;
        }

        return dbObj;
    }

    public static boolean insertData(String tableDocName, String pkValue, DBObject dataObj,String dataStoreDir) throws Exception
    {
    	if (concurrencyType == CONCURRENCY_TYPE_SEQUENTIAL)
    		return syncDMLTransactions (TRANS_TYPE_INSERT, tableDocName, pkValue, dataObj,dataStoreDir);
    	else
    		return insertDataSync (tableDocName, pkValue, dataObj,dataStoreDir);
    }

    public static boolean updateData(String tableDocName, String pkValue, DBObject dataObj,String dataStoreDir) throws Exception
    {
    	if (concurrencyType == CONCURRENCY_TYPE_SEQUENTIAL)
    		return syncDMLTransactions (TRANS_TYPE_UPDATE, tableDocName, pkValue, dataObj,dataStoreDir);
    	else
    		return updateDataSync(tableDocName, pkValue, dataObj,dataStoreDir);
    }
    public static boolean deleteData(String tableDocName, String pkValue,String dataStoreDir) throws Exception
    {
    	if (concurrencyType == CONCURRENCY_TYPE_SEQUENTIAL)
    		return syncDMLTransactions (TRANS_TYPE_DELETE, tableDocName, pkValue, null,dataStoreDir);
    	else
    		return deleteDataSync(tableDocName, pkValue,dataStoreDir);
    }

    private synchronized static boolean syncDMLTransactions (int transType, String tableDocName, String pkValue, DBObject dataObj,String dataStoreDir) throws Exception
    {
    	switch (transType)
    	{
    		case TRANS_TYPE_INSERT:
        		return insertDataSync (tableDocName, pkValue, dataObj, dataStoreDir);
    		case TRANS_TYPE_UPDATE:
        		return updateDataSync (tableDocName, pkValue, dataObj, dataStoreDir);
    		case TRANS_TYPE_DELETE:
        		return deleteDataSync (tableDocName, pkValue, dataStoreDir);
    	}

    	return false;
    }

    private static boolean insertDataSync(String tableDocName, String pkValue, DBObject dataObj,String dataStoreDir) throws Exception
    {
    	File dataFile = getDataFile (tableDocName, pkValue,dataStoreDir);
    	if (dataFile != null)
			throw (new Exception("Key already exists - Cannot insert this record"));

        try
        {
        	
            File file = new File(dataStoreDir + "\\" + tableDocName);
            file.mkdirs();
            
            File dtfile = new File(file + "\\" + pkValue + ".bin" );
            //System.out.println("dtfile---"+dtfile);
            FileOutputStream fileOutStream = new FileOutputStream(dtfile);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOutStream);
            objOut.writeObject(dataObj);
            objOut.flush();
            objOut.close();
            fileOutStream.flush();
            fileOutStream.close();
        }
        catch (Exception ex)
        {
//            logger.error("UserID:" + keyInfo.getUserId() + " ProjectID:" + tableDocName + " Error Message:" + ex.getMessage(), ex);
            //throw ex;
        	return false;
        }

        return true;
    }

    private static boolean updateDataSync(String tableDocName, String pkValue, DBObject dataObj,String dataStoreDir) throws Exception
    {
    	File dataFile = getDataFile (tableDocName, pkValue,dataStoreDir);
    	if (dataFile == null)
    		return false;

        try
        {
        	dataFile.delete();
            FileOutputStream fileOutStream = new FileOutputStream(dataFile);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOutStream);
            objOut.writeObject(dataObj);
            objOut.flush();
            objOut.close();
            fileOutStream.flush();
            fileOutStream.close();
        }
        catch (Exception ex)
        {
          //  logger.error("UserID:" + keyInfo.getUserId() + " ProjectID:" + tableDocName + " Error Message:" + ex.getMessage(), ex);
            //throw ex;
        	return false;
        }

        return true;
    }

    private static boolean deleteDataSync(String tableDocName, String pkValue,String dataStoreDir) throws Exception
    {
    	File dataFile = getDataFile (tableDocName, pkValue,dataStoreDir);
    	if (dataFile == null)
    		return false;

        try
        {
        	return dataFile.delete();
        }
        catch (Exception ex)
        {
          //  logger.error("UserID:" + keyInfo.getUserId() + " ProjectID:" + tableDocName + " Error Message:" + ex.getMessage(), ex);
            return false;
        }
    }

    public static ArrayList<String> getKeyValuesList(String tableDocName,String dataStoreDir) throws Exception
    {
    	
    	
    	if (!isValidNoDBTableName (tableDocName))
			throw (new Exception("Invalid Table Name"));

        ArrayList<String> lst = new ArrayList<String>();
        //lst.add("");
        System.out.println("dataStoreDir---"+dataStoreDir);
        try
        {
            File file = new File(dataStoreDir + "\\" + tableDocName + "\\");
            if (file.exists()){
            	//return lst;

            String fname[] = file.list();
            for (int i = 0; i < fname.length; i++)
            {
            	//System.out.println("filename---->"+fname[i]);
                lst.add(fname[i].substring(0, fname[i].lastIndexOf(".bin")));
            }
            }
        }
        catch (Exception ex)
        {
           // logger.error("UserID:" + keyInfo.getUserId() + " ProjectID:" + tableDocName + " Error Message:" + ex.getMessage(), ex);
            throw ex;
        }

        return lst;
    }

    private static File getDataFile (String tableDocName, String pkValue,String dataStoreDir) throws Exception
    {
    	
    	if (!isValidNoDBTableName (tableDocName))
			throw (new Exception("Invalid Table Doc Name"));

    	if (!isValidNoDBKeyValue(pkValue))
			throw (new Exception("Invalid Key Value"));

        try
        {
    		File file = new File(dataStoreDir + "\\" + tableDocName + "\\" + pkValue + ".bin");
            if (file.exists())
                return file;
        }
        catch (Exception ex)
        {
        	return null;
        }
        finally
        {
        }

        return null;
    }

    private static String getNoDBCompositeKeyValue(ArrayList<String> pkCompositeValues) throws Exception
    {
    	String pkValue = "";

    	if (pkCompositeValues.size() <= 0)
			throw (new Exception("Key not found in Key Values List"));

    	for (int i = 0; i < pkCompositeValues.size(); i++)
    	{
    		if (!isValidNoDBKeyValue(pkCompositeValues.get(i)))
    			throw (new Exception("Invalid Key Value"));

    		if (i > 0)
    			pkValue += ("_" + pkCompositeValues.get(i));
    		else
    			pkValue = pkCompositeValues.get(i);
    	}

    	return pkValue;
    }

    private static boolean isValidNoDBKeyValue (String keyValue) throws Exception
    {
    	if (keyValue == null)
    		throw (new Exception("Key Value cannot be NULL"));

    	if (keyValue.trim().length() <= 0)
    		throw (new Exception("Key Value cannot be empty string"));

    	return true;
    }

    private static boolean isValidNoDBTableName (String tableName) throws Exception
    {
    	if (tableName == null)
    		throw (new Exception("Table Name cannot be NULL"));

    	if (tableName.trim().length() <= 0)
    		throw (new Exception("Table Name cannot be empty string"));

    	return true;
    }
}
