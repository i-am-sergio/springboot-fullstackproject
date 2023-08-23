package com.sergiodev.springbootAPI.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.sergiodev.springbootAPI.models.UsuarioModel;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Repository
@Transactional
public class UsuarioDAOimp implements UsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UsuarioModel> getUsuarios() {
        String query = "FROM UsuarioModel";
        return entityManager.createQuery(query, UsuarioModel.class).getResultList();
    }

    @Override
    public UsuarioModel getUsuario(Long id) {
        UsuarioModel usuario = entityManager.find(UsuarioModel.class, id);
        return usuario;
    }

    @Override
    public void eliminarUsuario(Long id) {
        UsuarioModel usuario = entityManager.find(UsuarioModel.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrarUsuario(UsuarioModel usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public UsuarioModel obtenerUsuarioPorCredenciales(UsuarioModel usuario) {
        String query = "FROM UsuarioModel WHERE email = :email";
        List<UsuarioModel> listResult = entityManager.createQuery(query, UsuarioModel.class)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if(listResult.isEmpty()){
            return null;
        }

        String passwordHashed = listResult.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        
        if(argon2.verify(passwordHashed,usuario.getPassword())){
            return listResult.get(0);
        }
        return null;
    }

}
