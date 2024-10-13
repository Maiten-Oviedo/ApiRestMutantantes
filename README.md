# 🧬 Mutant DNA API

### Accede a la api en línea
 - **POST** https://apirestmutantesspringboot.onrender.com/are-you-mutant/api/v1/mutant
 - **GET** https://apirestmutantesspringboot.onrender.com/are-you-mutant/api/v1/stats
 

## 📑 Descripción

Este proyecto es una API desarrollada con **Spring Boot** que permite detectar si una secuencia de ADN corresponde a un mutante o no, siguiendo una lógica específica para identificar patrones en el ADN.

## 🗺️ Diagrama de Secuencia
![diagrama-de-secuencia](https://github.com/user-attachments/assets/6131d02d-91d6-4ada-8894-e3bd11378bd8)


### Funcionalidades principales:
- Detectar ADN mutante a partir de una secuencia de ADN.
- Validación personalizada de ADN.
- Manejo de excepciones y respuestas HTTP apropiadas (200 OK para mutantes, 403 Forbidden para humanos).

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Gradle**
- **JUnit 5** para pruebas unitarias
- **Mockito** para simular dependencias
- **Hibernate Validator** para validaciones personalizadas
- **Lombok** (opcional para reducir código repetitivo)

## 🚀 Requisitos previos

Asegúrate de tener instalados los siguientes componentes antes de empezar:

- **Java 17** o superior
- **Gradle** 7.x o superior

## ⚙️ Instalación

1. Clona el repositorio en tu máquina local:
    ```bash
    git clone https://github.com/tuusuario/mutant-dna-api.git
    ```

2. Ve al directorio del proyecto:
    ```bash
    cd mutant-dna-api
    ```

3. Construye el proyecto con Gradle:
    ```bash
    ./gradlew build
    ```

## 🚦 Ejecución de la Aplicación

Para ejecutar la aplicación en un entorno local, usa el siguiente comando:

```bash
./gradlew bootRun
```
La API estará disponible en tu localHost en el puerto 8080.
**POST** http://localhost:8080/are-you-mutant/api/v1/mutant
**GET** http://localhost:8080/are-you-mutant/api/v1/stats

## 📑 Documentación de Endpoints

- **GET** `stats/`
  - **Descripción**: Devuelve las estadísticas de las verificaciones de ADN, incluyendo el número de mutantes, el número de humanos, y el ratio entre ambos.
  - **Responses**:
    - `200 OK`: Devuelve las estadísticas en el siguiente formato:
      ```json
      {
        "count_mutant_dna": 40,
        "count_human_dna": 100,
        "ratio": 0.4
      }
      ```

- **POST** `/are-you-mutant/api/v1/mutant/`
  - **Descripción**: Detecta si una secuencia de ADN corresponde a un mutante.
  - **Request Body**:
    ```json
    {
      "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
    }
    ```
  - **Restricciones**:
    - El ADN debe ser una secuencia cuadrada NxN.
    - Solo se permiten los caracteres `A`, `T`, `C`, `G`.
    - Se validará el formato de la secuencia utilizando una clase `DnaRequestDto` con la anotación `@ValidDna`, que lanza un error si la secuencia no es válida.
  
  - **Responses**:
    - `200 OK`: Si el ADN pertenece a un mutante.
      ```json
      {
        "isMutant": true
      }
      ```
    - `403 Forbidden`: Si el ADN no corresponde a un mutante.
      ```json
      {
        "isMutant": false
      }
      ```
    - `400 Bad Request`: Si la secuencia de ADN no es válida (por ejemplo, si no es NxN o contiene caracteres no permitidos).
      ```json
      {
        "status": 400,
        "error": "Bad Request",
        "message": "La solicitud de ADN no es válida.",
        "details": {
          "dna": "El array no es N x N."
        }
      }
      ```

## Uso de Postman con la API "Are You Mutant"

### Instrucciones detalladas para realizar peticiones con Postman:

#### 1. **Instalar Postman**
   - Si no tienes Postman instalado, puedes descargarlo desde [aquí](https://www.postman.com/downloads/).
   - Una vez instalado, abre la aplicación.

#### 2. **Crear una nueva petición**

   - Haz clic en el botón **"New"** y selecciona **"HTTP Request"**.

#### 3. **Realizar una petición POST al endpoint `/mutant`**
   - **Método**: Selecciona `POST` en el menú desplegable.
   - **URL**: Especifica la URL de tu API (ejemplo: `http://localhost:8080/are-you-mutant/api/v1/mutant/`).
   - **Body**: 
     - Selecciona la opción **"Body"**.
     - Marca la opción **"raw"** y selecciona **"JSON"** como formato de entrada.
     - Ingresa el siguiente contenido en el cuerpo de la petición:
       ```json
       {
         "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
       }
       ```

   - **Enviar petición**: Haz clic en el botón **"Send"** para enviar la solicitud.

#### 4. **Realizar una petición GET al endpoint `/stats`**
   - **Método**: Selecciona `GET`.
   - **URL**: Utiliza la URL `http://localhost:8080/are-you-mutant/api/v1/stats/`.
   - **Enviar petición**: Haz clic en **"Send"**.

#### 5. **Ver el historial de peticiones**
   - Postman almacena todas las solicitudes enviadas, por lo que puedes acceder al historial en la barra lateral izquierda para repetir cualquier solicitud con solo hacer clic sobre ella.

#### 6. **Guardar peticiones**
   - Puedes guardar cualquier solicitud en una colección para facilitar el acceso en el futuro. Simplemente haz clic en el botón **"Save"** y elige o crea una nueva colección.


