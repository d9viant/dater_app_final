package com.comtrade.broker;

import com.comtrade.connection.Connection;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.comtrade.domain.Constants.RDYFORDB;

public class Broker implements IBroker {
	private static Broker instance;
	private static final Object mutex = new Object();

	public Broker() {

	}
	public static Broker getInstance() {
		Broker result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null) {
					result = instance = new Broker();
				}
			}

		}
		return instance;
	}


	// BATCH FOR DATABASE
	public void save(Map<String, GeneralDomain> gd) throws SQLException {
		Statement st = Connection.getInstance().getConn().createStatement();
		for (Map.Entry<String, GeneralDomain> entry : gd.entrySet()) {
			User u = (User) entry.getValue();
			if(u.getReadyForSql() == RDYFORDB) {
				// USER QUERY
				String query = "INSERT INTO " + u.returnTableName() + " " + u.returnTableRows() + " " + u.returnInsertFormat();
				st.addBatch(query);

			}else if(u.getLocation().getReadyForSql() == RDYFORDB){
				// LOCATION QUERY
				String query = "INSERT INTO " + u.getLocation().returnTableName() + " " + u.getLocation().returnTableRows() + " " + u.getLocation().returnInsertFormat();
				st.addBatch(query);

			}else if(u.getAge().getReadyForSql() == RDYFORDB){
				// AGE QUERY
				String query = "INSERT INTO " + u.getAge().returnTableName() + " " + u.getAge().returnTableRows() + " " + u.getAge().returnInsertFormat();
				st.addBatch(query);

			}else if(u.getRating().getReadyForSql() == RDYFORDB){
				// RATING QUERY
				String query = "INSERT INTO " + u.getRating().returnTableName() + " " + u.getRating().returnTableRows() + " " + u.getRating().returnInsertFormat();
				st.addBatch(query);

			}else if(u.getGender().getReadyForSql() == RDYFORDB){
				// GENDER QUERY
				String query = "INSERT INTO " + u.getGender().returnTableName() + " " + u.getGender().returnTableRows() + " " + u.getGender().returnInsertFormat();
				st.addBatch(query);

			}
		}

		st.executeBatch();
	}

	@Override
	public void saveList(Map<String, List<GeneralDomain>> asd) throws SQLException {



	}

	public void delete(GeneralDomain gd) {
		String statement = "DELETE FROM " + gd.returnTableName() + gd.delete(gd);
		try {
			Statement st = Connection.getInstance().getConn().createStatement();
			st.executeUpdate(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public GeneralDomain getFromDb(GeneralDomain gd) {
        String upit = "select * from " + gd.returnTableName() + gd.returnUserName();
		GeneralDomain obj = null;
		try {
			Statement st = Connection.getInstance().getConn().createStatement();
			ResultSet rs = st.executeQuery(upit);
			obj = gd.fixSelect(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;
	}


    @Override
    public HashMap<String, GeneralDomain> getInnerJoinUser(GeneralDomain gd) {
		String upit = gd.returnInnerJoin();
        HashMap<String, GeneralDomain> userHash = new HashMap<>();
        try {
            Statement st = Connection.getInstance().getConn().createStatement();
            ResultSet rs = st.executeQuery(upit);

            userHash = gd.fixInnerSelect(rs);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userHash;
    }

    @Override
    public HashMap<String, List<GeneralDomain>> getInnerJoinList(GeneralDomain gd) {
        String query = gd.returnInnerJoin();
        HashMap<String, List<GeneralDomain>> userHash = new HashMap<>();
        try {
            Statement st = Connection.getInstance().getConn().createStatement();
            ResultSet rs = st.executeQuery(query);
            userHash = gd.fixInnerSelectList(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userHash;
    }


}
