package Microservicioadmin.Repositories;

import Microservicioadmin.Entities.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuario extends MongoRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);
}
