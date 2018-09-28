# Bienvenidos al examen de Rappi!

El examen consiste en listar películas por categorías y ver su detalle, también trabaja de manera offline.


# Capas de la aplicación

Se utilizó clean architecture para realizar el proyecto y las capas son
**Data**
En esta capa se trata toda la información que llega desde el servidor
Las clases que se utilizaron para esta capa son:
1. Paquete entity:
1.1 Paquete Response: 
1.1.1 MovieListResponse: Encargada de mapear los datos de la lista de películas o series que           vienen desde el servidor
1.2 MovieEntity: Encargada de mapear cada item de la lista de películas o series que vienen desde el servidor
2. Paquete mapper:
2.1 MovieListDataMapper: Encargada de convertir las clases entitys a las clases model para el manejo de la data del servidor en las pantallas de la aplicación
3. Paquete rest:
3.1 ApiClient: Clase donde se encuentra construida la interface de Retrofit (que es la librería para realizar llamadas HHTP), además de los métodos que llaman a URL´s para traer la información desde el servidor

**Dominio**
En esta capa se obtiene la data ya tratada del servidor y acomodada para mostrarla en las pantallas
Las clases que se utilizaron para esta capa son:
1. Paquete interactor:
1.1 MoviesListCallback: Interface que comunica a la capa presenter que la data ya fue consultada del servidor
1.2 MoviesListInteractor: Clase que implementa los métodos de la capa repositorio para traer la información requerida
2. Paquete Model:    
2.1 ListMovieModel: Encargada de mapear los datos de la lista de películas o series que serán mostradas en las pantallas de la aplicación
2.2 MovieModel: Encargada de mapear cada item de la lista de películas o series que serán mostradas en las pantallas de la aplicación 
3. Paquete Repository;
3.1 MoviesListRepository: Interface encargada de hacer la llamada a los métodos del servidor y de la base de datos 
4. Paquete Storage:
4.1 Paquete db:
4.1.1 DaoFactory: Clase encargada de obtener la información de la base de datos interna
4.1.2 DatabaseHelper: Clase encargada de inicializar la base de datos y tener los métodos para actualizar la base de datos cuando un objeto cambio, es como un versionamiento
4.1.3 DatabaseManager: Clase encargada de inicializar el DatabaseHelper

**Presentación**
En esta capa se encarga de realizar todas las operaciones con la data ya tratada del servidor
1.  Paquete view:
1.1 BaseView: Interface donde se encuentran los métodos comunes de las demás interfaces, será heredada
1.2 MoviesListView: Interface donde se encuentran los métodos que irán en el activity de la lista de películas o series
2. MoviesListPresenter: Clase encargada de invocar los primeros métodos que irán hacia los diferentes repositorios de datos y traer la información, es decir tiene todas las operaciones para mostrar información o grabarla 
3. Presenter: Interface que tiene los métodos de attach y dettach de cada presenter que necesite el app, ya que un presenter solo debe pertenecerle a un activity

**app**
Este paquete está dentro de la capa Presentación, solo que por orden lo coloco aparte
1. ui:
1.1 activity:
1.1.1 MovieActivity: Esta clase muestra el detalle del la serio o película seleccionada
1.1.2 MovieListActivity: Esta clase muestra la lista por categoría de las películas o series, tiene paginado, un buscador offline y trabaja de manera offline también
1.1.3 SplashActivity: Esta clase muestra un intro del app cuando el usuario inicia el app
1.2 adapter:
1.2.1 MoviesAdapter: Esta clase utiliza el patrón adapter para poder mostrar la información bloque a bloque en la grilla, además que permite selecciona un elemento e ir a su detalle
1.3 core:
1.3.1 BaseCompatActivity: Esta clase tiene métodos usados por todos los activitys para poder enviar data de uno a otro o solamente abrirlos
1.4 listener:
1.4.1 MoviesListListener: Esta interface tiene métodos que serán implementados en la clase de la lista de películas o series, para mostrar data  e ir al detalle de un elemento de la grilla
1.5 util:
1.5.1 DateUtil: Utilitario para el manejo de fechas
1.5.2 NetworkUtils: Utilitario para validar la conexion de datos
1.5.3 RecyclerViewDecorator: Utilitario para manejar los espacios entre los items de la grilla
1.6 RappiApplication: Clase donde se inicializa el contexto para toda el app y sea utilizado por la misma

**Repository**
Este paquete está en la capa Data, solo que por orden lo coloco aparte
1. datasource:
1.1 database:
1.1.1 DatabaseMovieListDataStore: Clase que tiene métodos para obtener data de la base de datos y grabar o actualizar información a esta misma
1.1.2 MovieListDatabaseDataStore: Interface que tiene métodos que serán implementados para solicitar a la base de datos información y/o grabar o actualizar información
1.2 ws:
1.2.1 NetworkMoviesListDataStore: Clase que tiene métodos para obtener data de los webservices del servidor
1.2.2 MoviesListDataStore: Interface que tiene métodos que serán implementados para solicitar al servidor información requerida
1.3 MoviesListDataRepository: Clase que tendrá métodos que devolverán la información de la base de datos o del servidor
1.4 MoviesListDataStoreFactory: Clase que servirá para decidir si se desea elegir base de datos o servidor para traer información
2. RepositoryCallback: Clase que será implementada por los diferentes Repository para devolver success o failure según se requiera

**Extra**
1. Constants: Esta clase no va en ningun paquete, ya que tienen constantes que serán utilizadas por toda el app

## Preguntas
**1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?**
El principio de responsabilidad única consiste en que una clase o un método solo deben ocuparse de una y solo una responsabilidad.
Ejemplo: 
1.1 El método suma solo debe ocuparse de sumar y no de otra cosa
1.2 Un método init() solo debe ocuparse de inicializar valores u objetos, no deberían haber líneas de código externas a inicializar objetos. Para esto se crea otro método que realize otras acciones

**2. Qué características tiene, según su opinión, un “buen” código o código limpio?**
Para mí, el código limpio debe cumplir con los patrones S.O.L.I.D, o cumplir con la mayoría de estos
Single responsability
Open / close
Liskov subtitution
Interface segregation
Dependecy inversion

Es muy difícil lograr tener las 5 letras en el código pero con la mayoría que se cumplan no hay problema

Por otro lado también dicho código debe ser:
1. Ser testeable
2. Tener buen naming convention (El que decida el equipo de trabajo)
3. Poder ser leible y entendible
4. Tener una buena documentación
5. Que no hayan imports sin usar