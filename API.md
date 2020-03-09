# Voluntaweb - API REST Documentation

## About our API
All you can find in our API REST is information about volunteerings of different NGOs, where users can give like to ONGs and join them. If any NGOs or Users try to do something wrong, API REST will send an error.

## How to use our API
1. Download [Postman](https://www.getpostman.com/).
2. You only can send GET, POST, PUT and DELETE requests.

## API requests

### Resources
The resource API has GET, POST, PUT and DELETE methods.
http: // localhost: 8443 followed by the containt request URL.

**All API queries have been preceded by /api**

## Authentication

#### User login
Allows a user to log in.

* ##### URL:

< /user/login >

* ##### Method:

`GET`

* ##### Success Response:
```
        {
          "id": 65,
          "registrations": [
               {},
               {},
               {},
               {},
               {}
          ],
          "name": "Rosa",
          "surname": "López",
          "email": "rosalpz95@gmail.com",
          "city": "Pamplona",
          "telephone": "680523542",
          "roles": [
                "ROLE_USER"
          ],
          "image": "false",
          "registeredAt": "2020-03-01T23:00:00.000+0000"
          "password":"cambiarlo en develop"
        }

```
 
 
* ##### Error response:

     **Code**: 401 UNAUTHORIZED

#### User logout  
Allows a user to log out.

* ##### URL:

< /user/logout >

* ##### Method:
`GET`

* ##### Success Response:

```
   true
```

  * ##### Error Response:

**Code**: 401 UNAUTHORIZED

#### NGO login  
Allows a ngo to log in.

* ##### URL:

     < /ong/login >

* ##### Method:

     `GET`

* ##### Success Response:

```
    {
                    "id": 29,
                    "name": "Médicos Sin Fronteras",
                    "responsiblename": "Juan Pablo",
                    "responsiblesurname": "Ramirez",
                    "description": "Médicos Sin Fronteras es una organización de acción médico-     humanitaria: asistimos a personas amenazadas por conflictos armados, violencia, epidemias o enfermedades olvidadas, desastres naturales y exclusión de la atención médica.  Nuestros equipos están formados por miles de profesionales de la salud, especialistas en logística y personal administrativo, unidos y regidos por nuestra carta magna en pos de brindar asistencia humanitaria. La acción humanitaria es un gesto solidario de sociedad civil a sociedad civil, de persona a persona, cuya finalidad es preservar la vida y aliviar el sufrimiento de otros seres humanos: esta es nuestra razón de ser. Nuestro accionar está guiado por la ética médica y los principios de imparcialidad, independencia y neutralidad, que se encuentran plasmados en nuestra carta magna.",
                    "address": "Calle de Cavanilles, 33, Madrid",
                    "email": "info@msf.es",
                    "postal": "28007",
                    "image": "true",
                    "volunteerings": [
                        {
                            "id": 232,
                            "name": "Agente de desarrollo local 3",
                            "category": {},
                            "startdate": "2020-03-08",
                            "enddate": "2020-08-09",
                            "description": "Este proyecto plantea, como objetivo fundamental, desarrollar y fortalecer las capacidades de respuesta de las Asambleas Locales, Comarcales e Insulares. Tiene como actores principales del mismo a los miembros de Órganos de Gobierno y personas con responsabilidad en el ámbito de la gestión de Cruz Roja Española en la red local. Decimos que dichas personas (presidentes/as, vicepresidentes/as, referentes de las distintas áreas/planes, etc.) serán los actores principales ya que a ellos y ellas corresponderá incorporar e implementar, en sus ámbitos correspondientes, las líneas de trabajo marcadas en este proyecto, con el apoyo de las estructuras de soporte de otros ámbitos de Cruz Roja, fundamentalmente provinciales o autonómicos uniprovinciales. El proyecto quiere incidir sobre los ámbitos clave que afectan a la realidad local: - las personas (sus capacidades y relaciones) - los procesos y metodologías - la gestión de los recursos. Para todo esto, por una parte, se plantea la mejora de las competencias de los Órganos de Gobierno y estructuras de gestión para, entre otros aspectos, asegurar el adecuado liderazgo que les corresponde. Asimismo, pretende articular mecanismos efectivos de relación y coordinación interna (dentro de la propia asamblea) y externa (con otros ámbitos de Cruz Roja, así como con agentes externos). Y todo ello, además, desarrollando estrategias para garantizar una gestión adecuada con las personas que componen nuestra base social, fomentando la participación a todos los niveles. Por otra parte, se plantea la adecuada implementación de procesos y metodologías, adecuadas a la realidad local, que mejoren la eficacia y eficiencia en nuestras respuestas. Siendo uno de los procesos clave la planificación anual del trabajo de la asamblea local/comarcal/insular, ajustada a la realidad y necesidades de su entorno y a la estrategia de la propia Organización; apostando, de forma decidida, por la evaluación y la mejora continua. Por último, el proyecto persigue propiciar las condiciones para una adecuada y eficiente gestión de los recursos (económicos, materiales, infraestructuras, humanos, etc.) con que la asamblea cuenta para lograr el cumplimiento de su misión. ",
                            "image": "true",
                            "city": "Alcalá de Henares",
                            "email": "informa@cruzroja.es"
                        }
                    ],
                    "telephone": "915411375"
                }
```

  * ##### Error Response:

**Code**: 401 UNAUTHORIZED

#### NGO logout  
Allows a ngo to log out.

* ##### URL:

< /ong/logout >

* ##### Method:

`GET`

* ##### Success Response:

```
   true
```

  * ##### Error Response:

**Code**: 401 UNAUTHORIZED

## Admin
The following queries will be preceded by /admin.
 
### Obtain Volunteering's list
Resource to show all volunteerings with their data.

* ##### URL

< /volunteerings >

* ##### Method:

`GET`

* ##### Success Response:

```
    [
    {
        "id": 4,
        "name": "Agente de desarrollo local",
        "category": {},
        "startdate": "2020-03-08",
        "enddate": "2020-08-09",
        "description": "Este proyecto plantea, como objetivo fundamental, desarrollar y fortalecer las capacidades de respuesta de las Asambleas Locales, Comarcales e Insulares. Tiene como actores principales del mismo a los miembros de Órganos de Gobierno y personas con responsabilidad en el ámbito de la gestión de Cruz Roja Española en la red local. Decimos que dichas personas (presidentes/as, vicepresidentes/as, referentes de las distintas áreas/planes, etc.) serán los actores principales ya que a ellos y ellas corresponderá incorporar e implementar, en sus ámbitos correspondientes, las líneas de trabajo marcadas en este proyecto, con el apoyo de las estructuras de soporte de otros ámbitos de Cruz Roja, fundamentalmente provinciales o autonómicos uniprovinciales. El proyecto quiere incidir sobre los ámbitos clave que afectan a la realidad local: - las personas (sus capacidades y relaciones) - los procesos y metodologías - la gestión de los recursos. Para todo esto, por una parte, se plantea la mejora de las competencias de los Órganos de Gobierno y estructuras de gestión para, entre otros aspectos, asegurar el adecuado liderazgo que les corresponde. Asimismo, pretende articular mecanismos efectivos de relación y coordinación interna (dentro de la propia asamblea) y externa (con otros ámbitos de Cruz Roja, así como con agentes externos). Y todo ello, además, desarrollando estrategias para garantizar una gestión adecuada con las personas que componen nuestra base social, fomentando la participación a todos los niveles. Por otra parte, se plantea la adecuada implementación de procesos y metodologías, adecuadas a la realidad local, que mejoren la eficacia y eficiencia en nuestras respuestas. Siendo uno de los procesos clave la planificación anual del trabajo de la asamblea local/comarcal/insular, ajustada a la realidad y necesidades de su entorno y a la estrategia de la propia Organización; apostando, de forma decidida, por la evaluación y la mejora continua. Por último, el proyecto persigue propiciar las condiciones para una adecuada y eficiente gestión de los recursos (económicos, materiales, infraestructuras, humanos, etc.) con que la asamblea cuenta para lograr el cumplimiento de su misión. ",
        "image": "true",
        "city": "Alcalá de Henares",
        "email": "informa@cruzroja.es"
    },
    {
        "id": 97,
        "name": "Concierto solidario contra la pobreza y la marginación",
        "category": {},
        "startdate": "2021-03-04",
        "enddate": "2021-03-05",
        "description": "¡Por un poco de música en nuestras vidas! Además de fomentar valores positivos y contar con múltiples beneficios, la música refuerza capacidades y aptitudes en los más pequeños. Desde Ayuda en Acción te invitamos a participar en nuestro concierto solidario Acordes con Solidaridad el próximo 4 de Marzo de 2021 en el Teatro Real de Madrid. Además de disfrutar de la mano del director Pablo Heras-Casado y la Orquesta Sinfónica de Madrid, estarás contribuyendo a una buena causa: luchar contra la pobreza infantil en España.  Todos los fondos recaudados serán destinados a nuestro programa Aquí también, que apoya a la infancia y familias en riesgo de pobreza y exclusión en nuestro país. ¿Quieres disfrutar de una de las actividades solidarias para niños más esperadas del año? ¡Te esperamos!",
        "image": "true",
        "city": "Buenos Aires",
        "email": "informacion@ayudaenaccion.org"
    },
    {
        "id": 98,
        "name": "Cuidado infantil",
        "category": {},
        "startdate": "2020-05-05",
        "enddate": "2020-07-18",
        "description": "El Programa de Voluntariado de Cuidado Infantil con sede en Pamplona, en una casa de acogida para niños. La casa tiene alrededor de 6-8 niños en el grupo de edad de hasta 3 años en un momento dado. Si bien la mayoría de los niños son adoptados cuando cruzan tres, hay otros niños que necesitan atención y cuidado continuos. Los viajeros en su año sabático que buscan oportunidades para trabajar para niños pueden optar por ser voluntarios para este proyecto. Esta puede ser una gran experiencia de aprendizaje para los voluntarios, ya que pueden pasar tiempo con los niños pequeños y probar su propio nivel de paciencia y resistencia. Este programa es muy adecuado para aquellos que aman estar cerca de los niños y pueden manejarlos realmente bien.",
        "image": "false",
        "city": "Pamplona",
        "email": "informa@aventura.org"
    },
    {
        "id": 224,
        "name": "Agente de desarrollo local",
        "category": {},
        "startdate": "2020-03-08",
        "enddate": "2020-08-09",
        "description": "Este proyecto plantea, como objetivo fundamental, desarrollar y fortalecer las capacidades de respuesta de las Asambleas Locales, Comarcales e Insulares. Tiene como actores principales del mismo a los miembros de Órganos de Gobierno y personas con responsabilidad en el ámbito de la gestión de Cruz Roja Española en la red local. Decimos que dichas personas (presidentes/as, vicepresidentes/as, referentes de las distintas áreas/planes, etc.) serán los actores principales ya que a ellos y ellas corresponderá incorporar e implementar, en sus ámbitos correspondientes, las líneas de trabajo marcadas en este proyecto, con el apoyo de las estructuras de soporte de otros ámbitos de Cruz Roja, fundamentalmente provinciales o autonómicos uniprovinciales. El proyecto quiere incidir sobre los ámbitos clave que afectan a la realidad local: - las personas (sus capacidades y relaciones) - los procesos y metodologías - la gestión de los recursos. Para todo esto, por una parte, se plantea la mejora de las competencias de los Órganos de Gobierno y estructuras de gestión para, entre otros aspectos, asegurar el adecuado liderazgo que les corresponde. Asimismo, pretende articular mecanismos efectivos de relación y coordinación interna (dentro de la propia asamblea) y externa (con otros ámbitos de Cruz Roja, así como con agentes externos). Y todo ello, además, desarrollando estrategias para garantizar una gestión adecuada con las personas que componen nuestra base social, fomentando la participación a todos los niveles. Por otra parte, se plantea la adecuada implementación de procesos y metodologías, adecuadas a la realidad local, que mejoren la eficacia y eficiencia en nuestras respuestas. Siendo uno de los procesos clave la planificación anual del trabajo de la asamblea local/comarcal/insular, ajustada a la realidad y necesidades de su entorno y a la estrategia de la propia Organización; apostando, de forma decidida, por la evaluación y la mejora continua. Por último, el proyecto persigue propiciar las condiciones para una adecuada y eficiente gestión de los recursos (económicos, materiales, infraestructuras, humanos, etc.) con que la asamblea cuenta para lograr el cumplimiento de su misión. ",
        "image": "true",
        "city": "Alcalá de Henares",
        "email": "informa@cruzroja.es"
    },
    {
        "id": 232,
        "name": "Agente de desarrollo local 3",
        "category": {},
        "startdate": "2020-03-08",
        "enddate": "2020-08-09",
        "description": "Este proyecto plantea, como objetivo fundamental, desarrollar y fortalecer las capacidades de respuesta de las Asambleas Locales, Comarcales e Insulares. Tiene como actores principales del mismo a los miembros de Órganos de Gobierno y personas con responsabilidad en el ámbito de la gestión de Cruz Roja Española en la red local. Decimos que dichas personas (presidentes/as, vicepresidentes/as, referentes de las distintas áreas/planes, etc.) serán los actores principales ya que a ellos y ellas corresponderá incorporar e implementar, en sus ámbitos correspondientes, las líneas de trabajo marcadas en este proyecto, con el apoyo de las estructuras de soporte de otros ámbitos de Cruz Roja, fundamentalmente provinciales o autonómicos uniprovinciales. El proyecto quiere incidir sobre los ámbitos clave que afectan a la realidad local: - las personas (sus capacidades y relaciones) - los procesos y metodologías - la gestión de los recursos. Para todo esto, por una parte, se plantea la mejora de las competencias de los Órganos de Gobierno y estructuras de gestión para, entre otros aspectos, asegurar el adecuado liderazgo que les corresponde. Asimismo, pretende articular mecanismos efectivos de relación y coordinación interna (dentro de la propia asamblea) y externa (con otros ámbitos de Cruz Roja, así como con agentes externos). Y todo ello, además, desarrollando estrategias para garantizar una gestión adecuada con las personas que componen nuestra base social, fomentando la participación a todos los niveles. Por otra parte, se plantea la adecuada implementación de procesos y metodologías, adecuadas a la realidad local, que mejoren la eficacia y eficiencia en nuestras respuestas. Siendo uno de los procesos clave la planificación anual del trabajo de la asamblea local/comarcal/insular, ajustada a la realidad y necesidades de su entorno y a la estrategia de la propia Organización; apostando, de forma decidida, por la evaluación y la mejora continua. Por último, el proyecto persigue propiciar las condiciones para una adecuada y eficiente gestión de los recursos (económicos, materiales, infraestructuras, humanos, etc.) con que la asamblea cuenta para lograr el cumplimiento de su misión. ",
        "image": "true",
        "city": "Alcalá de Henares",
        "email": "informa@cruzroja.es"
    }
    ]
```
 
* ##### Error Response:

**Code**: 404 NOT FOUND
 
#### Obtain User's list

Resource to show all users with their data.

* ##### URL

< /users >

* ##### Method:

`GET`

* ##### Success Response:

```
 [
    {
        "id": 61,
        "name": "Raul",
        "surname": "Hernández",
        "email": "rhernan@gmail.com",
        "password": "$2a$10$r310SA86YRzSPqiRhn1AQ.iHOIGGjUpjDIfCLghA9ZYcqT8T6P2wC",
        "city": "Toledo",
        "telephone": "677555444",
        "roles": [
            "ROLE_USER"
        ],
        "image": "false",
        "registeredAt": "2020-03-01T23:00:00.000+0000"
    },
    {
        "id": 65,
        "name": "Rosa",
        "surname": "López",
        "email": "rosalpz95@gmail.com",
        "password": "$2a$10$PBGWibkbMOCYgwEWx/ZFR.wqoYgxOEQmEhruuEiYO6/tfTYzCvbDK",
        "city": "Pamplona",
        "telephone": "680523542",
        "roles": [
            "ROLE_USER"
        ],
        "image": "false",
        "registeredAt": "2020-03-01T23:00:00.000+0000"
    },
    {
        "id": 70,
        "name": "Samuel",
        "surname": "Sev",
        "email": "s.severiche.2017@alumnos.urjc.es",
        "password": "$2a$10$3IEJcOciLFsTd5vtkVjApOaH.xSoJosd.hrArWLSo8if8135A8yAy",
        "city": "Madrid",
        "telephone": "28009",
        "roles": [
            "ROLE_ADMIN"
        ],
        "image": "false",
        "registeredAt": "2020-03-01T23:00:00.000+0000"
    },
    {
        "id": 163,
        "name": "Jesús",
        "surname": "Hernández",
        "email": "jesushernandez@gmail.com",
        "password": "test",
        "city": "Murcia",
        "telephone": "688555444",
        "roles": [
            "ROLE_USER"
        ],
        "image": "false",
        "registeredAt": "2020-03-01T23:00:00.000+0000"
    },
    {
        "id": 205,
        "name": "Daniel",
        "surname": "Serrano",
        "email": "theroxmine@hotmail.es",
        "password": "$2a$10$Pj0ssI/7D8a6rvW9eWYXj.YxRzpIt1t.y7joag5NNFbFPC6HVRuvC",
        "city": "Getafe",
        "telephone": "34534563",
        "roles": [
            "ROLE_USER"
        ],
        "image": "false",
        "registeredAt": null
    },
    {
        "id": 212,
        "name": "Raul",
        "surname": "Martínez",
        "email": "d.fuente.2017@alumnos.urjc.es",
        "password": "$2a$10$cthW4EZP.r4GphWhfMKtu.7bi6ZWspzgTNuCKZNAXT45EZ5BuRFPG",
        "city": "Madrid",
        "telephone": "666666666",
        "roles": [],
        "image": "true",
        "registeredAt": null
    },
    {
        "id": 242,
        "name": "Daniel",
        "surname": "Serrano",
        "email": "admin@admin.com",
        "password": "$2a$10$xfhQweLKdOXcMl7ABv3bouGFojMdR5CxEcWMfikwKE9CeOFJtO1yi",
        "city": "Getafe",
        "telephone": "34534563",
        "roles": [
            "ROLE_ADMIN"
        ],
        "image": "false",
        "registeredAt": null
    }
 ]
```

* ##### Error Response:

**Code**: 404 NOT FOUND

#### Obtain NGO's list

Resource to show all ngos with their data.

* ##### URL

< /ngos >

* ##### Method:

`GET`

* ##### Success Response:

```
 [
    {
        "id": 15,
        "name": "Fundación de Ayuda a los Animales",
        "responsiblename": "Diez",
        "responsiblesurname": null,
        "description": "Tenemos el placer de darle la bienvenida a la Fundación de Ayuda a los Animales (F.A.A.).En donde el respeto a los animales, al amor y el cariño que sentimos hacia ellos es lo que mueve a nuestra Fundación a luchar día a día por todos aquellos animales que sufren el comportamiento inhumano con el que algunas personas premian su ayuda y fidelidad. Por eso desde la Fundación de Ayuda a los Animales luchamos para que todos los animales puedan tener una vida digna y para que animales abandonados puedan recibir el amor, la amistad y lealtad que merecen. Desde la FAA apoyamos a Organizaciones y Albergues dedicados a esta difícil lucha que es la Defensa de los Animales. Por otro lado, consideramos que colaborar en la lucha por un mundo más justo con los animales es colaborar por un mundo más solidario y menos violento en general",
        "address": "Calle Centro Comercial Mocha Chica, 0 S/N, Villanueva de la cañada",
        "email": "informa@aventura.org",
        "postal": "28691",
        "image": "true",
        "telephone": null
    },
    {
        "id": 29,
        "name": "Médicos Sin Fronteras",
        "responsiblename": "Juan Pablo",
        "responsiblesurname": "Ramirez",
        "description": "Médicos Sin Fronteras es una organización de acción médico-humanitaria: asistimos a personas amenazadas por conflictos armados, violencia, epidemias o enfermedades olvidadas, desastres naturales y exclusión de la atención médica.  Nuestros equipos están formados por miles de profesionales de la salud, especialistas en logística y personal administrativo, unidos y regidos por nuestra carta magna en pos de brindar asistencia humanitaria. La acción humanitaria es un gesto solidario de sociedad civil a sociedad civil, de persona a persona, cuya finalidad es preservar la vida y aliviar el sufrimiento de otros seres humanos: esta es nuestra razón de ser. Nuestro accionar está guiado por la ética médica y los principios de imparcialidad, independencia y neutralidad, que se encuentran plasmados en nuestra carta magna.",
        "address": "Calle de Cavanilles, 33, Madrid",
        "email": "info@msf.es",
        "postal": "28007",
        "image": "true",
        "telephone": "915411375"
    },
    {
        "id": 53,
        "name": "Fundación de Ayuda a los Animales",
        "responsiblename": "Pilar",
        "responsiblesurname": "Diez",
        "description": "Tenemos el placer de darle la bienvenida a la Fundación de Ayuda a los Animales (F.A.A.).En donde el respeto a los animales, al amor y el cariño que sentimos hacia ellos es lo que mueve a nuestra Fundación a luchar día a día por todos aquellos animales que sufren el comportamiento inhumano con el que algunas personas premian su ayuda y fidelidad. Por eso desde la Fundación de Ayuda a los Animales luchamos para que todos los animales puedan tener una vida digna y para que animales abandonados puedan recibir el amor, la amistad y lealtad que merecen. Desde la FAA apoyamos a Organizaciones y Albergues dedicados a esta difícil lucha que es la Defensa de los Animales. Por otro lado, consideramos que colaborar en la lucha por un mundo más justo con los animales es colaborar por un mundo más solidario y menos violento en general",
        "address": "Calle Centro Comercial Mocha Chica, 0 S/N, Villanueva de la cañada",
        "email": "info@ayudaanimales.org",
        "postal": "28691",
        "image": "true",
        "telephone": "918152334"
    },
    {
        "id": 54,
        "name": "Ayuda en acción",
        "responsiblename": "Jaime",
        "responsiblesurname": "Montalvo",
        "description": "Somos una ONG apartidista y aconfesional que lucha contra la pobreza y la desigualdad. Impulsamos la dignidad y  la solidaridad para la construcción de un mundo justo.\r\n\r\nTenemos más de 38 años de historia. Con tu ayuda hoy trabajamos en 20 países de América Latina, Asia, África y Europa, incluidos España y Portugal. Además, estamos desarrollando nuestra presencia en 3 nuevos países. En total apoyamos a más de 1,4 millones de personas.",
        "address": "Calle de Bravo Murillo, 178, Madrid",
        "email": "informacion@ayudaenaccion.org",
        "postal": "28020",
        "image": "true",
        "telephone": "900858588"
    },
    {
        "id": 55,
        "name": "Save the children",
        "responsiblename": "Andrés",
        "responsiblesurname": "Conde",
        "description": "Todos los niños tienen derecho a un futuro. En España y en todo el mundo, trabajamos cada día para asegurar que todos los niños sobreviven, aprenden y están protegidos frente a la violencia. Cuando se produce una emergencia, y los niños son más vulnerables, somos siempre los primeros en llegar y los últimos en marcharnos. Atendemos las necesidades de los niños y nos aseguramos de que sus voces son escuchadas. Conseguimos cambios duraderos en la vida de millones de niños, incluso en aquellos a los que cuesta más llegar.  Hacemos todo lo que sea necesario para lograr que todos los niños, cada día y cuando ocurre una emergencia, puedan cambiar sus vidas y el futuro que estamos construyendo juntos.",
        "address": "Calle del Dr. Esquerdo, 138, Madrid",
        "email": "recepcion.central@savethechildren.org",
        "postal": "28007",
        "image": "true",
        "telephone": "915130500"
    }
 ]
```

* ##### Error Response:

**Code**: 404 NOT FOUND

#### Obtain Comment's list

Resource to show all comments with their data.

* ##### URL

< /comments >

* ##### Method:

`GET`

* ##### Success Response:

```
 [
    {
        "id": 31,
        "name": "Daniel",
        "email": "theroxmine@hotmail.es",
        "message": "Todo parece funcionar correctamente."
    },
    {
        "id": 207,
        "name": "Voluntaweb",
        "email": "voluntaweb@voluntaweb.com",
        "message": "Mensaje de prueba"
    }
 ]
```

* ##### Error Response:

**Code**: 404 NOT FOUND

## Users
The following queries will be preceded by /user.
 
#### Resource to show all users with their data.

* ##### URL

< / >

* ##### Method:

`GET`

* ##### Success respone:

```
  [
    {
        "id": 61,
        "name": "Raul",
        "surname": "Hernández",
        "email": "rhernan@gmail.com",
        "password": "$2a$10$r310SA86YRzSPqiRhn1AQ.iHOIGGjUpjDIfCLghA9ZYcqT8T6P2wC",
        "city": "Toledo",
        "telephone": "677555444",
        "roles": [
            "ROLE_USER"
        ],
        "image": "false",
        "registeredAt": "2020-03-01T23:00:00.000+0000"
    },
    {
        "id": 65,
        "name": "Rosa",
        "surname": "López",
        "email": "rosalpz95@gmail.com",
        "password": "$2a$10$PBGWibkbMOCYgwEWx/ZFR.wqoYgxOEQmEhruuEiYO6/tfTYzCvbDK",
        "city": "Pamplona",
        "telephone": "680523542",
        "roles": [
            "ROLE_USER"
        ],
        "image": "false",
        "registeredAt": "2020-03-01T23:00:00.000+0000"
    },
    {
        "id": 70,
        "name": "Samuel",
        "surname": "Sev",
        "email": "s.severiche.2017@alumnos.urjc.es",
        "password": "$2a$10$3IEJcOciLFsTd5vtkVjApOaH.xSoJosd.hrArWLSo8if8135A8yAy",
        "city": "Madrid",
        "telephone": "28009",
        "roles": [
            "ROLE_ADMIN"
        ],
        "image": "false",
        "registeredAt": "2020-03-01T23:00:00.000+0000"
    },
    {
        "id": 163,
        "name": "Jesús",
        "surname": "Hernández",
        "email": "jesushernandez@gmail.com",
        "password": "test",
        "city": "Murcia",
        "telephone": "688555444",
        "roles": [
            "ROLE_USER"
        ],
        "image": "false",
        "registeredAt": "2020-03-01T23:00:00.000+0000"
    },
    {
        "id": 205,
        "name": "Daniel",
        "surname": "Serrano",
        "email": "theroxmine@hotmail.es",
        "password": "$2a$10$Pj0ssI/7D8a6rvW9eWYXj.YxRzpIt1t.y7joag5NNFbFPC6HVRuvC",
        "city": "Getafe",
        "telephone": "34534563",
        "roles": [
            "ROLE_USER"
        ],
        "image": "false",
        "registeredAt": null
    },
    {
        "id": 212,
        "name": "Raul",
        "surname": "Martínez",
        "email": "d.fuente.2017@alumnos.urjc.es",
        "password": "$2a$10$cthW4EZP.r4GphWhfMKtu.7bi6ZWspzgTNuCKZNAXT45EZ5BuRFPG",
        "city": "Madrid",
        "telephone": "666666666",
        "roles": [],
        "image": "true",
        "registeredAt": null
    },
    {
        "id": 242,
        "name": "Daniel",
        "surname": "Serrano",
        "email": "admin@admin.com",
        "password": "$2a$10$xfhQweLKdOXcMl7ABv3bouGFojMdR5CxEcWMfikwKE9CeOFJtO1yi",
        "city": "Getafe",
        "telephone": "34534563",
        "roles": [
            "ROLE_ADMIN"
        ],
        "image": "false",
        "registeredAt": null
    }
  ]
```
 
* ##### Error response:

**Code**: 404 NOT FOUND
 
#### Obtain a user

* ##### URL

< /{id}>

* ##### Método:

`GET`
 
* ##### URL Params:

`id=[long]`

* ##### Example of query:

* URL

`/api/user/212`

* ##### Success response:
```
{
    "id": 212,
    "registrations": [],
    "likes": [],
    "name": "Raul",
    "surname": "Martínez",
    "email": "d.fuente.2017@alumnos.urjc.es",
    "password": "$2a$10$cthW4EZP.r4GphWhfMKtu.7bi6ZWspzgTNuCKZNAXT45EZ5BuRFPG",
    "city": "Madrid",
    "telephone": "666666666",
    "roles": [],
    "image": "true",
    "registeredAt": null
}
```
* ##### Error response:

**Code**: 404 NOT FOUND

#### Create a new user

* ##### URL

< />

* ##### Method:

`POST`
* ##### Request Body:
```
{
    "name": "Pepe",
    "surname": "López",
    "email": "pepe99@gmail.com",
    "password": "test",
    "city": "Madrid",
    "telephone": "645645645",
    "roles": [
        "ROLE_USER"
    ],
    "image": "false"
}
```
* ##### Success response:
```
{
    "id": 254,
    "registrations": null,
    "likes": null,
    "name": "Pepe",
    "surname": "López",
    "email": "pepe99@gmail.com",
    "password": "$2a$10$P3rC3djKhlh0MTsaNE3nBehryy2wG6aIUBzl5zBewup2cR5GEoMO.",
    "city": "Madrid",
    "telephone": "645645645",
    "roles": [
        "ROLE_USER"
    ],
    "image": "false",
    "registeredAt": null
}
```
* ##### Error response:

**Code**: hay que ponerlo

#### Edit an user 

* ##### URL

< / >

* ##### Method:

`PUT`
 
* ##### Request Body:
```
{
    "name": "Pepe",
    "surname": "Merino",
    "email": "pepe99@gmail.com",
    "password": "12345",
    "city": "Lugo",
    "telephone": "677777777",
    "roles": [
        "ROLE_USER"
    ],
    "image": "false"
}
```
* ##### Success response:
```
{
    "id": 254,
    "registrations": [],
    "likes": [],
    "name": "Pepe",
    "surname": "Merino",
    "email": "pepe99@gmail.com",
    "password": "12345",
    "city": "Lugo",
    "telephone": "677777777",
    "roles": [
        "ROLE_USER"
    ],
    "image": "false",
    "registeredAt": null
}
```
* ##### Error response:

**Code**: 401 UNAUTHORIZED or 404 NOT FOUND

#### Delete an user 

* ##### URL

< /{id}>

* ##### URL Params:

`id=[long]`

* ##### Example of query:

* URL

`/api/user/248`

* ##### Method:

`DELETE`
 
* ##### Success response:
```

```
* ##### Error response:

**Code**: 

#### Guardar una imagen 

* ##### URL

< /image >

* ##### Method:

`POST`
 
* ##### Request Body:
```
MultipartFile
```
* ##### Success response:
```
{
        "id": 212,
        "name": "Raul",
        "surname": "Martínez",
        "email": "d.fuente.2017@alumnos.urjc.es",
        "city": "Madrid",
        "telephone": "666666666",
        "roles": [],
        "image": "true",
        "registeredAt": null
}
```
#### Otro metodo de imagenes 

* ##### URL

< /{id}/image >

* ##### Method:

`GET`
 
* ##### Path Variable:
```
`id=[long]`
```
* ##### Success response:
```
MultipartFile
```
* ##### Error response:

**Code**: 404 NOT FOUND

## Comment
The following queries will be preceded by /comments.

### Resource to show all comments

* ##### URL:

< / >

* ##### Method:

`GET`

* ##### Success response:

```
  [
    {
        "id": 31,
        "name": "Daniel",
        "email": "theroxmine@hotmail.es",
        "message": "Todo parece funcionar correctamente."
    },
    {
        "id": 207,
        "name": "Voluntaweb",
        "email": "voluntaweb@voluntaweb.com",
        "message": "Mensaje de prueba"
    }
  ]
```

### Obtain a comment

* ##### URL:

< /{id} >

* ##### Method:

`GET`

* ##### URL Params:

`id=[long]`

* ##### Example of query:

* URL

`/api/comments/31`

* ##### Success response:

```
{
    "id": 31,
    "name": "Daniel",
    "email": "theroxmine@hotmail.es",
    "message": "Todo parece funcionar correctamente."
}
```

* ##### Error response:

**Code:** 404 NOT FOUND


### Create a comment

* ##### URL:

< / >

* ##### Method:

`POST`

* ##### Request Body:

```
{
    "name": "Alicia",
    "email": "alicia@gmail.com",
    "message": "Me gustaría trabajar para ustedes"
}
```

* ##### Success response:

```
{
    "id": 256,
    "name": "Alicia",
    "email": "alicia@gmail.com",
    "message": "Me gustaría trabajar para ustedes"
}
```

### Delete a comment

* ##### URL:

< / {id}>

* ##### Method:

`DELETE`

* ##### URL Params:

`id=[long]`

* ##### Example of query:

* URL

`/api/comments/207`

* ##### Success response:
```
 {
    "id": 207,
    "name": "Voluntaweb",
    "email": "voluntaweb@voluntaweb.com",
    "message": "Mensaje de prueba"
 }
```

* ##### Error response:

**Code:** 404 NOT FOUND

## NGO
The following queries will be preceded by /ong.

### See all NGOs in the database

* ##### URL:

< / >

* ##### Method:

`GET`

* ##### Success response:

```
  [
    {
        "id": 15,
        "name": "Fundación de Ayuda a los Animales",
        "responsiblename": "Diez",
        "responsiblesurname": null,
        "description": "Tenemos el placer de darle la bienvenida a la Fundación de Ayuda a los Animales (F.A.A.).En donde el respeto a los animales, al amor y el cariño que sentimos hacia ellos es lo que mueve a nuestra Fundación a luchar día a día por todos aquellos animales que sufren el comportamiento inhumano con el que algunas personas premian su ayuda y fidelidad. Por eso desde la Fundación de Ayuda a los Animales luchamos para que todos los animales puedan tener una vida digna y para que animales abandonados puedan recibir el amor, la amistad y lealtad que merecen. Desde la FAA apoyamos a Organizaciones y Albergues dedicados a esta difícil lucha que es la Defensa de los Animales. Por otro lado, consideramos que colaborar en la lucha por un mundo más justo con los animales es colaborar por un mundo más solidario y menos violento en general",
        "address": "Calle Centro Comercial Mocha Chica, 0 S/N, Villanueva de la cañada",
        "email": "informa@aventura.org",
        "postal": "28691",
        "image": "true",
        "telephone": null
    },
    {
        "id": 29,
        "name": "Médicos Sin Fronteras",
        "responsiblename": "Juan Pablo",
        "responsiblesurname": "Ramirez",
        "description": "Médicos Sin Fronteras es una organización de acción médico-humanitaria: asistimos a personas amenazadas por conflictos armados, violencia, epidemias o enfermedades olvidadas, desastres naturales y exclusión de la atención médica.  Nuestros equipos están formados por miles de profesionales de la salud, especialistas en logística y personal administrativo, unidos y regidos por nuestra carta magna en pos de brindar asistencia humanitaria. La acción humanitaria es un gesto solidario de sociedad civil a sociedad civil, de persona a persona, cuya finalidad es preservar la vida y aliviar el sufrimiento de otros seres humanos: esta es nuestra razón de ser. Nuestro accionar está guiado por la ética médica y los principios de imparcialidad, independencia y neutralidad, que se encuentran plasmados en nuestra carta magna.",
        "address": "Calle de Cavanilles, 33, Madrid",
        "email": "info@msf.es",
        "postal": "28007",
        "image": "true",
        "telephone": "915411375"
    },
    {
        "id": 53,
        "name": "Fundación de Ayuda a los Animales",
        "responsiblename": "Pilar",
        "responsiblesurname": "Diez",
        "description": "Tenemos el placer de darle la bienvenida a la Fundación de Ayuda a los Animales (F.A.A.).En donde el respeto a los animales, al amor y el cariño que sentimos hacia ellos es lo que mueve a nuestra Fundación a luchar día a día por todos aquellos animales que sufren el comportamiento inhumano con el que algunas personas premian su ayuda y fidelidad. Por eso desde la Fundación de Ayuda a los Animales luchamos para que todos los animales puedan tener una vida digna y para que animales abandonados puedan recibir el amor, la amistad y lealtad que merecen. Desde la FAA apoyamos a Organizaciones y Albergues dedicados a esta difícil lucha que es la Defensa de los Animales. Por otro lado, consideramos que colaborar en la lucha por un mundo más justo con los animales es colaborar por un mundo más solidario y menos violento en general",
        "address": "Calle Centro Comercial Mocha Chica, 0 S/N, Villanueva de la cañada",
        "email": "info@ayudaanimales.org",
        "postal": "28691",
        "image": "true",
        "telephone": "918152334"
    },
    {
        "id": 54,
        "name": "Ayuda en acción",
        "responsiblename": "Jaime",
        "responsiblesurname": "Montalvo",
        "description": "Somos una ONG apartidista y aconfesional que lucha contra la pobreza y la desigualdad. Impulsamos la dignidad y la solidaridad para la construcción de un mundo justo.\r\n\r\nTenemos más de 38 años de historia. Con tu ayuda hoy trabajamos en 20 países de América Latina, Asia, África y Europa, incluidos España y Portugal. Además, estamos desarrollando nuestra presencia en 3 nuevos países. En total apoyamos a más de 1,4 millones de personas.",
        "address": "Calle de Bravo Murillo, 178, Madrid",
        "email": "informacion@ayudaenaccion.org",
        "postal": "28020",
        "image": "true",
        "telephone": "900858588"
    },
    {
        "id": 55,
        "name": "Save the children",
        "responsiblename": "Andrés",
        "responsiblesurname": "Conde",
        "description": "Todos los niños tienen derecho a un futuro. En España y en todo el mundo, trabajamos cada día para asegurar que todos los niños sobreviven, aprenden y están protegidos frente a la violencia. Cuando se produce una emergencia, y los niños son más vulnerables, somos siempre los primeros en llegar y los últimos en marcharnos. Atendemos las necesidades de los niños y nos aseguramos de que sus voces son escuchadas. Conseguimos cambios duraderos en la vida de millones de niños, incluso en aquellos a los que cuesta más llegar.  Hacemos todo lo que sea necesario para lograr que todos los niños, cada día y cuando ocurre una emergencia, puedan cambiar sus vidas y el futuro que estamos construyendo juntos.",
        "address": "Calle del Dr. Esquerdo, 138, Madrid",
        "email": "recepcion.central@savethechildren.org",
        "postal": "28007",
        "image": "true",
        "telephone": "915130500"
    }
  ]
```
* ##### Error response:

**Code:** 404 NOT FOUND

#### Find a NGO

* ##### URL:

< /{id} >

* ##### Method:

`GET`

* ##### URL Params:

`id=[long]`

* ##### Example of query:

* URL

`/api/ong/53`

* ##### Success response:

```
{
    "id": 53,
    "name": "Fundación de Ayuda a los Animales",
    "responsiblename": "Pilar",
    "responsiblesurname": "Diez",
    "description": "Tenemos el placer de darle la bienvenida a la Fundación de Ayuda a los Animales (F.A.A.).En donde el respeto a los animales, al amor y el cariño que sentimos hacia ellos es lo que mueve a nuestra Fundación a luchar día a día por todos aquellos animales que sufren el comportamiento inhumano con el que algunas personas premian su ayuda y fidelidad. Por eso desde la Fundación de Ayuda a los Animales luchamos para que todos los animales puedan tener una vida digna y para que animales abandonados puedan recibir el amor, la amistad y lealtad que merecen. Desde la FAA apoyamos a Organizaciones y Albergues dedicados a esta difícil lucha que es la Defensa de los Animales. Por otro lado, consideramos que colaborar en la lucha por un mundo más justo con los animales es colaborar por un mundo más solidario y menos violento en general",
    "address": "Calle Centro Comercial Mocha Chica, 0 S/N, Villanueva de la cañada",
    "email": "info@ayudaanimales.org",
    "postal": "28691",
    "image": "true",
    "volunteerings": [],
    "telephone": "918152334"
}
```

* ##### Error response:

**Code:** 404 NOT FOUND

#### Create a new NGO

* ##### URL:

< / >

* ##### Method:

`POST`

* ##### Request Body:

```

```

* ##### Success response:

```

```

* ##### Error response:

**Code:** 404 NOT FOUND

#### images

* ##### URL:

< /image/{id} >

* ##### Method:

`POST`

* ##### Request Body:

```
MultipartFile
```

* ##### Success response:

```
{
        "id": 29,
        "name": "Médicos Sin Fronteras",
        "responsiblename": "Ramón",
        "responsiblesurname": "Ramirez",
        "description": "Médicos Sin Fronteras es una organización de acción médico-humanitaria: asistimos a personas amenazadas por             conflictos armados, violencia, epidemias o enfermedades olvidadas, desastres naturales y exclusión de la atención médica.               Nuestros equipos están formados por miles de profesionales de la salud, especialistas en logística y personal administrativo,           unidos y regidos por nuestra carta magna en pos de brindar asistencia humanitaria. La acción humanitaria es un gesto solidario         de sociedad civil a sociedad civil, de persona a persona, cuya finalidad es preservar la vida y aliviar el sufrimiento de               otros seres humanos: esta es nuestra razón de ser. Nuestro accionar está guiado por la ética médica y los principios de                 imparcialidad, independencia y neutralidad, que se encuentran plasmados en nuestra carta magna.",
        "address": "Calle de Cavanilles, 33, Madrid",
        "email": "info@msf.es",
        "postal": "28007",
        "image": "true",
        "telephone": "915411375"
    }
```

* ##### Error response:

**Code:** 404 NOT FOUND

#### imagenes2

* ##### URL:

< /image/{id} >

* ##### Method:

`GET`

* ##### Path Variable:

```
`id=[long]`
```

* ##### Success response:

```
MultipartFile
```

* ##### Error response:

**Code:** 404 NOT FOUND

## Volunteering
The following queries will be preceded by /volunteering.

#### images

* ##### URL:

< /image/{id} >

* ##### Method:

`POST`

* ##### Request Body, Path Variable:

```
MultipartFile, `id=[long]`
```

* ##### Success response:

```
{
        "id": 4,
        "name": "Agente de desarrollo local",
        "category": {
            "id": 9,
            "name": "Protección de animales"
        },
        "startdate": "2020-03-08",
        "enddate": "2020-08-09",
        "description": "Este proyecto plantea, como objetivo fundamental, desarrollar y fortalecer las capacidades de respuesta de las          Asambleas Locales, Comarcales e Insulares. Tiene como actores principales del mismo a los miembros de Órganos de Gobierno y            personas con responsabilidad en el ámbito de la gestión de Cruz Roja Española en la red local. Decimos que dichas personas              (presidentes/as, vicepresidentes/as, referentes de las distintas áreas/planes, etc.) serán los actores principales ya que a            ellos y ellas corresponderá incorporar e implementar, en sus ámbitos correspondientes, las líneas de trabajo marcadas en este          proyecto, con el apoyo de las estructuras de soporte de otros ámbitos de Cruz Roja, fundamentalmente provinciales o                    autonómicos uniprovinciales. El proyecto quiere incidir sobre los ámbitos clave que afectan a la realidad local: - las                  personas (sus capacidades y relaciones) - los procesos y metodologías - la gestión de los recursos. Para todo esto, por una            parte, se plantea la mejora de las competencias de los Órganos de Gobierno y estructuras de gestión para, entre otros                  aspectos, asegurar el adecuado liderazgo que les corresponde. Asimismo, pretende articular mecanismos efectivos de relación y          coordinación interna (dentro de la propia asamblea) y externa (con otros ámbitos de Cruz Roja, así como con agentes externos).          Y todo ello, además, desarrollando estrategias para garantizar una gestión adecuada con las personas que componen nuestra base          social, fomentando la participación a todos los niveles. Por otra parte, se plantea la adecuada implementación de procesos y            metodologías, adecuadas a la realidad local, que mejoren la eficacia y eficiencia en nuestras respuestas. Siendo uno de los            procesos clave la planificación anual del trabajo de la asamblea local/comarcal/insular, ajustada a la realidad y necesidades          de su entorno y a la estrategia de la propia Organización; apostando, de forma decidida, por la evaluación y la mejora                  continua. Por último, el proyecto persigue propiciar las condiciones para una adecuada y eficiente gestión de los recursos               (económicos, materiales, infraestructuras, humanos, etc.) con que la asamblea cuenta para lograr el cumplimiento de su misión. ",
        "image": "true",
        "city": "Alcalá de Henares",
        "email": "informa@cruzroja.es"
    }
```

* ##### Error response:

**Code:** 404 NOT FOUND

#### imagenes2

* ##### URL:

< /image/{id} >

* ##### Method:

`GET`

* ##### Path Variable:

```
`id=[long]`
```

* ##### Success response:

```
MultipartFile
```

* ##### Error response:

**Code:** 404 NOT FOUND
