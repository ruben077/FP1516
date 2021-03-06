package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl2;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.Tutoria;
import fp.grados.tipos.TutoriaImpl;
import fp.grados.utiles.Grados;

public class TestProfesor2 {
	public static void main(String[] args) {
		testConstructor1Normal();
		testConstructor1Excepcion();
		
		testConstructor2Normal();
		testConstructor2Excepcion();
		
		testMetodoCreacionalParametrosNormal();
		
		testMetodoCreacionalCopiaNormal();
		
		testSetFechaNacimientoNormal();
		testSetFechaNacimientoExcepcion();

		testSetCategoriaNormal();
		testSetCategoriaExcepcion();
		
		testSetDepartamento();
		
		testGetDedicacionTotalNormal();
		
		testNuevaTutoria();

		testBorraTutoria();
		
		testBorraTutorias();
		
		testImparteAsignatura1Normal();
		testImparteAsignatura1Excepcion1();
		testImparteAsignatura1Excepcion2();
		testImparteAsignatura1Excepcion3();
		testImparteAsignatura1Excepcion4();
		testImparteAsignatura1Excepcion5();
		
		testImparteAsignatura2Normal();
		testImparteAsignatura2Excepcion1();
		testImparteAsignatura2Excepcion2();
		testImparteAsignatura2Excepcion3();
		testImparteAsignatura2Excepcion4();
		testImparteAsignatura2Excepcion5();
		
		testDedicacionAsignatura();
		
		testEliminaAsignatura();
		
		testMetodosPoblacionales();
	}

	private static void testConstructor1Normal() {
		System.out.println("========Probando el primer constructor======================================================================================");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.es", Categoria.TITULAR);
	}

	private static void testConstructor1Excepcion() {
		System.out.println("\n========Probando el primer constructor siendo menor de edad======================================================================================");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(2000, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR);
	}
	
	private static void testConstructor2Normal() {
		System.out.println("\n========Probando el segundo constructor======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.es", Categoria.TITULAR, dep);
	}

	private static void testConstructor2Excepcion() {
		System.out.println("\n========Probando el segundo constructor siendo menor de edad======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(2000, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, dep);
	}
	
	private static void testMetodoCreacionalParametrosNormal() {
		System.out.println("\n========Probando m�todo creacional por par�metros======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		testMetodoCreacionalParametros("12345678Z", "Pepe", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.es", Categoria.TITULAR, dep);
		//Nota: para hacer saltar la excepci�n, habr�a que modificar los datos
	}
	
	private static void testMetodoCreacionalCopiaNormal() {
		System.out.println("\n========Probando m�todo creacional por copia======================================================================================");
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.es", Categoria.TITULAR, lsi);
		lsi.nuevaAsignatura(fp);
		lsi.nuevoProfesor(pr);
		testMetodoCreacionalCopia(pr, fp, 12.0, LocalTime.of(10, 30), 30, DayOfWeek.FRIDAY);
		//Nota: para hacer saltar la excepci�n, habr�a que modificar los datos
	}
	
	private static void testSetFechaNacimientoNormal() {
		System.out.println("\n========Probando setFechaNacimiento======================================================================================");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.es", Categoria.TITULAR);
		testSetFechaNacimiento(pr, LocalDate.of(1960, 3, 15));
	}
	
	private static void testSetFechaNacimientoExcepcion() {
		System.out.println("\n========Probando setFechaNacimiento con un profesor menor de edad======================================================================================");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.es", Categoria.TITULAR);
		testSetFechaNacimiento(pr, LocalDate.of(2005, 3, 15));
	}

	private static void testSetCategoriaNormal() {
		System.out.println("\n========Probando setCategoria======================================================================================");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.es", Categoria.TITULAR);
		testSetCategoria(pr, Categoria.INTERINO);
	}
	
	private static void testSetCategoriaExcepcion() {
		System.out.println("\n========Probando setCategoria en el caso de que se cambie la categor�a a ayudante y supere el m�ximo de cr�ditos que puede impartir el profesor de dicha categor�a======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevaAsignatura(fp);
		lsi.nuevoProfesor(pr);
		testImparteAsignatura(fp, 12.0, pr);
		testSetCategoria(pr, Categoria.AYUDANTE);
	}
	
	private static void testSetDepartamento() {
		System.out.println("\n========Probando setDepartamento======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Departamento dep2 = new DepartamentoImpl("Ingenier�a de sistemas y autom�tica");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.es", Categoria.TITULAR, dep);
		testSetDepartamento(pr, dep2);
	}
	
	private static void testGetDedicacionTotalNormal(){
		System.out.println("\n========Probando el m�todo getDedicacionTotal======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Dise�o de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.ANUAL, 2);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevaAsignatura(fp);
		lsi.nuevaAsignatura(adda);
		lsi.nuevoProfesor(pr);
		testDedicacionAsignatura(fp, 12.0, lsi, pr);
		testDedicacionAsignatura(adda, 6.0, lsi, pr);
		testGetDedicacionTotal(pr);
	}

	private static void testNuevaTutoria(){
		System.out.println("\n========Probando nuevaTutoria======================================================================================");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR);
		testNuevaTutoria(LocalTime.of(10, 30), 30, DayOfWeek.FRIDAY, pr);
	}

	private static void testBorraTutoria(){
		System.out.println("\n========Probando borraTutoria======================================================================================");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR);
		pr.nuevaTutoria(LocalTime.of(10, 30), 60, DayOfWeek.FRIDAY);
		testBorraTutoria(LocalTime.of(10, 30), DayOfWeek.FRIDAY, pr);
	}
	
	private static void testBorraTutorias(){
		System.out.println("\n========Probando borraTutorias======================================================================================");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR);
		pr.nuevaTutoria(LocalTime.of(10, 30), 60, DayOfWeek.WEDNESDAY);
		pr.nuevaTutoria(LocalTime.of(12, 30), 30, DayOfWeek.FRIDAY);
		testBorraTutorias(pr);
	}

	private static void testImparteAsignatura1Normal() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de nuevaAsignatura======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevaAsignatura(fp);
		lsi.nuevoProfesor(pr);
		testImparteAsignatura(fp, 12.0, pr);
	}
	
	private static void testImparteAsignatura1Excepcion1() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de nuevaAsignatura, a�adiendo asignatura que no es del departamento del profesor======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevoProfesor(pr);
		testImparteAsignatura(fp, 12.0, pr);
	}
	
	private static void testImparteAsignatura1Excepcion2() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de nuevaAsignatura, excediendo el n�mero de cr�ditos de la asignatura======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevaAsignatura(fp);
		lsi.nuevoProfesor(pr);
		testImparteAsignatura(fp, 15.0, pr);
	}
	
	private static void testImparteAsignatura1Excepcion3() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de nuevaAsignatura, n�mero de cr�ditos igual a 0======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevaAsignatura(fp);
		lsi.nuevoProfesor(pr);
		testImparteAsignatura(fp, 0.0, pr);
	}
	
	private static void testImparteAsignatura1Excepcion4() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de nuevaAsignatura, con un profesor (que no es tipo ayudante) que imparte m�s creditos de los permitidos======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Dise�o de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.ANUAL, 2);
		Asignatura ir = new AsignaturaImpl("Ingenier�a de Requisitos", "2050020", 9.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 3);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);		
		lsi.nuevaAsignatura(fp);
		lsi.nuevaAsignatura(adda);
		lsi.nuevaAsignatura(ir);
		lsi.nuevoProfesor(pr);
		pr.imparteAsignatura(fp, 12.0);
		pr.imparteAsignatura(adda, 12.0);
		testImparteAsignatura(ir, 9.0, pr);
	}
	
	private static void testImparteAsignatura1Excepcion5() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de nuevaAsignatura, con un profesor de tipo 'ayudante' que imparte m�s creditos de los permitidos======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 3.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Dise�o de Datos y Algoritmos", "2050010", 5.0, TipoAsignatura.ANUAL, 2);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.AYUDANTE);		
		lsi.nuevaAsignatura(fp);
		lsi.nuevaAsignatura(adda);
		lsi.nuevoProfesor(pr);
		pr.imparteAsignatura(fp, 3.0);
		testImparteAsignatura(adda, 5.0, pr);
	}	
	
	private static void testImparteAsignatura2Normal() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de actualizaDedicacion======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);		
		lsi.nuevaAsignatura(fp);
		lsi.nuevoProfesor(pr);
		pr.imparteAsignatura(fp, 12.0);
		testImparteAsignatura(fp, 10.0, pr);
	}
	
	private static void testImparteAsignatura2Excepcion1() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de actualizaDedicacion, actualizando la asignatura de manera que no es del departamento del profesor======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Departamento isa = new DepartamentoImpl("Ingenier�a de sistemas y autom�tica");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);		
		lsi.nuevaAsignatura(fp);
		lsi.nuevoProfesor(pr);
		pr.imparteAsignatura(fp, 12.0);
		fp.setDepartamento(isa);
		testImparteAsignatura(fp, 7.0, pr);
	}
	
	private static void testImparteAsignatura2Excepcion2() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de actualizaDedicacion, excediendo el n�mero de cr�ditos de la asignatura======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevaAsignatura(fp);
		lsi.nuevoProfesor(pr);
		pr.imparteAsignatura(fp, 12.0);
		testImparteAsignatura(fp, 15.0, pr);
	}
	
	private static void testImparteAsignatura2Excepcion3() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de actualizaDedicacion, n�mero de cr�ditos igual a 0======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevaAsignatura(fp);
		lsi.nuevoProfesor(pr);
		pr.imparteAsignatura(fp, 12.0);
		testImparteAsignatura(fp, 0.0, pr);
	}
	
	private static void testImparteAsignatura2Excepcion4() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de actualizaDedicacion, con un profesor (que no es tipo ayudante) que imparte m�s creditos de los permitidos======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 30.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Dise�o de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.ANUAL, 2);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);		
		lsi.nuevaAsignatura(fp);
		lsi.nuevaAsignatura(adda);
		lsi.nuevoProfesor(pr);
		pr.imparteAsignatura(fp, 25.0);
		pr.imparteAsignatura(adda, 2.0);
		testImparteAsignatura(adda, 8.0, pr);
	}
	
	private static void testImparteAsignatura2Excepcion5() {
		System.out.println("\n========Probando el m�todo imparteAsignatura con map en el caso de actualizaDedicacion, con un profesor de tipo 'ayudante' que imparte m�s creditos de los permitidos======================================================================================");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 3.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Dise�o de Datos y Algoritmos", "2050010", 6.0, TipoAsignatura.ANUAL, 2);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.AYUDANTE);		
		lsi.nuevaAsignatura(fp);
		lsi.nuevaAsignatura(adda);
		lsi.nuevoProfesor(pr);
		pr.imparteAsignatura(fp, 3.0);
		pr.imparteAsignatura(adda, 2.0);
		testImparteAsignatura(adda, 4.0, pr);
	}
	
	private static void testDedicacionAsignatura() {
		System.out.println("\n========Probando el m�todo dedicacionAsignatura con map======================================================================================");
		Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevaAsignatura(fp);
		lsi.nuevoProfesor(pr);
		testDedicacionAsignatura(fp, 12.0, lsi, pr);
	}
	
	private static void testEliminaAsignatura() {
		System.out.println("\n========Probando el m�todo eliminaAsignatura con map======================================================================================");
		Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Profesor pr = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevaAsignatura(fp);
		lsi.nuevoProfesor(pr);
		testEliminaAsignatura(fp, 12.0, lsi, pr);
	}
	
	private static void testMetodosPoblacionales() {
		System.out.println("\n========Probando m�todos poblacionales======================================================================================");
		System.out.println("N�mero de profesores creados: " + Grados.getNumProfesoresCreados());
		System.out.println("Profesores creados: " + Grados.getProfesoresCreados());
	}
	
	
	
	//////////////////////////////////////////////////
	//M�todos auxiliares
	
	private static void testConstructor1(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email, Categoria categoria) {
		try {
			Profesor pr = new ProfesorImpl2(dni, nombre, apellidos, fechaNacimiento, email, categoria);
			mostrarProfesor(pr);
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("Se ha capturado la excepci�n ExcepcionProfesorNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepci�n inesperada.");
		}
	}
	
	private static void testConstructor2(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email, Categoria categoria, Departamento dep) {
		try {
			Profesor pr = new ProfesorImpl2(dni, nombre, apellidos, fechaNacimiento, email, categoria, dep);
			mostrarProfesor(pr);
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("Se ha capturado la excepci�n ExcepcionProfesorNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepci�n inesperada.");
		}
	}
	
	private static void testMetodoCreacionalParametros(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email, Categoria categoria, Departamento departamento) {
		try {
			Grados.setUsarImplementacionMapProfesor(true);
			Profesor pr = Grados.createProfesor(dni, nombre, apellidos, fechaNacimiento, email, categoria, departamento);
			mostrarProfesor(pr);
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("Se ha capturado la excepci�n ExcepcionProfesorNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepci�n inesperada.");
		}
	}
	
	private static void testMetodoCreacionalCopia(Profesor pr, Asignatura asig, Double dedicacion, LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia) {
		try {
			Grados.setUsarImplementacionMapProfesor(true);
			pr.imparteAsignatura(asig, dedicacion);
			pr.nuevaTutoria(horaComienzo, duracionMinutos, dia);
			Profesor res = Grados.createProfesor(pr);
			mostrarProfesor(res);
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("Se ha capturado la excepci�n ExcepcionProfesorNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepci�n inesperada.");
		}
	}
	
	private static void testSetFechaNacimiento(Profesor pr, LocalDate fechaNacimiento) {
		try {
			System.out.println("La fecha de nacimiento del profesor antes de la operaci�n es: " + pr.getFechaNacimiento());
			System.out.println("La nueva fecha de nacimiento es: " + fechaNacimiento);
			pr.setFechaNacimiento(fechaNacimiento);;
			System.out.println("La categor�a despu�s de la operaci�n es: " + pr.getFechaNacimiento());
		} catch(ExcepcionProfesorNoValido e){
			System.out.println("Se ha capturado la excepci�n ExcepcionProfesorNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepci�n inesperada.");
		}
	}

	private static void testSetCategoria(Profesor pr, Categoria nuevaCategoria) {
		try {
			System.out.println("La categor�a antes de la operaci�n es: " + pr.getCategoria());
			System.out.println("La nueva categor�a es: " + nuevaCategoria);
			pr.setCategoria(nuevaCategoria);
			System.out.println("La categor�a despu�s de la operaci�n es: " + pr.getCategoria());
		} catch(ExcepcionProfesorOperacionNoPermitida e){
			System.out.println("Se ha capturado la excepci�n ExcepcionProfesorOperacionNoPermitida: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepci�n inesperada.");
		}
	}
	
	private static void testSetDepartamento(Profesor pr, Departamento dep) {
		System.out.println("El departamento del profesor antes de la operaci�n es: " + pr.getDepartamento());
		System.out.println("El nuevo departamento es: " + dep);
		pr.setDepartamento(dep);
		System.out.println("El departamento del profesor despu�s de la operaci�n es: " + pr.getDepartamento());
	}
	
	private static void testGetDedicacionTotal(Profesor pr) {
		System.out.println("Las asignaturas impartidas por el profesor " + pr.getNombre() + " " + pr.getApellidos() + " son: " + pr.getAsignaturas());
		System.out.println("La dedicaci�n total es: " + pr.getDedicacionTotal());
	}

	private static void testNuevaTutoria(LocalTime horaComienzo, Integer duracion, DayOfWeek diaSemana, Profesor pr) {
		Tutoria tutoria = new TutoriaImpl(diaSemana, horaComienzo, duracion);
		System.out.println("Las tutorias del profesor antes de la operaci�n es: " + pr.getTutorias());
		pr.nuevaTutoria(horaComienzo, duracion, diaSemana);
		System.out.println("Nueva tutoria: " + tutoria);
		System.out.println("Las tutorias del profesor despu�s de la operaci�n es: " + pr.getTutorias());
	}

	private static void testBorraTutoria(LocalTime horaComienzo, DayOfWeek diaSemana, Profesor pr) {
		Tutoria tutoria = new TutoriaImpl(diaSemana, horaComienzo, 60);
		System.out.println("Las tutorias del profesor antes de la operaci�n es: " + pr.getTutorias());
		System.out.println("Tutoria a borrar: " + tutoria);
		pr.borraTutoria(horaComienzo, diaSemana);
		System.out.println("Las tutorias del profesor despu�s de la operaci�n es: " + pr.getTutorias());
	}
	
	private static void testBorraTutorias(Profesor pr) {
		System.out.println("Las tutorias del profesor antes de la operaci�n son: " + pr.getTutorias());
		System.out.println("Tutorias a borrar: " + pr.getTutorias());
		pr.borraTutorias();
		System.out.println("Las tutorias del profesor despu�s de la operaci�n es: " + pr.getTutorias());
	}
	
	private static void testImparteAsignatura(Asignatura asig, Double dedicacion, Profesor pr) {
		try{
			System.out.println("Asignaturas que imparte el profesor antes de la operaci�n: " +pr.getAsignaturas());
			System.out.println("Cr�ditos totales que imparte el profesor antes de la operaci�n: " + pr.getDedicacionTotal());
			System.out.println("Asignatura a la que se le va a realizar la operaci�n: " + asig);
			System.out.println("Profesor al que se le va a realizar la operaci�n: " + pr);
			System.out.println("Departamento al que pertenece la asignatura: " + asig.getDepartamento());
			System.out.println("Departamento al que pertenece el profesor: " + pr.getDepartamento());
			System.out.println("Cr�ditos de la asignatura: " + asig.getCreditos());
			System.out.println("Cr�ditos que va a impartir el profesor en dicha asignatura: " + dedicacion);
			pr.imparteAsignatura(asig, dedicacion);
			System.out.println("Asignaturas que imparte el profesor despu�s de la operaci�n: " + pr.getAsignaturas());
			System.out.println("Cr�ditos totales que imparte el profesor despu�s de la operaci�n: " + pr.getDedicacionTotal());
		} catch(ExcepcionProfesorOperacionNoPermitida e){
			System.out.println("Se ha capturado la excepci�n ExcepcionProfesorOperacionNoPermitida: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepci�n inesperada.");
		}
	}
	
	private static void testDedicacionAsignatura(Asignatura asig, Double creditos, Departamento dep, Profesor pr) {
		System.out.println("Asignaturas que imparte el profesor antes de la operaci�n: " + pr.getAsignaturas());
		System.out.println("Cr�ditos que imparte el profesor antes de la operaci�n: " + pr.getCreditos());
		System.out.println("Nueva asignatura que va a impartir el profesor: " + asig);
		System.out.println("Cr�ditos que va a impartir el profesor: " + creditos);
		System.out.println("- A�adiendo la asignatura " + asig.getNombre() + " al profesor " + pr.getNombre() + ": ");
		pr.imparteAsignatura(asig, creditos);
		System.out.println("Asignaturas que imparte el profesor despu�s de la operaci�n: " + pr.getAsignaturas());
		System.out.println("Cr�ditos que imparte el profesor despu�s de la operaci�n: " + pr.getCreditos());
		System.out.println("La dedicaci�n en " + asig.getNombre() + " es: " + pr.dedicacionAsignatura(asig));
	}
	
	private static void testEliminaAsignatura(Asignatura asig, Double creditos, Departamento dep, Profesor pr) {
		System.out.println("Asignaturas que imparte el profesor antes de la operaci�n: " + pr.getAsignaturas());
		System.out.println("Cr�ditos que imparte el profesor antes de la operaci�n: " + pr.getCreditos());
		System.out.println("Nueva asignatura que va a impartir el profesor: " + asig);
		System.out.println("Cr�ditos que va a impartir el profesor: " + creditos);
		System.out.println("- A�adiendo la asignatura " + asig.getNombre() + " al profesor " + pr.getNombre() + ": ");
		pr.imparteAsignatura(asig, creditos);
		System.out.println("Asignaturas que imparte el profesor despu�s de la operaci�n: " + pr.getAsignaturas());
		System.out.println("Cr�ditos que imparte el profesor despu�s de la operaci�n: " + pr.getCreditos());
		System.out.println("- Eliminando la asignatura " + asig.getNombre() + ": ");
		pr.eliminaAsignatura(asig);
		System.out.println("Asignaturas que imparte el profesor despu�s de la operaci�n: " + pr.getAsignaturas());
		System.out.println("Cr�ditos que imparte el profesor despu�s de la operaci�n: " + pr.getCreditos());
	}

	private static void mostrarProfesor(Profesor pr) {
		System.out.println("Profesor --> <" + pr + ">");
		System.out.println("\tDNI: <" + pr.getDNI() + ">");
		System.out.println("\tNombre: <" + pr.getNombre() + ">");
		System.out.println("\tApellidos: <" + pr.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <" + pr.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + pr.getEdad() + ">");
		System.out.println("\tEmail: <" + pr.getEmail() + ">");
		System.out.println("\tCategor�a: <" + pr.getCategoria() + ">");
		System.out.println("\tTutorias: <" + pr.getTutorias() + ">");
		System.out.println("\tDepartamento: <" + pr.getDepartamento() + ">");
		System.out.println("\tAsignaturas que imparte: <" + pr.getAsignaturas() + ">");
		System.out.println("\tCr�ditos que imparte: <" + pr.getCreditos() + ">");
	}
}