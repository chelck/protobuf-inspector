package org.bohdi.protobuf.inspector;


import java.util.function.Function;
import java.util.function.Predicate;

public class Field<T, V> implements PiPredicate<T> {
    private final String comment;
    private final Function<T,V> f;
    private final Predicate<V> p;

    public Field(String comment, Function<T, V> f, Predicate<V> p) {
        this.comment = comment;
        this.f = f;
        this.p = p;
    }

    public boolean test(ProtobufInspector<T> auditor, T t) {
        boolean result = p.test(f.apply(t));
        if (result) {
            auditor.success(comment);
        }
        else {
            auditor.fail(comment);
        }
        return result;
    }


}
