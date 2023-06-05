/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comissao.dao;

import comissao.util.FabricaConexao;
import comissao.model.Aluno;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author clayton
 */

public class AlunoDAO {
    public static Aluno getAluno(String pID) {

        Connection objConexao = FabricaConexao.getConexao();
        Aluno objAluno = new Aluno();


        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT A.id, A.nome, A.email, A.celular, sum(B.valor) as saldo " +
                    "FROM Aluno A " +
                    "LEFT JOIN Boleto B ON B.idAluno = A.id AND B.Pago = false " +
                    "WHERE A.Id = " + pID +
                    " GROUP BY A.id;");
            

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                String vID = objResultSet.getString("id");
                String vNome = objResultSet.getString("nome");
                String vEmail = objResultSet.getString("email");
                String vCelular = objResultSet.getString("celular");
                float vSaldo = objResultSet.getFloat("saldo");
                objAluno = new Aluno();
                objAluno.setId(vID);
                objAluno.setNome(vNome);
                objAluno.setEmail(vEmail);
                objAluno.setCelular(vCelular);
                objAluno.setSaldo(vSaldo);
            }
            objResultSet.close();
            objSTM.close();
        } catch (Exception erro) {
            String errorMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return objAluno;
    }

    public static String getCodigo() {

        Connection objConexao = FabricaConexao.getConexao();
        String vID = null;

        try {

            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT max(A.id)+1 as id FROM Aluno A ");

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                vID = objResultSet.getString("id");
                //System.out.println(objResultSet.getString("id"));
            }
            objResultSet.close();
            objSTM.close();


        } catch (Exception erro) {
            String errorMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }


        return vID;


    }

    public static ArrayList<Aluno> getAlunos() {
        Connection objConexao = FabricaConexao.getConexao();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT A.id, A.nome, A.email, A.celular, sum(B.valor) as saldo " +
                    "FROM Aluno A " +
                    "LEFT JOIN Boleto B ON B.idAluno = A.id AND B.Pago = true " +
                    "GROUP BY A.id;");

            ResultSet objResultSet = objSTM.getResultSet();
            Aluno objAluno;
            while (objResultSet.next()) {
                String vID = objResultSet.getString("id");
                String vNome = objResultSet.getString("nome");
                String vEmail = objResultSet.getString("email");
                String vCelular = objResultSet.getString("celular");
                float vSaldo = objResultSet.getFloat("saldo");
                objAluno = new Aluno();
                objAluno.setId(vID);
                objAluno.setNome(vNome);
                objAluno.setEmail(vEmail);
                objAluno.setCelular(vCelular);
                objAluno.setSaldo(vSaldo);
                alunos.add(objAluno);
            }
            objResultSet.close();
            objSTM.close();
        } catch (Exception erro) {
            String errorMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return alunos;

    }

    public boolean salvar(Aluno pAluno) {
        System.out.println("Persistindo Objeto: " + pAluno.toString());
        Connection objConexao = FabricaConexao.getConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("INSERT INTO aluno(id, nome, email, celular) VALUES('" + pAluno.getId() +
                    "','" + pAluno.getNome() +
                    "','" + pAluno.getEmail() +
                    "','" + pAluno.getCelular() + "')");
            objSTM.close();
            return true;
        } catch (Exception erro) {
            String errorMsg = "Erro ao Persistir: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean atualizar(Aluno pAluno) {
        System.out.println("Atualizando Objeto: " + pAluno.toString());
        Connection objConexao = FabricaConexao.getConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("UPDATE aluno " +
                    "SET " +
                    "nome = '" + pAluno.getNome() + "', " +
                    "email = '" + pAluno.getEmail() + "', " +
                    "celular = '" + pAluno.getCelular() + "' " +
                    " WHERE id = '" + pAluno.getId() + "'");
            objSTM.close();
            return true;
        } catch (Exception erro) {
            String errorMsg = "Erro ao Atualizar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean excluir(Aluno pAluno) {
        System.out.println("Excluindo Objeto: " + pAluno.toString());
        Connection objConexao = FabricaConexao.getConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("DELETE FROM aluno WHERE id = '   " + pAluno.getId() + "      '");
            objSTM.close();
            return true;
        } catch (Exception erro) {
            String errorMsg = "Erro ao Deletar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
