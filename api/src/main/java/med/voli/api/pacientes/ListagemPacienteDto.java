package med.voli.api.pacientes;

public record ListagemPacienteDto(String nome, String email, String cpf) {

    public ListagemPacienteDto(Paciente paciente) {
        this(paciente.getNome(),  paciente.getEmail(), paciente.getCpf());
    }
}
