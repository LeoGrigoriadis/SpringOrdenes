create database Dia27Manana;
drop database Dia27Manana;
use Dia27Manana;


DELIMITER $
drop trigger if exists restarCantidad $
CREATE TRIGGER restarCantidad BEFORE INSERT ON ticket
FOR EACH ROW BEGIN
    IF (SELECT stock FROM product WHERE product.code=NEW.code)<NEW.ammount_products THEN
        SIGNAL SQLSTATE '42927' 
        SET MESSAGE_TEXT = 'por el culo te la hinco';
    END IF;
    UPDATE product 
        SET product.stock=(product.stock - NEW.ammount_products)
        WHERE product.code=NEW.code;
END $
DELIMITER ;





