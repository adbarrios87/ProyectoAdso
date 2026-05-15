package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends
        JpaRepository<ProveedorEntity, Integer>,
        JpaSpecificationExecutor<ProveedorEntity> {

    java.util.Optional<ProveedorEntity> findByCorreoPrincipal(String correoPrincipal);

}
