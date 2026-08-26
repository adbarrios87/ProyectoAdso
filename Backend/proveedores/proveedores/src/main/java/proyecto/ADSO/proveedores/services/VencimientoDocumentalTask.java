package proyecto.ADSO.proveedores.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import proyecto.ADSO.proveedores.entites.ProveedorEntity;
import proyecto.ADSO.proveedores.repositories.ProveedorRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class VencimientoDocumentalTask {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private NotificacionesService notificacionesService;

    // Ejecutar todos los días a medianoche (0 0 0 * * ?)
    @Scheduled(cron = "0 0 0 * * ?")
    public void procesarVencimientos() {
        System.out.println("Iniciando tarea programada: VencimientoDocumentalTask");

        // Buscar proveedores aprobados (id_estado = 6) que requieran actualización
        List<ProveedorEntity> proveedores = proveedorRepository.findByIdEstadoProveedorAndRequiereActualizacionTrue(6);

        LocalDateTime hoy = LocalDateTime.now();

        for (ProveedorEntity proveedor : proveedores) {
            LocalDateTime fechaAprobacion = proveedor.getFechaAprobacion();
            if (fechaAprobacion == null) {
                // Si no hay fecha de aprobación, usar la fecha de creación como fallback
                fechaAprobacion = proveedor.getFechaCreado();
            }

            if (fechaAprobacion != null) {
                long dias = ChronoUnit.DAYS.between(fechaAprobacion, hoy);

                if (dias == 330) {
                    // Notificar: Próximo a vencer (PRX) - 30 días antes de los 360/365
                    String mensaje = "Su documentación está próxima a vencer. Le invitamos a ingresar y actualizar la documentación para evitar la suspensión de su cuenta.";
                    notificacionesService.generarNotificacion(proveedor.getIdUsuario(), "PRX", mensaje, true);
                    System.out.println("Notificación PRX generada para proveedor ID: " + proveedor.getIdProveedor());
                } else if (dias > 365) {
                    // Solo notificar el día 366 (para no inundar de correos cada día)
                    if (dias == 366) {
                        String mensaje = "Su documentación se encuentra vencida (superó los 365 días). Su relación comercial queda suspendida hasta que la información sea actualizada.";
                        notificacionesService.generarNotificacion(proveedor.getIdUsuario(), "VEN", mensaje, true);
                        System.out.println("Notificación VEN generada para proveedor ID: " + proveedor.getIdProveedor());
                    }
                }
            }
        }
        
        System.out.println("Finalizada tarea programada: VencimientoDocumentalTask");
    }
}
