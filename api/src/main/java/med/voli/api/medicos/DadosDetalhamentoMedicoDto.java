package med.voli.api.medicos;

import med.voli.api.endereco.Endereco;

public record DadosDetalhamentoMedicoDto(Long id, String nome, String email, String crm, String telefone, Especiality especialidade, Endereco endereco) {

    public DadosDetalhamentoMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
