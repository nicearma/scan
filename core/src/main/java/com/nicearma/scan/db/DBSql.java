package com.nicearma.scan.db;

/**
 * Created by nicea on 23/10/2016.
 */
public class DBSql {

    public final static String CREATE_TABLE_FILE=  "CREATE TABLE IF NOT EXISTS file  ( ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, PATH varchar(255), NAME varchar(255), size integer) ";
    public final static String INSERT_FILE= "INSERT INTO file (path, name, size) values (?, ?,?)";
    public final static String GET_BIGGEST_FILE= "SELECT path, name, size FROM file ORDER BY size DESC LIMIT ?,? ";

    public final static String CREATE_TABLE_DIR=  "CREATE TABLE IF NOT EXISTS dir ( ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, PATH varchar(255), size integer) ";
    public final static String INSERT_DIR="INSERT INTO dir (path, size) values (?, ?)";

}