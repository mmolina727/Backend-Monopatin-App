package MonopatinApp.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MonopatinApp.entities.*;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Integer>{

}
