package crap;

import java.util.Objects;

public class Entity {
    int a;
    int b;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return a == entity.a &&
                b == entity.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
