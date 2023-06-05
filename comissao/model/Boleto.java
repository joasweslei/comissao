/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package comissao.model;

import comissao.dao.BoletoDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author clayton
 */
public class Boleto {
    private String id;
    private String idAluno;
    private boolean Pago;
    private Date DataEmissao;    
    private Date DataQuitacao;        
    private float valorBoleto;
    private boolean Emitido;

    public Boleto (){
        
    }

    public static Boleto getBoleto(String id) {
        Boleto boleto = BoletoDAO.getBoleto(id);
        return boleto;
        //return Aluno;
    }
    
    public boolean consultaBoleto(int pIdAluno, Date pData) {
        BoletoDAO boleto = new BoletoDAO();
        return boleto.consultaBoleto(pIdAluno, pData);
    }

    public static ArrayList<Boleto> getBoletos() {
        return BoletoDAO.getBoletos();
    }

 public boolean persistir(){
        BoletoDAO objBoletoDAO = new BoletoDAO();
        if (objBoletoDAO.salvar(this)){
            return true;
        }else{
            return false;
        }
    }    
 
   public boolean excluir(){
        BoletoDAO objBoletoDAO = new BoletoDAO();
        if (objBoletoDAO.excluir(this)){
            return true;
        }else{
            return false;
        }
    }
 
   public boolean quitarBoleto (String vIdBoleto) {
        BoletoDAO objBoletoDAO = new BoletoDAO();
        if (objBoletoDAO.quitarBoleto(vIdBoleto)){
            return true;
        }else{
            return false;
        }
   }
   
   public boolean setImpresso (String vIdBoleto) {
        BoletoDAO objBoletoDAO = new BoletoDAO();
        if (objBoletoDAO.setImpresso(vIdBoleto)){
            return true;
        }else{
            return false;
        }
   }
   

    public boolean atualizar(){
        BoletoDAO objBoletoDAO = new BoletoDAO();
        if (objBoletoDAO.atualizar(this)){
            return true;
        }else{
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public String getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    public boolean isPago() {
        return Pago;
    }

    public void setPago(boolean Pago) {
        this.Pago = Pago;
    }

    public Date getDataEmissao() {
        return DataEmissao;
    }

    public void setDataEmissao(Date DataEmissao) {
        this.DataEmissao = DataEmissao;
    }

    public Date getDataQuitacao() {
        return DataQuitacao;
    }

    public void setDataQuitacao(Date DataQuitacao) {
        this.DataQuitacao = DataQuitacao;
    }

    public float getValorBoleto() {
        return valorBoleto;
    }

    public void setValorBoleto(float valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEmitido() {
        return Emitido;
    }

    public void setEmitido(boolean Impresso) {
        this.Emitido = Impresso;
    }
 
}
