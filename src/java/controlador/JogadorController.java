package controlador;

import DAO.InterfaceDAO;
import DAO.JogadorDAO;
import modelo.Jogador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JogadorController implements IControllerDAO<JogadorController, String> {

    private Jogador jogador;

    public JogadorController() {
        jogador = new Jogador();
    }

    public JogadorController(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogador getJogadorLogado() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        Map<String, Object> sessao = contexto.getExternalContext().getSessionMap();
        if (sessao.containsKey("jogador")) {
            return ((Jogador) sessao.get("jogador"));
        }
        return null;
    }

    public void logout() {
        System.out.println("Logout");
        FacesContext contexto = FacesContext.getCurrentInstance();
        Map<String, Object> sessao = contexto.getExternalContext().getSessionMap();
        if (sessao.containsKey("jogador")) {
            sessao.remove("jogador");
            this.jogador = new Jogador();
        }
    }

    public String verificaLogin() {
       // Singleton.obterInstancia().setJogadorAutenticado(getJogador());
        JogadorDAO jogadorDAO = new JogadorDAO();
        FacesContext contexto = FacesContext.getCurrentInstance();
        Map<String, Object> sessao = contexto.getExternalContext().getSessionMap();
        ExternalContext contextoExterno = contexto.getExternalContext();
        String url = ((HttpServletRequest)contextoExterno.getRequest()).getRequestURI();
        String urlContexto = contextoExterno.getRequestContextPath();
        System.out.println("urlContexto: " + urlContexto);
        System.out.println("URL: " + url);
            if (sessao.containsKey("jogador")) {
                if (url.equals(urlContexto + "/index.xhtml") || url.equals(urlContexto + "/faces/index.xhtml") || url.equals(urlContexto + "/")) {
                    return "login_ok";
                }
                return null;
            } else if (jogador.getEmail() == null || jogador.getSenha() == null) {
                try {
                    if (url.startsWith(urlContexto + "/faces/paginas/") || url.startsWith(urlContexto + "/paginas/")) {
                        contextoExterno.redirect(urlContexto + "/index.xhtml");
                    }
                } catch (IOException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                return null;
            } else if (jogadorDAO.verificaLogin(jogador)) {
                sessao.put("jogador", jogador);
                return "login_ok";
            } else {
                FacesMessage msg = null;
                msg = new FacesMessage("login inválido!");
                contexto.addMessage("form_login", msg);            
                return null;
            }
        
    }
    
    public String recuperaSenha() { 
        System.out.println(jogador);
        JogadorDAO jogadorDao = new JogadorDAO();
        boolean enviou = jogadorDao.recuperaSenha(jogador);
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage msg;
        if (enviou) {
            msg = new FacesMessage("Senha enviada ao e-mail informado com Sucesso! \n Verifique sua Caixa de Mensagens!");
            contexto.addMessage("form_email", msg);
            jogador = new Jogador();
            return "email_ok";
        }
        msg = new FacesMessage("E-mail não encontrado!");
        contexto.addMessage("form_email", msg);
        return "falhou";        
    }
            
        
    
    
    public boolean validaCPF(String cpf) {
        
        boolean validador = false;
        String s1, s2, s3, s4, s5, s6, s7, s8, s9, confere = "";
        int n1, n2, n3, n4, n5, n6, n7, n8, n9, verificador1, verificador2;
        s1 = cpf.substring(0, 1);
        n1 = Integer.parseInt(s1);
        s2 = cpf.substring(1, 2);
        n2 = Integer.parseInt(s2);
        s3 = cpf.substring(2, 3);
        n3 = Integer.parseInt(s3);
        s4 = cpf.substring(4, 5);
        n4 = Integer.parseInt(s4);
        s5 = cpf.substring(5, 6);
        n5 = Integer.parseInt(s5);
        s6 = cpf.substring(6, 7);
        n6 = Integer.parseInt(s6);
        s7 = cpf.substring(8, 9);
        n7 = Integer.parseInt(s7);
        s8 = cpf.substring(9, 10);
        n8 = Integer.parseInt(s8);
        s9 = cpf.substring(10, 11);
        n9 = Integer.parseInt(s9);

        verificador1 = (n1 * 10 + n2 * 9 + n3 * 8 + n4 * 7 + n5 * 6 + n6 * 5 + n7 * 4 + n8 * 3 + n9 * 2);
        if ((verificador1 % 11) < 2) {
            verificador1 = 0;
        } else {
            verificador1 = 11 - (verificador1 % 11);
        }

        verificador2 = (n1 * 11 + n2 * 10 + n3 * 9 + n4 * 8 + n5 * 7 + n6 * 6 + n7 * 5 + n8 * 4 + n9 * 3 + verificador1 * 2);;

        if ((verificador2 % 11) < 2) {
            verificador2 = 0;
        } else {
            verificador2 = 11 - (verificador2 % 11);
        }

        confere = (s1 + s2 + s3 + "." + s4 + s5 + s6 + "." + s7 + s8 + s9 + "-" + verificador1 + "" + verificador2);
        if (confere == cpf) {
            
            validador = true;
            return validador;
        } else {
            return validador;
        }
        
        
    }
    
    public boolean verificaEmail (String email){
        List <String> emails = new ArrayList<>();
        boolean validador = true;
        emails.add("@ig.com");
        emails.add("@globomail.com");
        emails.add("@uol.com.br");
        emails.add("@live.com");
        emails.add("@outlook.com");
        emails.add("@bol.com");
        emails.add("@yahoo.com");
        emails.add("@hotmail.com");
        emails.add("@gmail.com");
        for (int i = 0; i <emails.size(); i++) { 
           if (email.toLowerCase().contains(emails.get(i).toLowerCase())){
                 validador=false;
                 return validador;
           }
       }
     return validador;   
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public String redireciona() {
        
        return  "/index.xhtml?faces-redirect=true";
    }
    /*
    public boolean typeTemplate(){        
        String tipo1 = "admin";    
        String tipo2 = "jogador";    
        String tipo3 = "jogadorPeriodico";    
        String tipo0 = "noLogin";
        if(tipo1.equals(jogador.getTipo())){
            return true;
        }else if(tipo2.equals(jogador.getTipo())){
            
        }
        return false;
        
        
        
    }
            
            
    public boolean isAdmin() {
    }*/
    
 //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    @Override
    public boolean salvar() {
        System.out.println("Salvando jogador");//categoria
        JogadorDAO jogadorDao = new JogadorDAO();
        boolean loginValido = jogadorDao.verificaLogin(jogador);
        System.out.println(loginValido);
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage msg = null;
        if(jogador.getIdJogador() != null){
            boolean acao = alterar();
            msg = new FacesMessage("Jogador atualizado com sucesso");
            contexto.addMessage("form_novo", msg);
            jogador = new Jogador();
            redireciona();            
            return acao;
        }
        if (!verificaEmail(jogador.getEmail())){
            msg = new FacesMessage("Dominio de e-mail inválido");
            contexto.addMessage("form_novo", msg);
            return true;
        }
        /*
        if (!validaCPF(jogador.getCpf())){
            msg = new FacesMessage("CPFinvalido");
            contexto.addMessage("form_novo", msg);
            return false;
        }
       */
        if (!loginValido) {
            boolean salvou = jogadorDao.salvar(jogador);
            if (salvou) {
                msg = new FacesMessage("Cadastro Realizado com sucesso! \n Verifique o e-mail informado para confirmar seu cadastro!");
                contexto.addMessage("form_novo", msg);
                jogador = new Jogador();
                return true;
            }
        }
        msg = new FacesMessage("Não foi possível cadastrar o jogador");
        contexto.addMessage("form_novo", msg);
        
        return false;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
    
    
    
    @Override
    public ListDataModel<JogadorController> listar() {
        JogadorDAO jogadorDAO = new JogadorDAO();
        List<JogadorController> jogadoresController = new ArrayList();

        List<Jogador> jogadores = jogadorDAO.findAll();

        if (jogadores != null) {
            for (Jogador jog : jogadores) {
                jogadoresController.add(new JogadorController(jog));
            }
            return new ListDataModel(jogadoresController);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean alterar() {
        InterfaceDAO<Jogador, String> jogadorDao = new JogadorDAO();
        return jogadorDao.alterar(jogador);
    }

    @Override
    public String remover() {
        InterfaceDAO<Jogador, String> jogadorDao = new JogadorDAO();
        if (jogadorDao.excluir(jogador)) {
            return "removeu_jogador";
        }
        else {
            return "nao_removeu_jogador";
        }
    }
    
    public String alterarDados(Jogador jogador) {
        this.jogador = jogador;
        return "alterar_jogador";
    }
        

    @Override
    public JogadorController consultar(String chave) {        
        InterfaceDAO<Jogador, String> jogadorDao = new JogadorDAO();
        Jogador jogador = jogadorDao.consultar(chave);
        return new JogadorController(jogador);
    }
    
    
    
}
