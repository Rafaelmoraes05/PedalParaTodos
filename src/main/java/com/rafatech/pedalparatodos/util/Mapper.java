package com.rafatech.pedalparatodos.util;

import com.rafatech.pedalparatodos.dto.ParticipacaoDTO;
import com.rafatech.pedalparatodos.dto.PedalDTO;
import com.rafatech.pedalparatodos.dto.UsuarioDTO;
import com.rafatech.pedalparatodos.entity.Participacao;
import com.rafatech.pedalparatodos.entity.Pedal;
import com.rafatech.pedalparatodos.entity.Usuario;

public final class Mapper {

    private Mapper() {
        throw new UnsupportedOperationException("Esta é uma classe utilitária e não pode ser instanciada.");
    }

    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        // A senha (mesmo que hasheada) não é retornada no DTO.
        return dto;
    }

    /**
     * Converte uma entidade {@link Pedal} em um {@link PedalDTO}.
     * Este método também extrai informações do organizador (Usuario) associado.
     *
     * @param pedal A entidade Pedal a ser convertida.
     * @return O PedalDTO correspondente, ou null se a entrada for null.
     */
    public static PedalDTO toPedalDTO(Pedal pedal) {
        if (pedal == null) {
            return null;
        }
        PedalDTO dto = new PedalDTO();
        dto.setId(pedal.getId());
        dto.setNomePedal(pedal.getNomePedal());
        dto.setNomeGrupo(pedal.getNomeGrupo());
        dto.setDescricao(pedal.getDescricao());
        dto.setCategoria(pedal.getCategoria());
        dto.setDataHora(pedal.getDataHora());
        dto.setLocalEncontro(pedal.getLocalEncontro());
        dto.setNivelDificuldade(pedal.getNivelDificuldade());
        dto.setLinkWhatsapp(pedal.getLinkWhatsapp());

        // Mapeia os dados do organizador, se presente
        if (pedal.getOrganizador() != null) {
            dto.setOrganizadorId(pedal.getOrganizador().getId());
            dto.setOrganizadorNome(pedal.getOrganizador().getNome());
        }

        return dto;
    }

    /**
     * Converte uma entidade {@link Participacao} em uma {@link ParticipacaoDTO}.
     * Este método extrai informações do usuário e do pedal associados para
     * fornecer um DTO mais completo e útil para o cliente da API.
     *
     * @param participacao A entidade Participacao a ser convertida.
     * @return O ParticipacaoDTO correspondente, ou null se a entrada for null.
     */
    public static ParticipacaoDTO toParticipacaoDTO(Participacao participacao) {
        if (participacao == null) {
            return null;
        }
        ParticipacaoDTO dto = new ParticipacaoDTO();
        dto.setId(participacao.getId());
        dto.setDataConfirmacao(participacao.getDataConfirmacao());

        // Mapeia os dados do usuário, se presente
        if (participacao.getUsuario() != null) {
            dto.setUsuarioId(participacao.getUsuario().getId());
            dto.setUsuarioNome(participacao.getUsuario().getNome());
        }

        // Mapeia os dados do pedal, se presente
        if (participacao.getPedal() != null) {
            dto.setPedalId(participacao.getPedal().getId());
            dto.setNomePedal(participacao.getPedal().getNomePedal());
        }

        return dto;
    }
}
