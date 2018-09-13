package DAO;

import modelo.Jogos;
import conexao.ConexaoBD;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JogoDAO implements InterfaceDAO<Jogos, Integer> {

    private Session session;

    @Override
    public boolean salvar(Jogos objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;

        try {
            transacao = session.beginTransaction();
            session.save(objeto);
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
    public List<Jogos> findAll(){
        try{
            session = ConexaoBD.getInstance();
            Query q = session.createQuery("SELECT p FROM Jogo p ORDER BY nome ASC");

            List<Jogos> jogos = q.list();
  
            return jogos;
        }
        catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
        finally{
            session.close();
        }
    }

    @Override
    public Jogos consultar(Integer chave) {
        session = ConexaoBD.getInstance();

        Jogos jgo = null;
        try {
            jgo = (Jogos)session.get(Jogos.class, chave); 
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        finally{
            session.close();
        }
        return jgo;
    }

    @Override
    public boolean alterar(Jogos objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;

        try {
            Jogos jgo = (Jogos)session.get(Jogos.class, objeto.getIdJogo()); 
            
            jgo.setNome(objeto.getNome());
            
            if(jgo != null){
                transacao = session.beginTransaction();
                session.update(jgo);
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
    public boolean excluir(Jogos objeto) {
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
}
