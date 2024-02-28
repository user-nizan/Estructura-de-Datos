import Dict.MiDiccionario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MiDiccionarioTest {
    private MiDiccionario<String, String> dicString;
    private MiDiccionario<Integer, String> dicInt;
    private MiDiccionario<String, Object> dicObject;
    private MiDiccionario<Integer, String> diccionario;

    @BeforeEach
    public void setUp() {
        // Inicializar diccionarios para diferentes tipos de pruebas
        dicString = new MiDiccionario<>();
        dicInt = new MiDiccionario<>();
        dicObject = new MiDiccionario<>();
        diccionario = new MiDiccionario<>();
    }

    @Test
    public void testPutAndGetWithString() {
        dicString.put("clave", "valor");
        assertEquals("valor", dicString.get("clave"));
    }

    @Test
    public void testPutAndGetWithInt() {
        dicInt.put(1, "valor");
        assertEquals("valor", dicInt.get(1));
    }

    @Test
    public void testPutAndGetWithObject() {
        Object valor = new Object();
        dicObject.put("clave", valor);
        assertEquals(valor, dicObject.get("clave"));
    }

    @Test
    public void testRemoveWithString() {
        dicString.put("clave", "valor");
        dicString.remove("clave");
        assertNull(dicString.get("clave"));
    }

    @Test
    public void testRemoveWithInt() {
        dicInt.put(1, "valor");
        dicInt.remove(1);
        assertNull(dicInt.get(1));
    }

    @Test
    public void testClear() {
        dicString.put("clave1", "valor1");
        dicString.put("clave2", "valor2");
        dicString.clear();
        assertEquals(0, dicString.size());
    }

    @Test
    public void testSize() {
        assertEquals(0, dicString.size());
        dicString.put("clave1", "valor1");
        assertEquals(1, dicString.size());
        dicString.put("clave2", "valor2");
        assertEquals(2, dicString.size());
    }

    @Test
    public void testContainsKey() {
        dicString.put("clave", "valor");
        assertTrue(dicString.containsKey("clave"));
        assertFalse(dicString.containsKey("clave_inexistente"));
    }

    @Test
    public void testRedimensionamiento() {
        // Inserta elementos suficientes para superar el umbral del factor de carga y provocar un redimensionamiento.
        // Ajusta este número según el tamaño inicial de tu diccionario y el factor de carga.
        int numeroElementos = 100; // Este número depende de la capacidad inicial y el factor de carga de tu diccionario.
        for (int i = 0; i < numeroElementos; i++) {
            diccionario.put(i, "Valor" + i);
        }

        // Verifica que el tamaño del diccionario sea el esperado después de las inserciones.
        assertEquals(numeroElementos, diccionario.size(), "El tamaño del diccionario debería ser igual al número de elementos insertados.");

        // Verifica que todos los elementos insertados estén presentes y correctamente ubicados después del redimensionamiento.
        for (int i = 0; i < numeroElementos; i++) {
            assertTrue(diccionario.containsKey(i), "El diccionario debería contener la clave: " + i);
            assertEquals("Valor" + i, diccionario.get(i), "El valor asociado con la clave " + i + " debería ser 'Valor" + i + "'.");
        }
    }


    @Test
    public void testRemove() {
        MiDiccionario<Integer, String> dic = new MiDiccionario<>();
        dic.put(1, "valor1");
        dic.remove(1);
        assertFalse(dic.containsKey(1), "La clave '1' no debería estar presente después de ser eliminada.");
    }

    @Test
    public void testKeys() {
        MiDiccionario<String, Integer> diccionario = new MiDiccionario<>();
        diccionario.put("Uno", 1);
        diccionario.put("Dos", 2);
        diccionario.put("Tres", 3);

        Object[] keys = diccionario.keys();
        assertEquals(3, keys.length, "Debería devolver tres claves.");
        assertTrue(Arrays.asList(keys).contains("Uno"), "Debería contener 'Uno'.");
        assertTrue(Arrays.asList(keys).contains("Dos"), "Debería contener 'Dos'.");
        assertTrue(Arrays.asList(keys).contains("Tres"), "Debería contener 'Tres'.");
    }

    @Test
    public void testValues() {
        MiDiccionario<String, Integer> diccionario = new MiDiccionario<>();
        diccionario.put("Uno", 1);
        diccionario.put("Dos", 2);
        diccionario.put("Tres", 3);

        Object[] values = diccionario.values();
        assertEquals(3, values.length, "Debería devolver tres valores.");
        assertTrue(Arrays.asList(values).contains(1), "Debería contener 1.");
        assertTrue(Arrays.asList(values).contains(2), "Debería contener 2.");
        assertTrue(Arrays.asList(values).contains(3), "Debería contener 3.");
    }


    @Test
    public void testItems() {
        MiDiccionario<String, Integer> diccionario = new MiDiccionario<>();
        diccionario.put("Uno", 1);
        diccionario.put("Dos", 2);

        Object[][] items = diccionario.items();
        assertEquals(2, items.length, "Debería haber dos pares clave-valor.");

        // Verificamos cada par usando una bandera booleana.
        boolean foundUno = false;
        boolean foundDos = false;
        for (Object[] item : items) {
            if (item[0].equals("Uno") && item[1].equals(1)) {
                foundUno = true;
            } else if (item[0].equals("Dos") && item[1].equals(2)) {
                foundDos = true;
            }
        }
        assertTrue(foundUno, "Debería contener el par ['Uno', 1].");
        assertTrue(foundDos, "Debería contener el par ['Dos', 2].");
    }

}
