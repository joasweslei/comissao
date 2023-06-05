/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comissao.dao;

import comissao.util.FabricaConexao;
import comissao.model.Configuracao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author clayton
 */
public class ConfiguracaoDAO {

    public static Configuracao getConfiguracao() {

        Connection objConexao = FabricaConexao.getConexao();
        Configuracao objConfiguracao = new Configuracao();


        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT * " +
                    " FROM Configuracao");

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                String vID = objResultSet.getString("idConfiguracao");
                int vNroMeses = objResultSet.getInt("NroMeses");
                int vBanco = objResultSet.getInt("Banco");
                float vValorMensalidade = objResultSet.getFloat("ValorMensalidade");
                int vNroBoletosGerados = objResultSet.getInt("NroBoletosGerados");
                String vCedente = objResultSet.getString("Cedente");
                String vConta = objResultSet.getString("Conta");

                objConfiguracao = new Configuracao();
                objConfiguracao.setNroMeses(vNroMeses);
                objConfiguracao.setBanco(vBanco);
                objConfiguracao.setValorMensalidade(vValorMensalidade);
                objConfiguracao.setNroBoletosGerados(vNroBoletosGerados);
                objConfiguracao.setCedente(vCedente);
                objConfiguracao.setConta(vConta);
            }
            objResultSet.close();
            objSTM.close();
        } catch (Exception erro) {
            String errorMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return objConfiguracao;
    }




    public boolean atualizar(Configuracao pConfiguracao) {
        System.out.println("Atualizando Objeto: " + pConfiguracao.toString());
        Connection objConexao = FabricaConexao.getConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("UPDATE configuracao " +
                    "SET " +
                    "NroMeses = '" + pConfiguracao.getNroMeses() + "', " +
                    "Banco = '" + pConfiguracao.getBanco() + "', " +                    
                    "ValorMensalidade = '" + pConfiguracao.getValorMensalidade() + "', " +
                    "NroBoletosGerados = '" + pConfiguracao.getNroBoletosGerados() + "', " +                    
                    "NroBoletosGerados = '" + pConfiguracao.getNroBoletosGerados() + "', " +                                        
                    "Cedente = '" + pConfiguracao.getCedente() + "', " +                                                            
                    "Conta = '" + pConfiguracao.getConta() + "' " +
                    " WHERE idConfiguracao = 1");
            objSTM.close();
            return true;
        } catch (Exception erro) {
            String errorMsg = "Erro ao Atualizar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

  
}
