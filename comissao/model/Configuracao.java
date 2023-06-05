/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comissao.model;

import comissao.dao.ConfiguracaoDAO;

/**
 *
 * @author clayton
 */
public class Configuracao {

    private String id;
    private int NroMeses;
    private int Banco;
    private float valorMensalidade;
    private int NroBoletosGerados;
    private String Cedente;
    private String Conta;

    public static Configuracao getConfiguracao() {
        Configuracao configuracao = ConfiguracaoDAO.getConfiguracao();
        return configuracao;
    }

    public boolean atualizar() {
        ConfiguracaoDAO objConfiguracaoDAO = new ConfiguracaoDAO();
        if (objConfiguracaoDAO.atualizar(this)) {
            return true;
        } else {
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public int getNroMeses() {
        return NroMeses;
    }

    public void setNroMeses(int NroMeses) {
        this.NroMeses = NroMeses;
    }

    public int getBanco() {
        return Banco;
    }

    public void setBanco(int Banco) {
        this.Banco = Banco;
    }

    public float getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(float valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public int getNroBoletosGerados() {
        return NroBoletosGerados;
    }

    public void setNroBoletosGerados(int NroBoletosGerados) {
        this.NroBoletosGerados = NroBoletosGerados;
    }

    public String getCedente() {
        return Cedente;
    }

    public void setCedente(String Cedente) {
        this.Cedente = Cedente;
    }

    public String getConta() {
        return Conta;
    }

    public void setConta(String Conta) {
        this.Conta = Conta;
    }
    
    public void incrementaBoleto (){
        
    }    
}
