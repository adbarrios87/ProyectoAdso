package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.MunicipioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends
        JpaRepository<MunicipioEntity, Integer>,
        JpaSpecificationExecutor<MunicipioEntity> {
    
    java.util.List<MunicipioEntity> findByIdDepartamento(Integer idDepartamento);
}
