package org.example.Domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Long totalventas ;
    private String estado = "Activo";

    public Cliente(String nombre, Long totalventas, String estado) {
        this.nombre = nombre;
        this.totalventas = totalventas;
        this.estado = estado;
    }
}
