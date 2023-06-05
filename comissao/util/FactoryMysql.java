/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Joás
 */
public class FactoryMysql {

    private static String local = "localhost";
    private static String porta = "3306";
    private static String banco = "db_visitantes";
    private static String url =  "jdbc:mysql://"+local+":"+porta+"/"+banco;
    private static String user = "root";
    private static String pws =  "765";

public static Connection getConnection() {
     // String que compreende onde o banco esta
     try {
         // Carregando o Driver
         Class.forName("com.mysql.jdbc.Driver");
         // Efetuando a Conexao
         Connection conexao = DriverManager.getConnection(url, user, pws);
         return conexao;
      } catch (ClassNotFoundException objErroDriver) {
         JOptionPane.showMessageDialog(null, "Erro no Driver: "+objErroDriver.getMessage());
         return null;
      }
      catch (SQLException objErroConexao) {
         JOptionPane.showMessageDialog(null, "Erro na Conexao: "+objErroConexao.getMessage());
         return null;
      }
    }

    public static void setBanco(String banco) {
        FactoryMysql.banco = banco;
    }

    public static void setLocal(String local) {
        FactoryMysql.local = local;
    }

    public static void setPorta(String porta) {
        FactoryMysql.porta = porta;
    }

    public static void setPws(String pws) {
        FactoryMysql.pws = pws;
    }

    public static void setUrl(String url) {
        FactoryMysql.url = url;
    }

    public static void setUser(String user) {
        FactoryMysql.user = user;
    }
    
}
