package com.usuario.usuario.repository;

import org.springframework.data.repository.CrudRepository;

import com.usuario.usuario.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	Usuario findById(int id);

}
