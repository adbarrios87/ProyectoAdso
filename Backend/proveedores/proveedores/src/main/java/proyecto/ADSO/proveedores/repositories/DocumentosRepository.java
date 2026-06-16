package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.DocumentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentosRepository extends
        JpaRepository<DocumentosEntity, Integer>,
        JpaSpecificationExecutor<DocumentosEntity> {
    
    java.util.List<DocumentosEntity> findByIdProveedor(Integer idProveedor);
    
    java.util.Optional<DocumentosEntity> findFirstByIdProveedorAndIdTipoDocumentoAndEstadoDocumentoIsTrue(Integer idProveedor, Integer idTipoDocumento);
}

