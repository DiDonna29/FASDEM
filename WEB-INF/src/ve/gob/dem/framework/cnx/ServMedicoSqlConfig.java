/**
 * 
 */
package ve.gob.dem.framework.cnx;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * @author marcenrl
 * 
 */
public class ServMedicoSqlConfig {

	private static final SqlMapClient sqlMap;
	static {
		try {
			String resource = "sqlMap-config-servmedico.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error initializing MyAppSqlConfig class. Cause: " + e);
		}
	}

	public static SqlMapClient getSqlMapInstance() {
		return sqlMap;
	}
}
