package hrds.commons.utils;

import hrds.commons.codes.Store_type;

import java.util.*;

/**
 * 存储层的key常量
 * 所有的存储层的key都应该从这里取
 */
public class StorageTypeKey {

	private static final Map<String, List<String>> FINALLY_STORAGE_KEYS = new HashMap<>();
	private static final List<String> UPDATE_FINALLY_STORAGE_KEYS = new ArrayList<>();


	public static final String database_driver = "database_driver";
	public static final String jdbc_url = "jdbc_url";
	public static final String user_name = "user_name";
	public static final String database_pwd = "database_pwd";
	public static final String database_type = "database_type";
	public static final String database_name = "database_name";

	public static final String core_site = "core-site.xml";
	public static final String hdfs_site = "hdfs-site.xml";
	public static final String yarn_site = "yarn-site.xml";
	public static final String hbase_site = "hbase-site.xml";
	public static final String mapred_site = "mapred-site.xml";
	public static final String keytab = "keytab";
	public static final String krb5 = "krb5";
	//平台版本
	public static final String platform = "platform";
	//操作hdfs的用户名
	public static final String hadoop_user_name = "hadoop_user_name";
	//kerberos认证文件名称
	public static final String keytab_file = "keytab_file";
	//kerberos认证用户
	public static final String keytab_user = "keytab_user";
	//sftpHost
	public static final String sftp_host = "sftp_host";
	//sftpUser
	public static final String sftp_user = "sftp_user";
	//sftpPwd
	public static final String sftp_pwd = "sftp_pwd";
	//sftpPort
	public static final String sftp_port = "sftp_port";
	//服务器外部表存储根目录
	public static final String external_root_path = "external_root_path";

	public static final String solr_url = "solr_url";

	public static final String zkhost = "zkhost";
	//数据库的编码
	public static final String database_code = "database_code";

	static {

		List<String> databaseKeys = new ArrayList<>(Arrays.
				asList(database_driver, jdbc_url, user_name, database_pwd, database_type, database_name));
		FINALLY_STORAGE_KEYS.put(Store_type.DATABASE.getCode(), databaseKeys);

		List<String> hiveKeys = new ArrayList<>(Arrays.
				asList(database_driver, jdbc_url, user_name, database_pwd, core_site,
						hdfs_site, yarn_site, hbase_site, mapred_site, keytab, krb5));
		FINALLY_STORAGE_KEYS.put(Store_type.HIVE.getCode(), hiveKeys);

		List<String> hbaseKeys = new ArrayList<>(Arrays.
				asList(zkhost, core_site, hdfs_site, hbase_site, keytab, krb5));
		FINALLY_STORAGE_KEYS.put(Store_type.HBASE.getCode(), hbaseKeys);

		List<String> solrKeys = new ArrayList<>(Collections.singletonList(solr_url));
		FINALLY_STORAGE_KEYS.put(Store_type.SOLR.getCode(), solrKeys);

		UPDATE_FINALLY_STORAGE_KEYS.add(core_site);
		UPDATE_FINALLY_STORAGE_KEYS.add(hdfs_site);
		UPDATE_FINALLY_STORAGE_KEYS.add(yarn_site);
		UPDATE_FINALLY_STORAGE_KEYS.add(hbase_site);
		UPDATE_FINALLY_STORAGE_KEYS.add(mapred_site);
		UPDATE_FINALLY_STORAGE_KEYS.add(keytab);
		UPDATE_FINALLY_STORAGE_KEYS.add(krb5);

	}


	public static Map<String, List<String>> getFinallyStorageKeys() {

		return FINALLY_STORAGE_KEYS;
	}

	public static List<String> getUpdateFinallyStorageKeys() {

		return UPDATE_FINALLY_STORAGE_KEYS;
	}
}