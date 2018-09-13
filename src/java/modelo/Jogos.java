package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "jogo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jogo.findAll", query = "SELECT p FROM Jogo p"),
    @NamedQuery(name = "Jogo.findByIdJogo", query = "SELECT p FROM Jogo p WHERE p.idJogo = :idJogo"),
    @NamedQuery(name = "Jogo.findByNome", query = "SELECT p FROM Jogo p WHERE p.nome = :nome")})
public class Jogos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idJogo")
    private Integer idJogo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nome")
    private String nome;

    public Jogos() {
    }

    public Jogos(Integer idJogo) {
        this.idJogo = idJogo;
    }

    public Jogos(Integer idJogo, String nome) {
        this.idJogo = idJogo;
        this.nome = nome;
    }

    public Integer getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(Integer idJogo) {
        this.idJogo = idJogo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJogo != null ? idJogo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Jogos)) {
            return false;
        }
        Jogos other = (Jogos) object;
        if ((this.idJogo == null && other.idJogo != null) || (this.idJogo != null && !this.idJogo.equals(other.idJogo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Jogo[ idJogo=" + idJogo + " ]";
    }
    
}
