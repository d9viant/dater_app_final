package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static com.comtrade.domain.Constants.RDYFORDB;

public class Rating implements GeneralDomain, Serializable
{

    private String username;
    private int rating = 1300;
    private boolean newStatus = true;
    private boolean superUser = false;
	private static int K = 50;
	private int readyForSql = RDYFORDB;



	public Rating() {
		
	}
	//Method to calculate the player Rating.	
  
	public void EloRating(int userA, boolean btn){
		float userB=rating;
		if(newStatus) {
			K = 100;
		}
		
	    // Calculate the Winning 
        // Probability of User B 
        float Pb = Probability(userA,userB );
      
        // Calculate the Winning 
        // Probability of User A 
        float Pa = Probability(userB, userA);
      
        // User B Wins and gets + rating
        // Updating the ratings 
        if (btn) {
        	userB = userB + K * (1 - Pb);        	
        } 
      
        // User B Loss and gets - rating
        // Updating the ratings 
        else { 
        	userB = userB + K * (0 - Pa);         	
        }

      rating = (int) (Math.round(userB * 1000000.0) / 1000000.0);
	}
	 // Function to calculate the Probability 
	private static float Probability(float rating1,
                              float rating2)
   {
       return 1.0f * 1.0f / (1 + 1.0f *
               (float)(Math.pow(10, 1.0f *
              (rating1 - rating2) / 400)));
   }
	
	
	
	// Setters and Getters
    public int getRating() {
		return rating;
	}

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isNewStatus() {
		return newStatus;
	}

    public void setNewStatus(boolean newStatus) {
        this.newStatus = newStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getK() {
		return K;
	}

    public void setK(int k) {
		K = k;
	}

    public boolean isSuperUser() {
		return superUser;
	}

    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
	}

	@Override
	public GeneralDomain fixSelect(ResultSet rs) {
		return null;
	}

	@Override
	public String returnInnerJoin() {
		return null;
	}

	@Override
	public String returnTableName() {
		return null;
	}

	@Override
	public String returnTableRows() {

		return null;
	}

	@Override
    public String returnInsertFormat() {
		return null;
	}

	@Override
	public String delete(GeneralDomain gd) {
		return null;
	}

    @Override
    public HashMap<String, GeneralDomain> fixInnerSelect(ResultSet rs) throws SQLException {
        return null;
    }

	@Override
	public HashMap<String, List<GeneralDomain>> fixInnerSelectList(ResultSet rs) throws SQLException {
		return null;
	}

	@Override
    public String returnUserName() {
        return null;
    }


    @Override
    public String getForSelectForSpecific(GeneralDomain u) {
		return null;
	}

	public int getReadyForSql() {
		return readyForSql;
	}

	public void setReadyForSql(int readyForSql) {
		this.readyForSql = readyForSql;
	}
}
