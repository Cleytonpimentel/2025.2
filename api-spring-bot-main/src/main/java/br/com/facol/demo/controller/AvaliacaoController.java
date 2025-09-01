package br.com.facol.demo.controller;

import br.com.facol.demo.dto.AvaliacaoRequestDTO;
import br.com.facol.demo.dto.AvaliacaoResponseDTO;
import br.com.facol.demo.entity.AvaliacaoEntity;
import br.com.facol.demo.mapper.AvaliacaoMapper;
import br.com.facol.demo.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/avaliacoes")
@Tag(name = "Avaliações", description = "Operações relacionadas às avaliações")
public class AvaliacaoController {

    private static final Logger logger = LoggerFactory.getLogger(AvaliacaoController.class);

    private final AvaliacaoService service;
    private final AvaliacaoMapper mapper;

    public AvaliacaoController(AvaliacaoService service, AvaliacaoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(summary = "Criar nova avaliação")
    public ResponseEntity<AvaliacaoResponseDTO> criar(@RequestBody AvaliacaoRequestDTO dto,
                                                      UriComponentsBuilder uriBuilder) {
        logger.info("Recebendo avaliação: {}", dto);
        AvaliacaoEntity salvo = service.salvar(dto);
        AvaliacaoResponseDTO response = mapper.toResponseDTO(salvo);
        URI uri = uriBuilder.path("/avaliacoes/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar avaliações")
    public ResponseEntity<Page<AvaliacaoResponseDTO>> listar(@ParameterObject Pageable pageable) {
        Page<AvaliacaoResponseDTO> page = service.listarTodas(pageable)
                .map(mapper::toResponseDTO);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar avaliação por ID")
    public ResponseEntity<AvaliacaoResponseDTO> buscar(@PathVariable Long id) {
        AvaliacaoEntity entity = service.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponseDTO(entity));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar avaliação")
    public ResponseEntity<AvaliacaoResponseDTO> atualizar(@PathVariable Long id,
                                                          @RequestBody AvaliacaoRequestDTO dto) {
        AvaliacaoEntity atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(mapper.toResponseDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar avaliação")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
