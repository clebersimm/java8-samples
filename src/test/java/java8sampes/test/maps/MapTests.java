package java8sampes.test.maps;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

/**
 * Some tests with maps in java8
 * 
 * @author clebersimm
 *
 */
public class MapTests {

	Map<Integer, String> map1 = new HashMap<>();
	String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

	@Before
	public void init() {
		for (int i = 0; i < 10; i++) {
			map1.put(i, alphabet[i]);
		}
	}

	/**
	 * Test with two maps, with the same content. The idea is show the final value(
	 * the alphabet vector)
	 */
	@Test
	public void extractValuesFromMapOfMap() {
		Map<Integer, Map<Integer, String>> map = new HashMap<>();
		map.put(1, map1);
		map.put(2, map1);

		List<String> collect = map.values().stream().map(value -> value.values().stream().collect(Collectors.toList()))
				.flatMap(value -> value.stream()).collect(Collectors.toList());
		List<String> result = new ArrayList<>();
		List<String> asList = Arrays.asList(alphabet);
		result.addAll(asList);
		result.addAll(asList);
		assertTrue(collect.equals(result));
	}
	
	//Convert the values of the map to lower case
	@Test
	public void convertValuesToLowerCase() {
		Map<Integer,String> result =  map1.keySet().stream().collect(Collectors.toMap(key->key, key->map1.get(key).toLowerCase()));
		List<String> comp = Arrays.asList(alphabet);
		comp = comp.stream().map(s->s.toLowerCase()).collect(Collectors.toList());
		assertTrue(result.values().containsAll(comp));
	}	
}