package com.javanauta.aprendendospring.business.services;

import com.javanauta.aprendendospring.infrastructure.entity.Contato;
import com.javanauta.aprendendospring.infrastructure.entity.TipoContato;
import com.javanauta.aprendendospring.infrastructure.entity.Usuario;
import com.javanauta.aprendendospring.infrastructure.exceptions.ConflictException;
import com.javanauta.aprendendospring.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.aprendendospring.infrastructure.repository.TipoContatoRepository;
import com.javanauta.aprendendospring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TipoContatoRepository tipoContatoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvaUsuario(Usuario usuario) {
        validateEmailUnique(usuario.getEmail());
        attachTiposContato(usuario.getContatos());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    private void validateEmailUnique(String email) {
        if (email == null || email.isBlank()) {
            throw new ConflictException("Email é obrigatório.");
        }

        if (usuarioRepository.existsByEmail(email)) {
            throw new ConflictException("Email já cadastrado: " + email);
        }
    }

    private void attachTiposContato(List<Contato> contatos) {
        if (contatos == null || contatos.isEmpty()) return;

        for (Contato contato : contatos) {
            UUID tipoId = extractTipoId(contato);
            TipoContato tipoContato = tipoContatoRepository.findById(tipoId)
                    .orElseThrow(() -> new ConflictException("TipoContato não encontrado: " + tipoId));

            // ✅ aqui está a solução: substitui o "stub" pelo objeto gerenciado
            contato.setTipo(tipoContato);
        }
    }

    private UUID extractTipoId(Contato contato) {
        if (contato == null || contato.getTipo() == null || contato.getTipo().getId() == null) {
            throw new ConflictException("Contato precisa informar tipo.id.");
        }
        return contato.getTipo().getId();
    }

    public Usuario buscaUsuarioPorEmail(String email) {
        return (Usuario) usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email não encontrado "+email));
    }

    public void deleteUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }
}
