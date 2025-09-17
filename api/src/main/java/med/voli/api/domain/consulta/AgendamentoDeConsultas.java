package med.voli.api.domain.consulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.validacoes.agendarConsulta.ValidadorAgendamentoDeConsulta;
import med.voli.api.domain.consulta.validacoes.CancelarConsulta.ValidadorCancelamentoDeConsulta;
import med.voli.api.domain.medicos.Medico;
import med.voli.api.domain.medicos.MedicoRepository;
import med.voli.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadoresAgendamentoDeConsultas;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamentoDeConsultas;

    public DadosDetalhamentoConsultaDto agendar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto) {

        //verificar se medico existe
        if(dadosAgendamentoConsultaDto.idMedico() != null && !medicoRepository.existsById(dadosAgendamentoConsultaDto.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }
        //verificar se paciente existe
        if(!pacienteRepository.existsById(dadosAgendamentoConsultaDto.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        validadoresAgendamentoDeConsultas.forEach(v -> v.validar(dadosAgendamentoConsultaDto));

        var medico = escolherMedico(dadosAgendamentoConsultaDto);
        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsultaDto.idPaciente());
        if(medico==null){
            throw new ValidacaoException("Nao ha nenhum medico disponivel nessa data!");

        }

        var consulta = new Consulta(null, medico, paciente, dadosAgendamentoConsultaDto.data(), null);
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsultaDto(consulta);

    }

    public void cancelar(DadosCancelamentoConsultaDto dadosCancelamentoConsultaDto) {

        if(!consultaRepository.existsById(dadosCancelamentoConsultaDto.idConsulta())){
            throw new ValidacaoException("Id da consulta informada não existe!");
        }

        validadoresCancelamentoDeConsultas.forEach(v -> v.validar(dadosCancelamentoConsultaDto));

        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsultaDto.idConsulta());
        consulta.cancelar(dadosCancelamentoConsultaDto.motivo());
    }

    private Medico escolherMedico(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto) {

        if(dadosAgendamentoConsultaDto.idMedico() != null){
            return medicoRepository.getReferenceById(dadosAgendamentoConsultaDto.idMedico());
        }
        if(dadosAgendamentoConsultaDto.especialidade() == null){
            throw new ValidacaoException("O campo 'especialidade' é obrigatória quando o médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dadosAgendamentoConsultaDto.especialidade(), dadosAgendamentoConsultaDto.data());
    }
}
