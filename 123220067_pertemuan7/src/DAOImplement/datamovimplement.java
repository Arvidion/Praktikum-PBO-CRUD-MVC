/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplement;
import java.util.List;
import model.*;
/**
 *
 * @author LENOVO
 */
public interface datamovimplement {
    public void insert(datamov p);
    public void update(datamov p);
    public void delete(String judul);
    public List<datamov> getAll();
}
