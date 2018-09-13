package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "jogador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jogador.findAll", query = "SELECT j FROM Jogador j"),
    @NamedQuery(name = "Jogador.findByIdJogador", query = "SELECT j FROM Jogador j WHERE j.idJogador = :idJogador"),
    @NamedQuery(name = "Jogador.findByEmail", query = "SELECT j FROM Jogador j WHERE j.email = :email"),
    @NamedQuery(name = "Jogador.findByNome", query = "SELECT j FROM Jogador j WHERE j.nome = :nome"),
    @NamedQuery(name = "Jogador.findByTel1", query = "SELECT j FROM Jogador j WHERE j.tel1 = :tel1"),
    @NamedQuery(name = "Jogador.findByTel2", query = "SELECT j FROM Jogador j WHERE j.tel2 = :tel2"),
    @NamedQuery(name = "Jogador.findByCpf", query = "SELECT j FROM Jogador j WHERE j.cpf = :cpf"),
    @NamedQuery(name = "Jogador.findByNacionalidade", query = "SELECT j FROM Jogador j WHERE j.nacionalidade = :nacionalidade"),
    @NamedQuery(name = "Jogador.findBySenha", query = "SELECT j FROM Jogador j WHERE j.senha = :senha")})
public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idJogador")
    private Integer idJogador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 7, max = 60)
    @Column(name = "email")
    private String email;
    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "nome")
    private String nome;
    @NotNull
    @Size(max = 17)
    @Column(name = "tel1")
    private String tel1;
    @NotNull
    @Size(max = 17)
    @Column(name = "tel2")
    private String tel2;
    @NotNull
    @Size(max = 14)
    @Column(name = "cpf")
    private String cpf;
    @NotNull
    @Size(max = 30)
    @Column(name = "nacionalidade")
    private String nacionalidade;  
    @Basic(optional = false)
    @NotNull
    @Size(min = 8, max = 25)
    @Column(name = "senha")
    private String senha;
        
    public Jogador() {
    }

    public Jogador(String email) {
        this.email = email;
    }
      
    public Jogador(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Integer getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Integer idJogador) {
        this.idJogador = idJogador;
    }
    
    
    
    public Jogador(Integer idJogador, String nome, String senha, String email, String tel1, String tel2, String cpf, String nacionalidade) {
        this.idJogador = idJogador;
        this.nome = nome;
        this.email = email;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.cpf = cpf;
        this.nacionalidade = nacionalidade;
        this.senha = senha;
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
    
    public String getTel1() {
        return tel1;
    }
    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }
    
    public String getTel2() {
        return tel2;
    }
    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }
    
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getNacionalidade() {
        return nacionalidade;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
    
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public enum modoUser{
        admin, jogador, jogadorPeriodico, noLogin;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJogador != null ? idJogador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Jogador)) {
            return false;
        }
        Jogador other = (Jogador) object;
        if ((this.idJogador == null && other.idJogador != null) || (this.idJogador != null && !this.idJogador.equals(other.idJogador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Jogador[ idJogador=" + idJogador + " ]";
    }
    
    
    
}
