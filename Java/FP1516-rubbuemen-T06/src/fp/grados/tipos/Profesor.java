package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.SortedSet;

public interface Profesor extends Persona {
	Categoria getCategoria();
	SortedSet<Tutoria> getTutorias();
	Departamento getDepartamento();
	void setCategoria(Categoria categoria);
	void setDepartamento(Departamento d);
	
	void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia);
	void borraTutoria(LocalTime horaComienzo, DayOfWeek dia);
	void borraTutorias();
}
