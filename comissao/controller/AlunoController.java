/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comissao.controller;

import comissao.model.Aluno;
import java.util.ArrayList;
import java.util.Vector;
import comissao.util.Moeda;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author clayton
 */
public class AlunoController {

    private Aluno objAluno;
    private String objGUID;

    public AlunoController() {
        this.objAluno = new Aluno();
        this.objGUID = Aluno.getCodigo();
    }

    public Aluno getAluno(String pID) {
        Aluno aluno = new Aluno();
        return aluno.getAluno(pID);
    }

    public ArrayList<Aluno> getAlunos() {
        Aluno aluno = new Aluno();
        return aluno.getAlunos();
    }

    public static DefaultTableModel getTableAlunos(DefaultTableModel pModeloTabela) {
        ArrayList<Aluno> alunos = Aluno.getAlunos();
        Vector<String> listaAlunos;
        Aluno objAluno;
        for (int i = 0; i < alunos.size(); i++) {
            listaAlunos = new Vector<String>();
            objAluno = alunos.get(i);
            listaAlunos.addElement(objAluno.getId());
            listaAlunos.addElement(objAluno.getNome());
            listaAlunos.addElement(objAluno.getEmail());
            listaAlunos.addElement(objAluno.getCelular());
            listaAlunos.addElement(Moeda.FORMATO_REAL.format(objAluno.getSaldo()));
            pModeloTabela.addRow(listaAlunos);
        }
        return pModeloTabela;
    }

    public static Vector<String> getChavesAlunos() {
        ArrayList<Aluno> alunos = Aluno.getAlunos();
        Vector<String> listaAlunos = new Vector<String>();
        Aluno objAluno;
        for (int i = 0; i < alunos.size(); i++) {
            objAluno = alunos.get(i);
            listaAlunos.addElement(objAluno.getId());
        }
        return listaAlunos;
    }

    public boolean salvar(ArrayList<String> pValores) {
        if (pValores.get(0) == null) {
            //Criar novo
            this.objAluno.setId(objGUID.toString());
            this.objAluno.setNome(pValores.get(1));
            this.objAluno.setEmail(pValores.get(2));
            this.objAluno.setCelular(pValores.get(3));
            if (this.objAluno.persistir()) {
                return true;
            } else {
                return false;
            }
        } else {
            this.objAluno.setId(pValores.get(0));
            this.objAluno.setNome(pValores.get(1));
            this.objAluno.setEmail(pValores.get(2));
            this.objAluno.setCelular(pValores.get(3));
            if (this.objAluno.atualizar()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean excluir(String pID) {
        Aluno objAluno = Aluno.getAluno(pID);
        if (objAluno.excluir()) {
            return true;
        } else {
            return false;
        }
    }
}
