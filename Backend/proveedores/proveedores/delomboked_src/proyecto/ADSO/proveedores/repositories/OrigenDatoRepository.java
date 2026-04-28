package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.OrigenDatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrigenDatoRepository extends
        JpaRepository<OrigenDatoEntity, Integer>,
        JpaSpecificationExecutor<OrigenDatoEntity> {
}
