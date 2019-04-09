package com.comtrade.broker;

import com.comtrade.connection.Connection;
import com.comtrade.domain.GeneralDomain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

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


	public void save(GeneralDomain gd) {
		String query = "INSERT INTO " + gd.returnTableName() + " " + gd.returnTableRows() + " " + gd.returnInsertFormat(gd);
		try {
			Statement st = Connection.getInstance().getConn().createStatement();
			st.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
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
		String upit = "select * from " + gd.returnTableName() + gd.returnUserName(gd);
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
