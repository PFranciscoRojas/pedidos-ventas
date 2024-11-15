
INSERT INTO customer (name, email, phone, address)
VALUES 
('Juan Perez', 'juan.perez@gmail.com', '555-1234', 'Calle 123'),
('Maria Lopez', 'maria.lopez@gmail.com', '555-5678', 'Avenida 45');


INSERT INTO product (name, description, price, stock)
VALUES 
('Laptop', 'Laptop de 15 pulgadas', 1000.00, 10),
('Mouse', 'Mouse inalámbrico', 25.50, 50);
SELECT * FROM customer;

SELECT * FROM product;

http://localhost:8080/orders (POST)


{
  "customerId": 1,
  "orderDate": "2024-11-15",
  "status": "PENDING",
  "orderDetails": [
    {
      "productId": 1,
      "quantity": 2,
      "price": 1000.00
    }
  ]
}


http://localhost:8080/orders/5 (GET)


http://localhost:8080/orders/5/status (PUT)

{Pagado}


http://localhost:8080/orders/5 (DELETE)