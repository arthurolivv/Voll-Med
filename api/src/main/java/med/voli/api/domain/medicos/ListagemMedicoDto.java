package med.voli.api.domain.medicos;

public record ListagemMedicoDto(Long id, String nome, String email, String crm, Especiality especialidace) {

    public ListagemMedicoDto(Medico medico) {

        this(medico.getId(), medico.getNome(), medico.getEmail(),medico.getCrm(), medico.getEspecialidade());
    }
}
