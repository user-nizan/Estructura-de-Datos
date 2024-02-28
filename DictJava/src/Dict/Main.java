package Dict;

public class Main {
    public static void main(String[] args) {
        MiDiccionario<String, Integer> diccionario = new MiDiccionario<>();

        // Prueba de inserción y recuperación
        diccionario.put("Uno", 1);
        System.out.println("Insertado ['Uno', 1]");
        System.out.println("Valor para 'Uno': " + diccionario.get("Uno"));

        // Prueba de actualización
        diccionario.put("Uno", 11);
        System.out.println("Actualizado ['Uno', 11]");
        System.out.println("Valor actualizado para 'Uno': " + diccionario.get("Uno"));

        // Prueba de no permitir claves nulas
        try {
            diccionario.put(null, 2);   // Comentar para no para la ejecución del código
        } catch (IllegalArgumentException e) {
            System.out.println("Correctamente evitado insertar una clave nula.");
        }

        // Prueba de eliminación
        diccionario.put("Dos", 2);
        diccionario.remove("Dos");
        System.out.println("Eliminado 'Dos'. Existencia post-eliminación: " + diccionario.containsKey("Dos"));

        // Prueba de redimensionamiento
        for (int i = 0; i < 100; i++) {
            diccionario.put("Clave" + i, i);
        }
        System.out.println("Insertados 100 elementos. Tamaño post-inserción: " + diccionario.size());

        // Pruebas de keys(), values(), e items()
        Object[] keys = diccionario.keys();
        Object[] values = diccionario.values();
        Object[][] items = diccionario.items();

        System.out.println("Claves: " + java.util.Arrays.toString(keys));
        System.out.println("Valores: " + java.util.Arrays.toString(values));
        System.out.println("Items (primeros 5):");
        for (int i = 0; i < 5; i++) {
            System.out.println(java.util.Arrays.toString(items[i]));
        }

        // Prueba de clear()
        diccionario.clear();
        System.out.println("Diccionario limpiado. Tamaño post-limpieza: " + diccionario.size());
    }
}
