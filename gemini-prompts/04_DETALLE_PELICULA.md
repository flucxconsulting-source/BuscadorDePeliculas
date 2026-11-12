# Prompt 04 — Detalle de la película

Implementa DetailActivity en Kotlin y activity_detail.xml. Lee el identificador con intent.getStringExtra("imdbID") y llama al segundo endpoint Retrofit (parámetro i). Muestra exactamente los campos exigidos: Título, Año, Póster, Sinopsis (Plot), Duración (Runtime), Director, Género (Genre) y País (Country). Usa TextViews e ImageView; Picasso para el póster. Puedes usar ScrollView para ver todo el contenido en pantallas pequeñas.

Si falta imdbID o falla la llamada, muestra un mensaje sencillo. No añadas nuevos campos ni Activities, animaciones o arquitectura avanzada. Verifica compilación y navegación desde la lista. Haz commit y push solo de archivos sin secretos; detente.
