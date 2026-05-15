package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.NotificacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionesRepository extends
        JpaRepository<NotificacionesEntity, Integer>,
        JpaSpecificationExecutor<NotificacionesEntity> {
    
    java.util.List<NotificacionesEntity> findByIdUsuario(Integer idUsuario);
}
