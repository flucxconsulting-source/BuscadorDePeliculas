# Prompt 02 — API OMDb con Retrofit

Continúa el proyecto del prompt 01. Implementa una interfaz Retrofit Kotlin con exactamente dos peticiones GET a https://www.omdbapi.com/: búsqueda por nombre usando parámetro s, y detalle por identificador usando parámetro i. Ambas incluyen apikey. Usa Gson converter, data classes sencillas para Search (Title, Year, imdbID, Poster), respuesta de búsqueda (Search, Response, Error) y detalle (Title, Year, Poster, Plot, Runtime, Director, Genre, Country, Response, Error). Usa llamadas asíncronas Retrofit Call/Callback/enqueue; sin coroutines.

La clave OMDb ya está activada. No la incluyas en commits ni en GitHub: utiliza un valor local que el alumno pueda configurar en local.properties (ignorado por Git), y una lectura sencilla mediante BuildConfig si ya está disponible. Si esto requiere ajustes de Gradle, mantenlos mínimos. No imprimas la clave en logs. Indica cómo introducirla localmente, sin poner su valor en los archivos del repositorio.

No crees pantallas aún. Comprueba compilación, resume archivos, haz commit y push solo de archivos sin secretos y detente.
