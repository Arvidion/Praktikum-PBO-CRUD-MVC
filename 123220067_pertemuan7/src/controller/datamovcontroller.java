/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAOdatamov.datamovDAO;
import DAOImplement.datamovimplement;
import model.*;
import view.Mainv;
/**
 *
 * @author LENOVO
 */
public class datamovcontroller {
    Mainv frame;
    datamovimplement impldatamov;
    List<datamov> dp;
    
    public datamovcontroller(Mainv frame){
        this.frame = frame;
        impldatamov = new datamovDAO();
        dp = impldatamov.getAll();
    }
    public void isitable(){
        dp = impldatamov.getAll();
        modeltabledata mp = new modeltabledata(dp);
        frame.getTabledata().setModel(mp);
    }
    
    public void insert(){
        datamov dp = new datamov();
        double txtalur = Double.parseDouble(frame.getInalur().getText());
        double txtpenokohan = Double.parseDouble(frame.getInpenokohan().getText());
        double txtakting = Double.parseDouble(frame.getInakting().getText());
        double txtnilai = (txtalur + txtpenokohan + txtakting) / 3;
        dp.setJudul(frame.getInjudul().getText());
        dp.setAlur(txtalur);
        dp.setPenokohan(txtpenokohan);
        dp.setAkting(txtakting);
        dp.setNilai(txtnilai);
        impldatamov.insert(dp);
    }
    
    public void update(){
        datamov dp = new datamov();
        double txtalur = Double.parseDouble(frame.getInalur().getText());
        double txtpenokohan = Double.parseDouble(frame.getInpenokohan().getText());
        double txtakting = Double.parseDouble(frame.getInakting().getText());
        double txtnilai = (txtalur + txtpenokohan + txtakting) / 3;
        dp.setJudul(frame.getInjudul().getText());
        dp.setAlur(txtalur);
        dp.setPenokohan(txtpenokohan);
        dp.setAkting(txtakting);
        dp.setNilai(txtnilai);
        impldatamov.update(dp);
    }
    
    public void delete(){
        String judul = frame.getInjudul().getText();
        impldatamov.delete(judul);
    }
    
    public void clear(){
        frame.getInjudul().setText(null);
        frame.getInalur().setText(null);
        frame.getInpenokohan().setText(null);
        frame.getInakting().setText(null);
    }
}
