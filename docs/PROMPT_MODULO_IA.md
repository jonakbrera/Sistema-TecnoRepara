# Prompt para crear módulos con IA

Actúa como desarrollador JavaFX senior.

Estoy trabajando en el sistema TecnoRepara con Java 21, Maven, JavaFX y PostgreSQL.

La arquitectura oficial es MVC:

- model
- dao
- controller
- fxml

El proyecto ya tiene estas clases reutilizables:

- BaseDAO
- AlertUtil
- ValidatorUtil
- Mensajes
- SceneManager
- Session
- AppConfig
- ConexionBD

Necesito crear el módulo: [NOMBRE DEL MÓDULO]

Tabla PostgreSQL: [NOMBRE DE TABLA]

Campos:
[PEGAR CAMPOS DE LA TABLA]

Reglas:

1. Crear Model.
2. Crear DAO extendiendo BaseDAO.
3. Crear Controller usando AlertUtil, ValidatorUtil y Mensajes.
4. Crear FXML usando el mismo estilo CSS del sistema.
5. El módulo debe tener:
   - Guardar
   - Editar
   - Eliminar lógico si aplica
   - Buscar
   - Limpiar
   - Actualizar
   - TableView
   - Doble clic para cargar datos al formulario
   - Validaciones
6. No modificar App.java.
7. No modificar style.css.
8. No modificar MenuPrincipalController salvo para conectar el botón al FXML.
9. No crear conexión propia a la base de datos.
10. Usar PreparedStatement.
11. No escribir SQL dentro del Controller.
12. Seguir el ejemplo del módulo Clientes.