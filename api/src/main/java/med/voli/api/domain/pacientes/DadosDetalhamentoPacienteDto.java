package med.voli.api.domain.pacientes;

import med.voli.api.domain.endereco.Endereco;

public record DadosDetalhamentoPacienteDto(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {

    public DadosDetalhamentoPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
