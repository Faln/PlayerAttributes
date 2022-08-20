package me.faln.playerattributes.cache;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface SimpleCache<K, V> {

    Map<K, V> getCache();

    default void register(@NotNull final K key, @NotNull final V value) {
        this.getCache().put(key, value);
    }

    default void get(@NotNull final K key) {
        this.getCache().get(key);
    }

    default void remove(@Nonnull final K key) {
        this.getCache().remove(key);
    }

    default boolean containsKey(@Nonnull final K key) {
        return this.getCache().containsKey(key);
    }

    default boolean containsValue(@Nonnull final V value) {
        return this.getCache().containsValue(value);
    }

    default Set<K> keySet() {
        return this.getCache().keySet();
    }

    default Collection<V> values() {
        return this.getCache().values();
    }

    default Set<Map.Entry<K, V>> entrySet() {
        return this.getCache().entrySet();
    }
}
