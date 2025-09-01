package br.com.facol.demo.service;

import br.com.facol.demo.dto.AvaliacaoRequestDTO;
import br.com.facol.demo.entity.AvaliacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AvaliacaoService {
    Page<AvaliacaoEntity> listarTodas(Pageable pageable);
    AvaliacaoEntity buscarPorId(Long id);
    AvaliacaoEntity salvar(AvaliacaoRequestDTO dto);
    AvaliacaoEntity atualizar(Long id, AvaliacaoRequestDTO dto);
    void deletarPorId(Long id);
}
