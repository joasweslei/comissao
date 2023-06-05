/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package comissao.model;

import comissao.dao.AlunoDAO;
import java.util.ArrayList;

/**
 *
 * @author clayton
 */
public class Aluno {
    private String id;
    private String nome;
    private String email;
    private String celular;    
    private float saldo;

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public static String getCodigo() {
        return AlunoDAO.getCodigo();
    }
    
    public static Aluno getAluno(String id) {
        Aluno aluno = AlunoDAO.getAluno(id);
        return aluno;
        //return Aluno;
    }
    
    public static ArrayList<Aluno> getAlunos() {
        return AlunoDAO.getAlunos();
    }

    public void setId(String id) {
        this.id = id;
    }

 public boolean persistir(){
        AlunoDAO objAlunoDAO = new AlunoDAO();
        if (objAlunoDAO.salvar(this)){
            return true;
        }else{
            return false;
        }
    }    
 
   public boolean excluir(){
        AlunoDAO objAlunoDAO = new AlunoDAO();
        if (objAlunoDAO.excluir(this)){
            return true;
        }else{
            return false;
        }
    }
 

    public boolean atualizar(){
        AlunoDAO objAlunoDAO = new AlunoDAO();
        if (objAlunoDAO.atualizar(this)){
            return true;
        }else{
            return false;
        }
    }    
 
}
