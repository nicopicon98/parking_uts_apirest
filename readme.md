<h1>Parking UTS API Rest</h1>
This API controls the entire UTS parking system.

<h2>Getting Started</h2>
<h3>Properties</h3>

> To use the API, you'll need to make sure that the following system variables are set correctly in src/main/resources/application.properties:

* spring.datasource.url=DB_NAME
* spring.datasource.username=DB_USERNAME
* spring.datasource.password=DB_USERNAME_PASSWORD

<h3>Starting App Backend</h3>

1. Clone this repository on your local machine: git clone https://github.com/nicopicon98/parking_uts_apirest.git
2. Install Maven if you haven't already. You can download it from https://maven.apache.org/download.cgi
3. Open a terminal or command prompt and navigate to the project's root directory.
4. Run the following command to build the project: mvn clean install
5. After the build is successful, run the following command to start the application:
6. The application should now be running and can be accessed via the following URL: http://localhost:8080


<h3>Starting project on STS</h3>

1. Open a command prompt or terminal and navigate to the directory where you want to clone the project.
2. Clone the project by running the following command: git clone https://github.com/nicopicon98/parking_uts_apirest.git
3. Open Spring Tool Suite (STS) and go to File -> Import.
4. In the Import dialog box, expand the "Maven" folder and select "Existing Maven Projects". Click "Next".
5. Click the "Browse" button next to "Root Directory" and select the directory where you cloned the project. Make sure the pom.xml file is selected, then click "Finish".
6. Once the project has been imported, right-click on the project in the "Project Explorer" and select "Run As" -> "Spring Boot App".
7. The application should start up and be accessible at http://localhost:8080.

 

<h3>Endpoints</h3>

> The following endpoints are available in the API:

* **POST /auth:** Check auth user
* **GET /vehicles:** Returns a list of all available vehicles in the parking lot.
* **GET /vehicles/{id}:** Returns information about a specific vehicle with the provided ID.
* **POST /vehicles:** Adds a new vehicle to the parking lot. You'll need to provide the name, plate, and type of the vehicle in the request body.
* **PUT /vehicles/{id}:** Updates the state of a specific vehicle with the provided ID to "left" and sets the check-out time. You'll need to provide the ID of the vehicle in the URL.

<h3>Request and Response Formats</h3>

> The following request and response formats are used in the API:

<h4>POST /auth Request</h4>

> When trying to login, you'll need to provide the following information in the request body:

* **email (string):** user email.
* **password (string):** user password.

***Example:***
```
{
    "email": "admin@domain.com",
    "password": "1234"
}
```

<h4>POST /auth Response</h4>

> When trying to login, the API will return whether the email and password sent matched the database user

* **metadata (obj):** object that provides information about the status of the request or any error messages that occurred.
* **name (string | null):** The name of the user.
* **email (string | null):** The email of the user.

***Success Example***:

```
{
    "metadata": [
        {
            "code": "00",
            "data": "Success",
            "type": "ok"
        }
    ],
    "name": "admin",
    "email": "admin@domain.com"
}
```

***No Success Example***:

```
{
    "metadata": [
        {
            "code": "01",
            "data": "Email or password incorrect",
            "type": "nok"
        }
    ],
    "name": null,
    "email": null
}
```


<h4>GET /vehicles Request</h4>

> No body req needed.

<h4>GET /vehicles Response</h4>

> When getting information about a vehicle, the API will return the following information in the response body:

* **id (long):** The unique ID of the vehicle.
* **name (string):** The name of the vehicle's owner.
* **plate (string):** The license plate of the vehicle.
* **type (string):** The type of vehicle (e.g. "car", "motorcycle").
* **checkIn (string):** The check-in time of the vehicle.
* **state (int):** The current vehicle state. 0 = Vehicle is still in parking lot, 1 = Vehicle is no longer in parking lot

***Success Example***:

```
{
    "metadata": [
        {
            "tipo": "ok",
            "codigo": "00",
            "data": "Exito"
        }
    ],
    "vehicleResponse": {
        "vehicle": [
            {
                "check_in": "2023-02-26T22:50:21.287794",
                "name": "Walter Jhones Junior",
                "plate": "KJQ95D",
                "id": 5,
                "state": 0,
                "type": "Motocicleta"
            },
            {
                "check_in": "2023-02-26T23:27:19.710415",
                "name": "Walter Jhones",
                "plate": "KJQ95E",
                "id": 7,
                "state": 0,
                "type": "Carro"
            }
        ]
    }
}
```

***No Success Example***:

```
{
    "metadata": [
        {
            "tipo": "nok",
            "codigo": "-1",
            "data": "Error al recuperar los vehículos de la base de datos"
        }
    ],
    "vehicleResponse": null
}
```

<h4>POST /vehicles Request</h4>

> When adding a new vehicle, you'll need to provide the following information in the request body:

* **name (string):** The name of the vehicle's owner.
* **plate (string):** The license plate of the vehicle.
* **type (string):** The type of vehicle (e.g. "car", "motorcycle").

***Example:***
```
{
    "name": "Walter Jhones",
    "plate": "KJQ95E",
    "type": "Motocicleta"
}
```

<h4>POST /vehicles Response</h4>

> When adding a new vehicle, the API will return the following information in the response body:

* **id (long):** The unique ID of the vehicle.
* **name (string):** The name of the vehicle's owner.
* **plate (string):** The license plate of the vehicle.
* **type (string):** The type of vehicle (e.g. "car", "motorcycle").
* **checkIn (string):** The check-in time of the vehicle.
* **state (int):** The current vehicle state. 0 = Vehicle is still in parking lot, 1 = Vehicle is no longer in parking lot

***Sucess Example***:

```
{
    "metadata": [
        {
            "code": "00",
            "data": "Vehiculo ingresado con exito",
            "type": "ok"
        }
    ],
    "vehicleResponse": {
        "vehicle": [
            {
                "check_in": "2023-02-27T01:23:45.3739116",
                "name": "Walter Jhones",
                "plate": "KJQ95G",
                "id": 9,
                "state": 0,
                "type": "Motocicleta"
            }
        ]
    }
}
```

***No Sucess Example***:

```
{
    "metadata": [
        {
            "code": "-1",
            "data": "Vehículo aún no ha marcado a estado SALIDA",
            "type": "nok"
        }
    ],
    "vehicleResponse": null
}
```

<h4>GET /vehicles/{id} Request</h4>

> When getting a vehicle by id, you'll need to provide the following information in the request body:

> No body req needed.

<h4>GET /vehicles Response</h4>

> When getting information about a vehicle, the API will return the following information in the response body:

* **id (long):** The unique ID of the vehicle.
* **name (string):** The name of the vehicle's owner.
* **plate (string):** The license plate of the vehicle.
* **type (string):** The type of vehicle (e.g. "car", "motorcycle").
* **checkIn (string):** The check-in time of the vehicle.
* **state (int):** The current vehicle state. 0 = Vehicle is still in parking lot, 1 = Vehicle is no longer in parking lot
* **metadata (obj):** object that provides information about the status of the request or any error messages that occurred.

***Success Example***:

```
{
    "metadata": [
        {
            "code": "00",
            "data": "Exito",
            "type": "ok"
        }
    ],
    "vehicleResponse": {
        "vehicle": [
            {
                "check_in": "2023-02-26T22:44:52.987523",
                "name": "Walter Jhones",
                "plate": "KJQ95C",
                "id": 4,
                "state": 1,
                "type": "Motocicleta"
            }
        ]
    }
}
```

***No Success Example***:

```
{
    "metadata": [
        {
            "code": "-2",
            "data": "No se encontró ningún vehículo con el ID proporcionado",
            "type": "nok"
        }
    ],
    "vehicleResponse": null
}
```

<h4>PUT /vehicles/{id} Request</h4>

> When getting a vehicle by id, you'll need to provide the following information in the request body:

> No body req needed.

<h4>PUT /vehicles Response</h4>

> When getting information about a vehicle, the API will return the following information in the response body:

* **id (long):** The unique ID of the vehicle.
* **name (string):** The name of the vehicle's owner.
* **plate (string):** The license plate of the vehicle.
* **type (string):** The type of vehicle (e.g. "car", "motorcycle").
* **checkIn (string):** The check-in time of the vehicle.
* **checkOut (string):** The check-out time of the vehicle.
* **state (int):** The current vehicle state. 0 = Vehicle is still in parking lot, 1 = Vehicle is no longer in parking lot
* **metadata (obj):** object that provides information about the status of the request or any error messages that occurred.

***Success Example***:

```
{
    "metadata": [
        {
            "code": "00",
            "data": "Vehículo marcado a estado SALIDA exitosamente",
            "type": "ok"
        }
    ],
    "vehicleResponse": {
        "vehicle": [
            {
                "check_out": "2023-02-27T01:39:54.117",
                "check_in": "2023-02-26T23:27:19.710415",
                "name": "Walter Jhones",
                "plate": "KJQ95E",
                "id": 7,
                "state": 1,
                "type": "Motocicleta"
            }
        ]
    }
}
```

***No Success Example***:

```
{
    "metadata": [
        {
            "code": "-2",
            "data": "El vehiculo que intentas marcar estado a SALIDA no se encuentra en el parqueadero",
            "type": "nok"
        }
    ],
    "vehicleResponse": null
}
```

***No Success Example***:

```
{
    "metadata": [
        {
            "code": "-1",
            "data": "No se encontró ningún vehículo con el ID proporcionado",
            "type": "nok"
        }
    ],
    "vehicleResponse": null
}
```
