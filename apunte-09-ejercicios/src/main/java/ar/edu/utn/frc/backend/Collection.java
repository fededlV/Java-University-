package ar.edu.utn.frc.backend;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.edu.utn.frc.backend.modelo.Auto;

public class Collection {

	/*
	 * Devuelve la cantidad de autos de una marca y un año determinado
	 *
	 * @param autos array de autos
	 * @param marca marca a buscar
	 * @param anio año a buscar
	 * @return cantidad de autos de una marca y un año determinado
	 */
	public static long obtenerCantidadPorMarcaYAnio(List<Auto> autos, String marca, int anio) {
		return autos
				.stream()
				.filter(auto -> auto.getAnio() == anio && auto.getMarca().equalsIgnoreCase(marca))
				.count();
	}

	/*
	 * Devuelve la cantidad de autos convertibles
	 *
	 * @param autos array de autos
	 * @return cantidad de autos convertibles
	 */
	public static long obtenerCantidadDeConvertibles(List<Auto> autos) {
		return autos
				.stream()
				.filter(auto -> auto.getTipos().contains("Convertible"))
				.count();
	}

	/*
	 * Devuelve un array con las marcas que vendan sedanes
	 *
	 * @param autos array de autos
	 * @return array de marcas
	 */
	public static Set<String> obtenerLasMarcasQueVendanSedanes(List<Auto> autos) {
		return autos
				.stream()
				.filter(auto -> auto.getTipos().contains("Sedan"))
				.map(Auto::getMarca)
				.collect(Collectors.toSet());
	}

	/*
	 * Devuelve un map con la cantidad de autos por marca
	 *
	 * @param autos array de autos
	 * @return map con la cantidad de autos por marca
	 */
	public static Map<String, Long> obtenerCantidadDeAutosPorMarca(List<Auto> autos) {
		return autos
				.stream()
				.map(Auto::getMarca)// Transforma el flujo de autos en un flujo de marcas (String)
				.collect(Collectors.groupingBy(m -> m, Collectors.counting())); //Agrupa por marca y cuenta cuántos hay
	}

	/*
	 * Devuelve un map con la cantidad de autos por año
	 *
	 * @param autos array de autos
	 * @return map con la cantidad de autos por año
	 */
	public static Map<Integer, Long> obtenerCantidadDeAutosPorAnio(List<Auto> autos) {
		return autos
				.stream()
				.map(Auto::getAnio)
				.collect(Collectors.groupingBy(anio -> anio, Collectors.counting()));

	}

	/*
	 * Devuelve los autos filtrados
	 * @param autos array de autos
	 * @param filtro filtro a aplicar
	 * @return autos filtrados
	 */
	public static List<Auto> filtrarAutos(List<Auto> autos, Predicate<Auto> filtro) {
		return autos.stream().filter(filtro).toList();
	}

	/**
	 * Devuelve una lista de autos que no sean del tipo especificado
	 *
	 * @param autos array de autos
	 * @param tipo  tipo a filtrar
	 * @return lista de autos que no sean del tipo especificado
	 */
	public static List<Auto> obtenerTodosLosAutosQueNoSeanDelTipo(List<Auto> autos, String tipo) {
		return autos
				.stream()
				.filter(auto -> auto.getTipos().stream().noneMatch(tipo::equals))
				.toList();
	}

	/*
	 * Obtener las marcas que tengan modelos con números en el nombre
	 * @param autos array de autos
	 * @return marcas que tengan modelos con números en el nombre
	 */
	public static Set<String> obtenerLasMarcasQueTenganModelosConNumeros(List<Auto> autos) {
		return autos
				.stream()
				.filter(auto -> auto.getModelo().matches(".*\\d.*"))
				.map(Auto::getMarca)
				.collect(Collectors.toSet());
	}

	/*
	 * Leer el archivo autos.csv y devuelve un array de autos
	 * @return array de autos
	 */
	public static List<Auto> obtenerAutos() {
			//String path = Files.lines(Path.of(ClassLoader.getSystemResource("autos.csv").toURI()));
		try {
			List<Auto> autos;
			autos = Files.lines(Path.of(ClassLoader.getSystemResource("autos.csv").toURI()))
				.map(Auto::fromString)
				.toList();
			return autos;
		} catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
