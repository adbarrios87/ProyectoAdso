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
	Diagrama Entidad-Relación: https://dbdiagram.io/d/6869aaecf413ba350872a25b 

	Secuencia del proyecto:

	1. Usuario: proveedores
		✔ Login.html  (OK)
		✔ sup_supplier_profile.html:  vista principal tras el login con "Acciones rápidas"  (OK)
		✔ Cargar documentos: upload_document.html (OK)
		✔ Actualizar información: data_form.html (OK)
		✔ Generar certificación: certification_supplier.html (OK)
		✔ Historial de calificaciones: qualification_history.html (OK)

	2. Usuario: Administrador
		Login.html  
		
		✔ admin_dashboard.html (OK)

		- Resultado de busqueda rapida de proveedores en el header: search_results.html (PDTE)
		
		- Gestion de usuarios (Usuarios)
			✔ Nuevos Usuarios: new_user.html (OK)
			✔ Listado de usuarios: list_users.html (OK)
			✔ Editar usuarios: Modal en list_users.html (OK)

		- Gestion de proveedores 
			Listado de proveedores: Supplier_Search.html(PDTE MEJORAR)
			Perfil del proveedor:  Informacion completa del proveedor (info del prov, contacto, rep legal, socios, ubicacion, contactos, documentos con estado actual,calificaciones e historial)
			Editar proveedor: Solo se podra editar desde el administrador datos del contacto (creando uno nuevo, sin eliminar el anterior. Este contacto debe tener un usuario nuevo vinculado al proveedor), forma de pago. Los otros campos debera modificarse desde el usuario proveedor con la actualizacion de documentos y data_form
			Certificacion Comercial: 
			Validacion de proveedores: supplier_profile.html (PDTE MEJORAR) Aqui debo obtener el listado de personas a consultar en listas restrictivas y el numero de la consulta
			Aprobación de proveedores: supplier_approve.html (PDTE MEJORAR) 
		
		- Gestion de riesgo
			Validaciones pendientes: Aqui deben quedar los proveedores cuya validacion en listas restrictivas tiene coincidencias
			Observacion del riesgo: El area de riesgos da sus observaciones, para aprobar o no el proveedor 
			Historial de validaciones:

		- Evaluacion de proveedores
			Listado de evaluaciones: Debe contener un filtro por proveedor por nombre, identificacion, año 
			Modal Detalle de evaluacion: Visualización de la evaluación, criterios y observaciones.
			Nueva Evaluacion:

		- Notificaciones
			Alertas de vencimiento: expiration_alerts.html(PDTE MEJORAR) (Bandeja de notificaciones generadas por el sistema.)
	
		- Configuracion del sistema
			Roles: Lista y gestión de roles (crear, editar, activar/desactivar).
			Parametros: Catálogos de apoyo: tipo documento, tipo pago, tipo persona, etc.

		- Reportes
			Reporte proveedores: Reporte consolidado de proveedores por estado, ubicación, tipo, etc.
			