package hrds.agent.trans.biz;

import fd.ng.core.annotation.DocClass;
import fd.ng.core.annotation.Method;
import fd.ng.core.annotation.Param;
import fd.ng.core.annotation.Return;
import fd.ng.db.conf.ConnWay;
import fd.ng.db.conf.DbinfosConf;
import fd.ng.db.conf.Dbtype;
import fd.ng.db.jdbc.DatabaseWrapper;
import hrds.agent.job.biz.bean.SourceDataConfBean;
import hrds.commons.codes.DatabaseType;
import hrds.commons.exception.AppSystemException;

import java.util.Map;

@DocClass(desc = "数据库直连采集获取数据库连接", author = "WangZhengcheng", createdate = "2019/10/28 14:26")
public class ConnectionTool {

	@Method(desc = "根据数据库配置信息获取数据库连接", logicStep = "" +
			"1、将SourceDataConfBean对象中的内容封装到dbInfo中" +
			"2、获取数据库类型" +
			"3、根据数据库类型获取对应数据库的数据库连接")
	@Param(name = "dbConfigBean", desc = "该对象封装了海云应用服务端发过来的数据库采集连接数据库的信息", range = "DBConfigBean类型对象")
	@Return(desc = "项目中常用的DatabaseWrapper对象", range = "DatabaseWrapper类型对象")
	public static DatabaseWrapper getDBWrapper(SourceDataConfBean dbConfigBean) {
		//1、将SourceDataConfBean对象中的内容封装到dbInfo中
		DbinfosConf.Dbinfo dbInfo = new DbinfosConf.Dbinfo();
		dbInfo.setName(DbinfosConf.DEFAULT_DBNAME);
		dbInfo.setDriver(dbConfigBean.getDatabase_drive());
		dbInfo.setUrl(dbConfigBean.getJdbc_url());
		dbInfo.setUsername(dbConfigBean.getUser_name());
		dbInfo.setPassword(dbConfigBean.getDatabase_pad());
		dbInfo.setWay(ConnWay.JDBC);
		//2、获取数据库类型
		dbInfo.setDbtype(getDbType(dbConfigBean.getDatabase_type()));
		dbInfo.setShow_conn_time(true);
		dbInfo.setShow_sql(true);
		//3、根据数据库类型获取对应数据库的数据库连接
		return new DatabaseWrapper.Builder().dbconf(dbInfo).create();
	}

	public static DatabaseWrapper getDBWrapper(Map<String, String> dbConfig) {
		//1、将SourceDataConfBean对象中的内容封装到dbInfo中
		DbinfosConf.Dbinfo dbInfo = new DbinfosConf.Dbinfo();
		dbInfo.setName(DbinfosConf.DEFAULT_DBNAME);
		dbInfo.setDriver(dbConfig.get("database_drive"));
		dbInfo.setUrl(dbConfig.get("jdbc_url"));
		dbInfo.setUsername(dbConfig.get("user_name"));
		dbInfo.setPassword(dbConfig.get("database_pad"));
		dbInfo.setWay(ConnWay.JDBC);
		//2、获取数据库类型
		dbInfo.setDbtype(getDbType(dbConfig.get("database_type")));
		dbInfo.setShow_conn_time(true);
		dbInfo.setShow_sql(true);
		//3、根据数据库类型获取对应数据库的数据库连接
		return new DatabaseWrapper.Builder().dbconf(dbInfo).create();
	}

	private static Dbtype getDbType(String database_type) {
		//获取数据库类型
		DatabaseType typeConstant = DatabaseType.ofEnumByCode(database_type);
		if (typeConstant == DatabaseType.MYSQL) {
			return Dbtype.MYSQL;
		} else if (typeConstant == DatabaseType.Oracle9i) {
			throw new AppSystemException("系统不支持Oracle9i及以下");
		} else if (typeConstant == DatabaseType.Oracle10g) {
			return Dbtype.ORACLE;
		} else if (typeConstant == DatabaseType.SqlServer2000) {
			return Dbtype.SQLSERVER;
		} else if (typeConstant == DatabaseType.SqlServer2005) {
			return Dbtype.SQLSERVER;
		} else if (typeConstant == DatabaseType.DB2) {
			//TODO 使用db2v1还是db2v2
			return Dbtype.DB2V1;
		} else if (typeConstant == DatabaseType.SybaseASE125) {
			return Dbtype.SYBASE;
		} else if (typeConstant == DatabaseType.Informatic) {
			//Informatic
			throw new AppSystemException("系统不支持该数据库类型");
		} else if (typeConstant == DatabaseType.H2) {
			//H2
			throw new AppSystemException("系统不支持该数据库类型");
		} else if (typeConstant == DatabaseType.ApacheDerby) {
			//ApacheDerBy
			throw new AppSystemException("系统不支持该数据库类型");
		} else if (typeConstant == DatabaseType.Postgresql) {
			return Dbtype.POSTGRESQL;
		} else if (typeConstant == DatabaseType.GBase) {
			return Dbtype.GBASE;
		} else if (typeConstant == DatabaseType.TeraData) {
			return Dbtype.TERADATA;
		} else {
			throw new AppSystemException("系统不支持该数据库类型");
		}
	}
}