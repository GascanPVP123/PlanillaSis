
create database if not exists sistema_planilla;
use sistema_planilla;


create table proceso_periodos (
    id bigint not null auto_increment,
    mes char(20) not null,
    anio integer not null,
    estado char(20) not null,
    empleados integer not null,
    gastos decimal(15,2) not null,
    horas_extras integer not null,
    primary key (id)
) engine=innodb;

insert into proceso_periodos (mes, anio, estado, empleados, gastos, horas_extras) values
('Enero', 2025, 'CERRADO', 148, 24200000.00, 17900),
('Febrero', 2025, 'CERRADO', 150, 24550000.00, 18400),
('Marzo', 2025, 'CERRADO', 153, 25100000.00, 20200),
('Abril', 2025, 'CERRADO', 152, 24900000.00, 19500),
('Mayo', 2025, 'CERRADO', 155, 25800000.00, 21300),
('Junio', 2025, 'CERRADO', 158, 27100000.00, 23600),
('Julio', 2025, 'CERRADO', 160, 27950000.00, 22400),
('Agosto', 2025, 'CERRADO', 159, 27400000.00, 20800),
('Septiembre', 2025, 'CERRADO', 161, 28100000.00, 21500),
('Octubre', 2025, 'CERRADO', 164, 28900000.00, 22900),
('Noviembre', 2025, 'CERRADO', 163, 28450000.00, 23100),
('Diciembre', 2025, 'CERRADO', 166, 30500000.00, 27800),

('Enero', 2026, 'CERRADO', 165, 29000000.00, 22100),
('Febrero', 2026, 'CERRADO', 166, 29200000.00, 23400),
('Marzo', 2026, 'CERRADO', 168, 29800000.00, 24800),
('Abril', 2026, 'CERRADO', 165, 28815000.00, 24150),
('Mayo', 2026, 'CERRADO', 168, 29540000.00, 25100),
('Junio', 2026, 'EN_PROCESO', 171, 30610374.00, 29273),
('Julio', 2026, 'ABIERTO', 0, 0.00, 0),
('Agosto', 2026, 'ABIERTO', 0, 0.00, 0),
('Septiembre', 2026, 'ABIERTO', 0, 0.00, 0),
('Octubre', 2026, 'ABIERTO', 0, 0.00, 0),
('Noviembre', 2026, 'ABIERTO', 0, 0.00, 0),
('Diciembre', 2026, 'ABIERTO', 0, 0.00, 0);