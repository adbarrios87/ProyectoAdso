package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.EvaluacionProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionProveedorRepository extends
        JpaRepository<EvaluacionProveedorEntity, Integer>,
        JpaSpecificationExecutor<EvaluacionProveedorEntity> {
}
