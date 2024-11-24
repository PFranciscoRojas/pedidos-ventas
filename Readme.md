# Instrucciones para Realizar Solicitudes en Postman

Este proyecto implementa un servicio de gestión de órdenes, clientes y productos. A continuación, se describe cómo configurar y realizar solicitudes a las diferentes rutas del servicio utilizando Postman.

## Introducción
1. **Requisitos Previos:**
   - Instalar Postman: [Descargar Postman](https://www.postman.com/downloads/).
   - El servicio debe estar corriendo en el puerto `8080` de tu servidor local.

2. **Base URL:**
   ```
   http://localhost:8080
   ```

3. **Cabeceras Comunes:**
   - `Content-Type: application/json`

---

## Endpoints Disponibles

### 1. Gestión de Productos

#### 1.1. Añadir Producto
- **URL:** `/products`
- **Método:** `POST`
- **Cuerpo de la Solicitud:**
```json
{
    "name": "Nombre del Producto",
    "description": "Descripción del Producto",
    "price": 100.0,
    "stock": 10
}
```
- **Respuesta Exitosa:**
```json
{
    "productId": 1,
    "name": "Nombre del Producto",
    "description": "Descripción del Producto",
    "price": 100.0,
    "stock": 10
}
```

#### 1.2. Consultar Producto por ID
- **URL:** `/products/{id}`
- **Método:** `GET`
- **Respuesta Exitosa:**
```json
{
    "productId": 1,
    "name": "Nombre del Producto",
    "description": "Descripción del Producto",
    "price": 100.0,
    "stock": 10
}
```

#### 1.3. Listar Productos
- **URL:** `/products`
- **Método:** `GET`
- **Respuesta Exitosa:**
```json
[
    {
        "productId": 1,
        "name": "Nombre del Producto",
        "description": "Descripción del Producto",
        "price": 100.0,
        "stock": 10
    }
]
```

---

### 2. Gestión de Clientes

#### 2.1. Añadir Cliente
- **URL:** `/customers`
- **Método:** `POST`
- **Cuerpo de la Solicitud:**
```json
{
    "name": "Nombre del Cliente",
    "email": "cliente@example.com",
    "phoneNumber": "123456789"
}
```
- **Respuesta Exitosa:**
```json
{
    "customerId": 1,
    "name": "Nombre del Cliente",
    "email": "cliente@example.com",
    "phoneNumber": "123456789"
}
```

#### 2.2. Consultar Cliente por ID
- **URL:** `/customers/{id}`
- **Método:** `GET`
- **Respuesta Exitosa:**
```json
{
    "customerId": 1,
    "name": "Nombre del Cliente",
    "email": "cliente@example.com",
    "phoneNumber": "123456789"
}
```

#### 2.3. Listar Clientes
- **URL:** `/customers`
- **Método:** `GET`
- **Respuesta Exitosa:**
```json
[
    {
        "customerId": 1,
        "name": "Nombre del Cliente",
        "email": "cliente@example.com",
        "phoneNumber": "123456789"
    }
]
```

---

### 3. Gestión de Órdenes

#### 3.1. Crear una Orden
- **URL:** `/orders`
- **Método:** `POST`
- **Cuerpo de la Solicitud:**
```json
{
    "customerId": 1,
    "orderDate": "2024-11-20T12:00:00",
    "status": "PENDING",
    "totalAmount": 100.0,
    "orderDetails": [
        {
            "productId": 1,
            "quantity": 2,
            "price": 50.0
        }
    ]
}
```
- **Respuesta Exitosa:**
```json
{
    "orderId": 1,
    "customerId": 1,
    "orderDate": "2024-11-20T12:00:00",
    "status": "PENDING",
    "totalAmount": 100.0,
    "orderDetails": [
        {
            "detailId": 1,
            "orderId": 1,
            "productId": 1,
            "productName": "Nombre del Producto",
            "productDescription": "Descripción del Producto",
            "quantity": 2,
            "price": 50.0
        }
    ]
}
```

#### 3.2. Consultar Orden por ID
- **URL:** `/orders/{id}`
- **Método:** `GET`
- **Respuesta Exitosa:**
```json
{
    "orderId": 1,
    "customerId": 1,
    "orderDate": "2024-11-20T12:00:00",
    "status": "PENDING",
    "totalAmount": 100.0,
    "orderDetails": [
        {
            "detailId": 1,
            "orderId": 1,
            "productId": 1,
            "productName": "Nombre del Producto",
            "productDescription": "Descripción del Producto",
            "quantity": 2,
            "price": 50.0
        }
    ]
}
```

#### 3.3. Actualizar Estado de Orden
- **URL:** `/orders/{id}/status`
- **Método:** `PUT`
- **Cuerpo de la Solicitud:**
```json
"COMPLETED"
```
- **Respuesta Exitosa:**
```json
{
    "orderId": 1,
    "status": "COMPLETED"
}
```

---

## Notas Adicionales
- Usa las cabeceras:
   - `Content-Type: application/json`
   - `Accept: application/json`
- Asegúrate de que los IDs de cliente y producto existan en la base de datos antes de realizar solicitudes.
