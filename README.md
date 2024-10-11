# 🧬 Mutant DNA API

## 📑 Descripción

Este proyecto es una API desarrollada con **Spring Boot** que permite detectar si una secuencia de ADN corresponde a un mutante o no, siguiendo una lógica específica para identificar patrones en el ADN.

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
La API estará disponible en http://localhost:8080.

## 📑 Documentación de Endpoints

- **POST** `/are-you-mutant/api/v1/mutant/`
  - **Descripción**: Detecta si una secuencia de ADN corresponde a un mutante.
  - **Request Body**:
    ```json
    {
      "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
    }
    ```
  - **Responses**:
    - `200 OK`: Si el ADN pertenece a un mutante.
    - `403 Forbidden`: Si el ADN no corresponde a un mutante.
