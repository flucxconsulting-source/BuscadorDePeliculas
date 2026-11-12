# Prompt 03 — Pantalla de búsqueda y RecyclerView

Implementa solo la pantalla principal del examen. XML sencillo con TextView 'Buscador de películas', EditText para el título, Button 'Buscar' y RecyclerView. Crea un layout XML para cada fila: ImageView para póster y TextViews para título y año. Nada decorativo.

En Kotlin crea MovieAdapter que herede de RecyclerView.Adapter con ViewHolder. Muestra Title, Year y Poster de los resultados. Carga el póster usando Picasso. Usa LinearLayoutManager y findViewById en MainActivity. El botón Buscar llama al endpoint Retrofit de búsqueda del prompt 02. Actualiza la lista con los resultados; muestra un mensaje básico si está vacía o si falla la conexión. Al pulsar una película, abre DetailActivity mediante Intent y putExtra("imdbID", imdbID).

No añadas búsqueda automática, filtros, favoritos, base de datos, navegación avanzada ni funcionalidades adicionales. Comprueba compilación, haz commit y push sin secretos si funciona y detente.
