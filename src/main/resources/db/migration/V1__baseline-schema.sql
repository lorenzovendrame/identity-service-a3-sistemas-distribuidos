-- `identity`.address definition

CREATE TABLE `address` (
                           `address_id` binary(16) NOT NULL,
                           `city` varchar(30) NOT NULL,
                           `state` varchar(20) NOT NULL,
                           `district` varchar(50) NOT NULL,
                           `cep` varchar(8) NOT NULL,
                           `street_name` varchar(50) NOT NULL,
                           `building_number` varchar(10) NOT NULL,
                           `details` varchar(50) DEFAULT NULL,
                           PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- `identity`.permissions definition

CREATE TABLE `permissions` (
                               `permission_id` int NOT NULL,
                               `permission_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                               `permission_description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                               PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- `identity`.roles definition

CREATE TABLE `roles` (
                         `role_id` int NOT NULL,
                         `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         `role_description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                         PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- `identity`.role_permissions definition

CREATE TABLE `role_permissions` (
                                    `role_id` int NOT NULL,
                                    `permission_id` int NOT NULL,
                                    PRIMARY KEY (`role_id`,`permission_id`),
                                    KEY `role_permissions_permissions_fk` (`permission_id`),
                                    CONSTRAINT `role_permissions_permissions_fk` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`permission_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
                                    CONSTRAINT `role_permissions_roles_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- `identity`.users definition

CREATE TABLE `users` (
                         `user_id` binary(16) NOT NULL,
                         `name` varchar(50) DEFAULT NULL,
                         `cpf` varchar(11) NOT NULL,
                         `address_id` binary(16) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `phone_number` varchar(11) NOT NULL,
                         `date_of_birth` date NOT NULL,
                         `password` varchar(80) NOT NULL,
                         `created_at` datetime DEFAULT NULL,
                         PRIMARY KEY (`user_id`),
                         KEY `users_address_FK` (`address_id`),
                         CONSTRAINT `users_address_FK` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- `identity`.user_roles definition

CREATE TABLE `user_roles` (
                              `user_id` binary(16) NOT NULL,
                              `role_id` int NOT NULL,
                              PRIMARY KEY (`user_id`,`role_id`),
                              KEY `user_roles_roles_fk` (`role_id`),
                              CONSTRAINT `user_roles_roles_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
                              CONSTRAINT `user_roles_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;