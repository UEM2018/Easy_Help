package DAO;

import modelo.Jogador;
import conexao.ConexaoBD;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JogadorDAO implements InterfaceDAO<Jogador, String> {

    private Session session;

    public boolean verificaLogin(Jogador objeto) {
        session = ConexaoBD.getInstance();
        boolean exists = exists(objeto);

        return exists;
    }
    public boolean recuperaSenha(Jogador objeto) {
        session = ConexaoBD.getInstance();
        Query query = session.createQuery("SELECT j FROM Jogador j WHERE j.email=:email");
        query.setParameter("email", objeto.getEmail());
        try{
            Jogador jogador = (Jogador)query.uniqueResult();
            if(jogador == null){
                return false;
            }
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        finally{
            session.close();
        }
        return true;
    }

    @Override
    public boolean salvar(Jogador objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null; //permite transacao com o BD 

        try {
            transacao = session.beginTransaction();
            session.save(objeto);
            transacao.commit();//faz a transacao
            return true;
        }
        catch (Exception e) {
            //cancela a transcao em caso de falha
            System.out.println("Erro: " + e.getMessage());
            if(transacao.isActive()){
                transacao.rollback();
            }
        }
        finally{
            session.close();
        }
        return false;
    }
    
    public boolean exists(Jogador objeto) {
        session = ConexaoBD.getInstance();
        Query query = session.createQuery("SELECT j FROM Jogador j WHERE j.email=:email AND senha=:senha");
        query.setParameter("email", objeto.getEmail());
        query.setParameter("senha", objeto.getSenha());
        try{
            Jogador jogador = (Jogador)query.uniqueResult();
            if(jogador == null){
                return false;
            }
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        finally{
            session.close();
        }
        return true;
    }

    @Override
    public Jogador consultar(String objeto) {
        session = ConexaoBD.getInstance();

        Jogador jog = null;
        try {
            jog = (Jogador)session.get(Jogador.class, objeto); 
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        finally{
            session.close();
        }
        return jog;
    }

    @Override
    public boolean alterar(Jogador objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;

        try {
            Jogador jog = (Jogador)session.get(Jogador.class, objeto.getIdJogador()); 
            jog.setEmail(objeto.getEmail());
            jog.setNome(objeto.getNome());
            jog.setTel1(objeto.getTel1());
            jog.setTel2(objeto.getTel2());
            jog.setCpf(objeto.getCpf());
            jog.setNacionalidade(objeto.getNacionalidade());
            jog.setSenha(objeto.getSenha());
            
            if(jog != null){
                transacao = session.beginTransaction();
                session.update(jog);
                transacao.commit();
                return true;
            }
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            if(transacao.isActive()){
                transacao.rollback();
            }
        }
        finally{
            session.close();
        }
        return false;
    }

    @Override
    public boolean excluir(Jogador objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;
      
        try {
            transacao = session.beginTransaction();
            session.delete(objeto);
            transacao.commit();
            return true;
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            if(transacao.isActive()){
                transacao.rollback();
            }
        }
        finally{
            session.close();
        }
        return false;
    }

    @Override
    public List<Jogador> findAll() {
        try{
            session = ConexaoBD.getInstance();
            Query q = session.createQuery("SELECT j FROM Jogador j ORDER BY nome ASC");

            List<Jogador> jogadores = q.list();
  
            return jogadores;
        }
        catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
        finally{
            session.close();
        }
    }
}
