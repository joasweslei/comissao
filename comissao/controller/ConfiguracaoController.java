/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comissao.controller;

import comissao.model.Configuracao;
import java.util.ArrayList;

/**
 *
 * @author clayton
 */
public class ConfiguracaoController {

    private Configuracao objConfiguracao;

    public ConfiguracaoController() {
        this.objConfiguracao = new Configuracao();
    }

    public Configuracao getConfiguracao() {
        return this.objConfiguracao.getConfiguracao();
    }

    public boolean salvar(ArrayList<String> pValores) {
        objConfiguracao.setNroMeses(Integer.parseInt(pValores.get(1)));
        objConfiguracao.setBanco(Integer.parseInt(pValores.get(2)));
        objConfiguracao.setValorMensalidade(Float.parseFloat(pValores.get(3)));
        objConfiguracao.setNroBoletosGerados(Integer.parseInt(pValores.get(4)));
        objConfiguracao.setCedente(pValores.get(5));
        objConfiguracao.setConta(pValores.get(6));
        if (this.objConfiguracao.atualizar()) {
            return true;
        } else {
            return false;
        }
    }
}    
