# Respuestas a PL2b

1. El camino más corto entre dos puntos se encuentra mirando primero a los conocidos directos de una persona, luego a los conocidos de esos conocidos, y así poco a poco. La primera vez que un camino llega al destino que buscas, esa es la ruta más rápida.


2. Un grafo es disjunto cuando hay grupos de información que no se tocan entre sí. En el código, esto se comprueba empezando desde un nombre cualquiera y viendo si somos capaces de llegar a todos los demás nombres del archivo. Si al terminar el recorrido nos han sobrado nombres, significa que el grafo está dividido.


3. Para averiguar qué físico nació en el mismo sitio que Einstein: primero mira dónde nació Einstein (que es Ulm), luego busca qué otras personas de la lista también nacieron en Ulm y, finalmente, comprueba si esas personas tienen la etiqueta de físico. Así es como el sistema acaba encontrando a Max Planck.


4. Cuando añadimos a Antonio, creamos una pieza nueva que no tiene puentes con el resto de los científicos, por lo que el grafo se separa. Para dar una lista con los lugares de nacimiento de los premiados, el programa tiene que hacer dos paradas: primero busca quién tiene un premio Nobel y, una vez que tiene esos nombres, mira el dato de su lugar de nacimiento para imprimirlo.


5. En este trabajo usamos dos tipos de nodos: unos sirven para identificar cosas únicas, como personas, ciudades o universidades específicas; otras sirven para agrupar, como las profesiones o los nombres de los premios que pueden tener varias personas a la vez.


6. Una ontología es el libro de instrucciones que dice qué relaciones tienen sentido. Por ejemplo, establece que una persona puede nacer en una ciudad, pero que una ciudad no puede nacer en una persona. 
6.1. Nos sirve para que los datos estén bien organizados y el programa sepa cómo conectar la información de manera lógica. 
6.2. Se podría hacer y hacer que el sistema sea más inteligente. 
6.3. Haría que, por ejemplo, todo el que gana un Nobel de Física es físico, y esto hace que sea mucho más completo.