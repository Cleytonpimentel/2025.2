package br.com.facol.demo.service.impl;

import br.com.facol.demo.dto.AvaliacaoRequestDTO;
import br.com.facol.demo.entity.AvaliacaoEntity;
import br.com.facol.demo.exception.NotFoundException;
import br.com.facol.demo.mapper.AvaliacaoMapper;
import br.com.facol.demo.repository.AvaliacaoRepository;
import br.com.facol.demo.service.AvaliacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final AvaliacaoRepository repository;
    private final AvaliacaoMapper mapper;

    public AvaliacaoServiceImpl(AvaliacaoRepository repository, AvaliacaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<AvaliacaoEntity> listarTodas(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public AvaliacaoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Avaliação com id " + id + " não encontrada"));
    }

    @Override
    public AvaliacaoEntity salvar(AvaliacaoRequestDTO dto) {
        AvaliacaoEntity entity = mapper.toEntity(dto);
        return repository.save(entity);
    }

    @Override
    public AvaliacaoEntity atualizar(Long id, AvaliacaoRequestDTO dto) {
        AvaliacaoEntity existente = buscarPorId(id);
        existente.setUsuario(dto.getUsuario());
        existente.setAcademia(dto.getAcademia());
        existente.setNota(dto.getNota());
        existente.setComentario(dto.getComentario());
        return repository.save(existente);
    }

    @Override
    public void deletarPorId(Long id) {
        AvaliacaoEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}
