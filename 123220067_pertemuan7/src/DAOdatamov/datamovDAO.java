/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOdatamov;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.datamovimplement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author LENOVO
 */
public class datamovDAO implements datamovimplement {
    Connection connection;
    
    final String select = "SELECT * FROM movie";
    final String insert = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?,?,?,?,?)";
    final String update = "Update movie set alur=?, penokohan=?, akting=?, nilai=? where judul=?";
    final String delete = "Delete from movie where judul=?";
    public datamovDAO(){
        connection = connector.connection();
    }

    @Override
    public void insert(datamov p) {
        PreparedStatement statement = null;
        try{
            if(p.getAlur() < 0 || p.getAlur() > 5 || p.getPenokohan() < 0 || p.getPenokohan() > 5 || p.getAkting() < 0 || p.getAkting() > 5){
                throw new SQLException("Rentang Nilai harus di antara 0 - 5");
            }
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getJudul());
            statement.setDouble(2, p.getAlur());
            statement.setDouble(3, p.getPenokohan());
            statement.setDouble(4, p.getAkting());
            statement.setDouble(5, p.getNilai());
            statement.executeUpdate();
            throw new SQLException("Data Movie Berhasil Ditambahkan");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(datamov p) {
        PreparedStatement statement = null;
        try{
            if(p.getAlur() < 0 || p.getAlur() > 5 || p.getPenokohan() < 0 || p.getPenokohan() > 5 || p.getAkting() < 0 || p.getAkting() > 5){
                throw new SQLException("Rentang ilai harus di antara 0 - 5");
            }
            statement = connection.prepareStatement(update);
            statement.setString(5, p.getJudul());
            statement.setDouble(1, p.getAlur());
            statement.setDouble(2, p.getPenokohan());
            statement.setDouble(3, p.getAkting());
            statement.setDouble(4, p.getNilai());
            statement.executeUpdate();
            throw new SQLException("Data Movie Berhasil Diupdate");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            statement.setString(1, judul);
            statement.executeUpdate();
            throw new SQLException("Data Movie Berhasil Di Delete");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        } 
    }

    @Override
    public List<datamov> getAll() {
        List<datamov> dp = null;
        try{
            dp = new ArrayList<datamov>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                datamov mov = new datamov();
                mov.setJudul(rs.getString("judul"));
                mov.setAlur(rs.getDouble("alur"));
                mov.setPenokohan(rs.getDouble("penokohan"));
                mov.setAkting(rs.getDouble("akting"));
                mov.setNilai(rs.getDouble("nilai"));
                dp.add(mov);
            }
        }catch(SQLException ex){
            Logger.getLogger(datamovDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dp;
    }
}
