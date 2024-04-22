package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Dao {

	// 宣言：データソース、接続オブジェクト
	static DataSource ds;
	static Connection conn;


	public Connection getConnection() throws Exception {

		// try
		try {

			//	条件：データソースがnull
			if ( ds == null ) {}
				// InitialContextオブジェクトを生成
				InitialContext ic = new InitialContext();
				// JNDIリソースのルックアップ
				ds = (DataSource) ic.lookup("java:/comp/env/jdbc/student");
				// 接続を取得
				conn = ds.getConnection();

		// 例外：JNDIリソースが見つからない
        } catch (NamingException e) {
        	e.printStackTrace();
            throw new Exception("JNDIリソースが見つかりません。", e);

        // 例外：その他
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("データベース接続中にエラーが発生しました。", e);
        }

//		戻り値：接続オブジェクト
		return conn;
	}
}