package Dict;


/**
 * Implementación de un diccionario que puede almacenar cualquier tipo de objeto como clave y valor.
 * Esta clase usa una tabla hash interna con manejo de colisiones mediante listas enlazadas.
 *
 * @param <K> Tipo de las claves en el diccionario.
 * @param <V> Tipo de los valores en el diccionario.
 */
public class MiDiccionario<K, V> {
    /**
     * Clase interna para manejar las entradas del diccionario.
     */
    private class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry<K, V>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    @SuppressWarnings("unchecked")
    public MiDiccionario() {
        table = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Calcula el índice de la tabla hash para una clave dada.
     *
     * @param key La clave para calcular el índice.
     * @return El índice calculado.
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }


    /**
     * Agrega o actualiza un par clave-valor en el diccionario.
     *
     * @param key La clave del elemento a agregar o actualizar.
     * @param value El valor asociado a la clave.
     */
    public void put(K key, V value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser null");
        }

        int index = getIndex(key);
        Entry<K, V> newEntry = new Entry<>(key, value, null);
        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry<K, V> current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = newEntry;
        }
        size++;

        if (size >= LOAD_FACTOR * table.length) {
            resize();
        }
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }


    /**
     * Obtiene el valor asociado a una clave específica.
     *
     * @param key La clave del valor a obtener.
     * @return El valor asociado a la clave, o null si la clave no existe.
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser null");
        }

        int index = hash(key);
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    /**
     * Elimina el par clave-valor asociado a una clave específica.
     *
     * @param key La clave del elemento a eliminar.
     */
    public void remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser null");
        }

        int index = hash(key);
        Entry<K, V> prev = null;
        for (Entry<K, V> e = table[index]; e != null; prev = e, e = e.next) {
            if (e.key.equals(key)) {
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    table[index] = e.next;
                }
                size--;
                return;
            }
        }
    }

    /**
     * Redimensiona la tabla hash cuando el factor de carga supera un umbral específico.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[]) new Entry[oldTable.length * 2];
        size = 0;
        for (Entry<K, V> head : oldTable) {
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }

    /**
     * Devuelve el número de elementos almacenados en el diccionario.
     *
     * @return El tamaño del diccionario.
     */
    public int size() {
        return size;
    }

    /**
     * Limpia el diccionario eliminando todos los elementos.
     */
    public void clear() {
        table = (Entry<K, V>[]) new Entry[table.length];
        size = 0;
    }

    /**
     * Devuelve un array con las claves del diccionario
     */
    @SuppressWarnings("unchecked")
    public K[] keys() {
        K[] keys = (K[]) new Object[size];
        int index = 0;
        for (int i=0; i< table.length; i++) {
            for (Entry<K, V> e = table[i]; e != null; e = e.next) {
                keys[index] = e.key;
                index++;
            }
        }
        return keys;
    }


    /**
     * Devuelve un array con los valores del diccionario
     */
    @SuppressWarnings("unchecked")
    public V[] values() {
        V[] values = (V[]) new Object[size];
        int index = 0;
        for (int i=0; i< table.length; i++) {
            for (Entry<K, V> e = table[i]; e != null; e = e.next) {
                values[index] = e.value;
                index++;
            }
        }
        return values;
    }

    /**
     * Devuelve un array con las parejas (clave-valor) del diccionario
     */
    @SuppressWarnings("unchecked")
    public Object[][] items() {
        Object[][] items = new Object[size][2];
        int index = 0;
        for (int i=0; i< table.length; i++) {
            for (Entry<K, V> e = table[i]; e != null; e = e.next) {
                items[index][0] = e.key;
                items[index][1] = e.value;
                index++;
            }
        }
        return items;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser null");
        }

        int index = hash(key); // Calcula el índice usando la función hash.
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.key.equals(key)) {
                // Si encuentra una entrada con la clave, devuelve true.
                return true;
            }
        }
        // Si termina de recorrer la lista sin encontrar la clave, devuelve false.
        return false;
    }

}
