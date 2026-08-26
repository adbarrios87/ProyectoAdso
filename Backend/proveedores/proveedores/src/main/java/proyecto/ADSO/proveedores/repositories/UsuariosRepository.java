package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends
        JpaRepository<UsuariosEntity, Integer>,
        JpaSpecificationExecutor<UsuariosEntity> {
    
    java.util.Optional<UsuariosEntity> findByCorreoUsuarioAndContrasena(String correoUsuario, String contrasena);
    
    java.util.Optional<UsuariosEntity> findByCorreoUsuario(String correoUsuario);
    
    java.util.Optional<UsuariosEntity> findByResetToken(String resetToken);
    
    @org.springframework.data.jpa.repository.Modifying
    @jakarta.transaction.Transactional
    @org.springframework.data.jpa.repository.Query("UPDATE UsuariosEntity u SET u.ultimoIngreso = :fecha WHERE u.idUsuario = :id")
    void updateUltimoIngreso(Integer id, java.time.LocalDateTime fecha);

}
