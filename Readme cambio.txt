ESTE ES UN CAMBIO EN LA RAMA 1

Nombre del proyecto:

Descripción del proyecto: 
		El proyecto se centra en construir un sistema de información basado en web que integre las etapas de selección, evaluación y seguimiento a la documentación de proveedores.
Ingrantes: 
		Adriana Gineth Barrios Aponte / C.C. 1.032.382.178 / Cel. 3117009024 / Correo. adbarrios87@gmail.com 
		Juan Carlos Cadena Muñoz / C.C. 1.049.620.897 / Cel. 3107715000 / Correo. espriggan123@gmail.com

Link documentos:

	1. Acta de  definición del proyecto: 1 Formulación del proyecto de software.docx
	2. Especificación de Requerimientos de Software (SRS): 2 Especificacion de requerimientos.docx
	3. Casos de uso y diagramas asociados: 3 Diagrama casos de uso.jpeg
	4. Historias de usuario: 4 Historias de Usuario.xlsx
	5. Diagramas de clases: 5 Diagrama de clases.pdf
	6. Diagramas de despliegue: 6 diagrama de despliegue.pdf
	7. Diagramas de secuencia:
	8. Arquitectura del sistema:
	9. Plan de pruebas:
	10. Cronograma de desarrollo o backlog de sprint:

	Adicional: 
	Diagrama Relacional: https://dbdiagram.io/d/6869aaecf413ba350872a25b
	Diccionario Entidad-Relacion: Modelos conceptual y lógico para el proyecto desarrollo de software 
	Modelo Entidad-Relacion: Modelo Entidad relacion



	Secuencia del proyecto:

	1. Usuario: proveedores
		✔ Login.html  (OK)
		✔ Supplier_dashboard.html:  vista principal tras el login con "Acciones rápidas"  (OK)
		✔ supplier_upload_documents.html: cargar documentos (OK)
		✔ supplier_form.html: Actualizar información (OK)
		✔ supplier_certification.html: generar certificación comercial (OK)
		✔ supplier_qualification_history.html: Historial de calificaciones (OK)
			Notificaciones

	2. Usuario: Administrador
		
		 admin_dashboard.html (OK)
		- Resultado de busqueda rapida de proveedores en el header: 
		
		- Gestion de usuarios (Usuarios)
			 user_new.html: registrar nuevos Usuarios (OK)
			 user_list.html: listado de usuarios (OK)
			 Editar usuarios: Modal en list_users.html (OK)

		- Gestion de proveedores 
			✔ buyer_dashboard.html (OK) **
				buyer_supplier_list.html: Listado de proveedores(OK)
			 buyer_Supplier_profile: Perfil del proveedor (OK)  Informacion completa del proveedor 
			   Editar proveedor: Solo se podra editar desde el comprador datos del contacto que creara un usuario nuevo para el nuevo contacto 
			 Certificacion Comercial: Se reutiliza la del usuario proveedor 
			 buyer_supplier_validation.html: Validacion de proveedores (OK)
			 buyer_first_evaluation.html: Primera evaluación del proveedor (OK) - requerida por norma ISO9001 y debe ser aprobada por el jefe de area
			 buyer_supplier_approve.html: Aprobación de proveedores (OK) -usuario jefe de compras
			  Accion-documentos cargados: previsualizar el documento o descargar (Revisar si se requiere un modal)
			  nueva re-evaluacion: Se evalua el proveedor cada que la organizacion lo estipule
			  historial de calificaciones (debe ser diferente al del proveedor ya que debe permitir ingresar una nueva evaluacion)
			  agregar persona en validacion del proveedor con un modal
			  consulta masiva en validacion se debe conectar a una fuente externa para traaer los resultados 
			  las acciones en la validacion se deben desactivar una vez se realiza la consula en listas
			  Reporte proveedores: Reporte consolidado de proveedores por estado, ubicación, tipo, etc.

		
		- Gestion de riesgo
			 risk_dashboard.html: Validaciones pendientes Analista de riesgos (OK)
			 risk_review.html: Revisión del riesgo analista de riesgos  (OK)
			 risk_historial.html: Historial de validaciones  (OK)
			 dashboard oficial de cumplimiento: dashboard_compliance_officer.html (OK)
			 Revisión oficial de cumplimiento: review_compliance_officer.html (OK)

		- Evaluacion de proveedores
			Listado de evaluaciones: Debe contener un filtro por proveedor por nombre, identificacion, año 
			Modal Detalle de evaluacion: Visualización de la evaluación, criterios y observaciones.
			Nueva Evaluacion:

		- Notificaciones
			Alertas de vencimiento: expiration_alerts.html(PDTE MEJORAR) (Bandeja de notificaciones generadas por el sistema.)
	
		- Configuracion del sistemabuy
			Roles: Lista y gestión de roles (crear, editar, activar/desactivar).
			Parametros: Catálogos de apoyo: tipo documento, tipo pago, tipo persona, etc.

		
			
		
		- Otras
			Actualizacion de contraseña (En proceso)
			Salida
			Ayuda
			Configuracion