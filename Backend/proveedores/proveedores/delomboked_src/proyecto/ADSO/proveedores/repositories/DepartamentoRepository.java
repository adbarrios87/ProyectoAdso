package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends
        JpaRepository<DepartamentoEntity, Integer>,
        JpaSpecificationExecutor<DepartamentoEntity> {
}
