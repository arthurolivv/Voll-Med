package med.voli.api.domain.consulta;

import jakarta.persistence.*;
import lombok.*;
import med.voli.api.domain.medicos.Medico;
import med.voli.api.domain.pacientes.Paciente;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivo_cancelamento;

    public void cancelar(MotivoCancelamento motivo) {
        this.motivo_cancelamento = motivo;
    }
}