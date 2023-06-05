/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comissao.dao;

import comissao.util.FabricaConexao;
import comissao.model.Boleto;
import comissao.util.Moeda;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author clayton
 */
public class BoletoDAO {

    public static Boleto getBoleto(String pID) {

        Connection objConexao = FabricaConexao.getConexao();
        Boleto objBoleto = new Boleto();


        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT idBoleto, idAluno, Valor, DataQuitacao, Pago, DataEmissao, Emitido " +
                    "FROM Boleto b " +
                    "WHERE B.idBoleto = " + pID);

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                String vID = objResultSet.getString("idBoleto");
                String vidAluno = objResultSet.getString("idAluno");
                float vValor = objResultSet.getFloat("Valor");
                Date vDataQuitacao = objResultSet.getDate("DataQuitacao");
                Date vDataEmissao = objResultSet.getDate("DataEmissao");                
                boolean vPago = objResultSet.getBoolean("Pago");
                boolean vEmitido = objResultSet.getBoolean("Emitido");
                objBoleto = new Boleto();
                objBoleto.setId(vID);
                objBoleto.setIdAluno(vidAluno);
                objBoleto.setValorBoleto(vValor);
                objBoleto.setDataEmissao(vDataEmissao);
                objBoleto.setDataQuitacao(vDataQuitacao);
                objBoleto.setPago(vPago);
                objBoleto.setEmitido(vEmitido);                
            }
            objResultSet.close();
            objSTM.close();
        } catch (Exception erro) {
            String errorMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return objBoleto;
    }
    
     public static ArrayList<Boleto> getBoletos() {
        Connection objConexao = FabricaConexao.getConexao();
        ArrayList<Boleto> boletos = new ArrayList<Boleto>();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT idBoleto, idAluno, Valor, DataQuitacao, Pago, DataEmissao, Emitido " +
                    "FROM Boleto b");

            ResultSet objResultSet = objSTM.getResultSet();
            Boleto objBoleto;
            while (objResultSet.next()) {
                String vID = objResultSet.getString("idBoleto");
                String vidAluno = objResultSet.getString("idAluno");
                float vValor = objResultSet.getFloat("Valor");
                Date vDataQuitacao = objResultSet.getDate("DataQuitacao");
                Date vDataEmissao = objResultSet.getDate("DataEmissao");                
                boolean vPago = objResultSet.getBoolean("Pago");
                boolean vEmitido = objResultSet.getBoolean("Emitido");                
                objBoleto = new Boleto();
                objBoleto.setId(vID);
                objBoleto.setIdAluno(vidAluno);
                objBoleto.setValorBoleto(vValor);
                objBoleto.setDataEmissao(vDataEmissao);
                objBoleto.setDataQuitacao(vDataQuitacao);
                objBoleto.setPago(vPago);
                objBoleto.setEmitido(vEmitido);
                boletos.add(objBoleto);
            }
            objResultSet.close();
            objSTM.close();
        } catch (Exception erro) {
            String errorMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return boletos;

    }


    public boolean consultaBoleto(int pIdAluno, Date pData) {
        Connection objConexao = FabricaConexao.getConexao();
        
        int vID = 0;
        
        try {
            
            Date dataDate = pData;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dataStr = format.format(dataDate);            
            
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT idBoleto " +
                    "FROM Boleto b " +
                    "WHERE B.idAluno = " + pIdAluno + " AND DataEmissao='" +  dataStr + "'");


            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                vID = objResultSet.getInt("idBoleto");
            }
            objResultSet.close();
            objSTM.close();
        } catch (Exception erro) {
            String errorMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        if (vID > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean salvar(Boleto pBoleto) {
        System.out.println("Persistindo Objeto: " + pBoleto.toString());
        Connection objConexao = FabricaConexao.getConexao();
        try {
            
 
            Date dataDate = pBoleto.getDataEmissao();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dataStr = format.format(dataDate);
            
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("INSERT INTO boleto(idAluno, Valor, Pago, DataEmissao) VALUES(" +
                    "'" + pBoleto.getIdAluno() +
                    "','" + pBoleto.getValorBoleto() +
                    "'," + pBoleto.isPago() +                    
                    ",'" + dataStr + "')");
            objSTM.close();
            return true;
        } catch (Exception erro) {
            String errorMsg = "Erro ao Persistir: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean atualizar(Boleto pBoleto) {
        System.out.println("Atualizando Objeto: " + pBoleto.toString());
        Connection objConexao = FabricaConexao.getConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("UPDATE boleto " +
                    "SET " +
                    "idAluno = '" + pBoleto.getIdAluno() + "', " +
                    "Valor = '" + pBoleto.getValorBoleto() + "', " +
                    "Pago = '" + pBoleto.isPago() + "', " +                                        
                    "DataEmissao = '" + pBoleto.getDataEmissao() + "' " +
                    " WHERE idBoleto = '" + pBoleto.getId() + "'");
            objSTM.close();
            return true;
        } catch (Exception erro) {
            String errorMsg = "Erro ao Atualizar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }
    
 public boolean quitarBoleto(String vIdBoleto) {
        Connection objConexao = FabricaConexao.getConexao();
        
        Date dataDate = GregorianCalendar.getInstance().getTime(); 
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dataStr = format.format(dataDate);        
        
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("UPDATE boleto " +
                    "SET " +
                    "Pago = 1 ," +
                    "DataQuitacao = '" + dataStr + "'" + 
                    " WHERE idBoleto = '" + vIdBoleto + "'");
            objSTM.close();
            return true;
        } catch (Exception erro) {
            String errorMsg = "Erro ao Atualizar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }    
 
public boolean setImpresso(String vIdBoleto) {
    
        Connection objConexao = FabricaConexao.getConexao();        
       
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("UPDATE boleto " +
                    "SET " +
                    "Emitido = 1 " +
                    " WHERE idBoleto = '" + vIdBoleto + "'");
            objSTM.close();
            return true;
        } catch (Exception erro) {
            String errorMsg = "Erro ao Atualizar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }     

    public boolean excluir(Boleto pBoleto) {
        System.out.println("Excluindo Objeto: " + pBoleto.toString());
        Connection objConexao = FabricaConexao.getConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("DELETE FROM boleto WHERE idBoleto = '" + pBoleto.getId() + "'");
            objSTM.close();
            return true;
        } catch (Exception erro) {
            String errorMsg = "Erro ao Deletar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
