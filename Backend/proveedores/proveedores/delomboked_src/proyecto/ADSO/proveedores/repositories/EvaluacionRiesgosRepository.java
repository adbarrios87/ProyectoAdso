package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.EvaluacionRiesgosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRiesgosRepository extends
        JpaRepository<EvaluacionRiesgosEntity, Integer>,
        JpaSpecificationExecutor<EvaluacionRiesgosEntity> {
}
