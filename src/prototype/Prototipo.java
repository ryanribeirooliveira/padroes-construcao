package prototype;

/**
 * INTERFACE PROTOTIPO (Prototype) do padrao Prototype.
 *
 * Declara a operacao de clonagem. Objetos que a implementam sabem produzir
 * copias de si mesmos, evitando recriar tudo do zero.
 */
public interface Prototipo<T> {
    T clonar();
}
