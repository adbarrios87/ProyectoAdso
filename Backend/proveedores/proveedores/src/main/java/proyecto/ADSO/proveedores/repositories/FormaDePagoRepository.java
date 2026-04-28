package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.FormaDePagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaDePagoRepository extends
        JpaRepository<FormaDePagoEntity, Integer>,
        JpaSpecificationExecutor<FormaDePagoEntity> {
}
