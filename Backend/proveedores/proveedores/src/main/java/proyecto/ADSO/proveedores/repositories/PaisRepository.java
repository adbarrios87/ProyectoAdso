package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends
        JpaRepository<PaisEntity, Integer>,
        JpaSpecificationExecutor<PaisEntity> {
}
