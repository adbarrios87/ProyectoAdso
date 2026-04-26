package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.ProveedoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends
        JpaRepository<ProveedoresEntity, Integer>,
        JpaSpecificationExecutor<ProveedoresEntity> {
}
