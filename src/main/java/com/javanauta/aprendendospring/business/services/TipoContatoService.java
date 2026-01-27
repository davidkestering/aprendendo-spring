package com.javanauta.aprendendospring.business.services;

import com.javanauta.aprendendospring.infrastructure.entity.TipoContato;
import com.javanauta.aprendendospring.infrastructure.exceptions.ConflictException;
import com.javanauta.aprendendospring.infrastructure.repository.TipoContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipoContatoService {

    private final TipoContatoRepository tipoContatoRepository;

    public TipoContato salvaTipoContato(TipoContato tipoContato) {
        try {
            tipoExiste(tipoContato.getTipo());
            // normaliza (opcional)
            tipoContato.setTipo(tipoContato.getTipo().trim().toUpperCase());
            return tipoContatoRepository.save(tipoContato);
        } catch (ConflictException e) {
            throw new ConflictException("Tipo de contato já cadastrado. #01", e.getCause());
        }
    }

    public void tipoExiste(String tipo) {
        try {
            boolean existe = tipoContatoRepository.existsByTipoIgnoreCase(tipo);
            if (existe) {
                throw new ConflictException("Tipo de contato já cadastrado. #02 " + tipo);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Tipo de contato já cadastrado. #03", e.getCause());
        }
    }
}
