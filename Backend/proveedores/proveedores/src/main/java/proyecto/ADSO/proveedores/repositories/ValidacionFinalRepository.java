package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.ValidacionFinalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidacionFinalRepository extends
        JpaRepository<ValidacionFinalEntity, Integer>,
        JpaSpecificationExecutor<ValidacionFinalEntity> {
}
