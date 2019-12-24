package com.revature.repositories;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.log4j.Logger;


import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementsDAOImpl implements ReimbursementsDAO {
	
	private static Logger logger = Logger.getLogger(ReimbursementsDAOImpl.class);

	public TreeMap<Integer, Reimbursement> getReimbursementsFromUserId(int userId) {

		TreeMap<Integer, Reimbursement> reimbursements = new TreeMap<>();
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM project1.reimbursements WHERE author = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);

			
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while(rs.next()) {
				int reimId = rs.getInt("reimbursementid");
				double amount = rs.getDouble("amount");
				String timeSubmitted = rs.getString("timesubmitted");
				String timeResolved = rs.getString("timeresolved");
				String descript = rs.getString("discription");
				byte[] receipt = rs.getBytes("receipt");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int statusNum = rs.getInt("status");
				int typeNum = rs.getInt("reimtype");
				
				
				Reimbursement reimbursement = new Reimbursement(reimId, amount, timeSubmitted, timeResolved, descript, receipt, author, resolver, statusNum, typeNum);
				reimbursements.put(i, reimbursement);
				i++;
				
			}
			rs.close();
			
		} catch (SQLException e) {
			logger.warn("Unable to get Reimbursements from userID", e);
			e.printStackTrace();
		}
		return reimbursements;
	}

	

	public TreeMap<Integer, Reimbursement> getReimbursementsFromStatus(int statusNo) {
		
		TreeMap<Integer, Reimbursement> statusReimbursements = new TreeMap<>();
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM project1.reimbursements WHERE status = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, statusNo);

			
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while(rs.next()) {
				int reimId = rs.getInt("reimbursementid");
				double amount = rs.getDouble("amount");
				String timeSubmitted = rs.getString("timesubmitted");
				String timeResolved = rs.getString("timeresolved");
				String descript = rs.getString("discription");
				byte[] receipt = rs.getBytes("receipt");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int statusNum = rs.getInt("status");
				int typeNum = rs.getInt("reimtype");
				
				
				Reimbursement reimbursement = new Reimbursement(reimId, amount, timeSubmitted, timeResolved, descript, receipt, author, resolver, statusNum, typeNum);
				statusReimbursements.put(i, reimbursement);
				i++;
				
			}
			rs.close();
			
		} catch (SQLException e) {
			logger.warn("Unable to get Reimbursements from Status", e);
			e.printStackTrace();
		}
		return statusReimbursements;
	}

	public Reimbursement getReimbursementFromReimId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateReimbursement(Reimbursement reimbursement) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE project1.reimbursements SET amount = ?, timesubmitted = ?, timeresolved = ?, discription = ?, receipt = ?, author = ?, resolver = ?, status = ?, reimtype = ? WHERE reimbursementid = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, reimbursement.getAmount());
			stmt.setString(2, reimbursement.getTimeSubmitted());
			stmt.setString(3, reimbursement.getTimeResolved());
			stmt.setString(4, reimbursement.getDescript());
			stmt.setBytes(5, reimbursement.getReceipt());
			stmt.setInt(6, reimbursement.getAuthor());
			stmt.setInt(7, reimbursement.getResolver());
			stmt.setInt(8, reimbursement.getStatusNum());
			stmt.setInt(9, reimbursement.getTypeNum());
			stmt.setInt(10, reimbursement.getReimId());
			boolean check = stmt.execute();
			if(check == false) {
				return true;
			}

		} catch (SQLException e) {
			logger.warn("Unable to update reimbursement information", e);
			e.printStackTrace();
		}
		return false;
	}

	public boolean createReimbursement(Reimbursement reimbursement) {


		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT into project1.reimbursements (amount, timeSubmitted, timeResolved, discription, receipt, author, resolver, status, reimType) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, reimbursement.getAmount());
			stmt.setString(2, reimbursement.getTimeSubmitted());
			stmt.setString(3, reimbursement.getTimeResolved());
			stmt.setString(4, reimbursement.getDescript());
			stmt.setBytes(5, reimbursement.getReceipt());
			stmt.setInt(6, reimbursement.getAuthor());
			stmt.setInt(7, reimbursement.getResolver());
			stmt.setInt(8, reimbursement.getStatusNum());
			stmt.setInt(9, reimbursement.getTypeNum());
			
			boolean check = stmt.execute();
			if(check == false) {
				return true;
			}

		} catch (SQLException e) {
			logger.warn("Unable to create reimbursement information", e);
			e.printStackTrace();
		}
		return false;
	}

}
