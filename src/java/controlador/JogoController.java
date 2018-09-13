package controlador;

import DAO.InterfaceDAO;
import DAO.JogoDAO;
import modelo.Jogos;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

public class JogoController implements IControllerDAO<JogoController, Integer> {

    private Jogos jogo;

    public JogoController() {
        jogo = new Jogos();
    }

    private JogoController(Jogos jogo) {
        this.jogo = jogo;
    }

    @Override
    public boolean salvar() {
        System.out.println(jogo);
        if(jogo.getIdJogo() != null){
            return alterar();
        }
        InterfaceDAO jogoDao = new JogoDAO();
        boolean salvou = jogoDao.salvar(jogo);
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage msg;
        if (salvou) {
            msg = new FacesMessage("Jogo cadastrada com sucesso");
            contexto.addMessage("form_cadastro_jogo", msg);
            jogo = new Jogos();
            return true;
        }
        msg = new FacesMessage("Não foi possível cadastrar a Jogo");
        contexto.addMessage("form_cadastro_jogo", msg);
        return false;

    }

    public Jogos getJogo() {
        return jogo;
    }

    public void setJogo(Jogos jogo) {
        this.jogo = jogo;
    }

    @Override
    public ListDataModel<JogoController> listar() {
        JogoDAO jogoDAO = new JogoDAO();
        List<JogoController> jogosController = new ArrayList();

        List<Jogos> jogos = jogoDAO.findAll();

        if (jogos != null) {
            for (Jogos jgo : jogos) {
                jogosController.add(new JogoController(jgo));
            }
            return new ListDataModel(jogosController);
        }
        else {
            return null;
        }
    }

    public String alterarDados(Jogos jogo) {
        this.jogo = jogo;
        return "alterar_jogo";
    }

    @Override
    public boolean alterar() {
        InterfaceDAO<Jogos, Integer> jogoDao = new JogoDAO();
        return jogoDao.alterar(jogo);
    }

    @Override
    public String remover() {
        InterfaceDAO<Jogos, Integer> jogoDao = new JogoDAO();
        if (jogoDao.excluir(jogo)) {
            return "removeu_jogo";
        }
        else {
            return "nao_removeu_jogo";
        }
    }
    
    @Override
    public JogoController consultar(Integer chave) {
        InterfaceDAO<Jogos, Integer> jogoDao = new JogoDAO();
        Jogos jogo = jogoDao.consultar(chave);
        return new JogoController(jogo);
    }
}
