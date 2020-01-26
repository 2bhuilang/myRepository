package com.greatcattle.dataservice.common.util;

/**
 * This class is the central location to store the internal
 * JNDI names of various entities. Any change here should
 * also be reflected in the deployment descriptors.
 */
public class JNDINames {
    private JNDINames(){} // prevent instanciation

    /** JNDI name of the ibss database source */
    public static final String JK_DATASOURCE = "dataSource" ;
    public static final String CRM_DATASOURCE = "dataSource" ;

    public static final String BSN_DATASOURCE = "dataSource" ;
    public static final String CRM_RCDB = "dataSource" ;
    public static final String CRM_INVOICE = "dataSource" ;
    public static final String DEFAULT_DATASOURCE = "dataSource" ;
    public static final String LOG_DATASOURCE = "LOGDB" ; 
    
    public static final String CM_DATASOURCE = "CMDB" ;
    public static final String PM_DATASOURCE = "dataSource" ;
    public static final String ORD_DATASOURCE = "dataSource" ;
    public static final String RM_DATASOURCE = "dataSource" ;
    public static final String OAAS_DATASOURCE = "dataSource" ;  
}

