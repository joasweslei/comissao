/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comissao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class FabricaConexao {

    private static final String STR_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE = "comissao";
    private static final String IP = "localhost";  //"192.168.2.7"
    private static final String STR_CON = "jdbc:mysql://" + IP + ":3306/" + DATABASE;
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection objConexao = null;

    public FabricaConexao() {
        try{
            Class.forName(STR_DRIVER);
            objConexao = DriverManager.getConnection(STR_CON, USER, PASSWORD);
        }catch (ClassNotFoundException e) {   
            String errorMsg = "Driver nao encontrado: "+e.getMessage();    
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {   
            String errorMsg = "Erro ao obter a conexao: "+e.getMessage();   
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }   
    }

    public static Connection getConexao() {
        if (objConexao == null) {
            new FabricaConexao();
        }
        return objConexao;
    }
}
