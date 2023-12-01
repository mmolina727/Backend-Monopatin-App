package Micoservicioadmin.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import Microservicioadmin.Entities.Usuario;

public interface RepositorioUsuario  extends JpaRepository<Usuario,Integer>{
	Optional<Usuario> findByUsername (String username);

}
