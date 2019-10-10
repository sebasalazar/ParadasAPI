package cl.utem.dist.proyecto.persistencia.modelo;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "paradas", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"micro_fk", "paradero_fk"})})
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk", nullable = false)
    private Long id = null;
    @JoinColumn(name = "micro_fk", referencedColumnName = "pk", nullable = false)
    @ManyToOne(optional = false)
    private Micro micro = null;
    @JoinColumn(name = "paradero_fk", referencedColumnName = "pk", nullable = false)
    @ManyToOne(optional = false)
    private Paradero paradero = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Micro getMicro() {
        return micro;
    }

    public void setMicro(Micro micro) {
        this.micro = micro;
    }

    public Paradero getParadero() {
        return paradero;
    }

    public void setParadero(Paradero paradero) {
        this.paradero = paradero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Parada other = (Parada) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
