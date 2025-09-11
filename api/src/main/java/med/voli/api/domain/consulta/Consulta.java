package med.voli.api.domain.consulta;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name ="consultas")
@Entity(name = "Consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idMedico;

    private Long idPaciente;

    private LocalDateTime data;
}