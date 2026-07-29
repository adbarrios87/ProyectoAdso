# Métodos del Proyecto

Este documento contiene la información de todos los métodos encontrados en el código fuente del proyecto (Java y JavaScript).

## Índice

- [Backend (Java)](#backend-java)
  - [CorsConfig (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/config/CorsConfig.java)](#corsconfig)
  - [CalificacionController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CalificacionController.java)](#calificacioncontroller)
  - [CampoValidacionController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CampoValidacionController.java)](#campovalidacioncontroller)
  - [ContactoController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ContactoController.java)](#contactocontroller)
  - [DepartamentoController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DepartamentoController.java)](#departamentocontroller)
  - [DocumentosController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosController.java)](#documentoscontroller)
  - [DocumentosSociosProveedorController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosSociosProveedorController.java)](#documentossociosproveedorcontroller)
  - [EstadoProveedorController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoProveedorController.java)](#estadoproveedorcontroller)
  - [EstadoUsuarioController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoUsuarioController.java)](#estadousuariocontroller)
  - [EvaluacionProveedorController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionProveedorController.java)](#evaluacionproveedorcontroller)
  - [EvaluacionRiesgosController [DEPRECADA/ELIMINADA] (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionRiesgosController.java)](#evaluacionriesgoscontroller)
  - [FirmaTokenController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FirmaTokenController.java)](#firmatokencontroller)
  - [FormaDePagoController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FormaDePagoController.java)](#formadepagocontroller)
  - [HistorialUsuarioController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/HistorialUsuarioController.java)](#historialusuariocontroller)
  - [MunicipioController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/MunicipioController.java)](#municipiocontroller)
  - [NotificacionesController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/NotificacionesController.java)](#notificacionescontroller)
  - [OrigenDatoController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/OrigenDatoController.java)](#origendatocontroller)
  - [PaisController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/PaisController.java)](#paiscontroller)
  - [ProveedorContactoController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorContactoController.java)](#proveedorcontactocontroller)
  - [ProveedorController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java)](#proveedorcontroller)
  - [RepresentanteLegalController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteLegalController.java)](#representantelegalcontroller)
  - [RepresentanteProveedorController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteProveedorController.java)](#representanteproveedorcontroller)
  - [RolesController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RolesController.java)](#rolescontroller)
  - [SociosProveedorController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/SociosProveedorController.java)](#sociosproveedorcontroller)
  - [TipoDocumentoController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoDocumentoController.java)](#tipodocumentocontroller)
  - [TipoIdentificacionController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoIdentificacionController.java)](#tipoidentificacioncontroller)
  - [TipoNotificacionController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoNotificacionController.java)](#tiponotificacioncontroller)
  - [TipoPagoController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPagoController.java)](#tipopagocontroller)
  - [TipoPersonaController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPersonaController.java)](#tipopersonacontroller)
  - [TipoTelefonoController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoTelefonoController.java)](#tipotelefonocontroller)
  - [UbicacionController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UbicacionController.java)](#ubicacioncontroller)
  - [UsuariosController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java)](#usuarioscontroller)
  - [ValidacionController (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ValidacionController.java)](#validacioncontroller)
  - [ProveedoresApplication (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/ProveedoresApplication.java)](#proveedoresapplication)
  - [CalificacionService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java)](#calificacionservice)
  - [CampoValidacionService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java)](#campovalidacionservice)
  - [ContactoService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java)](#contactoservice)
  - [DepartamentoService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java)](#departamentoservice)
  - [DocumentosService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java)](#documentosservice)
  - [DocumentosSociosProveedorService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java)](#documentossociosproveedorservice)
  - [DocumentParserService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentParserService.java)](#documentparserservice)
  - [EstadoProveedorService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java)](#estadoproveedorservice)
  - [EstadoUsuarioService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java)](#estadousuarioservice)
  - [EvaluacionProveedorService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java)](#evaluacionproveedorservice)
  - [EvaluacionRiesgosService [DEPRECADA/ELIMINADA] (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java)](#evaluacionriesgosservice)
  - [FirmaTokenService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FirmaTokenService.java)](#firmatokenservice)
  - [FormaDePagoService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java)](#formadepagoservice)
  - [HistorialUsuarioService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java)](#historialusuarioservice)
  - [MunicipioService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java)](#municipioservice)
  - [NotificacionesService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java)](#notificacionesservice)
  - [OrigenDatoService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java)](#origendatoservice)
  - [PaisService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java)](#paisservice)
  - [ProveedorContactoService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java)](#proveedorcontactoservice)
  - [ProveedorService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java)](#proveedorservice)
  - [RepresentanteLegalService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java)](#representantelegalservice)
  - [RepresentanteProveedorService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java)](#representanteproveedorservice)
  - [RolesService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java)](#rolesservice)
  - [SociosProveedorService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java)](#sociosproveedorservice)
  - [TipoDocumentoService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java)](#tipodocumentoservice)
  - [TipoIdentificacionService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java)](#tipoidentificacionservice)
  - [TipoNotificacionService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java)](#tiponotificacionservice)
  - [TipoPagoService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java)](#tipopagoservice)
  - [TipoPersonaService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java)](#tipopersonaservice)
  - [TipoTelefonoService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java)](#tipotelefonoservice)
  - [UbicacionService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java)](#ubicacionservice)
  - [UsuariosService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java)](#usuariosservice)
  - [ValidacionFinalService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionFinalService.java)](#validacionfinalservice)
  - [ValidacionService (file:///Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java)](#validacionservice)
- [Frontend (JavaScript)](#frontend-javascript)
  - [generate_code.js (file:///Backend/generate_code.js)](#generate_codejs)
  - [admin_config.js (file:///Frontend/js/admin_config.js)](#admin_configjs)
  - [buyer_supplier_list.js (file:///Frontend/js/buyer_supplier_list.js)](#buyer_supplier_listjs)
  - [buyer_supplier_profile.js (file:///Frontend/js/buyer_supplier_profile.js)](#buyer_supplier_profilejs)
  - [buyer_supplier_validation.js (file:///Frontend/js/buyer_supplier_validation.js)](#buyer_supplier_validationjs)
  - [compliance_officer_history.js (file:///Frontend/js/compliance_officer_history.js)](#compliance_officer_historyjs)
  - [compliance_officer_review.js (file:///Frontend/js/compliance_officer_review.js)](#compliance_officer_reviewjs)
  - [main.js (file:///Frontend/js/main.js)](#mainjs)
  - [sign_form.js (file:///Frontend/js/sign_form.js)](#sign_formjs)
  - [supplier_certification.js (file:///Frontend/js/supplier_certification.js)](#supplier_certificationjs)
  - [supplier_form.js (file:///Frontend/js/supplier_form.js)](#supplier_formjs)
  - [supplier_qualification_history.js (file:///Frontend/js/supplier_qualification_history.js)](#supplier_qualification_historyjs)
  - [supplier_upload_documents.js (file:///Frontend/js/supplier_upload_documents.js)](#supplier_upload_documentsjs)
  - [user_list.js (file:///Frontend/js/user_list.js)](#user_listjs)
  - [user_new.js (file:///Frontend/js/user_new.js)](#user_newjs)
  - [user_profile.js (file:///Frontend/js/user_profile.js)](#user_profilejs)

---

## Backend (Java)

### <a id="corsconfig"></a>CorsConfig
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/config/CorsConfig.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/config/CorsConfig.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `addCorsMappings` | `void` | `CorsRegistry registry` | @Configuration<br>@Override | [Línea 11](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/config/CorsConfig.java#L11) |

### <a id="calificacioncontroller"></a>CalificacionController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CalificacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CalificacionController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<CalificacionResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CalificacionController.java#L30) |
| `getDetail` | `ResponseDto<CalificacionResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CalificacionController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CalificacionController.java#L59) |

### <a id="campovalidacioncontroller"></a>CampoValidacionController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CampoValidacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CampoValidacionController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<CampoValidacionResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CampoValidacionController.java#L30) |
| `getDetail` | `ResponseDto<CampoValidacionResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CampoValidacionController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CampoValidacionController.java#L59) |

### <a id="contactocontroller"></a>ContactoController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ContactoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ContactoController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<ContactoResponseDto>>` | `ninguno` | @GetMapping | [Línea 31](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ContactoController.java#L31) |
| `getDetail` | `ResponseDto<ContactoResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 40](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ContactoController.java#L40) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ContactoController.java#L60) |

### <a id="departamentocontroller"></a>DepartamentoController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DepartamentoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DepartamentoController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<DepartamentoResponseDto>>` | `@RequestParam(required = false` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DepartamentoController.java#L30) |
| `getDetail` | `ResponseDto<DepartamentoResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DepartamentoController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DepartamentoController.java#L59) |

### <a id="documentoscontroller"></a>DocumentosController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getByProveedor` | `ResponseDto<List<DocumentosResponseDto>>` | `@PathVariable Integer idProveedor` | @GetMapping("/proveedor/{idProveedor}") | [Línea 33](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosController.java#L33) |
| `getAll` | `ResponseDto<List<DocumentosResponseDto>>` | `ninguno` | @GetMapping | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosController.java#L52) |
| `getDetail` | `ResponseDto<DocumentosResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 61](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosController.java#L61) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 81](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosController.java#L81) |

### <a id="documentossociosproveedorcontroller"></a>DocumentosSociosProveedorController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosSociosProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosSociosProveedorController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<DocumentosSociosProveedorResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosSociosProveedorController.java#L30) |
| `getDetail` | `ResponseDto<DocumentosSociosProveedorResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosSociosProveedorController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosSociosProveedorController.java#L59) |

### <a id="estadoproveedorcontroller"></a>EstadoProveedorController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoProveedorController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<EstadoProveedorResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoProveedorController.java#L30) |
| `getDetail` | `ResponseDto<EstadoProveedorResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoProveedorController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoProveedorController.java#L59) |

### <a id="estadousuariocontroller"></a>EstadoUsuarioController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoUsuarioController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoUsuarioController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<EstadoUsuarioResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoUsuarioController.java#L30) |
| `getDetail` | `ResponseDto<EstadoUsuarioResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoUsuarioController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoUsuarioController.java#L59) |

### <a id="evaluacionproveedorcontroller"></a>EvaluacionProveedorController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionProveedorController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getByProveedor` | `ResponseDto<List<EvaluacionProveedorResponseDto>>` | `@PathVariable Integer idProveedor` | @GetMapping("/proveedor/{idProveedor}") | [Línea 19](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionProveedorController.java#L19) |
| `getAll` | `ResponseDto<List<EvaluacionProveedorResponseDto>>` | `ninguno` | @GetMapping | [Línea 38](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionProveedorController.java#L38) |
| `getDetail` | `ResponseDto<EvaluacionProveedorResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionProveedorController.java#L47) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 67](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionProveedorController.java#L67) |

### <a id="evaluacionriesgoscontroller"></a>EvaluacionRiesgosController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionRiesgosController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionRiesgosController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<EvaluacionRiesgosResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionRiesgosController.java#L30) |
| `getDetail` | `ResponseDto<EvaluacionRiesgosResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionRiesgosController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionRiesgosController.java#L59) |

### <a id="firmatokencontroller"></a>FirmaTokenController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FirmaTokenController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FirmaTokenController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `validarToken` | `ResponseDto<FirmaTokenEntity>` | `@RequestParam String token` | @GetMapping("/validar") | [Línea 36](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FirmaTokenController.java#L36) |
| `firmarFormulario` | `ResponseDto<Boolean>` | `@RequestParam String token, HttpServletRequest request` | @PostMapping("/firmar") | [Línea 44](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FirmaTokenController.java#L44) |

### <a id="formadepagocontroller"></a>FormaDePagoController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FormaDePagoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FormaDePagoController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<FormaDePagoResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FormaDePagoController.java#L30) |
| `getDetail` | `ResponseDto<FormaDePagoResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FormaDePagoController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FormaDePagoController.java#L59) |

### <a id="historialusuariocontroller"></a>HistorialUsuarioController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/HistorialUsuarioController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/HistorialUsuarioController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<HistorialUsuarioResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/HistorialUsuarioController.java#L30) |
| `getDetail` | `ResponseDto<HistorialUsuarioResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/HistorialUsuarioController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/HistorialUsuarioController.java#L59) |

### <a id="municipiocontroller"></a>MunicipioController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/MunicipioController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/MunicipioController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<MunicipioResponseDto>>` | `@RequestParam(required = false` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/MunicipioController.java#L30) |
| `getDetail` | `ResponseDto<MunicipioResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/MunicipioController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/MunicipioController.java#L59) |

### <a id="notificacionescontroller"></a>NotificacionesController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/NotificacionesController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/NotificacionesController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<NotificacionesResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/NotificacionesController.java#L30) |
| `getDetail` | `ResponseDto<NotificacionesResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/NotificacionesController.java#L39) |
| `getByUserId` | `ResponseDto<List<NotificacionesResponseDto>>` | `@PathVariable Integer id` | @GetMapping("/usuario/{id}") | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/NotificacionesController.java#L47) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 67](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/NotificacionesController.java#L67) |

### <a id="origendatocontroller"></a>OrigenDatoController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/OrigenDatoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/OrigenDatoController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<OrigenDatoResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/OrigenDatoController.java#L30) |
| `getDetail` | `ResponseDto<OrigenDatoResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/OrigenDatoController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/OrigenDatoController.java#L59) |

### <a id="paiscontroller"></a>PaisController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/PaisController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/PaisController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<PaisResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/PaisController.java#L30) |
| `getDetail` | `ResponseDto<PaisResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/PaisController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/PaisController.java#L59) |

### <a id="proveedorcontactocontroller"></a>ProveedorContactoController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorContactoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorContactoController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<ProveedorContactoResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorContactoController.java#L30) |
| `getDetail` | `ResponseDto<ProveedorContactoResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorContactoController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorContactoController.java#L59) |

### <a id="proveedorcontroller"></a>ProveedorController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<ProveedorResponseDto>>` | `ninguno` | @GetMapping | [Línea 48](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java#L48) |
| `getDetail` | `ResponseDto<ProveedorResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 57](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java#L57) |
| `getDetalleCompleto` | `ResponseDto<ProveedorDetalleCompletoDto>` | `@PathVariable Integer id` | @GetMapping("/{id}/detalle-completo") | [Línea 65](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java#L65) |
| `getByEmail` | `ResponseDto<ProveedorResponseDto>` | `@RequestParam String email` | @GetMapping("/by-email") | [Línea 73](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java#L73) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 93](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java#L93) |
| `registroCompleto` | `ResponseDto<Boolean>` | `@RequestBody proyecto.ADSO.proveedores.dtos.ProveedorCompletoDto request` | @PostMapping("/registro-completo") | [Línea 104](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java#L104) |
| `updateEstado` | `ResponseDto<Boolean>` | `@PathVariable Integer id, @RequestBody Map<String, Object> body` | @PatchMapping("/{id}/estado") | [Línea 111](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java#L111) |

### <a id="representantelegalcontroller"></a>RepresentanteLegalController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteLegalController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteLegalController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<RepresentanteLegalResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteLegalController.java#L30) |
| `getDetail` | `ResponseDto<RepresentanteLegalResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteLegalController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteLegalController.java#L59) |

### <a id="representanteproveedorcontroller"></a>RepresentanteProveedorController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteProveedorController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<RepresentanteProveedorResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteProveedorController.java#L30) |
| `getDetail` | `ResponseDto<RepresentanteProveedorResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteProveedorController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteProveedorController.java#L59) |

### <a id="rolescontroller"></a>RolesController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RolesController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RolesController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<RolesResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RolesController.java#L30) |
| `getDetail` | `ResponseDto<RolesResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RolesController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RolesController.java#L59) |

### <a id="sociosproveedorcontroller"></a>SociosProveedorController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/SociosProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/SociosProveedorController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<SociosProveedorResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/SociosProveedorController.java#L30) |
| `getDetail` | `ResponseDto<SociosProveedorResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/SociosProveedorController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/SociosProveedorController.java#L59) |

### <a id="tipodocumentocontroller"></a>TipoDocumentoController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoDocumentoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoDocumentoController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getDetail` | `ResponseDto<TipoDocumentoResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 46](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoDocumentoController.java#L46) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 66](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoDocumentoController.java#L66) |

### <a id="tipoidentificacioncontroller"></a>TipoIdentificacionController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoIdentificacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoIdentificacionController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<TipoIdentificacionResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoIdentificacionController.java#L30) |
| `getDetail` | `ResponseDto<TipoIdentificacionResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoIdentificacionController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoIdentificacionController.java#L59) |

### <a id="tiponotificacioncontroller"></a>TipoNotificacionController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoNotificacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoNotificacionController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<TipoNotificacionResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoNotificacionController.java#L30) |
| `getDetail` | `ResponseDto<TipoNotificacionResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoNotificacionController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoNotificacionController.java#L59) |

### <a id="tipopagocontroller"></a>TipoPagoController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPagoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPagoController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<TipoPagoResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPagoController.java#L30) |
| `getDetail` | `ResponseDto<TipoPagoResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPagoController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPagoController.java#L59) |

### <a id="tipopersonacontroller"></a>TipoPersonaController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPersonaController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPersonaController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<TipoPersonaResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPersonaController.java#L30) |
| `getDetail` | `ResponseDto<TipoPersonaResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPersonaController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPersonaController.java#L59) |

### <a id="tipotelefonocontroller"></a>TipoTelefonoController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoTelefonoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoTelefonoController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<TipoTelefonoResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoTelefonoController.java#L30) |
| `getDetail` | `ResponseDto<TipoTelefonoResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoTelefonoController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoTelefonoController.java#L59) |

### <a id="ubicacioncontroller"></a>UbicacionController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UbicacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UbicacionController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<UbicacionResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UbicacionController.java#L30) |
| `getDetail` | `ResponseDto<UbicacionResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UbicacionController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UbicacionController.java#L59) |

### <a id="usuarioscontroller"></a>UsuariosController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getMenu` | `ResponseDto<List<MenuDto>>` | `@PathVariable Integer idRol` | @GetMapping("/menu/{idRol}") | [Línea 21](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java#L21) |
| `getAll` | `ResponseDto<List<UsuariosResponseDto>>` | `ninguno` | @GetMapping | [Línea 40](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java#L40) |
| `getDetail` | `ResponseDto<UsuariosResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 49](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java#L49) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 69](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java#L69) |
| `login` | `ResponseDto<LoginResponseDto>` | `@RequestBody @Validated LoginRequestDto request` | @PostMapping("/login") | [Línea 80](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java#L80) |
| `updateFoto` | `ResponseDto<Boolean>` | `@PathVariable Integer id, @RequestBody java.util.Map<String, String> body` | @PatchMapping("/{id}/foto") | [Línea 88](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java#L88) |
| `updateEstado` | `ResponseDto<Boolean>` | `@PathVariable Integer id, @RequestBody java.util.Map<String, Boolean> body` | @PatchMapping("/{id}/estado") | [Línea 96](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java#L96) |
| `updateRequiereActualizacion` | `ResponseDto<Boolean>` | `@PathVariable Integer id, @RequestBody java.util.Map<String, Boolean> body` | @PatchMapping("/{id}/requiere-actualizacion") | [Línea 104](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java#L104) |

### <a id="validacioncontroller"></a>ValidacionController
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ValidacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ValidacionController.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getAll` | `ResponseDto<List<ValidacionResponseDto>>` | `ninguno` | @GetMapping | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ValidacionController.java#L30) |
| `getDetail` | `ResponseDto<ValidacionResponseDto>` | `@PathVariable Integer id` | @GetMapping("/{id}") | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ValidacionController.java#L39) |
| `delete` | `ResponseDto<GeneralResponseDto>` | `@PathVariable Integer id` | @DeleteMapping("/{id}") | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ValidacionController.java#L59) |

### <a id="proveedoresapplication"></a>ProveedoresApplication
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/ProveedoresApplication.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/ProveedoresApplication.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `main` | `void` | `String[] args` | @SpringBootApplication | [Línea 12](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/ProveedoresApplication.java#L12) |
| `corsConfigurer` | `WebMvcConfigurer` | `ninguno` | @Bean | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/ProveedoresApplication.java#L17) |
| `addCorsMappings` | `void` | `CorsRegistry registry` | @Override | [Línea 20](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/ProveedoresApplication.java#L20) |

### <a id="calificacionservice"></a>CalificacionService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `CalificacionCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java#L17) |
| `getAll` | `List<CalificacionResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java#L23) |
| `getDetail` | `CalificacionResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java#L32) |
| `update` | `boolean` | `Integer id, CalificacionCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java#L47) |
| `validateIfExist` | `CalificacionEntity` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java#L52) |
| `dtoToEntity` | `CalificacionEntity` | `CalificacionCreateRequestDto dto` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java#L60) |
| `entityToDto` | `CalificacionResponseDto` | `CalificacionEntity entity` | - | [Línea 68](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java#L68) |

### <a id="campovalidacionservice"></a>CampoValidacionService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `CampoValidacionCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java#L17) |
| `getAll` | `List<CampoValidacionResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java#L23) |
| `getDetail` | `CampoValidacionResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java#L32) |
| `update` | `boolean` | `Integer id, CampoValidacionCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 48](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java#L48) |
| `validateIfExist` | `CampoValidacionEntity` | `Integer id` | - | [Línea 53](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java#L53) |
| `dtoToEntity` | `CampoValidacionEntity` | `CampoValidacionCreateRequestDto dto` | - | [Línea 61](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java#L61) |
| `entityToDto` | `CampoValidacionResponseDto` | `CampoValidacionEntity entity` | - | [Línea 70](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java#L70) |

### <a id="contactoservice"></a>ContactoService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `ContactoEntity` | `ContactoCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java#L17) |
| `getAll` | `List<ContactoResponseDto>` | `ninguno` | - | [Línea 28](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java#L28) |
| `getDetail` | `ContactoResponseDto` | `Integer id` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java#L37) |
| `update` | `boolean` | `Integer id, ContactoCreateRequestDto dto` | - | [Línea 42](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java#L42) |
| `delete` | `void` | `Integer id` | - | [Línea 66](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java#L66) |
| `validateIfExist` | `ContactoEntity` | `Integer id` | - | [Línea 71](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java#L71) |
| `dtoToEntity` | `ContactoEntity` | `ContactoCreateRequestDto dto` | - | [Línea 79](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java#L79) |
| `entityToDto` | `ContactoResponseDto` | `ContactoEntity entity` | - | [Línea 94](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java#L94) |

### <a id="departamentoservice"></a>DepartamentoService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `DepartamentoCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java#L17) |
| `getAll` | `List<DepartamentoResponseDto>` | `Integer idPais` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java#L23) |
| `getDetail` | `DepartamentoResponseDto` | `Integer id` | - | [Línea 38](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java#L38) |
| `update` | `boolean` | `Integer id, DepartamentoCreateRequestDto dto` | - | [Línea 43](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java#L43) |
| `delete` | `void` | `Integer id` | - | [Línea 54](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java#L54) |
| `validateIfExist` | `DepartamentoEntity` | `Integer id` | - | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java#L59) |
| `dtoToEntity` | `DepartamentoEntity` | `DepartamentoCreateRequestDto dto` | - | [Línea 67](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java#L67) |
| `entityToDto` | `DepartamentoResponseDto` | `DepartamentoEntity entity` | - | [Línea 76](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java#L76) |

### <a id="documentosservice"></a>DocumentosService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getByIdProveedor` | `List<DocumentosResponseDto>` | `Integer idProveedor` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java#L23) |
| `uploadDocumento` | `DocumentosResponseDto` | `MultipartFile file, Integer idProveedor, Integer idTipoDocumento, Integer creadoPor` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java#L32) |
| `create` | `boolean` | `DocumentosCreateRequestDto dto` | - | [Línea 94](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java#L94) |
| `getAll` | `List<DocumentosResponseDto>` | `ninguno` | - | [Línea 100](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java#L100) |
| `getDetail` | `DocumentosResponseDto` | `Integer id` | - | [Línea 109](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java#L109) |
| `update` | `boolean` | `Integer id, DocumentosCreateRequestDto dto` | - | [Línea 114](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java#L114) |
| `delete` | `void` | `Integer id` | - | [Línea 137](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java#L137) |
| `validateIfExist` | `DocumentosEntity` | `Integer id` | - | [Línea 142](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java#L142) |
| `dtoToEntity` | `DocumentosEntity` | `DocumentosCreateRequestDto dto` | - | [Línea 150](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java#L150) |
| `entityToDto` | `DocumentosResponseDto` | `DocumentosEntity entity` | - | [Línea 171](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java#L171) |

### <a id="documentossociosproveedorservice"></a>DocumentosSociosProveedorService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `DocumentosSociosProveedorCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java#L17) |
| `getAll` | `List<DocumentosSociosProveedorResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java#L23) |
| `getDetail` | `DocumentosSociosProveedorResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java#L32) |
| `update` | `boolean` | `Integer id, DocumentosSociosProveedorCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 51](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java#L51) |
| `validateIfExist` | `DocumentosSociosProveedorEntity` | `Integer id` | - | [Línea 56](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java#L56) |
| `dtoToEntity` | `DocumentosSociosProveedorEntity` | `DocumentosSociosProveedorCreateRequestDto dto` | - | [Línea 64](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java#L64) |
| `entityToDto` | `DocumentosSociosProveedorResponseDto` | `DocumentosSociosProveedorEntity entity` | - | [Línea 76](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java#L76) |

### <a id="documentparserservice"></a>DocumentParserService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentParserService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentParserService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `extractText` | `String` | `MultipartFile file` | - | [Línea 224](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentParserService.java#L224) |
| `buscarPatron` | `String` | `String text, String regex` | - | [Línea 254](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentParserService.java#L254) |
| `extraerSocios` | `List<SocioPreFill>` | `String text` | - | [Línea 263](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentParserService.java#L263) |
| `extraerRepresentantes` | `List<RepresentantePreFill>` | `String text` | - | [Línea 366](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentParserService.java#L366) |
| `agregarValidacion` | `void` | `List<ValidacionCreateRequestDto> list, int idCampo, String valorWeb, String valorDoc, Boolean resultado, String comentarios` | - | [Línea 385](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentParserService.java#L385) |
| `parseFechaDocumento` | `LocalDate` | `String text` | - | [Línea 398](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentParserService.java#L398) |

### <a id="estadoproveedorservice"></a>EstadoProveedorService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `EstadoProveedorCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java#L17) |
| `getAll` | `List<EstadoProveedorResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java#L23) |
| `getDetail` | `EstadoProveedorResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java#L32) |
| `update` | `boolean` | `Integer id, EstadoProveedorCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java#L47) |
| `validateIfExist` | `EstadoProveedorEntity` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java#L52) |
| `dtoToEntity` | `EstadoProveedorEntity` | `EstadoProveedorCreateRequestDto dto` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java#L60) |
| `entityToDto` | `EstadoProveedorResponseDto` | `EstadoProveedorEntity entity` | - | [Línea 68](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java#L68) |

### <a id="estadousuarioservice"></a>EstadoUsuarioService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `EstadoUsuarioCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java#L17) |
| `getAll` | `List<EstadoUsuarioResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java#L23) |
| `getDetail` | `EstadoUsuarioResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java#L32) |
| `update` | `boolean` | `Integer id, EstadoUsuarioCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java#L47) |
| `validateIfExist` | `EstadoUsuarioEntity` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java#L52) |
| `dtoToEntity` | `EstadoUsuarioEntity` | `EstadoUsuarioCreateRequestDto dto` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java#L60) |
| `entityToDto` | `EstadoUsuarioResponseDto` | `EstadoUsuarioEntity entity` | - | [Línea 68](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java#L68) |

### <a id="evaluacionproveedorservice"></a>EvaluacionProveedorService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getByIdProveedor` | `List<EvaluacionProveedorResponseDto>` | `Integer idProveedor` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java#L17) |
| `create` | `boolean` | `EvaluacionProveedorCreateRequestDto dto` | - | [Línea 26](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java#L26) |
| `getAll` | `List<EvaluacionProveedorResponseDto>` | `ninguno` | - | [Línea 39](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java#L39) |
| `getDetail` | `EvaluacionProveedorResponseDto` | `Integer id` | - | [Línea 48](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java#L48) |
| `update` | `boolean` | `Integer id, EvaluacionProveedorCreateRequestDto dto` | - | [Línea 53](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java#L53) |
| `delete` | `void` | `Integer id` | - | [Línea 84](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java#L84) |
| `validateIfExist` | `EvaluacionProveedorEntity` | `Integer id` | - | [Línea 89](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java#L89) |
| `calcularPuntajeYCalificacion` | `void` | `EvaluacionProveedorEntity entity` | - | [Línea 97](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java#L97) |
| `dtoToEntity` | `EvaluacionProveedorEntity` | `EvaluacionProveedorCreateRequestDto dto` | - | [Línea 118](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java#L118) |
| `entityToDto` | `EvaluacionProveedorResponseDto` | `EvaluacionProveedorEntity entity` | - | [Línea 148](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java#L148) |

### <a id="evaluacionriesgosservice"></a>EvaluacionRiesgosService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `EvaluacionRiesgosCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java#L17) |
| `getAll` | `List<EvaluacionRiesgosResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java#L23) |
| `getDetail` | `EvaluacionRiesgosResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java#L32) |
| `update` | `boolean` | `Integer id, EvaluacionRiesgosCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 55](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java#L55) |
| `validateIfExist` | `EvaluacionRiesgosEntity` | `Integer id` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java#L60) |
| `dtoToEntity` | `EvaluacionRiesgosEntity` | `EvaluacionRiesgosCreateRequestDto dto` | - | [Línea 68](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java#L68) |
| `entityToDto` | `EvaluacionRiesgosResponseDto` | `EvaluacionRiesgosEntity entity` | - | [Línea 84](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java#L84) |

### <a id="firmatokenservice"></a>FirmaTokenService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FirmaTokenService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FirmaTokenService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `solicitarFirma` | `FirmaTokenEntity` | `Integer idProveedor` | - | [Línea 24](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FirmaTokenService.java#L24) |
| `validarToken` | `FirmaTokenEntity` | `String token` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FirmaTokenService.java#L52) |
| `firmarFormulario` | `boolean` | `String token, String ip` | - | [Línea 67](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FirmaTokenService.java#L67) |

### <a id="formadepagoservice"></a>FormaDePagoService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `FormaDePagoCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java#L17) |
| `getAll` | `List<FormaDePagoResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java#L23) |
| `getDetail` | `FormaDePagoResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java#L32) |
| `update` | `boolean` | `Integer id, FormaDePagoCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 53](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java#L53) |
| `validateIfExist` | `FormaDePagoEntity` | `Integer id` | - | [Línea 58](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java#L58) |
| `dtoToEntity` | `FormaDePagoEntity` | `FormaDePagoCreateRequestDto dto` | - | [Línea 66](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java#L66) |
| `entityToDto` | `FormaDePagoResponseDto` | `FormaDePagoEntity entity` | - | [Línea 80](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java#L80) |

### <a id="historialusuarioservice"></a>HistorialUsuarioService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `HistorialUsuarioCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java#L17) |
| `getAll` | `List<HistorialUsuarioResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java#L23) |
| `getDetail` | `HistorialUsuarioResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java#L32) |
| `update` | `boolean` | `Integer id, HistorialUsuarioCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java#L52) |
| `validateIfExist` | `HistorialUsuarioEntity` | `Integer id` | - | [Línea 57](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java#L57) |
| `dtoToEntity` | `HistorialUsuarioEntity` | `HistorialUsuarioCreateRequestDto dto` | - | [Línea 65](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java#L65) |
| `entityToDto` | `HistorialUsuarioResponseDto` | `HistorialUsuarioEntity entity` | - | [Línea 78](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java#L78) |

### <a id="municipioservice"></a>MunicipioService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `MunicipioCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java#L17) |
| `getAll` | `List<MunicipioResponseDto>` | `Integer idDepartamento` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java#L23) |
| `getDetail` | `MunicipioResponseDto` | `Integer id` | - | [Línea 38](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java#L38) |
| `update` | `boolean` | `Integer id, MunicipioCreateRequestDto dto` | - | [Línea 43](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java#L43) |
| `delete` | `void` | `Integer id` | - | [Línea 54](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java#L54) |
| `validateIfExist` | `MunicipioEntity` | `Integer id` | - | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java#L59) |
| `dtoToEntity` | `MunicipioEntity` | `MunicipioCreateRequestDto dto` | - | [Línea 67](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java#L67) |
| `entityToDto` | `MunicipioResponseDto` | `MunicipioEntity entity` | - | [Línea 76](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java#L76) |

### <a id="notificacionesservice"></a>NotificacionesService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `NotificacionesCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java#L17) |
| `getAll` | `List<NotificacionesResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java#L23) |
| `getByUserId` | `List<NotificacionesResponseDto>` | `Integer userId` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java#L32) |
| `getDetail` | `NotificacionesResponseDto` | `Integer id` | - | [Línea 41](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java#L41) |
| `update` | `boolean` | `Integer id, NotificacionesCreateRequestDto dto` | - | [Línea 46](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java#L46) |
| `delete` | `void` | `Integer id` | - | [Línea 62](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java#L62) |
| `validateIfExist` | `NotificacionesEntity` | `Integer id` | - | [Línea 67](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java#L67) |
| `dtoToEntity` | `NotificacionesEntity` | `NotificacionesCreateRequestDto dto` | - | [Línea 75](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java#L75) |
| `entityToDto` | `NotificacionesResponseDto` | `NotificacionesEntity entity` | - | [Línea 89](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java#L89) |

### <a id="origendatoservice"></a>OrigenDatoService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `OrigenDatoCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java#L17) |
| `getAll` | `List<OrigenDatoResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java#L23) |
| `getDetail` | `OrigenDatoResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java#L32) |
| `update` | `boolean` | `Integer id, OrigenDatoCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java#L47) |
| `validateIfExist` | `OrigenDatoEntity` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java#L52) |
| `dtoToEntity` | `OrigenDatoEntity` | `OrigenDatoCreateRequestDto dto` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java#L60) |
| `entityToDto` | `OrigenDatoResponseDto` | `OrigenDatoEntity entity` | - | [Línea 68](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java#L68) |

### <a id="paisservice"></a>PaisService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `PaisCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java#L17) |
| `getAll` | `List<PaisResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java#L23) |
| `getDetail` | `PaisResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java#L32) |
| `update` | `boolean` | `Integer id, PaisCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java#L47) |
| `validateIfExist` | `PaisEntity` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java#L52) |
| `dtoToEntity` | `PaisEntity` | `PaisCreateRequestDto dto` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java#L60) |
| `entityToDto` | `PaisResponseDto` | `PaisEntity entity` | - | [Línea 68](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java#L68) |

### <a id="proveedorcontactoservice"></a>ProveedorContactoService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `ProveedorContactoCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java#L17) |
| `getAll` | `List<ProveedorContactoResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java#L23) |
| `getDetail` | `ProveedorContactoResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java#L32) |
| `update` | `boolean` | `Integer id, ProveedorContactoCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java#L52) |
| `validateIfExist` | `ProveedorContactoEntity` | `Integer id` | - | [Línea 57](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java#L57) |
| `dtoToEntity` | `ProveedorContactoEntity` | `ProveedorContactoCreateRequestDto dto` | - | [Línea 65](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java#L65) |
| `entityToDto` | `ProveedorContactoResponseDto` | `ProveedorContactoEntity entity` | - | [Línea 78](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java#L78) |

### <a id="proveedorservice"></a>ProveedorService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `ProveedorCreateRequestDto dto` | - | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L47) |
| `getAll` | `List<ProveedorResponseDto>` | `ninguno` | - | [Línea 53](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L53) |
| `getDetail` | `ProveedorResponseDto` | `Integer id` | - | [Línea 62](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L62) |
| `getByEmail` | `ProveedorResponseDto` | `String email` | - | [Línea 67](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L67) |
| `update` | `boolean` | `Integer id, ProveedorCreateRequestDto dto` | - | [Línea 73](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L73) |
| `delete` | `void` | `Integer id` | - | [Línea 112](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L112) |
| `registroCompleto` | `boolean` | `proyecto.ADSO.proveedores.dtos.ProveedorCompletoDto dto` | @org.springframework.transaction.annotation.Transactional | [Línea 118](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L118) |
| `validateIfExist` | `ProveedorEntity` | `Integer id` | - | [Línea 339](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L339) |
| `dtoToEntity` | `ProveedorEntity` | `ProveedorCreateRequestDto dto` | - | [Línea 347](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L347) |
| `entityToDto` | `ProveedorResponseDto` | `ProveedorEntity entity` | - | [Línea 375](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L375) |
| `getDetalleCompleto` | `ProveedorDetalleCompletoDto` | `Integer id` | - | [Línea 404](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L404) |
| `updateEstado` | `boolean` | `Integer id, Integer idEstadoProveedor, Integer modificadoPor` | - | [Línea 573](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java#L573) |

### <a id="representantelegalservice"></a>RepresentanteLegalService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `RepresentanteLegalCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java#L17) |
| `getAll` | `List<RepresentanteLegalResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java#L23) |
| `getDetail` | `RepresentanteLegalResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java#L32) |
| `update` | `boolean` | `Integer id, RepresentanteLegalCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 58](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java#L58) |
| `validateIfExist` | `RepresentanteLegalEntity` | `Integer id` | - | [Línea 63](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java#L63) |
| `dtoToEntity` | `RepresentanteLegalEntity` | `RepresentanteLegalCreateRequestDto dto` | - | [Línea 71](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java#L71) |
| `entityToDto` | `RepresentanteLegalResponseDto` | `RepresentanteLegalEntity entity` | - | [Línea 90](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java#L90) |

### <a id="representanteproveedorservice"></a>RepresentanteProveedorService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `RepresentanteProveedorCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java#L17) |
| `getAll` | `List<RepresentanteProveedorResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java#L23) |
| `getDetail` | `RepresentanteProveedorResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java#L32) |
| `update` | `boolean` | `Integer id, RepresentanteProveedorCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 54](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java#L54) |
| `validateIfExist` | `RepresentanteProveedorEntity` | `Integer id` | - | [Línea 59](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java#L59) |
| `dtoToEntity` | `RepresentanteProveedorEntity` | `RepresentanteProveedorCreateRequestDto dto` | - | [Línea 67](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java#L67) |
| `entityToDto` | `RepresentanteProveedorResponseDto` | `RepresentanteProveedorEntity entity` | - | [Línea 82](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java#L82) |

### <a id="rolesservice"></a>RolesService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `RolesCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java#L17) |
| `getAll` | `List<RolesResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java#L23) |
| `getDetail` | `RolesResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java#L32) |
| `update` | `boolean` | `Integer id, RolesCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 51](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java#L51) |
| `validateIfExist` | `RolesEntity` | `Integer id` | - | [Línea 56](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java#L56) |
| `dtoToEntity` | `RolesEntity` | `RolesCreateRequestDto dto` | - | [Línea 64](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java#L64) |
| `entityToDto` | `RolesResponseDto` | `RolesEntity entity` | - | [Línea 76](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java#L76) |

### <a id="sociosproveedorservice"></a>SociosProveedorService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `SociosProveedorCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java#L17) |
| `getAll` | `List<SociosProveedorResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java#L23) |
| `getDetail` | `SociosProveedorResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java#L32) |
| `update` | `boolean` | `Integer id, SociosProveedorCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java#L60) |
| `validateIfExist` | `SociosProveedorEntity` | `Integer id` | - | [Línea 65](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java#L65) |
| `dtoToEntity` | `SociosProveedorEntity` | `SociosProveedorCreateRequestDto dto` | - | [Línea 73](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java#L73) |
| `entityToDto` | `SociosProveedorResponseDto` | `SociosProveedorEntity entity` | - | [Línea 94](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java#L94) |

### <a id="tipodocumentoservice"></a>TipoDocumentoService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `TipoDocumentoCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java#L17) |
| `getAll` | `List<TipoDocumentoResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java#L23) |
| `getByTipoPersona` | `List<TipoDocumentoResponseDto>` | `Integer idTipoPersona` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java#L32) |
| `getDetail` | `TipoDocumentoResponseDto` | `Integer id` | - | [Línea 41](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java#L41) |
| `update` | `boolean` | `Integer id, TipoDocumentoCreateRequestDto dto` | - | [Línea 46](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java#L46) |
| `delete` | `void` | `Integer id` | - | [Línea 56](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java#L56) |
| `validateIfExist` | `TipoDocumentoEntity` | `Integer id` | - | [Línea 61](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java#L61) |
| `dtoToEntity` | `TipoDocumentoEntity` | `TipoDocumentoCreateRequestDto dto` | - | [Línea 69](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java#L69) |
| `entityToDto` | `TipoDocumentoResponseDto` | `TipoDocumentoEntity entity` | - | [Línea 77](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java#L77) |

### <a id="tipoidentificacionservice"></a>TipoIdentificacionService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `TipoIdentificacionCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java#L17) |
| `getAll` | `List<TipoIdentificacionResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java#L23) |
| `getDetail` | `TipoIdentificacionResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java#L32) |
| `update` | `boolean` | `Integer id, TipoIdentificacionCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java#L47) |
| `validateIfExist` | `TipoIdentificacionEntity` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java#L52) |
| `dtoToEntity` | `TipoIdentificacionEntity` | `TipoIdentificacionCreateRequestDto dto` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java#L60) |
| `entityToDto` | `TipoIdentificacionResponseDto` | `TipoIdentificacionEntity entity` | - | [Línea 68](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java#L68) |

### <a id="tiponotificacionservice"></a>TipoNotificacionService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `TipoNotificacionCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java#L17) |
| `getAll` | `List<TipoNotificacionResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java#L23) |
| `getDetail` | `TipoNotificacionResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java#L32) |
| `update` | `boolean` | `Integer id, TipoNotificacionCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java#L47) |
| `validateIfExist` | `TipoNotificacionEntity` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java#L52) |
| `dtoToEntity` | `TipoNotificacionEntity` | `TipoNotificacionCreateRequestDto dto` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java#L60) |
| `entityToDto` | `TipoNotificacionResponseDto` | `TipoNotificacionEntity entity` | - | [Línea 68](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java#L68) |

### <a id="tipopagoservice"></a>TipoPagoService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `TipoPagoCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java#L17) |
| `getAll` | `List<TipoPagoResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java#L23) |
| `getDetail` | `TipoPagoResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java#L32) |
| `update` | `boolean` | `Integer id, TipoPagoCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java#L47) |
| `validateIfExist` | `TipoPagoEntity` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java#L52) |
| `dtoToEntity` | `TipoPagoEntity` | `TipoPagoCreateRequestDto dto` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java#L60) |
| `entityToDto` | `TipoPagoResponseDto` | `TipoPagoEntity entity` | - | [Línea 68](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java#L68) |

### <a id="tipopersonaservice"></a>TipoPersonaService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `TipoPersonaCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java#L17) |
| `getAll` | `List<TipoPersonaResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java#L23) |
| `getDetail` | `TipoPersonaResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java#L32) |
| `update` | `boolean` | `Integer id, TipoPersonaCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java#L47) |
| `validateIfExist` | `TipoPersonaEntity` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java#L52) |
| `dtoToEntity` | `TipoPersonaEntity` | `TipoPersonaCreateRequestDto dto` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java#L60) |
| `entityToDto` | `TipoPersonaResponseDto` | `TipoPersonaEntity entity` | - | [Línea 68](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java#L68) |

### <a id="tipotelefonoservice"></a>TipoTelefonoService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `TipoTelefonoCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java#L17) |
| `getAll` | `List<TipoTelefonoResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java#L23) |
| `getDetail` | `TipoTelefonoResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java#L32) |
| `update` | `boolean` | `Integer id, TipoTelefonoCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 47](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java#L47) |
| `validateIfExist` | `TipoTelefonoEntity` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java#L52) |
| `dtoToEntity` | `TipoTelefonoEntity` | `TipoTelefonoCreateRequestDto dto` | - | [Línea 60](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java#L60) |
| `entityToDto` | `TipoTelefonoResponseDto` | `TipoTelefonoEntity entity` | - | [Línea 68](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java#L68) |

### <a id="ubicacionservice"></a>UbicacionService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `UbicacionCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java#L17) |
| `getAll` | `List<UbicacionResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java#L23) |
| `getDetail` | `UbicacionResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java#L32) |
| `update` | `boolean` | `Integer id, UbicacionCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 52](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java#L52) |
| `validateIfExist` | `UbicacionEntity` | `Integer id` | - | [Línea 57](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java#L57) |
| `dtoToEntity` | `UbicacionEntity` | `UbicacionCreateRequestDto dto` | - | [Línea 65](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java#L65) |
| `entityToDto` | `UbicacionResponseDto` | `UbicacionEntity entity` | - | [Línea 78](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java#L78) |

### <a id="usuariosservice"></a>UsuariosService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `getMenuByRole` | `List<MenuDto>` | `Integer idRol` | - | [Línea 24](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L24) |
| `create` | `boolean` | `UsuariosCreateRequestDto dto` | - | [Línea 94](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L94) |
| `getAll` | `List<UsuariosResponseDto>` | `ninguno` | - | [Línea 129](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L129) |
| `getDetail` | `UsuariosResponseDto` | `Integer id` | - | [Línea 139](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L139) |
| `update` | `boolean` | `Integer id, UsuariosCreateRequestDto dto` | - | [Línea 145](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L145) |
| `delete` | `void` | `Integer id` | - | [Línea 166](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L166) |
| `updateFoto` | `boolean` | `Integer id, String fotoUrl` | @jakarta.transaction.Transactional | [Línea 174](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L174) |
| `updateEstado` | `boolean` | `Integer id, Boolean estado` | - | [Línea 181](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L181) |
| `updateRequiereActualizacion` | `boolean` | `Integer idUsuario, Boolean requiereActualizacion` | @jakarta.transaction.Transactional | [Línea 189](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L189) |
| `validateIfExist` | `UsuariosEntity` | `Integer id` | - | [Línea 200](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L200) |
| `login` | `LoginResponseDto` | `LoginRequestDto request` | - | [Línea 208](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L208) |
| `dtoToEntity` | `UsuariosEntity` | `UsuariosCreateRequestDto dto` | - | [Línea 234](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L234) |
| `entityToDto` | `UsuariosResponseDto` | `UsuariosEntity entity` | - | [Línea 255](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java#L255) |

### <a id="validacionfinalservice"></a>ValidacionFinalService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionFinalService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionFinalService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `ValidacionFinalCreateRequestDto dto` | @Transactional | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionFinalService.java#L32) |

### <a id="validacionservice"></a>ValidacionService
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java)

| Método | Tipo de Retorno | Parámetros | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `create` | `boolean` | `ValidacionCreateRequestDto dto` | - | [Línea 17](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java#L17) |
| `getAll` | `List<ValidacionResponseDto>` | `ninguno` | - | [Línea 23](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java#L23) |
| `getDetail` | `ValidacionResponseDto` | `Integer id` | - | [Línea 32](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java#L32) |
| `update` | `boolean` | `Integer id, ValidacionCreateRequestDto dto` | - | [Línea 37](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java#L37) |
| `delete` | `void` | `Integer id` | - | [Línea 58](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java#L58) |
| `validateIfExist` | `ValidacionEntity` | `Integer id` | - | [Línea 63](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java#L63) |
| `dtoToEntity` | `ValidacionEntity` | `ValidacionCreateRequestDto dto` | - | [Línea 71](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java#L71) |
| `entityToDto` | `ValidacionResponseDto` | `ValidacionEntity entity` | - | [Línea 90](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java#L90) |

## Frontend (JavaScript)

### <a id="generate_codejs"></a>generate_code.js
**Archivo:** [Backend/generate_code.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `toCamelCase` | function | `str` | [Línea 20](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js#L20) |
| `toPascalCase` | function | `str` | [Línea 25](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js#L25) |
| `parseSql` | function | `ninguno` | [Línea 30](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js#L30) |
| `writeFile` | function | `folder, filename, content` | [Línea 71](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js#L71) |
| `generateDtos` | function | `table` | [Línea 79](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js#L79) |
| `generateEntity` | function | `table` | [Línea 113](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js#L113) |
| `generateRepository` | function | `table` | [Línea 146](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js#L146) |
| `generateService` | function | `table` | [Línea 167](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js#L167) |
| `generateController` | function | `table` | [Línea 261](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js#L261) |
| `generateCommonDtos` | function | `ninguno` | [Línea 338](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js#L338) |
| `main` | function | `ninguno` | [Línea 368](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/generate_code.js#L368) |

### <a id="admin_configjs"></a>admin_config.js
**Archivo:** [Frontend/js/admin_config.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/admin_config.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `initTabs` | function | `ninguno` | [Línea 159](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/admin_config.js#L159) |
| `renderTableRows` | function | `records` | [Línea 210](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/admin_config.js#L210) |
| `initSearch` | function | `ninguno` | [Línea 254](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/admin_config.js#L254) |
| `initModal` | function | `ninguno` | [Línea 276](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/admin_config.js#L276) |
| `openFormModal` | function | `record = null` | [Línea 294](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/admin_config.js#L294) |

### <a id="buyer_supplier_listjs"></a>buyer_supplier_list.js
**Archivo:** [Frontend/js/buyer_supplier_list.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_list.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `renderizarTablaProveedores` | function | `proveedores, estadosMap, ubicacionesMap, contactosMap, calificacionMap, ultimasEvaluacionesMap` | [Línea 124](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_list.js#L124) |
| `buscarProveedores` | function | `ninguno` | [Línea 196](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_list.js#L196) |

### <a id="buyer_supplier_profilejs"></a>buyer_supplier_profile.js
**Archivo:** [Frontend/js/buyer_supplier_profile.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_profile.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `openModal` | function | `mode, contact = null` | [Línea 407](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_profile.js#L407) |
| `closeModal` | function | `ninguno` | [Línea 431](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_profile.js#L431) |
| `openPaymentModal` | function | `mode, pago = null` | [Línea 552](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_profile.js#L552) |
| `closePaymentModal` | function | `ninguno` | [Línea 573](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_profile.js#L573) |
| `generarFichaPDF` | function | `ninguno` | [Línea 700](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_profile.js#L700) |
| `drawSectionHeader` | function | `title` | [Línea 752](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_profile.js#L752) |

### <a id="buyer_supplier_validationjs"></a>buyer_supplier_validation.js
**Archivo:** [Frontend/js/buyer_supplier_validation.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_validation.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `renderMatrix` | function | `validaciones` | [Línea 26](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_validation.js#L26) |
| `openEditModal` | function | `campoId, validacion` | [Línea 164](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_validation.js#L164) |
| `closeEditModal` | function | `ninguno` | [Línea 185](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/buyer_supplier_validation.js#L185) |

### <a id="compliance_officer_historyjs"></a>compliance_officer_history.js
**Archivo:** [Frontend/js/compliance_officer_history.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/compliance_officer_history.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `insertarDatosPrueba` | function | `ninguno` | [Línea 10](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/compliance_officer_history.js#L10) |
| `cargarDecisiones` | function | `ninguno` | [Línea 87](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/compliance_officer_history.js#L87) |
| `verDetalle` | function | `index` | [Línea 139](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/compliance_officer_history.js#L139) |
| `createModal` | function | `ninguno` | [Línea 159](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/compliance_officer_history.js#L159) |
| `cerrarModal` | function | `ninguno` | [Línea 201](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/compliance_officer_history.js#L201) |
| `filtrarDecisiones` | function | `ninguno` | [Línea 208](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/compliance_officer_history.js#L208) |
| `actualizarTabla` | function | `decisiones` | [Línea 239](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/compliance_officer_history.js#L239) |
| `limpiarFiltros` | function | `ninguno` | [Línea 284](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/compliance_officer_history.js#L284) |

### <a id="compliance_officer_reviewjs"></a>compliance_officer_review.js
**Archivo:** [Frontend/js/compliance_officer_review.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/compliance_officer_review.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `guardarDecision` | function | `decision, justificacion` | [Línea 18](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/compliance_officer_review.js#L18) |

### <a id="mainjs"></a>main.js
**Archivo:** [Frontend/js/main.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/main.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `inyectarHeaderGlobal` | function | `ninguno` | [Línea 16](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/main.js#L16) |
| `activarEventosHeader` | function | `ninguno` | [Línea 53](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/main.js#L53) |
| `actualizarInterfazUsuario` | function | `ninguno` | [Línea 80](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/main.js#L80) |
| `inicializarEventosMenu` | function | `ninguno` | [Línea 202](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/main.js#L202) |
| `resaltarEnlaceActivo` | function | `ninguno` | [Línea 213](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/main.js#L213) |
| `llenarOpcionesSelect` | function | `selectId, data, propertyValue, propertyText` | [Línea 245](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/main.js#L245) |

### <a id="sign_formjs"></a>sign_form.js
**Archivo:** [Frontend/js/sign_form.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/sign_form.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `showError` | function | `title, desc` | [Línea 67](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/sign_form.js#L67) |
| `initSignaturePad` | function | `ninguno` | [Línea 78](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/sign_form.js#L78) |
| `resizeCanvas` | function | `ninguno` | [Línea 86](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/sign_form.js#L86) |
| `getMousePos` | function | `e` | [Línea 102](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/sign_form.js#L102) |
| `startDrawing` | function | `e` | [Línea 112](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/sign_form.js#L112) |
| `draw` | function | `e` | [Línea 120](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/sign_form.js#L120) |
| `stopDrawing` | function | `e` | [Línea 130](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/sign_form.js#L130) |
| `validateForm` | function | `ninguno` | [Línea 156](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/sign_form.js#L156) |

### <a id="supplier_certificationjs"></a>supplier_certification.js
**Archivo:** [Frontend/js/supplier_certification.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_certification.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `formatSpanishDate` | function | `dateVal` | [Línea 178](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_certification.js#L178) |

### <a id="supplier_formjs"></a>supplier_form.js
**Archivo:** [Frontend/js/supplier_form.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_form.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `aplicarFiltroTexto` | function | `inputElement` | [Línea 26](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_form.js#L26) |
| `aplicarFiltroNumero` | function | `inputElement` | [Línea 43](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_form.js#L43) |
| `actualizarDeclaracionOrigenFondos` | function | `ninguno` | [Línea 97](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_form.js#L97) |
| `actualizarRenderizadoCondicional` | function | `ninguno` | [Línea 136](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_form.js#L136) |
| `syncNaturalFields` | function | `ninguno` | [Línea 184](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_form.js#L184) |
| `syncRepFields` | function | `ninguno` | [Línea 258](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_form.js#L258) |
| `irAPaso` | function | `paso` | [Línea 763](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_form.js#L763) |
| `prellenarFormularioOCR` | function | `data` | [Línea 942](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_form.js#L942) |
| `setVal` | arrow function | `id, val, readOnly = true` | [Línea 943](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_form.js#L943) |

### <a id="supplier_qualification_historyjs"></a>supplier_qualification_history.js
**Archivo:** [Frontend/js/supplier_qualification_history.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_qualification_history.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `poblarFiltroAnos` | function | `evaluaciones` | [Línea 84](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_qualification_history.js#L84) |
| `renderizarTabla` | function | `evaluaciones, califMap` | [Línea 101](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_qualification_history.js#L101) |
| `filterByYear` | function | `ninguno` | [Línea 211](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_qualification_history.js#L211) |

### <a id="supplier_upload_documentsjs"></a>supplier_upload_documents.js
**Archivo:** [Frontend/js/supplier_upload_documents.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_upload_documents.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `renderizarTablaDocumentos` | function | `documentos, documentosCargados` | [Línea 65](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_upload_documents.js#L65) |
| `enviarTodo` | function | `ninguno` | [Línea 149](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/supplier_upload_documents.js#L149) |

### <a id="user_listjs"></a>user_list.js
**Archivo:** [Frontend/js/user_list.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/user_list.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `renderizarTabla` | function | `usuarios` | [Línea 65](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/user_list.js#L65) |
| `updateReqUI` | function | `id, isValid` | [Línea 166](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/user_list.js#L166) |
| `closeEditModal` | function | `ninguno` | [Línea 224](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/user_list.js#L224) |

### <a id="user_newjs"></a>user_new.js
**Archivo:** [Frontend/js/user_new.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/user_new.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `updateReqUI` | function | `id, isValid` | [Línea 71](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/user_new.js#L71) |

### <a id="user_profilejs"></a>user_profile.js
**Archivo:** [Frontend/js/user_profile.js](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/user_profile.js)

| Función / Método | Tipo | Parámetros | Línea |
| :--- | :--- | :--- | :--- |
| `actualizarAvatarPerfil` | function | `fotoUrl, nombre` | [Línea 18](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/user_profile.js#L18) |
| `updateReqUI` | function | `id, isValid` | [Línea 133](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/js/user_profile.js#L133) |

