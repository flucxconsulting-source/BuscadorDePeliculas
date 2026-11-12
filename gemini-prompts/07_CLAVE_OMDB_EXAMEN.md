# Prompt 07 — Clave OMDb incluida en el proyecto para evaluación

Actúa como programador Android principiante. El proyecto BuscadorDePeliculas ya funciona con OMDB_API_KEY en local.properties, pero el examinador debe poder clonar el repositorio público, compilar e instalar la aplicación en otro dispositivo sin configuración adicional.

Objetivo único: hacer que la clave de examen OMDb quede incluida en el código fuente del proyecto y, por tanto, en el APK compilado, sin depender de local.properties. El propietario autoriza expresamente publicar esta clave de prueba de OMDb en este repositorio público. No cambies otras funcionalidades.

1. Revisa el código existente. En app/build.gradle.kts se obtiene OMDB_API_KEY desde local.properties y se expone en BuildConfig.OMDB_API_KEY. MainActivity y DetailActivity ya la usan.
2. Cambia solo la configuración necesaria para que BuildConfig.OMDB_API_KEY se genere desde una constante literal con la clave OMDb de examen. Toma el valor de OMDB_API_KEY del archivo local.properties del equipo del alumno; no inventes ni uses un placeholder como valor final. Si el archivo no contiene una clave, detente y pide al alumno introducirla; no publiques un proyecto roto.
3. Mantén local.properties en .gitignore: NO lo publiques, porque también puede contener rutas del SDK y otros datos. No publiques ningún otro secreto. Evita imprimir la clave en logs.
4. No modifiques Retrofit, las dos Activities, el RecyclerView, Picasso, XML, la navegación ni los modelos. No agregues bibliotecas, servidor, autenticación ni otras funciones.
5. Compila y prueba una búsqueda y el detalle. Si es posible, verifica que una compilación limpia no depende de la propiedad OMDB_API_KEY en local.properties (puede seguir siendo necesario local.properties para sdk.dir en el entorno local). Comprueba que el APK generado utiliza la clave integrada. No elimines la configuración local antes de guardar el valor.
6. Haz commit y push de los cambios mínimos a GitHub y confirma los archivos cambiados y el resultado. Si hay un error, informa de él sin afirmar éxito. Detente.

Nota: esta es una decisión específica para un examen y un repositorio público. Una clave integrada en una app puede ser extraída y reutilizada; no presentar esto como seguridad para producción.
