package br.com.facol.demo.mapper;

import br.com.facol.demo.dto.AvaliacaoRequestDTO;
import br.com.facol.demo.dto.AvaliacaoResponseDTO;
import br.com.facol.demo.entity.AvaliacaoEntity;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoMapper {

    public AvaliacaoEntity toEntity(AvaliacaoRequestDTO dto) {
        AvaliacaoEntity entity = new AvaliacaoEntity();
        entity.setUsuario(dto.getUsuario());
        entity.setAcademia(dto.getAcademia());
        entity.setNota(dto.getNota());
        entity.setComentario(dto.getComentario());
        return entity;
    }

    public AvaliacaoResponseDTO toResponseDTO(AvaliacaoEntity entity) {
        AvaliacaoResponseDTO dto = new AvaliacaoResponseDTO();
        dto.setId(entity.getId());
        dto.setUsuario(entity.getUsuario());
        dto.setAcademia(entity.getAcademia());
        dto.setNota(entity.getNota());
        dto.setComentario(entity.getComentario());
        return dto;
    }
}
