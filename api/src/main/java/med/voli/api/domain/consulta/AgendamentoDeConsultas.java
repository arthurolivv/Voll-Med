package med.voli.api.domain.consulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.medicos.Medico;
import med.voli.api.domain.medicos.MedicoRepository;
import med.voli.api.domain.pacientes.PacienteRepository;
import med.voli.api.usuario.DadosAutenticacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto) {

        //verificar se medico existe
        if(dadosAgendamentoConsultaDto.idMedico() != null && !medicoRepository.existsById(dadosAgendamentoConsultaDto.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }
        //verificar se paciente existe
        if(!pacienteRepository.existsById(dadosAgendamentoConsultaDto.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }


        var medico = escolherMedico(dadosAgendamentoConsultaDto);
        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsultaDto.idPaciente());

        var consulta = new Consulta(null, medico, paciente, dadosAgendamentoConsultaDto.data());
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto) {

        if(dadosAgendamentoConsultaDto.idMedico() != null){
            return medicoRepository.getReferenceById(dadosAgendamentoConsultaDto.idMedico());
        }
        if(dadosAgendamentoConsultaDto.idMedico() == null){
            throw new ValidacaoException("O campo 'especialidade' é obrigatória quando o médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dadosAgendamentoConsultaDto.especialidade(), dadosAgendamentoConsultaDto.data());
    }
}
