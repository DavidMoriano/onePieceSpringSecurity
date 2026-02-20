# 🏴‍☠️ One Piece - Sistema de Gestión de la Marina 🏴‍☠️

## 📋 Descripción del Proyecto

Sistema de gestión desarrollado para la Marina de One Piece que permite administrar la información de los piratas. Desarrollado con Spring Boot, Spring Data JPA, Thymeleaf y Bootstrap 5.

## 🎨 Características

- **CRUD Completo de Piratas**: Crear, listar, actualizar y eliminar piratas
- **Diseño Temático One Piece**: Interfaz con colores y estilo inspirado en la Marina y los piratas
- **Bootstrap 5**: Diseño responsive y moderno
- **Validaciones**: Formularios con validación de datos
- **Base de Datos MySQL**: Persistencia con JPA/Hibernate

## 🏗️ Estructura del Proyecto

### Backend (Java)

```
src/main/java/com/adrian/onepiece/
├── controladores/
│   ├── MainController.java          # Controlador principal (index)
│   └── PiratasController.java       # CRUD de piratas
├── dao/
│   ├── interfaces/
│   │   ├── IDesplegablesDAO.java    # Interface para desplegables
│   │   └── IPiratasDAO.java         # Interface DAO de piratas
│   └── impl/
│       ├── DesplegablesDAOImpl.java # Implementación desplegables
│       └── PiratasDAOImpl.java      # Implementación DAO piratas
├── dtos/
│   ├── DesplegableDTO.java          # DTO para listas desplegables
│   └── PirataDTO.java               # DTO de pirata
├── entities/
│   ├── IslaEntity.java              # Entidad Isla
│   └── PirataEntity.java            # Entidad Pirata
├── repositorios/
│   ├── IslaRepository.java          # Repositorio de islas
│   └── PirataRepository.java        # Repositorio de piratas (con queries JPQL)
└── servicio/
    ├── interfaces/
    │   └── IPiratasService.java     # Interface servicio piratas
    └── impl/
        └── PiratasServiceImpl.java  # Implementación servicio piratas
```

### Frontend (HTML/CSS)

```
src/main/resources/
├── static/css/
│   └── onepiece.css                 # Estilos personalizados One Piece
└── templates/
    ├── index.html                   # Página principal
    ├── menu.html                    # Menú de navegación (fragment)
    └── piratas/
        ├── insertarPirata.html      # Formulario insertar pirata
        ├── listadoPiratas.html      # Listado con filtros
        ├── actualizarPiratas.html   # Formulario actualizar pirata
        └── borrarPiratas.html       # Eliminar pirata (soft delete)
```

## 🗄️ Base de Datos

### Tabla: isla
- `id` (INT, PK)
- `nombre` (VARCHAR)

### Tabla: pirata
- `id` (INT, PK)
- `nombre` (VARCHAR)
- `fruta_diablo` (VARCHAR, nullable)
- `fecha_nacimiento` (DATE)
- `activo` (INT, 0 o 1)
- `id_isla_origen` (INT, FK → isla.id)

## 🚀 Configuración

### 1. Base de Datos

Asegúrate de tener MySQL instalado y crea la base de datos:

```sql
CREATE DATABASE onepiece;
```

### 2. Configuración de Conexión

Edita `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/onepiece?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD_AQUI
```

### 3. Ejecutar la Aplicación

```bash
./mvnw spring-boot:run
```

O desde tu IDE, ejecuta `OnePieceApplication.java`

### 4. Acceder a la Aplicación

Abre tu navegador en: `http://localhost:8080`

## 🎯 Funcionalidades Implementadas

### ✅ CRUD Piratas

1. **Insertar Pirata**
   - Formulario con validación
   - Selección de isla de origen
   - Checkbox para estado activo
   - Mensajes de éxito/error

2. **Listar Piratas**
   - Filtros por: ID, nombre, fruta del diablo, estado
   - Tabla responsive con Bootstrap
   - Formato de fechas
   - Badges para estado activo/inactivo

3. **Actualizar Pirata**
   - Búsqueda de piratas
   - Formulario pre-rellenado
   - Actualización de todos los campos

4. **Borrar Pirata**
   - Soft delete (marca como inactivo)
   - Confirmación antes de eliminar
   - Búsqueda previa

## 🎨 Tema Visual

- **Colores Principales**:
  - Azul marino (header, navbar)
  - Rojo pirata (botones de acción)
  - Dorado (acentos)
  
- **Características**:
  - Gradientes suaves
  - Sombras y efectos hover
  - Iconos emoji temáticos
  - Cards con bordes redondeados
  - Animaciones sutiles

## 📝 Notas Técnicas

- **Arquitectura**: Patrón MVC con capas DAO y Service
- **ORM**: Hibernate/JPA
- **Template Engine**: Thymeleaf
- **Frontend**: Bootstrap 5.3.0
- **Validación**: HTML5 + Spring Validation
- **Queries**: JPQL con parámetros opcionales

## 🔧 Próximas Funcionalidades (Parte 2)

Según el enunciado, las siguientes funcionalidades están pendientes:
- CRUD de Tripulaciones
- CRUD de Reclutamientos
- CRUD de Recompensas
- Gestión de Tesorería Marina

---

**Desarrollado por**: Vegapunk Labs 🔬  
**Para**: Marina de One Piece ⚓  
**Versión**: 1.0 - Parte 1
