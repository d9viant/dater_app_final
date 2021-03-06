package com.comtrade.broker;

import com.comtrade.connection.Connection;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Matches;
import com.comtrade.domain.Message;
import com.comtrade.domain.User;

import java.sql.BatchUpdateException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.comtrade.domain.Constants.DBWRITTEN;
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
				u.setReadyForSql(DBWRITTEN);
			}

			if(u.getLocation().getReadyForSql() == RDYFORDB){
				// LOCATION QUERY
				String query = "INSERT INTO " + u.getLocation().returnTableName() + " " + u.getLocation().returnTableRows() + " " + u.getLocation().returnInsertFormat();
				st.addBatch(query);
				u.getLocation().setReadyForSql(DBWRITTEN);
			}

			if(u.getAge().getReadyForSql() == RDYFORDB){
				// AGE QUERY
				String query = "INSERT INTO " + u.getAge().returnTableName() + " " + u.getAge().returnTableRows() + " " + u.getAge().returnInsertFormat();
				st.addBatch(query);
				u.getAge().setReadyForSql(DBWRITTEN);
			}

			if(u.getRating().getReadyForSql() == RDYFORDB){
				// RATING QUERY
				String query = "INSERT INTO " + u.getRating().returnTableName() + " " + u.getRating().returnTableRows() + " " + u.getRating().returnInsertFormat();
				st.addBatch(query);
				u.getRating().setReadyForSql(DBWRITTEN);
			}

			if(u.getGender().getReadyForSql() == RDYFORDB){
				// GENDER QUERY
				String query = "INSERT INTO " + u.getGender().returnTableName() + " " + u.getGender().returnTableRows() + " " + u.getGender().returnInsertFormat();
				st.addBatch(query);
				u.getGender().setReadyForSql(DBWRITTEN);
			}
		}

		try {
			st.executeBatch();
		} catch (BatchUpdateException ex) {
			ex.printStackTrace();
			System.out.println("User batch Failed to Execute - Line 77 Broker");
		}
	}

	@Override
	public void saveListMessage(Map<String, List<GeneralDomain>> asd) throws SQLException {
		Statement st = Connection.getInstance().getConn().createStatement();
		for (Map.Entry<String, List<GeneralDomain>> entry : asd.entrySet()) {
			List<GeneralDomain> listentry = entry.getValue();
			for (GeneralDomain temp : listentry) {
				Message m = (Message) temp;
				if(m.getReadyForSql() == RDYFORDB){
					String query = "INSERT INTO " + m.returnTableName() + " " + m.returnTableRows() + " " + m.returnInsertFormat();
					st.addBatch(query);
					m.setReadyForSql(DBWRITTEN);
				}
			}
		}
		try {
			st.executeBatch();
		}catch (SQLException e){
			System.out.println("Message batch failed to execute - Line 98 - Broker");
		}



	}

	@Override
	public void saveListMatch(Map<String, List<GeneralDomain>> asd) throws SQLException {
		Statement st = Connection.getInstance().getConn().createStatement();
		for (Map.Entry<String, List<GeneralDomain>> entry : asd.entrySet()) {
			List<GeneralDomain> listentry = entry.getValue();
			for (GeneralDomain temp : listentry) {
				Matches m = (Matches) temp;
				if(m.getReadyForSql() == RDYFORDB){
					String query = "INSERT INTO " + m.returnTableName() + " " + m.returnTableRows() + " " + m.returnInsertFormat();
					st.addBatch(query);
					m.setReadyForSql(DBWRITTEN);
				}
			}
		}
		try {
			st.executeBatch();

		}catch (SQLException e){
			System.out.println("Match batch failed to execute - Line 124 - Broker");

		}
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
        HashMap<String, List<GeneralDomain>> list = new HashMap<>();
        try {
            Statement st = Connection.getInstance().getConn().createStatement();
            ResultSet rs = st.executeQuery(query);
			list = gd.fixInnerSelectList(rs);

		} catch (SQLException e) {
			System.out.println("No Database Entries for Messages and Matches");
		}

		return list;
    }


}
