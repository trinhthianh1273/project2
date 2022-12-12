package com.example.model;

import com.example.common.ICommon;
import com.example.connect.DBConnect;
import com.example.entity.Receipt;
import com.example.entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ReceiptModel implements ICommon<Receipt> {
    private Connection conn=null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private int insertedId = 0;
    private String table = "receipt";

    @Override
    public ObservableList<Receipt> getAll() {
        ObservableList<Receipt> list = FXCollections.observableArrayList();
        String sql = "Select * from " + this.table;

        try {
            conn = DBConnect.getConnect();
            pstmt = conn.prepareStatement(sql);
            rs= pstmt.executeQuery();

            while (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getInt("id"));
                receipt.setContract_id(rs.getInt("contract_id"));
                receipt.setYear(rs.getInt("year"));
                receipt.setMonth(rs.getInt("month"));
                receipt.setElectric(rs.getInt("electric"));
                receipt.setWater(rs.getInt("water"));
                receipt.setService(rs.getInt("service"));
                receipt.setForfeit(rs.getInt("forfeit"));
                receipt.setTotal_payment(rs.getInt("total_payment"));
                receipt.setStatus(rs.getInt("status"));
                receipt.setDescription(rs.getString("description"));

                list.add(receipt);
            }
        } catch (SQLException e) {

        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(pstmt);
            DBConnect.closeConnect(conn);
        }
        return list;
    }

    @Override
    public Receipt getOne(int id) {
        Receipt receipt = new Receipt();
        String sql = "Select * from " + this.table + " where id = ?";

        try {
            conn = DBConnect.getConnect();
            pstmt = conn.prepareStatement(sql);
            rs= pstmt.executeQuery();

            while (rs.next()) {

                receipt.setId(rs.getInt("id"));
                receipt.setContract_id(rs.getInt("contract_id"));
                receipt.setYear(rs.getInt("year"));
                receipt.setMonth(rs.getInt("month"));
                receipt.setElectric(rs.getInt("electric"));
                receipt.setWater(rs.getInt("water"));
                receipt.setService(rs.getInt("service"));
                receipt.setForfeit(rs.getInt("forfeit"));
                receipt.setTotal_payment(rs.getInt("total_payment"));
                receipt.setStatus(rs.getInt("status"));
                receipt.setDescription(rs.getString("description"));

            }
        } catch (SQLException e) {

        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(pstmt);
            DBConnect.closeConnect(conn);
        }
        return receipt;
    }

    @Override
    public boolean add(Receipt obj) {
        String sql = "insert into " + this.table + "(contract_id, year, month, electric, water, service, forfeit, total_payment, description)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = DBConnect.getConnect();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getContract_id());
            pstmt.setInt(2, obj.getYear());
            pstmt.setInt(3, obj.getMonth());
            pstmt.setInt(4, obj.getElectric());
            pstmt.setInt(5, obj.getWater());
            pstmt.setInt(6, obj.getService());
            pstmt.setInt(7, obj.getForfeit());
            pstmt.setInt(8, obj.getTotal_payment());
            pstmt.setString(9, obj.getDescription());

            int row = pstmt.executeUpdate();
            if(row == 1) {
                System.out.println("Insert successfully");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(pstmt);
            DBConnect.closeConnect(conn);
        }
        return false;
    }

    @Override
    public boolean update(Receipt obj) {
        String sql = "update " + this.table + "set year = ?, month = ?, electric = ?, water = ?, service = ?, forfeit = ?, total_payment = ?, status = ?, description = ?" +
                " where id = ?";
        try {
            conn = DBConnect.getConnect();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getYear());
            pstmt.setInt(2, obj.getMonth());
            pstmt.setInt(3, obj.getElectric());
            pstmt.setInt(4, obj.getWater());
            pstmt.setInt(5, obj.getService());
            pstmt.setInt(6, obj.getForfeit());
            pstmt.setInt(7, obj.getTotal_payment());
            pstmt.setInt(8, obj.getStatus());
            pstmt.setString(9, obj.getDescription());
            pstmt.setInt(10, obj.getId());

            int row = pstmt.executeUpdate();
            if(row == 1) {
                System.out.println("update successfully");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(pstmt);
            DBConnect.closeConnect(conn);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from " + this.table + " where id = ?";
        try {
            conn = DBConnect.getConnect();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, id);
            int row = pstmt.executeUpdate();
            if(row == 1) {
                System.out.println("delete successfully");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(pstmt);
            DBConnect.closeConnect(conn);
        }
        return false;
    }

    @Override
    public boolean refresh() {
        return false;
    }

    public static void main(String[] args) {
        ReceiptModel ReceiptModel = new ReceiptModel();

        System.out.println(ReceiptModel.getAll());
    }
}
