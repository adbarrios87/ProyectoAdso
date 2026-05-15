package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends
        JpaRepository<TipoDocumentoEntity, Integer>,
        JpaSpecificationExecutor<TipoDocumentoEntity> {

    java.util.List<TipoDocumentoEntity> findByTiposPersona_IdTipoPersona(Integer idTipoPersona);

}
