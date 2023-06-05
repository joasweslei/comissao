/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comissao.controller;

import comissao.model.Boleto;
import comissao.model.Aluno;
import java.util.ArrayList;
import java.util.Vector;
import comissao.util.Moeda;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.Generator;
import org.jboleto.control.PDFGenerator;

/**
 *
 * @author clayton
 */
public class BoletoController {

    private Boleto objBoleto;

    public boolean consultaBoleto(int idAluno, Date data) {
        Boleto boleto = new Boleto();
        return boleto.consultaBoleto(idAluno, data);
    }

    public BoletoController() {
        this.objBoleto = new Boleto();
    //this.objGUID = Boleto.
    }

    public static DefaultTableModel getBoletos(DefaultTableModel pModeloTabela) {
        ArrayList<Boleto> boletos = Boleto.getBoletos();
        Vector<String> listaBoletos;
        Boleto objBoleto;
        for (int i = 0; i < boletos.size(); i++) {
            listaBoletos = new Vector<String>();
            objBoleto = boletos.get(i);

            Aluno aluno = new Aluno();

            listaBoletos.addElement(objBoleto.getId());
            listaBoletos.addElement(objBoleto.getIdAluno());
            listaBoletos.addElement(aluno.getAluno(objBoleto.getIdAluno()).getNome());
            //listaBoletos.addElement(Moeda.FORMATO_REAL.format(objBoleto.getValorBoleto()));
            listaBoletos.addElement(Float.toString(objBoleto.getValorBoleto()));
            listaBoletos.addElement(objBoleto.getDataEmissao().toString());

            if (objBoleto.getDataQuitacao() == null) {
                listaBoletos.addElement("");
            } else {
                listaBoletos.addElement(objBoleto.getDataQuitacao().toString());
            }
            
            if (objBoleto.isEmitido()) {
                listaBoletos.addElement("Sim");
            } else {
                listaBoletos.addElement("Não");
            }
            pModeloTabela.addRow(listaBoletos);
        }
        return pModeloTabela;
    }

    public static Vector<String> getChavesBoletos() {
        ArrayList<Boleto> boletos = Boleto.getBoletos();
        Vector<String> listaBoletos = new Vector<String>();
        Boleto objBoleto;
        for (int i = 0; i < boletos.size(); i++) {
            objBoleto = boletos.get(i);
            listaBoletos.addElement(objBoleto.getId());
        }
        return listaBoletos;
    }

    public boolean salvar(ArrayList<String> pValores) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


        if (pValores.get(0) == null) {
            //Criar novo
            this.objBoleto.setIdAluno(pValores.get(1));
            this.objBoleto.setValorBoleto(Float.parseFloat(pValores.get(2)));
            this.objBoleto.setPago(Boolean.parseBoolean(pValores.get(3)));

            try {
                Date dataEmissao = format.parse(pValores.get(4));
                this.objBoleto.setDataEmissao(dataEmissao);
            } catch (Exception e) {
            }


            if (this.objBoleto.persistir()) {
                return true;
            } else {
                return false;
            }
        } else {

            this.objBoleto.setId(pValores.get(0));
            this.objBoleto.setIdAluno(pValores.get(1));
            this.objBoleto.setValorBoleto(Float.parseFloat(pValores.get(2)));



            this.objBoleto.setPago(Boolean.parseBoolean(pValores.get(3)));

            try {
                Date dataEmissao = format.parse(pValores.get(4));
                this.objBoleto.setDataEmissao(dataEmissao);
            } catch (Exception e) {
            }

            if (this.objBoleto.atualizar()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean excluir(String pID) {
        Boleto objBoleto = Boleto.getBoleto(pID);
        if (objBoleto.excluir()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean quitarBoleto(String pID) {
        Boleto objBoleto = new Boleto();
        if (objBoleto.quitarBoleto(pID)) {
            return true;
        } else {
            return false;
        }
    }
    
 public static boolean setImpresso(String pID) {
        Boleto objBoleto = new Boleto();
        if (objBoleto.setImpresso(pID)) {
            return true;
        } else {
            return false;
        }
    }    

    public boolean  geraBoleto(ArrayList<String> pParametros) {
        
        /*
         Cedente0
         Valor1
         Cliente2
         DataEmissao3
         Banco4
        */
                
        
        JBoletoBean jBoletoBean = new JBoletoBean();
        jBoletoBean.setDataDocumento(pParametros.get(3));
        jBoletoBean.setDataProcessamento(pParametros.get(3));
        jBoletoBean.setCedente(pParametros.get(0));
        jBoletoBean.setCarteira("17");
        jBoletoBean.setNomeSacado(pParametros.get(2));
        jBoletoBean.setEnderecoSacado("Rua Teste");
        jBoletoBean.setBairroSacado("Barra");
        jBoletoBean.setCidadeSacado("Rio de Janeiro");
        jBoletoBean.setUfSacado("RJ");
        jBoletoBean.setCepSacado("22753-501");
        jBoletoBean.setCpfSacado("0000000000000");
        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO BANCO DO BRASIL");
        jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO BANCO DO BRASIL");
        Vector descricoes = new Vector();
        jBoletoBean.setDescricoes(descricoes);
        jBoletoBean.setDataVencimento("10/06/2006");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");
        jBoletoBean.setAgencia("3415");
        jBoletoBean.setContaCorrente("00543004");
        
        jBoletoBean.setNossoNumero("0005963971", 10);
        jBoletoBean.setValorBoleto(pParametros.get(1));
        
        String banco = pParametros.get(4);
        String boleto = pParametros.get(2).replace(" ", "") + "_" + pParametros.get(3).replace("/", "") +".pdf";
        
        try {
        if (banco.equals("1")) {
            jBoletoBean.setNumConvenio("1101354");
            Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BANCO_DO_BRASIL);
            JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.BANCO_DO_BRASIL);
            jBoleto.addBoleto();
            jBoleto.closeBoleto(boleto);
            jBoleto = null;
            return true;

        } else if (banco.equals("0")) {
            jBoletoBean.setDvContaCorrente("6");            
            jBoletoBean.setNoDocumento("3020");
            jBoletoBean.setDataVencimento("02/10/2007");
            Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BANCO_REAL);
            JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.BANCO_REAL);
            jBoleto.addBoleto();
            jBoleto.closeBoleto(boleto);
            return true;            
        }
        } catch (Exception e) {
            return false;
        }
        
        return false;
    }
}
        
    
