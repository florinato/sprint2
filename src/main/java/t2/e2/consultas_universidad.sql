--Listado de alumnos por nombre y apellidos ordenados alfabeticamente:
SELECT apellido1, apellido2, nombre FROM persona WHERE tipo = 'alumno' ORDER BY apellido1, apellido2, nombre;

--Alumnos sin numero de telefono:
SELECT nombre, apellido1, apellido2 FROM persona WHERE tipo = 'alumno' AND telefono IS NULL;

--Alumnos nacidos en 1999:
SELECT nombre, apellido1, apellido2 FROM persona WHERE tipo = 'alumno' AND YEAR(fecha_nacimiento) = 1999;

--Profesores sin numero de telefono y cuyo NIF termina en 'K':
SELECT nombre, apellido1, apellido2 FROM persona WHERE tipo = 'profesor' AND telefono IS NULL AND nif LIKE '%K';

--Asignaturas del primer cuatrimestre del tercer curso del grado con ID 7:
SELECT nombre FROM asignatura WHERE cuatrimestre = 1 AND curso = 3 AND id_grado = 7;

--Profesores y el departamento al que pertenecen:
SELECT p.apellido1, p.apellido2, p.nombre, d.nombre AS nombre_departamento FROM persona p JOIN profesor pr ON p.id = pr.id_profesor JOIN departamento d ON pr.id_departamento = d.id WHERE p.tipo = 'profesor' ORDER BY p.apellido1, p.apellido2, p.nombre;

--Asignaturas y curso escolar de un alumno con NIF especifico:
SELECT a.nombre, ce.anyo_inicio, ce.anyo_fin FROM alumno_se_matricula_asignatura asm JOIN asignatura a ON asm.id_asignatura = a.id JOIN curso_escolar ce ON asm.id_curso_escolar = ce.id JOIN persona p ON asm.id_alumno = p.id WHERE p.nif = '26902806M';

--Departamentos con profesores que imparten en el Grado en Ingenieria Informatica (Plan 2015):
SELECT DISTINCT d.nombre FROM departamento d JOIN profesor pr ON d.id = pr.id_departamento JOIN asignatura a ON pr.id_profesor = a.id_profesor WHERE a.id_grado = (SELECT id FROM grado WHERE nombre = 'Ingeniería Informática (Plan 2015)');

--Alumnos matriculados en el curso escolar 2018/2019:
SELECT p.nombre, p.apellido1, p.apellido2 FROM persona p JOIN alumno_se_matricula_asignatura asm ON p.id = asm.id_alumno JOIN curso_escolar ce ON asm.id_curso_escolar = ce.id WHERE ce.anyo_inicio = '2018' AND ce.anyo_fin = '2019';

--Profesores y sus departamentos (incluyendo profesores sin departamento):
SELECT d.nombre AS nombre_departamento, p.apellido1, p.apellido2, p.nombre FROM profesor pr LEFT JOIN departamento d ON pr.id_departamento = d.id JOIN persona p ON pr.id_profesor = p.id  ORDER BY d.nombre, p.apellido1, p.apellido2, p.nombre;

--Profesores que no estan asociados a un departamento:
SELECT p.nombre, p.apellido1, p.apellido2 FROM profesor pr LEFT JOIN departamento d ON pr.id_departamento = d.id JOIN persona p ON pr.id_profesor = p.id WHERE d.id IS NULL;

--Departamentos sin profesores asociados:
SELECT d.nombre FROM departamento d LEFT JOIN profesor pr ON d.id = pr.id_departamento WHERE pr.id_profesor IS NULL;

--Profesores que no imparten ninguna asignatura:
SELECT p.nombre, p.apellido1, p.apellido2 FROM profesor pr LEFT JOIN asignatura a ON pr.id_profesor = a.id_profesor JOIN persona p ON pr.id_profesor = p.id WHERE a.id IS NULL;

--Asignaturas sin profesor asignado:
SELECT a.nombre FROM asignatura a LEFT JOIN profesor pr ON a.id_profesor = pr.id_profesor WHERE pr.id_profesor IS NULL;

--Departamentos que no han impartido asignaturas en ningún curso escolar:
SELECT DISTINCT d.nombre FROM departamento d LEFT JOIN profesor pr ON d.id = pr.id_departamento LEFT JOIN asignatura a ON pr.id_profesor = a.id_profesor WHERE a.id IS NULL;

-- Nombre total de alumnos
SELECT COUNT(*) AS total_alumnos FROM persona WHERE tipo = 'alumno';

-- Alumnos nacidos en 1999
SELECT COUNT(*) AS alumnos_1999 FROM persona WHERE YEAR(fecha_nacimiento) = 1999 AND tipo = 'alumno';

-- Profesores por departamento
SELECT d.nombre AS nombre_departamento, COUNT(pr.id_profesor) AS cantidad_profesores FROM departamento d LEFT JOIN profesor pr ON d.id = pr.id_departamento GROUP BY d.nombre HAVING COUNT(pr.id_profesor) > 0 ORDER BY cantidad_profesores DESC;

-- Departamentos con cantidad de profesores
SELECT d.nombre AS nombre_departamento, COUNT(pr.id_profesor) AS cantidad_profesores FROM departamento d LEFT JOIN profesor pr ON d.id = pr.id_departamento GROUP BY d.nombre ORDER BY cantidad_profesores DESC;

-- Grados y cantidad de asignaturas
SELECT g.nombre AS nombre_grado, COUNT(a.id) AS cantidad_asignaturas FROM grado g LEFT JOIN asignatura a ON g.id = a.id_grado GROUP BY g.nombre ORDER BY cantidad_asignaturas DESC;

-- Grados con mas de 40 asignaturas
SELECT g.nombre AS nombre_grado, COUNT(a.id) AS cantidad_asignaturas FROM grado g LEFT JOIN asignatura a ON g.id = a.id_grado GROUP BY g.nombre HAVING COUNT(a.id) > 40 ORDER BY cantidad_asignaturas DESC;

-- Suma de creditos por tipo de asignatura
SELECT g.nombre AS nombre_grado, a.tipo, SUM(a.creditos) AS suma_creditos FROM grado g LEFT JOIN asignatura a ON g.id = a.id_grado GROUP BY g.nombre, a.tipo;

-- Alumnos matriculados por año escolar
SELECT ce.anyo_inicio, COUNT(DISTINCT asm.id_alumno) AS alumnos_matriculados FROM curso_escolar ce LEFT JOIN alumno_se_matricula_asignatura asm ON ce.id = asm.id_curso_escolar GROUP BY ce.anyo_inicio;

-- Profesores y cantidad de asignaturas impartidas
SELECT pr.id_profesor, p.nombre, p.apellido1, p.apellido2, COUNT(a.id) AS cantidad_asignaturas FROM profesor pr LEFT JOIN asignatura a ON pr.id_profesor = a.id_profesor JOIN persona p ON pr.id_profesor = p.id GROUP BY pr.id_profesor, p.nombre, p.apellido1, p.apellido2 ORDER BY cantidad_asignaturas DESC;

-- Alumno mas joven
SELECT * FROM persona WHERE tipo = 'alumno' ORDER BY fecha_nacimiento ASC LIMIT 1;

-- Profesores con departamento pero sin asignaturas
SELECT p.id, p.nombre, p.apellido1, p.apellido2 FROM persona p JOIN profesor pr ON p.id = pr.id_profesor LEFT JOIN asignatura a ON pr.id_profesor = a.id_profesor WHERE pr.id_departamento IS NOT NULL AND a.id IS NULL;
