Aquí la cración de mi tabla tarea con sus registros inventados.
  --

  
    drop table if exists tarea;
    
    Create table tarea (
  
  	id int primary key auto_increment,
   
  	titulo varchar(100),
   
    descripcion varchar(100),
    
    fecha_creacion Date default localtime,
    
    fecha_vencimiento Date,
    
    estado enum('PENDIENTE','EN_PROCESO','COMPLETADA') default'PENDIENTE'
      
    );
    
    insert into tarea (titulo,descripcion,fecha_creacion,fecha_vencimiento,estado) values
    
    ('Jugar un competi','Carrilear al bro','2024-05-23','2033-03-03','EN_PROCESO'),
    
    ('Conducir',null,'2012-06-09','2027-08-24','COMPLETADA'),
    
    ('Escribir','Rafa no quiere poner nada','2020-11-30','2022-09-17','COMPLETADA'),
    
    ('Destruir','No se tio','1943-04-28','1945-04-28','COMPLETADA');
