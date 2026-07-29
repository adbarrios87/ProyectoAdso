# Clases del Proyecto

Este documento contiene la información detallada de todas las clases Java presentes en el proyecto, organizadas por categoría.

## Índice

- [Controllers](#controllers)
  - [CalificacionController](#calificacioncontroller)
  - [CampoValidacionController](#campovalidacioncontroller)
  - [ContactoController](#contactocontroller)
  - [DepartamentoController](#departamentocontroller)
  - [DocumentosController](#documentoscontroller)
  - [DocumentosSociosProveedorController](#documentossociosproveedorcontroller)
  - [EstadoProveedorController](#estadoproveedorcontroller)
  - [EstadoUsuarioController](#estadousuariocontroller)
  - [EvaluacionProveedorController](#evaluacionproveedorcontroller)
  - [EvaluacionRiesgosController (DEPRECADA/ELIMINADA)](#evaluacionriesgoscontroller)
  - [FirmaTokenController](#firmatokencontroller)
  - [FormaDePagoController](#formadepagocontroller)
  - [HistorialUsuarioController](#historialusuariocontroller)
  - [MunicipioController](#municipiocontroller)
  - [NotificacionesController](#notificacionescontroller)
  - [OrigenDatoController](#origendatocontroller)
  - [PaisController](#paiscontroller)
  - [ProveedorContactoController](#proveedorcontactocontroller)
  - [ProveedorController](#proveedorcontroller)
  - [RepresentanteLegalController](#representantelegalcontroller)
  - [RepresentanteProveedorController](#representanteproveedorcontroller)
  - [RolesController](#rolescontroller)
  - [SociosProveedorController](#sociosproveedorcontroller)
  - [TipoDocumentoController](#tipodocumentocontroller)
  - [TipoIdentificacionController](#tipoidentificacioncontroller)
  - [TipoNotificacionController](#tiponotificacioncontroller)
  - [TipoPagoController](#tipopagocontroller)
  - [TipoPersonaController](#tipopersonacontroller)
  - [TipoTelefonoController](#tipotelefonocontroller)
  - [UbicacionController](#ubicacioncontroller)
  - [UsuariosController](#usuarioscontroller)
  - [ValidacionController](#validacioncontroller)
  - [ValidacionFinalController](#validacionfinalcontroller)
- [Services](#services)
  - [CalificacionService](#calificacionservice)
  - [CampoValidacionService](#campovalidacionservice)
  - [ContactoService](#contactoservice)
  - [DepartamentoService](#departamentoservice)
  - [DocumentosService](#documentosservice)
  - [DocumentosSociosProveedorService](#documentossociosproveedorservice)
  - [DocumentParserService](#documentparserservice)
  - [EstadoProveedorService](#estadoproveedorservice)
  - [EstadoUsuarioService](#estadousuarioservice)
  - [EvaluacionProveedorService](#evaluacionproveedorservice)
  - [EvaluacionRiesgosService (DEPRECADA/ELIMINADA)](#evaluacionriesgosservice)
  - [FirmaTokenService](#firmatokenservice)
  - [FormaDePagoService](#formadepagoservice)
  - [HistorialUsuarioService](#historialusuarioservice)
  - [MunicipioService](#municipioservice)
  - [NotificacionesService](#notificacionesservice)
  - [OrigenDatoService](#origendatoservice)
  - [PaisService](#paisservice)
  - [ProveedorContactoService](#proveedorcontactoservice)
  - [ProveedorService](#proveedorservice)
  - [RepresentanteLegalService](#representantelegalservice)
  - [RepresentanteProveedorService](#representanteproveedorservice)
  - [RolesService](#rolesservice)
  - [SociosProveedorService](#sociosproveedorservice)
  - [TipoDocumentoService](#tipodocumentoservice)
  - [TipoIdentificacionService](#tipoidentificacionservice)
  - [TipoNotificacionService](#tiponotificacionservice)
  - [TipoPagoService](#tipopagoservice)
  - [TipoPersonaService](#tipopersonaservice)
  - [TipoTelefonoService](#tipotelefonoservice)
  - [UbicacionService](#ubicacionservice)
  - [UsuariosService](#usuariosservice)
  - [ValidacionFinalService](#validacionfinalservice)
  - [ValidacionService](#validacionservice)
- [Entities](#entities)
  - [CalificacionEntity](#calificacionentity)
  - [CampoValidacionEntity](#campovalidacionentity)
  - [ContactoEntity](#contactoentity)
  - [DepartamentoEntity](#departamentoentity)
  - [DocumentosEntity](#documentosentity)
  - [DocumentosSociosProveedorEntity](#documentossociosproveedorentity)
  - [EstadoProveedorEntity](#estadoproveedorentity)
  - [EstadoUsuarioEntity](#estadousuarioentity)
  - [EvaluacionProveedorEntity](#evaluacionproveedorentity)
  - [EvaluacionRiesgosEntity (DEPRECADA/ELIMINADA)](#evaluacionriesgosentity)
  - [FirmaTokenEntity](#firmatokenentity)
  - [FormaDePagoEntity](#formadepagoentity)
  - [HistorialUsuarioEntity](#historialusuarioentity)
  - [MunicipioEntity](#municipioentity)
  - [NotificacionesEntity](#notificacionesentity)
  - [OrigenDatoEntity](#origendatoentity)
  - [PaisEntity](#paisentity)
  - [ProveedorContactoEntity](#proveedorcontactoentity)
  - [ProveedorEntity](#proveedorentity)
  - [RepresentanteLegalEntity](#representantelegalentity)
  - [RepresentanteProveedorEntity](#representanteproveedorentity)
  - [RolesEntity](#rolesentity)
  - [SociosProveedorEntity](#sociosproveedorentity)
  - [TipoDocumentoEntity](#tipodocumentoentity)
  - [TipoIdentificacionEntity](#tipoidentificacionentity)
  - [TipoNotificacionEntity](#tiponotificacionentity)
  - [TipoPagoEntity](#tipopagoentity)
  - [TipoPersonaEntity](#tipopersonaentity)
  - [TipoTelefonoEntity](#tipotelefonoentity)
  - [UbicacionEntity](#ubicacionentity)
  - [UsuariosEntity](#usuariosentity)
  - [ValidacionEntity](#validacionentity)
  - [ValidacionFinalEntity](#validacionfinalentity)
- [DTOs](#dtos)
  - [MenuDto](#menudto)
  - [CalificacionCreateRequestDto](#calificacioncreaterequestdto)
  - [CalificacionResponseDto](#calificacionresponsedto)
  - [CampoValidacionCreateRequestDto](#campovalidacioncreaterequestdto)
  - [CampoValidacionResponseDto](#campovalidacionresponsedto)
  - [ContactoCreateRequestDto](#contactocreaterequestdto)
  - [ContactoResponseDto](#contactoresponsedto)
  - [DepartamentoCreateRequestDto](#departamentocreaterequestdto)
  - [DepartamentoResponseDto](#departamentoresponsedto)
  - [DocumentosCreateRequestDto](#documentoscreaterequestdto)
  - [DocumentosResponseDto](#documentosresponsedto)
  - [DocumentosSociosProveedorCreateRequestDto](#documentossociosproveedorcreaterequestdto)
  - [DocumentosSociosProveedorResponseDto](#documentossociosproveedorresponsedto)
  - [EstadoProveedorCreateRequestDto](#estadoproveedorcreaterequestdto)
  - [EstadoProveedorResponseDto](#estadoproveedorresponsedto)
  - [EstadoUsuarioCreateRequestDto](#estadousuariocreaterequestdto)
  - [EstadoUsuarioResponseDto](#estadousuarioresponsedto)
  - [EvaluacionProveedorCreateRequestDto](#evaluacionproveedorcreaterequestdto)
  - [EvaluacionProveedorResponseDto](#evaluacionproveedorresponsedto)
  - [EvaluacionRiesgosCreateRequestDto (DEPRECADA/ELIMINADA)](#evaluacionriesgoscreaterequestdto)
  - [EvaluacionRiesgosResponseDto (DEPRECADA/ELIMINADA)](#evaluacionriesgosresponsedto)
  - [FormaDePagoCreateRequestDto](#formadepagocreaterequestdto)
  - [FormaDePagoResponseDto](#formadepagoresponsedto)
  - [GeneralResponseDto](#generalresponsedto)
  - [HistorialUsuarioCreateRequestDto](#historialusuariocreaterequestdto)
  - [HistorialUsuarioResponseDto](#historialusuarioresponsedto)
  - [LoginRequestDto](#loginrequestdto)
  - [LoginResponseDto](#loginresponsedto)
  - [MunicipioCreateRequestDto](#municipiocreaterequestdto)
  - [MunicipioResponseDto](#municipioresponsedto)
  - [NotificacionesCreateRequestDto](#notificacionescreaterequestdto)
  - [NotificacionesResponseDto](#notificacionesresponsedto)
  - [OrigenDatoCreateRequestDto](#origendatocreaterequestdto)
  - [OrigenDatoResponseDto](#origendatoresponsedto)
  - [PaisCreateRequestDto](#paiscreaterequestdto)
  - [PaisResponseDto](#paisresponsedto)
  - [ProveedorCompletoDto](#proveedorcompletodto)
  - [ProveedorContactoCreateRequestDto](#proveedorcontactocreaterequestdto)
  - [ProveedorContactoResponseDto](#proveedorcontactoresponsedto)
  - [ProveedorCreateRequestDto](#proveedorcreaterequestdto)
  - [ProveedorDetalleCompletoDto](#proveedordetallecompletodto)
  - [ProveedorPreFillDto](#proveedorprefilldto)
  - [ProveedorResponseDto](#proveedorresponsedto)
  - [RepresentanteLegalCreateRequestDto](#representantelegalcreaterequestdto)
  - [RepresentanteLegalResponseDto](#representantelegalresponsedto)
  - [RepresentanteProveedorCreateRequestDto](#representanteproveedorcreaterequestdto)
  - [RepresentanteProveedorResponseDto](#representanteproveedorresponsedto)
  - [ResponseDto](#responsedto)
  - [RolesCreateRequestDto](#rolescreaterequestdto)
  - [RolesResponseDto](#rolesresponsedto)
  - [SociosProveedorCreateRequestDto](#sociosproveedorcreaterequestdto)
  - [SociosProveedorResponseDto](#sociosproveedorresponsedto)
  - [TipoDocumentoCreateRequestDto](#tipodocumentocreaterequestdto)
  - [TipoDocumentoResponseDto](#tipodocumentoresponsedto)
  - [TipoIdentificacionCreateRequestDto](#tipoidentificacioncreaterequestdto)
  - [TipoIdentificacionResponseDto](#tipoidentificacionresponsedto)
  - [TipoNotificacionCreateRequestDto](#tiponotificacioncreaterequestdto)
  - [TipoNotificacionResponseDto](#tiponotificacionresponsedto)
  - [TipoPagoCreateRequestDto](#tipopagocreaterequestdto)
  - [TipoPagoResponseDto](#tipopagoresponsedto)
  - [TipoPersonaCreateRequestDto](#tipopersonacreaterequestdto)
  - [TipoPersonaResponseDto](#tipopersonaresponsedto)
  - [TipoTelefonoCreateRequestDto](#tipotelefonocreaterequestdto)
  - [TipoTelefonoResponseDto](#tipotelefonoresponsedto)
  - [UbicacionCreateRequestDto](#ubicacioncreaterequestdto)
  - [UbicacionResponseDto](#ubicacionresponsedto)
  - [UsuariosCreateRequestDto](#usuarioscreaterequestdto)
  - [UsuariosResponseDto](#usuariosresponsedto)
  - [ValidacionCreateRequestDto](#validacioncreaterequestdto)
  - [ValidacionFinalCreateRequestDto](#validacionfinalcreaterequestdto)
  - [ValidacionResponseDto](#validacionresponsedto)
- [Repositories](#repositories)
  - [CalificacionRepository](#calificacionrepository)
  - [CampoValidacionRepository](#campovalidacionrepository)
  - [ContactoRepository](#contactorepository)
  - [DepartamentoRepository](#departamentorepository)
  - [DocumentosRepository](#documentosrepository)
  - [DocumentosSociosProveedorRepository](#documentossociosproveedorrepository)
  - [EstadoProveedorRepository](#estadoproveedorrepository)
  - [EstadoUsuarioRepository](#estadousuariorepository)
  - [EvaluacionProveedorRepository](#evaluacionproveedorrepository)
  - [EvaluacionRiesgosRepository (DEPRECADA/ELIMINADA)](#evaluacionriesgosrepository)
  - [FirmaTokenRepository](#firmatokenrepository)
  - [FormaDePagoRepository](#formadepagorepository)
  - [HistorialUsuarioRepository](#historialusuariorepository)
  - [MunicipioRepository](#municipiorepository)
  - [NotificacionesRepository](#notificacionesrepository)
  - [OrigenDatoRepository](#origendatorepository)
  - [PaisRepository](#paisrepository)
  - [ProveedorContactoRepository](#proveedorcontactorepository)
  - [ProveedorRepository](#proveedorrepository)
  - [RepresentanteLegalRepository](#representantelegalrepository)
  - [RepresentanteProveedorRepository](#representanteproveedorrepository)
  - [RolesRepository](#rolesrepository)
  - [SociosProveedorRepository](#sociosproveedorrepository)
  - [TipoDocumentoRepository](#tipodocumentorepository)
  - [TipoIdentificacionRepository](#tipoidentificacionrepository)
  - [TipoNotificacionRepository](#tiponotificacionrepository)
  - [TipoPagoRepository](#tipopagorepository)
  - [TipoPersonaRepository](#tipopersonarepository)
  - [TipoTelefonoRepository](#tipotelefonorepository)
  - [UbicacionRepository](#ubicacionrepository)
  - [UsuariosRepository](#usuariosrepository)
  - [ValidacionFinalRepository](#validacionfinalrepository)
  - [ValidacionRepository](#validacionrepository)
- [Others/Configs](#othersconfigs)
  - [CorsConfig](#corsconfig)
  - [ProveedoresApplication](#proveedoresapplication)
  - [ProveedoresApplicationTests](#proveedoresapplicationtests)

---

## Controllers

### <a id="calificacioncontroller"></a>CalificacionController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CalificacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CalificacionController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/calificacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `CalificacionService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="campovalidacioncontroller"></a>CampoValidacionController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CampoValidacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/CampoValidacionController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/campo_validacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `CampoValidacionService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="contactocontroller"></a>ContactoController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ContactoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ContactoController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/contacto")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `ContactoService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="departamentocontroller"></a>DepartamentoController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DepartamentoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DepartamentoController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/departamento")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `DepartamentoService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="documentoscontroller"></a>DocumentosController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/documentos")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `DocumentosService` | `private` | @Autowired | [Línea 17NaN17) |

---

### <a id="documentossociosproveedorcontroller"></a>DocumentosSociosProveedorController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosSociosProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/DocumentosSociosProveedorController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/documentos_socios_proveedor")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `DocumentosSociosProveedorService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="estadoproveedorcontroller"></a>EstadoProveedorController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoProveedorController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/estado_proveedor")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `EstadoProveedorService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="estadousuariocontroller"></a>EstadoUsuarioController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoUsuarioController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EstadoUsuarioController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/estado_usuario")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `EstadoUsuarioService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="evaluacionproveedorcontroller"></a>EvaluacionProveedorController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionProveedorController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/evaluacion_proveedor")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `EvaluacionProveedorService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="evaluacionriesgoscontroller"></a>EvaluacionRiesgosController (DEPRECADA/ELIMINADA)
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionRiesgosController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/EvaluacionRiesgosController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/evaluacion_riesgos")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `EvaluacionRiesgosService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="firmatokencontroller"></a>FirmaTokenController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FirmaTokenController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FirmaTokenController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/firmas")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `FirmaTokenService` | `private` | @Autowired | [Línea 18NaN18) |

---

### <a id="formadepagocontroller"></a>FormaDePagoController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FormaDePagoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/FormaDePagoController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/forma_de_pago")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `FormaDePagoService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="historialusuariocontroller"></a>HistorialUsuarioController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/HistorialUsuarioController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/HistorialUsuarioController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/historial_usuario")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `HistorialUsuarioService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="municipiocontroller"></a>MunicipioController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/MunicipioController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/MunicipioController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/municipio")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `MunicipioService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="notificacionescontroller"></a>NotificacionesController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/NotificacionesController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/NotificacionesController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/notificaciones")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `NotificacionesService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="origendatocontroller"></a>OrigenDatoController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/OrigenDatoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/OrigenDatoController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/origen_dato")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `OrigenDatoService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="paiscontroller"></a>PaisController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/PaisController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/PaisController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/pais")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `PaisService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="proveedorcontactocontroller"></a>ProveedorContactoController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorContactoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorContactoController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/proveedor_contacto")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `ProveedorContactoService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="proveedorcontroller"></a>ProveedorController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ProveedorController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/proveedores")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `ProveedorService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="representantelegalcontroller"></a>RepresentanteLegalController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteLegalController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteLegalController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/representante_legal")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `RepresentanteLegalService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="representanteproveedorcontroller"></a>RepresentanteProveedorController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RepresentanteProveedorController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/representante_proveedor")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `RepresentanteProveedorService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="rolescontroller"></a>RolesController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RolesController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/RolesController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/roles")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `RolesService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="sociosproveedorcontroller"></a>SociosProveedorController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/SociosProveedorController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/SociosProveedorController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/socios_proveedor")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `SociosProveedorService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="tipodocumentocontroller"></a>TipoDocumentoController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoDocumentoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoDocumentoController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/tipo_documento")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `TipoDocumentoService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="tipoidentificacioncontroller"></a>TipoIdentificacionController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoIdentificacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoIdentificacionController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/tipo_identificacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `TipoIdentificacionService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="tiponotificacioncontroller"></a>TipoNotificacionController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoNotificacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoNotificacionController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/tipo_notificacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `TipoNotificacionService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="tipopagocontroller"></a>TipoPagoController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPagoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPagoController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/tipo_pago")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `TipoPagoService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="tipopersonacontroller"></a>TipoPersonaController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPersonaController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoPersonaController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/tipo_persona")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `TipoPersonaService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="tipotelefonocontroller"></a>TipoTelefonoController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoTelefonoController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/TipoTelefonoController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/tipo_telefono")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `TipoTelefonoService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="ubicacioncontroller"></a>UbicacionController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UbicacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UbicacionController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/ubicacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `UbicacionService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="usuarioscontroller"></a>UsuariosController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/UsuariosController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/usuarios")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `UsuariosService` | `private` | @Autowired | [Línea 18NaN18) |

---

### <a id="validacioncontroller"></a>ValidacionController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ValidacionController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ValidacionController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/validacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `ValidacionService` | `private` | @Autowired | [Línea 16NaN16) |

---

### <a id="validacionfinalcontroller"></a>ValidacionFinalController
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.controllers`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ValidacionFinalController.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/ValidacionFinalController.java)  

**Anotaciones de Clase:**
- `@RestController`
- `@RequestMapping("/validacion-final")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `service` | `ValidacionFinalService` | `private` | @Autowired | [Línea 15NaN15) |

---

## Services

### <a id="calificacionservice"></a>CalificacionService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CalificacionService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `CalificacionRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="campovalidacionservice"></a>CampoValidacionService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/CampoValidacionService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `CampoValidacionRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="contactoservice"></a>ContactoService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ContactoService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `ContactoRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="departamentoservice"></a>DepartamentoService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DepartamentoService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `DepartamentoRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="documentosservice"></a>DocumentosService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `DocumentosRepository` | `private` | @Autowired | [Línea 18NaN18) |
| `proveedorRepository` | `ProveedorRepository` | `private` | @Autowired | [Línea 21NaN21) |

---

### <a id="documentossociosproveedorservice"></a>DocumentosSociosProveedorService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentosSociosProveedorService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `DocumentosSociosProveedorRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="documentparserservice"></a>DocumentParserService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentParserService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/DocumentParserService.java)  

**Anotaciones de Clase:**
- `@Service`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="estadoproveedorservice"></a>EstadoProveedorService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoProveedorService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `EstadoProveedorRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="estadousuarioservice"></a>EstadoUsuarioService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EstadoUsuarioService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `EstadoUsuarioRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="evaluacionproveedorservice"></a>EvaluacionProveedorService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionProveedorService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `EvaluacionProveedorRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="evaluacionriesgosservice"></a>EvaluacionRiesgosService (DEPRECADA/ELIMINADA)
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/EvaluacionRiesgosService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `EvaluacionRiesgosRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="firmatokenservice"></a>FirmaTokenService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FirmaTokenService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FirmaTokenService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `FirmaTokenRepository` | `private` | @Autowired | [Línea 19NaN19) |
| `proveedorRepository` | `ProveedorRepository` | `private` | @Autowired | [Línea 22NaN22) |

---

### <a id="formadepagoservice"></a>FormaDePagoService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/FormaDePagoService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `FormaDePagoRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="historialusuarioservice"></a>HistorialUsuarioService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/HistorialUsuarioService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `HistorialUsuarioRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="municipioservice"></a>MunicipioService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/MunicipioService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `MunicipioRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="notificacionesservice"></a>NotificacionesService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/NotificacionesService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `NotificacionesRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="origendatoservice"></a>OrigenDatoService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/OrigenDatoService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `OrigenDatoRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="paisservice"></a>PaisService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/PaisService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `PaisRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="proveedorcontactoservice"></a>ProveedorContactoService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorContactoService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `ProveedorContactoRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="proveedorservice"></a>ProveedorService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ProveedorService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `ProveedorRepository` | `private` | @Autowired | [Línea 15NaN15) |
| `ubicacionRepository` | `UbicacionRepository` | `private` | @Autowired | [Línea 18NaN18) |
| `formaDePagoRepository` | `FormaDePagoRepository` | `private` | @Autowired | [Línea 21NaN21) |
| `contactoRepository` | `ContactoRepository` | `private` | @Autowired | [Línea 24NaN24) |
| `proveedorContactoRepository` | `ProveedorContactoRepository` | `private` | @Autowired | [Línea 27NaN27) |
| `representanteLegalRepository` | `RepresentanteLegalRepository` | `private` | @Autowired | [Línea 30NaN30) |
| `representanteProveedorRepository` | `RepresentanteProveedorRepository` | `private` | @Autowired | [Línea 33NaN33) |
| `sociosProveedorRepository` | `SociosProveedorRepository` | `private` | @Autowired | [Línea 36NaN36) |
| `documentosRepository` | `DocumentosRepository` | `private` | @Autowired | [Línea 39NaN39) |
| `evaluacionProveedorRepository` | `EvaluacionProveedorRepository` | `private` | @Autowired | [Línea 42NaN42) |
| `validacionRepository` | `ValidacionRepository` | `private` | @Autowired | [Línea 45NaN45) |

---

### <a id="representantelegalservice"></a>RepresentanteLegalService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteLegalService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `RepresentanteLegalRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="representanteproveedorservice"></a>RepresentanteProveedorService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RepresentanteProveedorService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `RepresentanteProveedorRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="rolesservice"></a>RolesService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/RolesService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `RolesRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="sociosproveedorservice"></a>SociosProveedorService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/SociosProveedorService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `SociosProveedorRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="tipodocumentoservice"></a>TipoDocumentoService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoDocumentoService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `TipoDocumentoRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="tipoidentificacionservice"></a>TipoIdentificacionService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoIdentificacionService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `TipoIdentificacionRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="tiponotificacionservice"></a>TipoNotificacionService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoNotificacionService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `TipoNotificacionRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="tipopagoservice"></a>TipoPagoService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPagoService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `TipoPagoRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="tipopersonaservice"></a>TipoPersonaService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoPersonaService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `TipoPersonaRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="tipotelefonoservice"></a>TipoTelefonoService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/TipoTelefonoService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `TipoTelefonoRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="ubicacionservice"></a>UbicacionService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UbicacionService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `UbicacionRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

### <a id="usuariosservice"></a>UsuariosService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/UsuariosService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `UsuariosRepository` | `private` | @Autowired | [Línea 16NaN16) |
| `notificacionesRepository` | `NotificacionesRepository` | `private` | @Autowired | [Línea 19NaN19) |
| `proveedorRepository` | `ProveedorRepository` | `private` | @Autowired | [Línea 22NaN22) |

---

### <a id="validacionfinalservice"></a>ValidacionFinalService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionFinalService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionFinalService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `ValidacionFinalRepository` | `private` | @Autowired | [Línea 23NaN23) |
| `validacionRepository` | `ValidacionRepository` | `private` | @Autowired | [Línea 26NaN26) |
| `proveedorRepository` | `ProveedorRepository` | `private` | @Autowired | [Línea 29NaN29) |

---

### <a id="validacionservice"></a>ValidacionService
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.services`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/services/ValidacionService.java)  

**Anotaciones de Clase:**
- `@Service`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `repository` | `ValidacionRepository` | `private` | @Autowired | [Línea 15NaN15) |

---

## Entities

### <a id="calificacionentity"></a>CalificacionEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/CalificacionEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/CalificacionEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "calificacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idCalificacion` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_calificacion") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 26NaN26) |

---

### <a id="campovalidacionentity"></a>CampoValidacionEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/CampoValidacionEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/CampoValidacionEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "campo_validacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idCampoValidacion` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_campo_validacion") | [Línea 17NaN17) |
| `idTipoDocumento` | `Integer` | `private` | @Column(name = "id_tipo_documento") | [Línea 20NaN20) |
| `campo` | `String` | `private` | @Column(name = "campo") | [Línea 23NaN23) |
| `obligatorio` | `Boolean` | `private` | @Column(name = "obligatorio") | [Línea 26NaN26) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 29NaN29) |

---

### <a id="contactoentity"></a>ContactoEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/ContactoEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/ContactoEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "contacto")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idContacto` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_contacto") | [Línea 17NaN17) |
| `nombreContacto` | `String` | `private` | @Column(name = "nombre_contacto") | [Línea 20NaN20) |
| `cargoContacto` | `String` | `private` | @Column(name = "cargo_contacto") | [Línea 23NaN23) |
| `idTipoTelefono` | `Integer` | `private` | @Column(name = "id_tipo_telefono") | [Línea 26NaN26) |
| `telefonoContacto` | `String` | `private` | @Column(name = "telefono_contacto") | [Línea 29NaN29) |
| `correoContacto` | `String` | `private` | @Column(name = "correo_contacto") | [Línea 32NaN32) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 38NaN38) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 44NaN44) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 47NaN47) |

---

### <a id="departamentoentity"></a>DepartamentoEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/DepartamentoEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/DepartamentoEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "departamento")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idDepartamento` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_departamento") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `nombre` | `String` | `private` | @Column(name = "nombre") | [Línea 23NaN23) |
| `idPais` | `Integer` | `private` | @Column(name = "id_pais") | [Línea 26NaN26) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 29NaN29) |

---

### <a id="documentosentity"></a>DocumentosEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/DocumentosEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/DocumentosEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "documentos")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idDocumento` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_documento") | [Línea 17NaN17) |
| `idProveedor` | `Integer` | `private` | @Column(name = "id_proveedor") | [Línea 20NaN20) |
| `idTipoDocumento` | `Integer` | `private` | @Column(name = "id_tipo_documento") | [Línea 23NaN23) |
| `numeroDocumento` | `String` | `private` | @Column(name = "numero_documento") | [Línea 26NaN26) |
| `urlDocumento` | `String` | `private` | @Column(name = "url_documento") | [Línea 32NaN32) |
| `almacenamiento` | `String` | `private` | @Column(name = "almacenamiento") | [Línea 35NaN35) |
| `archivoBlob` | `byte[]` | `private` | @Column(name = "archivo_blob") | [Línea 38NaN38) |
| `hash` | `String` | `private` | @Column(name = "hash") | [Línea 41NaN41) |
| `tamanoBytes` | `Long` | `private` | @Column(name = "tamano_bytes") | [Línea 44NaN44) |
| `estadoDocumento` | `Boolean` | `private` | @Column(name = "estado_documento") | [Línea 50NaN50) |
| `validado` | `Boolean` | `private` | @Column(name = "validado") | [Línea 53NaN53) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 59NaN59) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 65NaN65) |

---

### <a id="documentossociosproveedorentity"></a>DocumentosSociosProveedorEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/DocumentosSociosProveedorEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/DocumentosSociosProveedorEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "documentos_socios_proveedor")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idDocumentoSocioProveedor` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_documento_socio_proveedor") | [Línea 17NaN17) |
| `idSocioProveedor` | `Integer` | `private` | @Column(name = "id_socio_proveedor") | [Línea 20NaN20) |
| `idDocumento` | `Integer` | `private` | @Column(name = "id_documento") | [Línea 23NaN23) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 29NaN29) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 35NaN35) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 38NaN38) |

---

### <a id="estadoproveedorentity"></a>EstadoProveedorEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/EstadoProveedorEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/EstadoProveedorEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "estado_proveedor")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idEstadoProveedor` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_estado_proveedor") | [Línea 17NaN17) |
| `estado` | `String` | `private` | @Column(name = "estado") | [Línea 20NaN20) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 26NaN26) |

---

### <a id="estadousuarioentity"></a>EstadoUsuarioEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/EstadoUsuarioEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/EstadoUsuarioEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "estado_usuario")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idEstadoUsuario` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_estado_usuario") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 26NaN26) |

---

### <a id="evaluacionproveedorentity"></a>EvaluacionProveedorEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/EvaluacionProveedorEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/EvaluacionProveedorEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "evaluacion_proveedor")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idEvaluacion` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_evaluacion") | [Línea 17NaN17) |
| `idProveedor` | `Integer` | `private` | @Column(name = "id_proveedor") | [Línea 20NaN20) |
| `idUsuario` | `Integer` | `private` | @Column(name = "id_usuario") | [Línea 23NaN23) |
| `idCalificacion` | `Integer` | `private` | @Column(name = "id_calificacion") | [Línea 26NaN26) |
| `puntaje` | `Integer` | `private` | @Column(name = "puntaje") | [Línea 29NaN29) |
| `observaciones` | `String` | `private` | @Column(name = "observaciones") | [Línea 32NaN32) |
| `urlCalificacion` | `String` | `private` | @Column(name = "url_calificacion") | [Línea 35NaN35) |
| `calidad` | `Integer` | `private` | @Column(name = "calidad") | [Línea 38NaN38) |
| `obsCalidad` | `String` | `private` | @Column(name = "obs_calidad") | [Línea 41NaN41) |
| `tiempo` | `Integer` | `private` | @Column(name = "tiempo") | [Línea 44NaN44) |
| `obsTiempo` | `String` | `private` | @Column(name = "obs_tiempo") | [Línea 47NaN47) |
| `documenta` | `Integer` | `private` | @Column(name = "documenta") | [Línea 50NaN50) |
| `obsDocumental` | `String` | `private` | @Column(name = "obs_documental") | [Línea 53NaN53) |
| `sarlaft` | `Integer` | `private` | @Column(name = "sarlaft") | [Línea 56NaN56) |
| `obsSarlaft` | `String` | `private` | @Column(name = "obs_sarlaft") | [Línea 59NaN59) |
| `comercial` | `Integer` | `private` | @Column(name = "comercial") | [Línea 62NaN62) |
| `obsComercial` | `String` | `private` | @Column(name = "obs_comercial") | [Línea 65NaN65) |
| `social` | `Integer` | `private` | @Column(name = "social") | [Línea 68NaN68) |
| `obsSocial` | `String` | `private` | @Column(name = "obs_social") | [Línea 71NaN71) |
| `mejora` | `Integer` | `private` | @Column(name = "mejora") | [Línea 74NaN74) |
| `obsMejora` | `String` | `private` | @Column(name = "obs_mejora") | [Línea 77NaN77) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 83NaN83) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 89NaN89) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 92NaN92) |

---

### <a id="evaluacionriesgosentity"></a>EvaluacionRiesgosEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/EvaluacionRiesgosEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/EvaluacionRiesgosEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "evaluacion_riesgos")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idEvaluacionRiesgos` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_evaluacion_riesgos") | [Línea 17NaN17) |
| `idUsuario` | `Integer` | `private` | @Column(name = "id_usuario") | [Línea 20NaN20) |
| `idProveedor` | `Integer` | `private` | @Column(name = "id_proveedor") | [Línea 23NaN23) |
| `idValidacion` | `Integer` | `private` | @Column(name = "id_validacion") | [Línea 26NaN26) |
| `validacionAuditoria` | `Boolean` | `private` | @Column(name = "validacion_auditoria") | [Línea 29NaN29) |
| `comentariosAuditoria` | `String` | `private` | @Column(name = "comentarios_auditoria") | [Línea 35NaN35) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 41NaN41) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 47NaN47) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 50NaN50) |

---

### <a id="firmatokenentity"></a>FirmaTokenEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/FirmaTokenEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/FirmaTokenEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "firma_token")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idToken` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_token") | [Línea 18NaN18) |
| `idProveedor` | `Integer` | `private` | @Column(name = "id_proveedor", nullable = false) | [Línea 21NaN21) |
| `token` | `String` | `private` | @Column(name = "token", nullable = false, unique = true) | [Línea 24NaN24) |
| `fechaExpiracion` | `LocalDateTime` | `private` | @Column(name = "fecha_expiracion", nullable = false) | [Línea 27NaN27) |
| `utilizado` | `Boolean` | `private` | @Column(name = "utilizado", nullable = false) | [Línea 30NaN30) |
| `fechaFirmado` | `LocalDateTime` | `private` | @Column(name = "fecha_firmado") | [Línea 33NaN33) |
| `ipFirma` | `String` | `private` | @Column(name = "ip_firma") | [Línea 36NaN36) |

---

### <a id="formadepagoentity"></a>FormaDePagoEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/FormaDePagoEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/FormaDePagoEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "forma_de_pago")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idFormaPago` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_forma_pago") | [Línea 17NaN17) |
| `idProveedor` | `Integer` | `private` | @Column(name = "id_proveedor") | [Línea 20NaN20) |
| `idTipoPago` | `Integer` | `private` | @Column(name = "id_tipo_pago") | [Línea 23NaN23) |
| `plazo` | `Integer` | `private` | @Column(name = "plazo") | [Línea 29NaN29) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 35NaN35) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 41NaN41) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 44NaN44) |

---

### <a id="historialusuarioentity"></a>HistorialUsuarioEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/HistorialUsuarioEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/HistorialUsuarioEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "historial_usuario")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idHistorialUsuario` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_historial_usuario") | [Línea 17NaN17) |
| `idUsuario` | `Integer` | `private` | @Column(name = "id_usuario") | [Línea 20NaN20) |
| `idEstadoUsuario` | `Integer` | `private` | @Column(name = "id_estado_usuario") | [Línea 23NaN23) |
| `comentarios` | `String` | `private` | @Column(name = "comentarios") | [Línea 26NaN26) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 32NaN32) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 38NaN38) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 41NaN41) |

---

### <a id="municipioentity"></a>MunicipioEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/MunicipioEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/MunicipioEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "municipio")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idMunicipio` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_municipio") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `nombre` | `String` | `private` | @Column(name = "nombre") | [Línea 23NaN23) |
| `idDepartamento` | `Integer` | `private` | @Column(name = "id_departamento") | [Línea 26NaN26) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 29NaN29) |

---

### <a id="notificacionesentity"></a>NotificacionesEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/NotificacionesEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/NotificacionesEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "notificaciones")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idNotificacion` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_notificacion") | [Línea 17NaN17) |
| `idUsuario` | `Integer` | `private` | @Column(name = "id_usuario") | [Línea 20NaN20) |
| `idTipoNotificacion` | `Integer` | `private` | @Column(name = "id_tipo_notificacion") | [Línea 23NaN23) |
| `mensaje` | `String` | `private` | @Column(name = "mensaje") | [Línea 29NaN29) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 35NaN35) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 41NaN41) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 44NaN44) |

---

### <a id="origendatoentity"></a>OrigenDatoEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/OrigenDatoEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/OrigenDatoEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "origen_dato")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idOrigen` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_origen") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 26NaN26) |

---

### <a id="paisentity"></a>PaisEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/PaisEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/PaisEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "pais")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idPais` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_pais") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `nombre` | `String` | `private` | @Column(name = "nombre") | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 26NaN26) |

---

### <a id="proveedorcontactoentity"></a>ProveedorContactoEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/ProveedorContactoEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/ProveedorContactoEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "proveedor_contacto")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idProveedorContacto` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_proveedor_contacto") | [Línea 17NaN17) |
| `idProveedor` | `Integer` | `private` | @Column(name = "id_proveedor") | [Línea 20NaN20) |
| `idContacto` | `Integer` | `private` | @Column(name = "id_contacto") | [Línea 23NaN23) |
| `estadoContacto` | `Boolean` | `private` | @Column(name = "estado_contacto") | [Línea 26NaN26) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 32NaN32) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 38NaN38) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 41NaN41) |

---

### <a id="proveedorentity"></a>ProveedorEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/ProveedorEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/ProveedorEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "proveedor")`
- `@SQLDelete(sql = "UPDATE proveedor SET activo = false, fecha_modificado = NOW() WHERE id_proveedor = ?")`
- `@SQLRestriction("activo = true")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idProveedor` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_proveedor") | [Línea 21NaN21) |
| `idUsuario` | `Integer` | `private` | @Column(name = "id_usuario") | [Línea 24NaN24) |
| `idTipoIdentificacion` | `Integer` | `private` | @Column(name = "id_tipo_identificacion") | [Línea 27NaN27) |
| `numeroIdentificacion` | `String` | `private` | @Column(name = "numero_identificacion") | [Línea 30NaN30) |
| `digitoVerificacion` | `String` | `private` | @Column(name = "digito_verificacion") | [Línea 33NaN33) |
| `razonSocial` | `String` | `private` | @Column(name = "razon_social") | [Línea 36NaN36) |
| `nombres` | `String` | `private` | @Column(name = "nombres") | [Línea 39NaN39) |
| `apellidos` | `String` | `private` | @Column(name = "apellidos") | [Línea 42NaN42) |
| `idTipoPersona` | `Integer` | `private` | @Column(name = "id_tipo_persona") | [Línea 45NaN45) |
| `telefonoPrincipal` | `String` | `private` | @Column(name = "telefono_principal") | [Línea 48NaN48) |
| `idTipoTelefono` | `Integer` | `private` | @Column(name = "id_tipo_telefono") | [Línea 51NaN51) |
| `correoPrincipal` | `String` | `private` | @Column(name = "correo_principal") | [Línea 54NaN54) |
| `idActualizacionProveedor` | `Integer` | `private` | @Column(name = "id_actualizacion_proveedor") | [Línea 57NaN57) |
| `requiereActualizacion` | `Boolean` | `private` | @Column(name = "requiere_actualizacion") | [Línea 60NaN60) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 63NaN63) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 69NaN69) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 75NaN75) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 78NaN78) |
| `idEstadoProveedor` | `Integer` | `private` | @Column(name = "id_estado_proveedor") | [Línea 81NaN81) |
| `ciiu` | `String` | `private` | @Column(name = "ciiu") | [Línea 87NaN87) |
| `paginaWeb` | `String` | `private` | @Column(name = "pagina_web") | [Línea 90NaN90) |
| `bancoReferencia` | `String` | `private` | @Column(name = "banco_referencia") | [Línea 93NaN93) |
| `laftP1` | `Boolean` | `private` | @Column(name = "laft_p1") | [Línea 111NaN111) |
| `laftP2` | `Boolean` | `private` | @Column(name = "laft_p2") | [Línea 114NaN114) |
| `laftP3` | `Boolean` | `private` | @Column(name = "laft_p3") | [Línea 117NaN117) |
| `laftP4` | `Boolean` | `private` | @Column(name = "laft_p4") | [Línea 120NaN120) |
| `laftP5` | `Boolean` | `private` | @Column(name = "laft_p5") | [Línea 123NaN123) |
| `tipoCuenta` | `String` | `private` | @Column(name = "tipo_cuenta") | [Línea 126NaN126) |
| `numCuenta` | `String` | `private` | @Column(name = "num_cuenta") | [Línea 129NaN129) |

---

### <a id="representantelegalentity"></a>RepresentanteLegalEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/RepresentanteLegalEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/RepresentanteLegalEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "representante_legal")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idRepresentanteLegal` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_representante_legal") | [Línea 17NaN17) |
| `idTipoIdentificacion` | `Integer` | `private` | @Column(name = "id_tipo_identificacion") | [Línea 20NaN20) |
| `idDocumento` | `Integer` | `private` | @Column(name = "id_documento") | [Línea 23NaN23) |
| `numeroIdentificacion` | `String` | `private` | @Column(name = "numero_identificacion") | [Línea 26NaN26) |
| `nombres` | `String` | `private` | @Column(name = "nombres") | [Línea 29NaN29) |
| `apellidos` | `String` | `private` | @Column(name = "apellidos") | [Línea 32NaN32) |
| `idTipoTelefono` | `Integer` | `private` | @Column(name = "id_tipo_telefono") | [Línea 35NaN35) |
| `telefono` | `String` | `private` | @Column(name = "telefono") | [Línea 38NaN38) |
| `correo` | `String` | `private` | @Column(name = "correo") | [Línea 41NaN41) |
| `idUsuario` | `Integer` | `private` | @Column(name = "id_usuario") | [Línea 44NaN44) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 50NaN50) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 56NaN56) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 59NaN59) |
| `nacionalidad` | `String` | `private` | @Column(name = "nacionalidad") | [Línea 62NaN62) |

---

### <a id="representanteproveedorentity"></a>RepresentanteProveedorEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/RepresentanteProveedorEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/RepresentanteProveedorEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "representante_proveedor")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idRelacion` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_relacion") | [Línea 17NaN17) |
| `idRepresentanteLegal` | `Integer` | `private` | @Column(name = "id_representante_legal") | [Línea 20NaN20) |
| `idProveedor` | `Integer` | `private` | @Column(name = "id_proveedor") | [Línea 23NaN23) |
| `cargo` | `String` | `private` | @Column(name = "cargo") | [Línea 26NaN26) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 38NaN38) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 44NaN44) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 47NaN47) |

---

### <a id="rolesentity"></a>RolesEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/RolesEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/RolesEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "roles")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idRol` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_rol") | [Línea 17NaN17) |
| `rol` | `String` | `private` | @Column(name = "rol") | [Línea 20NaN20) |
| `estadoRol` | `Boolean` | `private` | @Column(name = "estado_rol") | [Línea 23NaN23) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 26NaN26) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 32NaN32) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 38NaN38) |

---

### <a id="sociosproveedorentity"></a>SociosProveedorEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/SociosProveedorEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/SociosProveedorEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "socios_proveedor")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idSocioProveedor` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_socio_proveedor") | [Línea 17NaN17) |
| `idProveedor` | `Integer` | `private` | @Column(name = "id_proveedor") | [Línea 20NaN20) |
| `nombres` | `String` | `private` | @Column(name = "nombres") | [Línea 23NaN23) |
| `apellidos` | `String` | `private` | @Column(name = "apellidos") | [Línea 26NaN26) |
| `idTipoIdentificacion` | `Integer` | `private` | @Column(name = "id_tipo_identificacion") | [Línea 29NaN29) |
| `numeroIdentificacion` | `String` | `private` | @Column(name = "numero_identificacion") | [Línea 32NaN32) |
| `consulta` | `Integer` | `private` | @Column(name = "consulta") | [Línea 35NaN35) |
| `resultadoConsulta` | `Boolean` | `private` | @Column(name = "resultado_consulta") | [Línea 41NaN41) |
| `idOrigen` | `Integer` | `private` | @Column(name = "id_origen") | [Línea 44NaN44) |
| `validado` | `Boolean` | `private` | @Column(name = "validado") | [Línea 50NaN50) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 56NaN56) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 62NaN62) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 65NaN65) |
| `idTipoPersona` | `Integer` | `private` | @Column(name = "id_tipo_persona") | [Línea 68NaN68) |
| `nacionalidad` | `String` | `private` | @Column(name = "nacionalidad") | [Línea 71NaN71) |

---

### <a id="tipodocumentoentity"></a>TipoDocumentoEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoDocumentoEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoDocumentoEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "tipo_documento")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoDocumento` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_tipo_documento") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 26NaN26) |

---

### <a id="tipoidentificacionentity"></a>TipoIdentificacionEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoIdentificacionEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoIdentificacionEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "tipo_identificacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoIdentificacion` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_tipo_identificacion") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 26NaN26) |

---

### <a id="tiponotificacionentity"></a>TipoNotificacionEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoNotificacionEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoNotificacionEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "tipo_notificacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoNotificacion` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_tipo_notificacion") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 26NaN26) |

---

### <a id="tipopagoentity"></a>TipoPagoEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoPagoEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoPagoEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "tipo_pago")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoPago` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_tipo_pago") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 26NaN26) |

---

### <a id="tipopersonaentity"></a>TipoPersonaEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoPersonaEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoPersonaEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "tipo_persona")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoPersona` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_tipo_persona") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 26NaN26) |

---

### <a id="tipotelefonoentity"></a>TipoTelefonoEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoTelefonoEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/TipoTelefonoEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "tipo_telefono")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoTelefono` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_tipo_telefono") | [Línea 17NaN17) |
| `codigo` | `String` | `private` | @Column(name = "codigo") | [Línea 20NaN20) |
| `descripcion` | `String` | `private` | @Column(name = "descripcion") | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 26NaN26) |

---

### <a id="ubicacionentity"></a>UbicacionEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/UbicacionEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/UbicacionEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "ubicacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idUbicacion` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_ubicacion") | [Línea 17NaN17) |
| `idProveedor` | `Integer` | `private` | @Column(name = "id_proveedor") | [Línea 20NaN20) |
| `idMunicipio` | `Integer` | `private` | @Column(name = "id_municipio") | [Línea 23NaN23) |
| `direccion` | `String` | `private` | @Column(name = "direccion") | [Línea 26NaN26) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 32NaN32) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 38NaN38) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 41NaN41) |

---

### <a id="usuariosentity"></a>UsuariosEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/UsuariosEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/UsuariosEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "usuarios")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idUsuario` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_usuario") | [Línea 17NaN17) |
| `nombreUsuario` | `String` | `private` | @Column(name = "nombre_usuario") | [Línea 20NaN20) |
| `cargoUsuario` | `String` | `private` | @Column(name = "cargo_usuario") | [Línea 23NaN23) |
| `correoUsuario` | `String` | `private` | @Column(name = "correo_usuario") | [Línea 26NaN26) |
| `contrasena` | `String` | `private` | @Column(name = "contrasena") | [Línea 29NaN29) |
| `idRol` | `Integer` | `private` | @Column(name = "id_rol") | [Línea 32NaN32) |
| `roles` | `RolesEntity` | `private` | @ManyToOne(fetch = FetchType.EAGER)<br>@JoinColumn(name = "id_rol", insertable = false, updatable = false) | [Línea 36NaN36) |
| `fotoUrl` | `String` | `private` | @Column(name = "foto_url", columnDefinition = "LONGTEXT") | [Línea 39NaN39) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 45NaN45) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 51NaN51) |
| `estadoUsuario` | `Boolean` | `private` | @Column(name = "estado_usuario") | [Línea 57NaN57) |
| `notifStatus` | `Boolean` | `private` | @Column(name = "notif_status") | [Línea 61NaN61) |
| `notifDocs` | `Boolean` | `private` | @Column(name = "notif_docs") | [Línea 64NaN64) |
| `notifExpiry` | `Boolean` | `private` | @Column(name = "notif_expiry") | [Línea 67NaN67) |
| `notifNews` | `Boolean` | `private` | @Column(name = "notif_news") | [Línea 70NaN70) |

---

### <a id="validacionentity"></a>ValidacionEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/ValidacionEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/ValidacionEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "validacion")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idValidacion` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_validacion") | [Línea 17NaN17) |
| `idUsuario` | `Integer` | `private` | @Column(name = "id_usuario") | [Línea 20NaN20) |
| `idProveedor` | `Integer` | `private` | @Column(name = "id_proveedor") | [Línea 23NaN23) |
| `idCampoValidacion` | `Integer` | `private` | @Column(name = "id_campo_validacion") | [Línea 26NaN26) |
| `valorWeb` | `String` | `private` | @Column(name = "valor_web") | [Línea 29NaN29) |
| `valorDocumento` | `String` | `private` | @Column(name = "valor_documento") | [Línea 32NaN32) |
| `idDocumento` | `Integer` | `private` | @Column(name = "id_documento") | [Línea 35NaN35) |
| `resultadoValidacion` | `Boolean` | `private` | @Column(name = "resultado_validacion") | [Línea 38NaN38) |
| `comentarios` | `String` | `private` | @Column(name = "comentarios") | [Línea 44NaN44) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 50NaN50) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 56NaN56) |
| `idValidacionFinal` | `Integer` | `private` | @Column(name = "id_validacion_final") | [Línea 59NaN59) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 62NaN62) |

---

### <a id="validacionfinalentity"></a>ValidacionFinalEntity
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.entites`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/ValidacionFinalEntity.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/entites/ValidacionFinalEntity.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@Entity`
- `@Table(name = "validacion_final")`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idValidacionFinal` | `Integer` | `private` | @Id<br>@GeneratedValue(strategy = GenerationType.IDENTITY)<br>@Column(name = "id_validacion_final") | [Línea 19NaN19) |
| `idProveedor` | `Integer` | `private` | @Column(name = "id_proveedor") | [Línea 22NaN22) |
| `comentarioFinal` | `String` | `private` | @Column(name = "comentario_final") | [Línea 25NaN25) |
| `estadoValidacion` | `String` | `private` | @Column(name = "estado_validacion") | [Línea 28NaN28) |
| `creadoPor` | `Integer` | `private` | @Column(name = "creado_por") | [Línea 31NaN31) |
| `fechaCreado` | `LocalDateTime` | `private` | @Column(name = "fecha_creado") | [Línea 34NaN34) |
| `modificadoPor` | `Integer` | `private` | @Column(name = "modificado_por") | [Línea 37NaN37) |
| `fechaModificado` | `LocalDateTime` | `private` | @Column(name = "fecha_modificado") | [Línea 40NaN40) |
| `activo` | `Boolean` | `private` | @Column(name = "activo") | [Línea 43NaN43) |
| `validaciones` | `Set<ValidacionEntity>` | `private` | @OneToMany(fetch = FetchType.LAZY)<br>@JoinColumn(name = "id_validacion_final") | [Línea 47NaN47) |

---

## DTOs

### <a id="menudto"></a>MenuDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dto`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dto/MenuDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dto/MenuDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `titulo` | `String` | `private` | - | [Línea 15NaN15) |
| `url` | `String` | `private` | - | [Línea 16NaN16) |
| `icono` | `String` | `private` | - | [Línea 17NaN17) |
| `submenus` | `List<MenuDto>` | `private` | - | [Línea 18NaN18) |

---

### <a id="calificacioncreaterequestdto"></a>CalificacionCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/CalificacionCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/CalificacionCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `descripcion` | `String` | `private` | - | [Línea 11NaN11) |
| `activo` | `Boolean` | `private` | - | [Línea 12NaN12) |

---

### <a id="calificacionresponsedto"></a>CalificacionResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/CalificacionResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/CalificacionResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idCalificacion` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `descripcion` | `String` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="campovalidacioncreaterequestdto"></a>CampoValidacionCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/CampoValidacionCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/CampoValidacionCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoDocumento` | `Integer` | `private` | - | [Línea 10NaN10) |
| `campo` | `String` | `private` | - | [Línea 11NaN11) |
| `obligatorio` | `Boolean` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="campovalidacionresponsedto"></a>CampoValidacionResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/CampoValidacionResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/CampoValidacionResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idCampoValidacion` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idTipoDocumento` | `Integer` | `private` | - | [Línea 11NaN11) |
| `campo` | `String` | `private` | - | [Línea 12NaN12) |
| `obligatorio` | `Boolean` | `private` | - | [Línea 13NaN13) |
| `activo` | `Boolean` | `private` | - | [Línea 14NaN14) |

---

### <a id="contactocreaterequestdto"></a>ContactoCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ContactoCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ContactoCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `nombreContacto` | `String` | `private` | - | [Línea 10NaN10) |
| `cargoContacto` | `String` | `private` | - | [Línea 11NaN11) |
| `idTipoTelefono` | `Integer` | `private` | - | [Línea 12NaN12) |
| `telefonoContacto` | `String` | `private` | - | [Línea 13NaN13) |
| `correoContacto` | `String` | `private` | - | [Línea 14NaN14) |
| `creadoPor` | `Integer` | `private` | - | [Línea 16NaN16) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 18NaN18) |
| `activo` | `Boolean` | `private` | - | [Línea 19NaN19) |

---

### <a id="contactoresponsedto"></a>ContactoResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ContactoResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ContactoResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idContacto` | `Integer` | `private` | - | [Línea 10NaN10) |
| `nombreContacto` | `String` | `private` | - | [Línea 11NaN11) |
| `cargoContacto` | `String` | `private` | - | [Línea 12NaN12) |
| `idTipoTelefono` | `Integer` | `private` | - | [Línea 13NaN13) |
| `telefonoContacto` | `String` | `private` | - | [Línea 14NaN14) |
| `correoContacto` | `String` | `private` | - | [Línea 15NaN15) |
| `creadoPor` | `Integer` | `private` | - | [Línea 17NaN17) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 19NaN19) |
| `activo` | `Boolean` | `private` | - | [Línea 20NaN20) |

---

### <a id="departamentocreaterequestdto"></a>DepartamentoCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DepartamentoCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DepartamentoCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `nombre` | `String` | `private` | - | [Línea 11NaN11) |
| `idPais` | `Integer` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="departamentoresponsedto"></a>DepartamentoResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DepartamentoResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DepartamentoResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idDepartamento` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `nombre` | `String` | `private` | - | [Línea 12NaN12) |
| `idPais` | `Integer` | `private` | - | [Línea 13NaN13) |
| `activo` | `Boolean` | `private` | - | [Línea 14NaN14) |

---

### <a id="documentoscreaterequestdto"></a>DocumentosCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DocumentosCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DocumentosCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idTipoDocumento` | `Integer` | `private` | - | [Línea 11NaN11) |
| `numeroDocumento` | `String` | `private` | - | [Línea 12NaN12) |
| `urlDocumento` | `String` | `private` | - | [Línea 14NaN14) |
| `almacenamiento` | `String` | `private` | - | [Línea 15NaN15) |
| `archivoBlob` | `byte[]` | `private` | - | [Línea 16NaN16) |
| `hash` | `String` | `private` | - | [Línea 17NaN17) |
| `tamanoBytes` | `Long` | `private` | - | [Línea 18NaN18) |
| `estadoDocumento` | `Boolean` | `private` | - | [Línea 20NaN20) |
| `validado` | `Boolean` | `private` | - | [Línea 21NaN21) |
| `creadoPor` | `Integer` | `private` | - | [Línea 23NaN23) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 25NaN25) |

---

### <a id="documentosresponsedto"></a>DocumentosResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DocumentosResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DocumentosResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idDocumento` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idProveedor` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idTipoDocumento` | `Integer` | `private` | - | [Línea 12NaN12) |
| `numeroDocumento` | `String` | `private` | - | [Línea 13NaN13) |
| `urlDocumento` | `String` | `private` | - | [Línea 15NaN15) |
| `almacenamiento` | `String` | `private` | - | [Línea 16NaN16) |
| `archivoBlob` | `byte[]` | `private` | - | [Línea 17NaN17) |
| `hash` | `String` | `private` | - | [Línea 18NaN18) |
| `tamanoBytes` | `Long` | `private` | - | [Línea 19NaN19) |
| `estadoDocumento` | `Boolean` | `private` | - | [Línea 21NaN21) |
| `validado` | `Boolean` | `private` | - | [Línea 22NaN22) |
| `creadoPor` | `Integer` | `private` | - | [Línea 24NaN24) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 26NaN26) |

---

### <a id="documentossociosproveedorcreaterequestdto"></a>DocumentosSociosProveedorCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DocumentosSociosProveedorCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DocumentosSociosProveedorCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idSocioProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idDocumento` | `Integer` | `private` | - | [Línea 11NaN11) |
| `creadoPor` | `Integer` | `private` | - | [Línea 13NaN13) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 15NaN15) |
| `activo` | `Boolean` | `private` | - | [Línea 16NaN16) |

---

### <a id="documentossociosproveedorresponsedto"></a>DocumentosSociosProveedorResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DocumentosSociosProveedorResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/DocumentosSociosProveedorResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idSocioProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idDocumento` | `Integer` | `private` | - | [Línea 11NaN11) |
| `creadoPor` | `Integer` | `private` | - | [Línea 13NaN13) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 15NaN15) |
| `activo` | `Boolean` | `private` | - | [Línea 16NaN16) |

---

### <a id="estadoproveedorcreaterequestdto"></a>EstadoProveedorCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EstadoProveedorCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EstadoProveedorCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `estado` | `String` | `private` | - | [Línea 10NaN10) |
| `descripcion` | `String` | `private` | - | [Línea 11NaN11) |
| `activo` | `Boolean` | `private` | - | [Línea 12NaN12) |

---

### <a id="estadoproveedorresponsedto"></a>EstadoProveedorResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EstadoProveedorResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EstadoProveedorResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idEstadoProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `estado` | `String` | `private` | - | [Línea 11NaN11) |
| `descripcion` | `String` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="estadousuariocreaterequestdto"></a>EstadoUsuarioCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EstadoUsuarioCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EstadoUsuarioCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `descripcion` | `String` | `private` | - | [Línea 11NaN11) |
| `activo` | `Boolean` | `private` | - | [Línea 12NaN12) |

---

### <a id="estadousuarioresponsedto"></a>EstadoUsuarioResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EstadoUsuarioResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EstadoUsuarioResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idEstadoUsuario` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `descripcion` | `String` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="evaluacionproveedorcreaterequestdto"></a>EvaluacionProveedorCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EvaluacionProveedorCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EvaluacionProveedorCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idUsuario` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idCalificacion` | `Integer` | `private` | - | [Línea 12NaN12) |
| `puntaje` | `Integer` | `private` | - | [Línea 13NaN13) |
| `observaciones` | `String` | `private` | - | [Línea 14NaN14) |
| `urlCalificacion` | `String` | `private` | - | [Línea 15NaN15) |
| `calidad` | `Integer` | `private` | - | [Línea 16NaN16) |
| `obsCalidad` | `String` | `private` | - | [Línea 17NaN17) |
| `tiempo` | `Integer` | `private` | - | [Línea 18NaN18) |
| `obsTiempo` | `String` | `private` | - | [Línea 19NaN19) |
| `documenta` | `Integer` | `private` | - | [Línea 20NaN20) |
| `obsDocumental` | `String` | `private` | - | [Línea 21NaN21) |
| `sarlaft` | `Integer` | `private` | - | [Línea 22NaN22) |
| `obsSarlaft` | `String` | `private` | - | [Línea 23NaN23) |
| `comercial` | `Integer` | `private` | - | [Línea 24NaN24) |
| `obsComercial` | `String` | `private` | - | [Línea 25NaN25) |
| `social` | `Integer` | `private` | - | [Línea 26NaN26) |
| `obsSocial` | `String` | `private` | - | [Línea 27NaN27) |
| `mejora` | `Integer` | `private` | - | [Línea 28NaN28) |
| `obsMejora` | `String` | `private` | - | [Línea 29NaN29) |
| `creadoPor` | `Integer` | `private` | - | [Línea 31NaN31) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 33NaN33) |
| `activo` | `Boolean` | `private` | - | [Línea 34NaN34) |

---

### <a id="evaluacionproveedorresponsedto"></a>EvaluacionProveedorResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EvaluacionProveedorResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EvaluacionProveedorResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idEvaluacion` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idProveedor` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idUsuario` | `Integer` | `private` | - | [Línea 12NaN12) |
| `idCalificacion` | `Integer` | `private` | - | [Línea 13NaN13) |
| `puntaje` | `Integer` | `private` | - | [Línea 14NaN14) |
| `observaciones` | `String` | `private` | - | [Línea 15NaN15) |
| `urlCalificacion` | `String` | `private` | - | [Línea 16NaN16) |
| `calidad` | `Integer` | `private` | - | [Línea 17NaN17) |
| `obsCalidad` | `String` | `private` | - | [Línea 18NaN18) |
| `tiempo` | `Integer` | `private` | - | [Línea 19NaN19) |
| `obsTiempo` | `String` | `private` | - | [Línea 20NaN20) |
| `documenta` | `Integer` | `private` | - | [Línea 21NaN21) |
| `obsDocumental` | `String` | `private` | - | [Línea 22NaN22) |
| `sarlaft` | `Integer` | `private` | - | [Línea 23NaN23) |
| `obsSarlaft` | `String` | `private` | - | [Línea 24NaN24) |
| `comercial` | `Integer` | `private` | - | [Línea 25NaN25) |
| `obsComercial` | `String` | `private` | - | [Línea 26NaN26) |
| `social` | `Integer` | `private` | - | [Línea 27NaN27) |
| `obsSocial` | `String` | `private` | - | [Línea 28NaN28) |
| `mejora` | `Integer` | `private` | - | [Línea 29NaN29) |
| `obsMejora` | `String` | `private` | - | [Línea 30NaN30) |
| `creadoPor` | `Integer` | `private` | - | [Línea 32NaN32) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 34NaN34) |
| `activo` | `Boolean` | `private` | - | [Línea 35NaN35) |

---

### <a id="evaluacionriesgoscreaterequestdto"></a>EvaluacionRiesgosCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EvaluacionRiesgosCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EvaluacionRiesgosCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idUsuario` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idProveedor` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idValidacion` | `Integer` | `private` | - | [Línea 12NaN12) |
| `validacionAuditoria` | `Boolean` | `private` | - | [Línea 13NaN13) |
| `comentariosAuditoria` | `String` | `private` | - | [Línea 15NaN15) |
| `creadoPor` | `Integer` | `private` | - | [Línea 17NaN17) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 19NaN19) |
| `activo` | `Boolean` | `private` | - | [Línea 20NaN20) |

---

### <a id="evaluacionriesgosresponsedto"></a>EvaluacionRiesgosResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EvaluacionRiesgosResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/EvaluacionRiesgosResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idEvaluacionRiesgos` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idUsuario` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idProveedor` | `Integer` | `private` | - | [Línea 12NaN12) |
| `idValidacion` | `Integer` | `private` | - | [Línea 13NaN13) |
| `validacionAuditoria` | `Boolean` | `private` | - | [Línea 14NaN14) |
| `comentariosAuditoria` | `String` | `private` | - | [Línea 16NaN16) |
| `creadoPor` | `Integer` | `private` | - | [Línea 18NaN18) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 20NaN20) |
| `activo` | `Boolean` | `private` | - | [Línea 21NaN21) |

---

### <a id="formadepagocreaterequestdto"></a>FormaDePagoCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/FormaDePagoCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/FormaDePagoCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idTipoPago` | `Integer` | `private` | - | [Línea 11NaN11) |
| `plazo` | `Integer` | `private` | - | [Línea 13NaN13) |
| `creadoPor` | `Integer` | `private` | - | [Línea 15NaN15) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 17NaN17) |
| `activo` | `Boolean` | `private` | - | [Línea 18NaN18) |

---

### <a id="formadepagoresponsedto"></a>FormaDePagoResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/FormaDePagoResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/FormaDePagoResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idFormaPago` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idProveedor` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idTipoPago` | `Integer` | `private` | - | [Línea 12NaN12) |
| `plazo` | `Integer` | `private` | - | [Línea 14NaN14) |
| `creadoPor` | `Integer` | `private` | - | [Línea 16NaN16) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 18NaN18) |
| `activo` | `Boolean` | `private` | - | [Línea 19NaN19) |

---

### <a id="generalresponsedto"></a>GeneralResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/GeneralResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/GeneralResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `successful` | `boolean` | `private` | - | [Línea 10NaN10) |

---

### <a id="historialusuariocreaterequestdto"></a>HistorialUsuarioCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/HistorialUsuarioCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/HistorialUsuarioCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idUsuario` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idEstadoUsuario` | `Integer` | `private` | - | [Línea 11NaN11) |
| `comentarios` | `String` | `private` | - | [Línea 12NaN12) |
| `creadoPor` | `Integer` | `private` | - | [Línea 14NaN14) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 16NaN16) |
| `activo` | `Boolean` | `private` | - | [Línea 17NaN17) |

---

### <a id="historialusuarioresponsedto"></a>HistorialUsuarioResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/HistorialUsuarioResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/HistorialUsuarioResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idHistorialUsuario` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idUsuario` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idEstadoUsuario` | `Integer` | `private` | - | [Línea 12NaN12) |
| `comentarios` | `String` | `private` | - | [Línea 13NaN13) |
| `creadoPor` | `Integer` | `private` | - | [Línea 15NaN15) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 17NaN17) |
| `activo` | `Boolean` | `private` | - | [Línea 18NaN18) |

---

### <a id="loginrequestdto"></a>LoginRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/LoginRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/LoginRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `usuario` | `String` | `private` | - | [Línea 13NaN13) |
| `contrasena` | `String` | `private` | - | [Línea 14NaN14) |

---

### <a id="loginresponsedto"></a>LoginResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/LoginResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/LoginResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `successful` | `boolean` | `private` | - | [Línea 13NaN13) |
| `message` | `String` | `private` | - | [Línea 14NaN14) |
| `idUsuario` | `Integer` | `private` | - | [Línea 15NaN15) |
| `nombreUsuario` | `String` | `private` | - | [Línea 16NaN16) |
| `correoUsuario` | `String` | `private` | - | [Línea 17NaN17) |
| `idRol` | `Integer` | `private` | - | [Línea 18NaN18) |
| `fotoUrl` | `String` | `private` | - | [Línea 19NaN19) |

---

### <a id="municipiocreaterequestdto"></a>MunicipioCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/MunicipioCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/MunicipioCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `nombre` | `String` | `private` | - | [Línea 11NaN11) |
| `idDepartamento` | `Integer` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="municipioresponsedto"></a>MunicipioResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/MunicipioResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/MunicipioResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idMunicipio` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `nombre` | `String` | `private` | - | [Línea 12NaN12) |
| `idDepartamento` | `Integer` | `private` | - | [Línea 13NaN13) |
| `activo` | `Boolean` | `private` | - | [Línea 14NaN14) |

---

### <a id="notificacionescreaterequestdto"></a>NotificacionesCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/NotificacionesCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/NotificacionesCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idUsuario` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idTipoNotificacion` | `Integer` | `private` | - | [Línea 11NaN11) |
| `mensaje` | `String` | `private` | - | [Línea 13NaN13) |
| `creadoPor` | `Integer` | `private` | - | [Línea 15NaN15) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 17NaN17) |
| `activo` | `Boolean` | `private` | - | [Línea 18NaN18) |

---

### <a id="notificacionesresponsedto"></a>NotificacionesResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/NotificacionesResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/NotificacionesResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idNotificacion` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idUsuario` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idTipoNotificacion` | `Integer` | `private` | - | [Línea 12NaN12) |
| `mensaje` | `String` | `private` | - | [Línea 14NaN14) |
| `creadoPor` | `Integer` | `private` | - | [Línea 16NaN16) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 18NaN18) |
| `activo` | `Boolean` | `private` | - | [Línea 19NaN19) |

---

### <a id="origendatocreaterequestdto"></a>OrigenDatoCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/OrigenDatoCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/OrigenDatoCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `descripcion` | `String` | `private` | - | [Línea 11NaN11) |
| `activo` | `Boolean` | `private` | - | [Línea 12NaN12) |

---

### <a id="origendatoresponsedto"></a>OrigenDatoResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/OrigenDatoResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/OrigenDatoResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idOrigen` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `descripcion` | `String` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="paiscreaterequestdto"></a>PaisCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/PaisCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/PaisCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `nombre` | `String` | `private` | - | [Línea 11NaN11) |
| `activo` | `Boolean` | `private` | - | [Línea 12NaN12) |

---

### <a id="paisresponsedto"></a>PaisResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/PaisResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/PaisResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idPais` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `nombre` | `String` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="proveedorcompletodto"></a>ProveedorCompletoDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorCompletoDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorCompletoDto.java)  

**Anotaciones de Clase:**
- `@Data`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idUsuarioAsignado` | `String` | `private` | - | [Línea 9NaN9) |
| `empresa` | `EmpresaDto` | `private` | - | [Línea 10NaN10) |
| `contactos` | `List<ContactoDto>` | `private` | - | [Línea 11NaN11) |
| `representantes` | `List<RepresentanteDto>` | `private` | - | [Línea 12NaN12) |
| `socios` | `List<SocioDto>` | `private` | - | [Línea 13NaN13) |
| `validaciones` | `List<ValidacionCreateRequestDto>` | `private` | - | [Línea 14NaN14) |
| `bancaria` | `BancariaDto` | `private` | - | [Línea 15NaN15) |
| `financiera` | `FinancieraDto` | `private` | - | [Línea 16NaN16) |
| `laft` | `LaftDto` | `private` | - | [Línea 17NaN17) |
| `nombre` | `String` | `private` | - | [Línea 21NaN21) |
| `idTipoPersona` | `Integer` | `private` | - | [Línea 22NaN22) |
| `categoria` | `String` | `private` | - | [Línea 23NaN23) |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 24NaN24) |
| `numeroIdentificacion` | `String` | `private` | - | [Línea 25NaN25) |
| `ciiu` | `String` | `private` | - | [Línea 26NaN26) |
| `paginaWeb` | `String` | `private` | - | [Línea 27NaN27) |
| `idTipoTelefono` | `Integer` | `private` | - | [Línea 28NaN28) |
| `telefono` | `String` | `private` | - | [Línea 29NaN29) |
| `correo` | `String` | `private` | - | [Línea 30NaN30) |
| `idPais` | `Integer` | `private` | - | [Línea 31NaN31) |
| `idDepartamento` | `Integer` | `private` | - | [Línea 32NaN32) |
| `idMunicipio` | `Integer` | `private` | - | [Línea 33NaN33) |
| `direccion` | `String` | `private` | - | [Línea 34NaN34) |
| `nombres` | `String` | `private` | - | [Línea 39NaN39) |
| `apellidos` | `String` | `private` | - | [Línea 40NaN40) |
| `cargo` | `String` | `private` | - | [Línea 41NaN41) |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 42NaN42) |
| `numeroIdentificacion` | `String` | `private` | - | [Línea 43NaN43) |
| `idTipoTelefono` | `Integer` | `private` | - | [Línea 44NaN44) |
| `telefono` | `String` | `private` | - | [Línea 45NaN45) |
| `correo` | `String` | `private` | - | [Línea 46NaN46) |
| `esPrincipal` | `Boolean` | `private` | - | [Línea 51NaN51) |
| `nombres` | `String` | `private` | - | [Línea 52NaN52) |
| `apellidos` | `String` | `private` | - | [Línea 53NaN53) |
| `cargo` | `String` | `private` | - | [Línea 54NaN54) |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 55NaN55) |
| `numeroIdentificacion` | `String` | `private` | - | [Línea 56NaN56) |
| `nacionalidad` | `String` | `private` | - | [Línea 57NaN57) |
| `idTipoTelefono` | `Integer` | `private` | - | [Línea 58NaN58) |
| `telefono` | `String` | `private` | - | [Línea 59NaN59) |
| `correo` | `String` | `private` | - | [Línea 60NaN60) |
| `idTipoPersona` | `Integer` | `private` | - | [Línea 65NaN65) |
| `nombreCompleto` | `String` | `private` | - | [Línea 66NaN66) |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 67NaN67) |
| `numeroIdentificacion` | `String` | `private` | - | [Línea 68NaN68) |
| `participacion` | `BigDecimal` | `private` | - | [Línea 69NaN69) |
| `nacionalidad` | `String` | `private` | - | [Línea 70NaN70) |
| `tipoCuenta` | `String` | `private` | - | [Línea 75NaN75) |
| `numeroCuenta` | `String` | `private` | - | [Línea 76NaN76) |
| `banco` | `String` | `private` | - | [Línea 77NaN77) |
| `idMetodoPago` | `Integer` | `private` | - | [Línea 78NaN78) |
| `activos` | `BigDecimal` | `private` | - | [Línea 83NaN83) |
| `pasivos` | `BigDecimal` | `private` | - | [Línea 84NaN84) |
| `patrimonio` | `BigDecimal` | `private` | - | [Línea 85NaN85) |
| `totalIngresos` | `BigDecimal` | `private` | - | [Línea 86NaN86) |
| `totalGastos` | `BigDecimal` | `private` | - | [Línea 87NaN87) |
| `p1` | `Boolean` | `private` | - | [Línea 92NaN92) |
| `p2` | `Boolean` | `private` | - | [Línea 93NaN93) |
| `p3` | `Boolean` | `private` | - | [Línea 94NaN94) |
| `p4` | `Boolean` | `private` | - | [Línea 95NaN95) |
| `p5` | `Boolean` | `private` | - | [Línea 96NaN96) |

---

### <a id="proveedorcontactocreaterequestdto"></a>ProveedorContactoCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorContactoCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorContactoCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idContacto` | `Integer` | `private` | - | [Línea 11NaN11) |
| `estadoContacto` | `Boolean` | `private` | - | [Línea 12NaN12) |
| `creadoPor` | `Integer` | `private` | - | [Línea 14NaN14) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 16NaN16) |
| `activo` | `Boolean` | `private` | - | [Línea 17NaN17) |

---

### <a id="proveedorcontactoresponsedto"></a>ProveedorContactoResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorContactoResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorContactoResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idContacto` | `Integer` | `private` | - | [Línea 11NaN11) |
| `estadoContacto` | `Boolean` | `private` | - | [Línea 12NaN12) |
| `creadoPor` | `Integer` | `private` | - | [Línea 14NaN14) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 16NaN16) |
| `activo` | `Boolean` | `private` | - | [Línea 17NaN17) |

---

### <a id="proveedorcreaterequestdto"></a>ProveedorCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 10NaN10) |
| `numeroIdentificacion` | `String` | `private` | - | [Línea 11NaN11) |
| `digitoVerificacion` | `String` | `private` | - | [Línea 12NaN12) |
| `razonSocial` | `String` | `private` | - | [Línea 13NaN13) |
| `nombres` | `String` | `private` | - | [Línea 14NaN14) |
| `apellidos` | `String` | `private` | - | [Línea 15NaN15) |
| `idTipoPersona` | `Integer` | `private` | - | [Línea 16NaN16) |
| `telefonoPrincipal` | `String` | `private` | - | [Línea 17NaN17) |
| `idTipoTelefono` | `Integer` | `private` | - | [Línea 18NaN18) |
| `correoPrincipal` | `String` | `private` | - | [Línea 19NaN19) |
| `idActualizacionProveedor` | `Integer` | `private` | - | [Línea 20NaN20) |
| `requiereActualizacion` | `Boolean` | `private` | - | [Línea 21NaN21) |
| `descripcion` | `String` | `private` | - | [Línea 22NaN22) |
| `creadoPor` | `Integer` | `private` | - | [Línea 24NaN24) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 26NaN26) |
| `activo` | `Boolean` | `private` | - | [Línea 27NaN27) |
| `idEstadoProveedor` | `Integer` | `private` | - | [Línea 28NaN28) |
| `bancoReferencia` | `String` | `private` | - | [Línea 30NaN30) |
| `tipoCuenta` | `String` | `private` | - | [Línea 31NaN31) |
| `numCuenta` | `String` | `private` | - | [Línea 32NaN32) |

---

### <a id="proveedordetallecompletodto"></a>ProveedorDetalleCompletoDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorDetalleCompletoDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorDetalleCompletoDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `proveedor` | `ProveedorResponseDto` | `private` | - | [Línea 11NaN11) |
| `contactos` | `List<ContactoResponseDto>` | `private` | - | [Línea 12NaN12) |
| `representantes` | `List<RepresentanteLegalResponseDto>` | `private` | - | [Línea 13NaN13) |
| `socios` | `List<SociosProveedorResponseDto>` | `private` | - | [Línea 14NaN14) |
| `documentos` | `List<DocumentosResponseDto>` | `private` | - | [Línea 15NaN15) |
| `evaluaciones` | `List<EvaluacionProveedorResponseDto>` | `private` | - | [Línea 16NaN16) |
| `validaciones` | `List<ValidacionResponseDto>` | `private` | - | [Línea 17NaN17) |

---

### <a id="proveedorprefilldto"></a>ProveedorPreFillDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorPreFillDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorPreFillDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@NoArgsConstructor`
- `@AllArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `nit` | `String` | `private` | - | [Línea 11NaN11) |
| `razonSocial` | `String` | `private` | - | [Línea 12NaN12) |
| `tipoPersona` | `String` | `private` | - | [Línea 13NaN13) |
| `direccion` | `String` | `private` | - | [Línea 14NaN14) |
| `correo` | `String` | `private` | - | [Línea 15NaN15) |
| `telefono` | `String` | `private` | - | [Línea 16NaN16) |
| `ciiu` | `String` | `private` | - | [Línea 17NaN17) |
| `municipio` | `String` | `private` | - | [Línea 18NaN18) |
| `departamento` | `String` | `private` | - | [Línea 19NaN19) |
| `pais` | `String` | `private` | - | [Línea 20NaN20) |
| `banco` | `String` | `private` | - | [Línea 23NaN23) |
| `tipoCuenta` | `String` | `private` | - | [Línea 24NaN24) |
| `numeroCuenta` | `String` | `private` | - | [Línea 25NaN25) |
| `refComercialRazonSocial` | `String` | `private` | - | [Línea 28NaN28) |
| `refComercialNit` | `String` | `private` | - | [Línea 29NaN29) |
| `representantes` | `List<RepresentantePreFill>` | `private` | - | [Línea 32NaN32) |
| `socios` | `List<SocioPreFill>` | `private` | - | [Línea 33NaN33) |
| `validaciones` | `List<ValidacionCreateRequestDto>` | `private` | - | [Línea 34NaN34) |
| `extraccionExitosa` | `boolean` | `private` | - | [Línea 37NaN37) |
| `mensaje` | `String` | `private` | - | [Línea 38NaN38) |
| `nombres` | `String` | `private` | - | [Línea 45NaN45) |
| `apellidos` | `String` | `private` | - | [Línea 46NaN46) |
| `tipoDocumento` | `String` | `private` | - | [Línea 47NaN47) |
| `numeroDocumento` | `String` | `private` | - | [Línea 48NaN48) |
| `cargo` | `String` | `private` | - | [Línea 49NaN49) |
| `correo` | `String` | `private` | - | [Línea 50NaN50) |
| `telefono` | `String` | `private` | - | [Línea 51NaN51) |
| `tipoPersona` | `String` | `private` | - | [Línea 59NaN59) |
| `nombreCompleto` | `String` | `private` | - | [Línea 60NaN60) |
| `tipoDocumento` | `String` | `private` | - | [Línea 61NaN61) |
| `numeroDocumento` | `String` | `private` | - | [Línea 62NaN62) |
| `participacion` | `Double` | `private` | - | [Línea 63NaN63) |
| `nacionalidad` | `String` | `private` | - | [Línea 64NaN64) |

---

### <a id="proveedorresponsedto"></a>ProveedorResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ProveedorResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 11NaN11) |
| `numeroIdentificacion` | `String` | `private` | - | [Línea 12NaN12) |
| `digitoVerificacion` | `String` | `private` | - | [Línea 13NaN13) |
| `razonSocial` | `String` | `private` | - | [Línea 14NaN14) |
| `nombres` | `String` | `private` | - | [Línea 15NaN15) |
| `apellidos` | `String` | `private` | - | [Línea 16NaN16) |
| `idTipoPersona` | `Integer` | `private` | - | [Línea 17NaN17) |
| `telefonoPrincipal` | `String` | `private` | - | [Línea 18NaN18) |
| `idTipoTelefono` | `Integer` | `private` | - | [Línea 19NaN19) |
| `correoPrincipal` | `String` | `private` | - | [Línea 20NaN20) |
| `idActualizacionProveedor` | `Integer` | `private` | - | [Línea 21NaN21) |
| `requiereActualizacion` | `Boolean` | `private` | - | [Línea 22NaN22) |
| `descripcion` | `String` | `private` | - | [Línea 23NaN23) |
| `creadoPor` | `Integer` | `private` | - | [Línea 25NaN25) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 27NaN27) |
| `activo` | `Boolean` | `private` | - | [Línea 28NaN28) |
| `idEstadoProveedor` | `Integer` | `private` | - | [Línea 29NaN29) |
| `bancoReferencia` | `String` | `private` | - | [Línea 31NaN31) |
| `tipoCuenta` | `String` | `private` | - | [Línea 32NaN32) |
| `numCuenta` | `String` | `private` | - | [Línea 33NaN33) |

---

### <a id="representantelegalcreaterequestdto"></a>RepresentanteLegalCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RepresentanteLegalCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RepresentanteLegalCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idDocumento` | `Integer` | `private` | - | [Línea 11NaN11) |
| `numeroIdentificacion` | `String` | `private` | - | [Línea 12NaN12) |
| `nombres` | `String` | `private` | - | [Línea 13NaN13) |
| `apellidos` | `String` | `private` | - | [Línea 14NaN14) |
| `idTipoTelefono` | `Integer` | `private` | - | [Línea 15NaN15) |
| `telefono` | `String` | `private` | - | [Línea 16NaN16) |
| `correo` | `String` | `private` | - | [Línea 17NaN17) |
| `idUsuario` | `Integer` | `private` | - | [Línea 18NaN18) |
| `creadoPor` | `Integer` | `private` | - | [Línea 20NaN20) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 22NaN22) |
| `activo` | `Boolean` | `private` | - | [Línea 23NaN23) |

---

### <a id="representantelegalresponsedto"></a>RepresentanteLegalResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RepresentanteLegalResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RepresentanteLegalResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idRepresentanteLegal` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idDocumento` | `Integer` | `private` | - | [Línea 12NaN12) |
| `numeroIdentificacion` | `String` | `private` | - | [Línea 13NaN13) |
| `nombres` | `String` | `private` | - | [Línea 14NaN14) |
| `apellidos` | `String` | `private` | - | [Línea 15NaN15) |
| `idTipoTelefono` | `Integer` | `private` | - | [Línea 16NaN16) |
| `telefono` | `String` | `private` | - | [Línea 17NaN17) |
| `correo` | `String` | `private` | - | [Línea 18NaN18) |
| `idUsuario` | `Integer` | `private` | - | [Línea 19NaN19) |
| `creadoPor` | `Integer` | `private` | - | [Línea 21NaN21) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | - | [Línea 24NaN24) |
| `nacionalidad` | `String` | `private` | - | [Línea 25NaN25) |

---

### <a id="representanteproveedorcreaterequestdto"></a>RepresentanteProveedorCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RepresentanteProveedorCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RepresentanteProveedorCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idRepresentanteLegal` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idProveedor` | `Integer` | `private` | - | [Línea 11NaN11) |
| `cargo` | `String` | `private` | - | [Línea 12NaN12) |
| `creadoPor` | `Integer` | `private` | - | [Línea 16NaN16) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 18NaN18) |
| `activo` | `Boolean` | `private` | - | [Línea 19NaN19) |

---

### <a id="representanteproveedorresponsedto"></a>RepresentanteProveedorResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RepresentanteProveedorResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RepresentanteProveedorResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idRelacion` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idRepresentanteLegal` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idProveedor` | `Integer` | `private` | - | [Línea 12NaN12) |
| `cargo` | `String` | `private` | - | [Línea 13NaN13) |
| `creadoPor` | `Integer` | `private` | - | [Línea 17NaN17) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 19NaN19) |
| `activo` | `Boolean` | `private` | - | [Línea 20NaN20) |

---

### <a id="responsedto"></a>ResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `data` | `T` | `private` | - | [Línea 10NaN10) |

---

### <a id="rolescreaterequestdto"></a>RolesCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RolesCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RolesCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `rol` | `String` | `private` | - | [Línea 10NaN10) |
| `estadoRol` | `Boolean` | `private` | - | [Línea 11NaN11) |
| `descripcion` | `String` | `private` | - | [Línea 12NaN12) |
| `creadoPor` | `Integer` | `private` | - | [Línea 14NaN14) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 16NaN16) |

---

### <a id="rolesresponsedto"></a>RolesResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RolesResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/RolesResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idRol` | `Integer` | `private` | - | [Línea 10NaN10) |
| `rol` | `String` | `private` | - | [Línea 11NaN11) |
| `estadoRol` | `Boolean` | `private` | - | [Línea 12NaN12) |
| `descripcion` | `String` | `private` | - | [Línea 13NaN13) |
| `creadoPor` | `Integer` | `private` | - | [Línea 15NaN15) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 17NaN17) |

---

### <a id="sociosproveedorcreaterequestdto"></a>SociosProveedorCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/SociosProveedorCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/SociosProveedorCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `nombres` | `String` | `private` | - | [Línea 11NaN11) |
| `apellidos` | `String` | `private` | - | [Línea 12NaN12) |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 13NaN13) |
| `numeroIdentificacion` | `String` | `private` | - | [Línea 14NaN14) |
| `consulta` | `Integer` | `private` | - | [Línea 15NaN15) |
| `resultadoConsulta` | `Boolean` | `private` | - | [Línea 17NaN17) |
| `idOrigen` | `Integer` | `private` | - | [Línea 18NaN18) |
| `validado` | `Boolean` | `private` | - | [Línea 20NaN20) |
| `creadoPor` | `Integer` | `private` | - | [Línea 22NaN22) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 24NaN24) |
| `activo` | `Boolean` | `private` | - | [Línea 25NaN25) |

---

### <a id="sociosproveedorresponsedto"></a>SociosProveedorResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/SociosProveedorResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/SociosProveedorResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idSocioProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idProveedor` | `Integer` | `private` | - | [Línea 11NaN11) |
| `nombres` | `String` | `private` | - | [Línea 12NaN12) |
| `apellidos` | `String` | `private` | - | [Línea 13NaN13) |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 14NaN14) |
| `numeroIdentificacion` | `String` | `private` | - | [Línea 15NaN15) |
| `consulta` | `Integer` | `private` | - | [Línea 16NaN16) |
| `resultadoConsulta` | `Boolean` | `private` | - | [Línea 18NaN18) |
| `idOrigen` | `Integer` | `private` | - | [Línea 19NaN19) |
| `validado` | `Boolean` | `private` | - | [Línea 21NaN21) |
| `creadoPor` | `Integer` | `private` | - | [Línea 23NaN23) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 25NaN25) |
| `activo` | `Boolean` | `private` | - | [Línea 26NaN26) |
| `idTipoPersona` | `Integer` | `private` | - | [Línea 27NaN27) |
| `nacionalidad` | `String` | `private` | - | [Línea 28NaN28) |

---

### <a id="tipodocumentocreaterequestdto"></a>TipoDocumentoCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoDocumentoCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoDocumentoCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `descripcion` | `String` | `private` | - | [Línea 11NaN11) |
| `activo` | `Boolean` | `private` | - | [Línea 12NaN12) |

---

### <a id="tipodocumentoresponsedto"></a>TipoDocumentoResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoDocumentoResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoDocumentoResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoDocumento` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `descripcion` | `String` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="tipoidentificacioncreaterequestdto"></a>TipoIdentificacionCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoIdentificacionCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoIdentificacionCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `descripcion` | `String` | `private` | - | [Línea 11NaN11) |
| `activo` | `Boolean` | `private` | - | [Línea 12NaN12) |

---

### <a id="tipoidentificacionresponsedto"></a>TipoIdentificacionResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoIdentificacionResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoIdentificacionResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `descripcion` | `String` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="tiponotificacioncreaterequestdto"></a>TipoNotificacionCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoNotificacionCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoNotificacionCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `descripcion` | `String` | `private` | - | [Línea 11NaN11) |
| `activo` | `Boolean` | `private` | - | [Línea 12NaN12) |

---

### <a id="tiponotificacionresponsedto"></a>TipoNotificacionResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoNotificacionResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoNotificacionResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoNotificacion` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `descripcion` | `String` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="tipopagocreaterequestdto"></a>TipoPagoCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoPagoCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoPagoCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `descripcion` | `String` | `private` | - | [Línea 11NaN11) |
| `activo` | `Boolean` | `private` | - | [Línea 12NaN12) |

---

### <a id="tipopagoresponsedto"></a>TipoPagoResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoPagoResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoPagoResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoPago` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `descripcion` | `String` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="tipopersonacreaterequestdto"></a>TipoPersonaCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoPersonaCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoPersonaCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `descripcion` | `String` | `private` | - | [Línea 11NaN11) |
| `activo` | `Boolean` | `private` | - | [Línea 12NaN12) |

---

### <a id="tipopersonaresponsedto"></a>TipoPersonaResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoPersonaResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoPersonaResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoPersona` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `descripcion` | `String` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="tipotelefonocreaterequestdto"></a>TipoTelefonoCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoTelefonoCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoTelefonoCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `codigo` | `String` | `private` | - | [Línea 10NaN10) |
| `descripcion` | `String` | `private` | - | [Línea 11NaN11) |
| `activo` | `Boolean` | `private` | - | [Línea 12NaN12) |

---

### <a id="tipotelefonoresponsedto"></a>TipoTelefonoResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoTelefonoResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/TipoTelefonoResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idTipoTelefono` | `Integer` | `private` | - | [Línea 10NaN10) |
| `codigo` | `String` | `private` | - | [Línea 11NaN11) |
| `descripcion` | `String` | `private` | - | [Línea 12NaN12) |
| `activo` | `Boolean` | `private` | - | [Línea 13NaN13) |

---

### <a id="ubicacioncreaterequestdto"></a>UbicacionCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/UbicacionCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/UbicacionCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idProveedor` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idMunicipio` | `Integer` | `private` | - | [Línea 11NaN11) |
| `direccion` | `String` | `private` | - | [Línea 12NaN12) |
| `creadoPor` | `Integer` | `private` | - | [Línea 14NaN14) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 16NaN16) |
| `activo` | `Boolean` | `private` | - | [Línea 17NaN17) |

---

### <a id="ubicacionresponsedto"></a>UbicacionResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/UbicacionResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/UbicacionResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idUbicacion` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idProveedor` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idMunicipio` | `Integer` | `private` | - | [Línea 12NaN12) |
| `direccion` | `String` | `private` | - | [Línea 13NaN13) |
| `creadoPor` | `Integer` | `private` | - | [Línea 15NaN15) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 17NaN17) |
| `activo` | `Boolean` | `private` | - | [Línea 18NaN18) |

---

### <a id="usuarioscreaterequestdto"></a>UsuariosCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/UsuariosCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/UsuariosCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `nombreUsuario` | `String` | `private` | - | [Línea 10NaN10) |
| `cargoUsuario` | `String` | `private` | - | [Línea 11NaN11) |
| `correoUsuario` | `String` | `private` | - | [Línea 12NaN12) |
| `contrasena` | `String` | `private` | - | [Línea 13NaN13) |
| `estadoUsuario` | `Boolean` | `private` | - | [Línea 14NaN14) |
| `idRol` | `Integer` | `private` | - | [Línea 15NaN15) |
| `fotoUrl` | `String` | `private` | - | [Línea 16NaN16) |
| `creadoPor` | `Integer` | `private` | - | [Línea 19NaN19) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 21NaN21) |
| `notifStatus` | `Boolean` | `private` | - | [Línea 24NaN24) |
| `notifDocs` | `Boolean` | `private` | - | [Línea 25NaN25) |
| `notifExpiry` | `Boolean` | `private` | - | [Línea 26NaN26) |
| `notifNews` | `Boolean` | `private` | - | [Línea 27NaN27) |
| `idTipoPersona` | `Integer` | `private` | - | [Línea 30NaN30) |
| `razonSocial` | `String` | `private` | - | [Línea 31NaN31) |
| `nombres` | `String` | `private` | - | [Línea 32NaN32) |
| `apellidos` | `String` | `private` | - | [Línea 33NaN33) |
| `idTipoIdentificacion` | `Integer` | `private` | - | [Línea 34NaN34) |
| `numeroIdentificacion` | `String` | `private` | - | [Línea 35NaN35) |

---

### <a id="usuariosresponsedto"></a>UsuariosResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/UsuariosResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/UsuariosResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idUsuario` | `Integer` | `private` | - | [Línea 10NaN10) |
| `nombreUsuario` | `String` | `private` | - | [Línea 11NaN11) |
| `cargoUsuario` | `String` | `private` | - | [Línea 12NaN12) |
| `correoUsuario` | `String` | `private` | - | [Línea 13NaN13) |
| `contrasena` | `String` | `private` | - | [Línea 14NaN14) |
| `estadoUsuario` | `Boolean` | `private` | - | [Línea 15NaN15) |
| `idRol` | `Integer` | `private` | - | [Línea 16NaN16) |
| `nombreRol` | `String` | `private` | - | [Línea 17NaN17) |
| `fotoUrl` | `String` | `private` | - | [Línea 18NaN18) |
| `creadoPor` | `Integer` | `private` | - | [Línea 21NaN21) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 23NaN23) |
| `notifStatus` | `Boolean` | `private` | - | [Línea 26NaN26) |
| `notifDocs` | `Boolean` | `private` | - | [Línea 27NaN27) |
| `notifExpiry` | `Boolean` | `private` | - | [Línea 28NaN28) |
| `notifNews` | `Boolean` | `private` | - | [Línea 29NaN29) |
| `requiereActualizacion` | `Boolean` | `private` | - | [Línea 30NaN30) |

---

### <a id="validacioncreaterequestdto"></a>ValidacionCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ValidacionCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ValidacionCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idUsuario` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idProveedor` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idCampoValidacion` | `Integer` | `private` | - | [Línea 12NaN12) |
| `valorWeb` | `String` | `private` | - | [Línea 13NaN13) |
| `valorDocumento` | `String` | `private` | - | [Línea 14NaN14) |
| `idDocumento` | `Integer` | `private` | - | [Línea 15NaN15) |
| `resultadoValidacion` | `Boolean` | `private` | - | [Línea 16NaN16) |
| `comentarios` | `String` | `private` | - | [Línea 18NaN18) |
| `creadoPor` | `Integer` | `private` | - | [Línea 20NaN20) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 22NaN22) |
| `activo` | `Boolean` | `private` | - | [Línea 23NaN23) |

---

### <a id="validacionfinalcreaterequestdto"></a>ValidacionFinalCreateRequestDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ValidacionFinalCreateRequestDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ValidacionFinalCreateRequestDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idProveedor` | `Integer` | `private` | - | [Línea 11NaN11) |
| `comentarioFinal` | `String` | `private` | - | [Línea 12NaN12) |
| `estadoValidacion` | `String` | `private` | - | [Línea 13NaN13) |
| `creadoPor` | `Integer` | `private` | - | [Línea 14NaN14) |
| `validationIds` | `Set<Integer>` | `private` | - | [Línea 15NaN15) |

---

### <a id="validacionresponsedto"></a>ValidacionResponseDto
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.dtos`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ValidacionResponseDto.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/dtos/ValidacionResponseDto.java)  

**Anotaciones de Clase:**
- `@Data`
- `@Builder`
- `@AllArgsConstructor`
- `@NoArgsConstructor`

#### Atributos / Campos

| Atributo | Tipo | Modificador | Anotaciones | Línea |
| :--- | :--- | :--- | :--- | :--- |
| `idValidacion` | `Integer` | `private` | - | [Línea 10NaN10) |
| `idUsuario` | `Integer` | `private` | - | [Línea 11NaN11) |
| `idProveedor` | `Integer` | `private` | - | [Línea 12NaN12) |
| `idCampoValidacion` | `Integer` | `private` | - | [Línea 13NaN13) |
| `valorWeb` | `String` | `private` | - | [Línea 14NaN14) |
| `valorDocumento` | `String` | `private` | - | [Línea 15NaN15) |
| `idDocumento` | `Integer` | `private` | - | [Línea 16NaN16) |
| `resultadoValidacion` | `Boolean` | `private` | - | [Línea 17NaN17) |
| `comentarios` | `String` | `private` | - | [Línea 19NaN19) |
| `creadoPor` | `Integer` | `private` | - | [Línea 21NaN21) |
| `modificadoPor` | `Integer` | `private` | - | [Línea 23NaN23) |
| `activo` | `Boolean` | `private` | - | [Línea 24NaN24) |

---

## Repositories

### <a id="calificacionrepository"></a>CalificacionRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/CalificacionRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/CalificacionRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="campovalidacionrepository"></a>CampoValidacionRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/CampoValidacionRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/CampoValidacionRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="contactorepository"></a>ContactoRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/ContactoRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/ContactoRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="departamentorepository"></a>DepartamentoRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/DepartamentoRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/DepartamentoRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="documentosrepository"></a>DocumentosRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/DocumentosRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/DocumentosRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="documentossociosproveedorrepository"></a>DocumentosSociosProveedorRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/DocumentosSociosProveedorRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/DocumentosSociosProveedorRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="estadoproveedorrepository"></a>EstadoProveedorRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/EstadoProveedorRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/EstadoProveedorRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="estadousuariorepository"></a>EstadoUsuarioRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/EstadoUsuarioRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/EstadoUsuarioRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="evaluacionproveedorrepository"></a>EvaluacionProveedorRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/EvaluacionProveedorRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/EvaluacionProveedorRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="evaluacionriesgosrepository"></a>EvaluacionRiesgosRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/EvaluacionRiesgosRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/EvaluacionRiesgosRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="firmatokenrepository"></a>FirmaTokenRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `JpaRepository<FirmaTokenEntity, Integer>` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/FirmaTokenRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/FirmaTokenRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="formadepagorepository"></a>FormaDePagoRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/FormaDePagoRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/FormaDePagoRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="historialusuariorepository"></a>HistorialUsuarioRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/HistorialUsuarioRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/HistorialUsuarioRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="municipiorepository"></a>MunicipioRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/MunicipioRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/MunicipioRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="notificacionesrepository"></a>NotificacionesRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/NotificacionesRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/NotificacionesRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="origendatorepository"></a>OrigenDatoRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/OrigenDatoRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/OrigenDatoRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="paisrepository"></a>PaisRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/PaisRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/PaisRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="proveedorcontactorepository"></a>ProveedorContactoRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/ProveedorContactoRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/ProveedorContactoRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="proveedorrepository"></a>ProveedorRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/ProveedorRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/ProveedorRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="representantelegalrepository"></a>RepresentanteLegalRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/RepresentanteLegalRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/RepresentanteLegalRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="representanteproveedorrepository"></a>RepresentanteProveedorRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/RepresentanteProveedorRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/RepresentanteProveedorRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="rolesrepository"></a>RolesRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `JpaRepository<RolesEntity, Integer>` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/RolesRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/RolesRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="sociosproveedorrepository"></a>SociosProveedorRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/SociosProveedorRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/SociosProveedorRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="tipodocumentorepository"></a>TipoDocumentoRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoDocumentoRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoDocumentoRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="tipoidentificacionrepository"></a>TipoIdentificacionRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoIdentificacionRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoIdentificacionRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="tiponotificacionrepository"></a>TipoNotificacionRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoNotificacionRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoNotificacionRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="tipopagorepository"></a>TipoPagoRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoPagoRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoPagoRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="tipopersonarepository"></a>TipoPersonaRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoPersonaRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoPersonaRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="tipotelefonorepository"></a>TipoTelefonoRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoTelefonoRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/TipoTelefonoRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="ubicacionrepository"></a>UbicacionRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/UbicacionRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/UbicacionRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="usuariosrepository"></a>UsuariosRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/UsuariosRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/UsuariosRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="validacionfinalrepository"></a>ValidacionFinalRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/ValidacionFinalRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/ValidacionFinalRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="validacionrepository"></a>ValidacionRepository
**Tipo:** `Interface` | **Paquete:** `proyecto.ADSO.proveedores.repositories`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/ValidacionRepository.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/repositories/ValidacionRepository.java)  

**Anotaciones de Clase:**
- `@Repository`

*No tiene campos o atributos definidos directamente en el archivo.*

---

## Others/Configs

### <a id="corsconfig"></a>CorsConfig
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores.config`  
**Herencia:** Extiende `-` / Implementa `WebMvcConfigurer`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/config/CorsConfig.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/config/CorsConfig.java)  

**Anotaciones de Clase:**
- `@Configuration`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="proveedoresapplication"></a>ProveedoresApplication
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/ProveedoresApplication.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/ProveedoresApplication.java)  

**Anotaciones de Clase:**
- `@SpringBootApplication`

*No tiene campos o atributos definidos directamente en el archivo.*

---

### <a id="proveedoresapplicationtests"></a>ProveedoresApplicationTests
**Tipo:** `Class` | **Paquete:** `proyecto.ADSO.proveedores`  
**Herencia:** Extiende `-` / Implementa `-`  
**Archivo:** [Backend/proveedores/proveedores/src/test/java/proyecto/ADSO/proveedores/ProveedoresApplicationTests.java](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Backend/proveedores/proveedores/src/test/java/proyecto/ADSO/proveedores/ProveedoresApplicationTests.java)  

**Anotaciones de Clase:**
- `@SpringBootTest`

*No tiene campos o atributos definidos directamente en el archivo.*

---

