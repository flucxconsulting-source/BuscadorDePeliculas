# Buscador de películas

Aplicación Android desarrollada como práctica de evaluación de la especialidad **Desarrollo de aplicaciones para dispositivos móviles (IFCD37)**.

**Alumno:** Gregory Ravestijn  
**Profesor:** Mansour Boutarbouch Mhaimeur  
**Fecha:** 12 de noviembre de 2026

## Objetivo

Buscar películas por su título y consultar sus datos principales mediante la API REST de OMDb.

## Funcionamiento

La aplicación dispone de dos pantallas:

1. **Búsqueda:** permite escribir el título de una película y muestra los resultados en una lista con título, año y póster. Al pulsar un resultado se abre su ficha.
2. **Detalle:** muestra título, año, póster, sinopsis, duración, director, género y país de la película seleccionada.

## Tecnologías utilizadas

- Kotlin y vistas XML para Android.
- Retrofit para las dos consultas REST a OMDb (búsqueda y detalle).
- RecyclerView y Adapter para mostrar los resultados.
- Intent y Extras para pasar el identificador IMDb entre las dos Activities.
- Picasso para cargar los pósteres.

## Ejecución y evaluación

Abrir el proyecto en Android Studio, sincronizar Gradle y ejecutarlo en un dispositivo o emulador Android compatible con la versión mínima configurada en el proyecto. Se requiere conexión a Internet.

**Clave de API:** la clave personal de OMDb está incluida en el código de esta versión de examen para facilitar que el examinador pueda compilar y probar el proyecto sin configurarla por separado. Esta decisión se limita a la entrega académica; la clave no debe considerarse secreta ni utilizarse como mecanismo de seguridad.

**Carácter temporal:** este repositorio está destinado exclusivamente a la evaluación y se eliminará una vez que el trabajo haya sido calificado.
