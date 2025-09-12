package med.voli.api.domain.consulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voli.api.domain.medicos.Medico;
import med.voli.api.domain.medicos.MedicoRepository;
import med.voli.api.domain.pacientes.PacienteRepository;
import med.voli.api.usuario.DadosAutenticacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public void agendar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto) {

        //verificar se medico existe
        if(dadosAgendamentoConsultaDto.idMedico() != null && !medicoRepository.existsById(dadosAgendamentoConsultaDto.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }
        //verificar se paciente existe
        if(!pacienteRepository.existsById(dadosAgendamentoConsultaDto.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        validadores.forEach(v -> v.validar(dadosAgendamentoConsultaDto));

        var medico = escolherMedico(dadosAgendamentoConsultaDto);
        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsultaDto.idPaciente());

        var consulta = new Consulta(null, medico, paciente, dadosAgendamentoConsultaDto.data(), null);
        consultaRepository.save(consulta);
    }

    public void cancelar(DadosCancelamentoConsultaDto dadosCancelamentoConsultaDto) {

        if(!consultaRepository.existsById(dadosCancelamentoConsultaDto.idConsulta())){
            throw new ValidacaoException("Id da consulta informada não existe!");
        }

        if(dadosCancelamentoConsultaDto.motivo() == null){
            throw new ValidacaoException("O preenchimento do campo 'Motivo' é obrigatório");
        }

        if(!verificarAntedecendia24Horas(dadosCancelamentoConsultaDto)){
            throw new ValidacaoException("A consulta só pode ser cancelada com no mínimo 24 horas de antecedência!");
        }

        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsultaDto.idConsulta());
        consulta.cancelar(dadosCancelamentoConsultaDto.motivo());
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

    private boolean verificarAntedecendia24Horas(DadosCancelamentoConsultaDto dadosCancelamentoConsultaDto) {

        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsultaDto.idConsulta());

        if(consulta.getData().isAfter(LocalDateTime.now().plusHours(24))){
            return false;
        }
        return true;
    }
}
