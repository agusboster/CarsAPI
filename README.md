# CarsAPI

Instructivo de Ejecución

La base de datos es MySQL, fue hosteada con Clever Cloud y ya fue populada con algunos datos (los mismos que se encuentran en el repo en src > main > resources > sql) por lo cual pueden directamente probarse las consultas de tipo GET.
La API fue deployada con heroku, por lo cual puede probarse directamente desde el browser o postman utilizando los siguientes endpoints: 
(enviar siempre el Header “Content/application” con valor “application/json”)

GetAll (método GET): https://cars-api-exercise.herokuapp.com/carsAPI/getAll

FindById (método GET): https://cars-api-exercise.herokuapp.com/carsAPI/cars/{introducirId}

Save (método POST): https://cars-api-exercise.herokuapp.com/carsAPI/ (mandar en el body el JSON de un car, y en el parámetro de optionals la lista con los json de los optionals deseados).

Update (método PUT): https://cars-api-exercise.herokuapp.com/carsAPI/ (mandar en el body el JSON de un car, y en el parámetro de optionals la lista con los json de los optionals deseados).

Delete (método DELETE): https://cars-api-exercise.herokuapp.com/carsAPI/{introducirId}

Stats (método GET): https://cars-api-exercise.herokuapp.com/carsAPI/stats

Obtener precio de un auto en particular por su id (método GET): https://cars-api-exercise.herokuapp.com/carsAPI/carPrice/{introducirId}




Decisiones de Diseño

En cuanto al modelado de objetos se utilizó el diseño de layering, en el cual hay tres capas: persistencia o repository, de negocio o service, y controller. Las clases creadas son Car, Optional y Stat, y no realicé ninguna clase hija (por ejemplo sedan, o en el caso de lo optionals airbag, etc) porque en un principio no me pareció necesario hacerla, no vi comportamiento diferente al ser meras consultas de precio. Luego testeando me di cuenta que sí hubiera estado bueno para realizar un update de algún optional de un auto o mismo de un auto ya creado, para no tener que hacer ‘ifes’ según el nuevo nombre y el precio que corresponda. No realicé dicho cambio porque pasaron dos semanas desde que se me dió la consigna y no quise demorar más.
En cuanto al modelo relacional lo hice lo más simple posible para este caso. Lo mejor hubiera sido normalizar para no tener tantos datos repetidos. Por ejemplo tener una tabla donde se guarde para cada auto básico su precio, lo mismo para los opcionales, y luego otras dos donde se guarden las compras de autos y de opcionales, todo con las fk correspondientes. Además el precio final decidí no persistirlo por ser un dato calculable.
Por último me tomé la libertad de agregar a la API un endpoint para el método que calcula el precio final de un auto.
