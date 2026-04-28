package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.TipoPagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagoRepository extends
        JpaRepository<TipoPagoEntity, Integer>,
        JpaSpecificationExecutor<TipoPagoEntity> {
}
