package org.bohdi.protobuf.inspector;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.bohdi.protobuf.inspector.ExpectationHelper.isHonda1999;
import static org.bohdi.protobuf.inspector.ExpectationHelper.isHonda2001;
import static org.bohdi.protobuf.inspector.ProtobufHelper.createCar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FieldTest {
    @Test
    public void test_Field() {

        List<Car.Sedan> list = new ArrayList<Car.Sedan>();
        list.add(createCar("Honda", 1999));
        list.add(createCar("Honda", 2001));
        list.add(createCar("Toyota", 1999));

        ProtobufInspector<Car.Sedan> pi = new ProtobufInspector<>(list);

        Field<Car.Sedan, String> fs = new Field<>("Make == Honda", f->f.getMake(), v->v.equals("Honda"));
        Field<Car.Sedan, Integer> fi = new Field<>("Year == 1999", f->f.getYear(), v->v == 1999);

        assertTrue("Honda", pi.test(fs));
        assertTrue("1999", pi.test(fi));

        assertEquals(list("success: Make == Honda", "success: Year == 1999"), pi.getAudit().trace);
    }

    @Test
    public void test_isSedan() {

        List<Car.Sedan> list = new ArrayList<Car.Sedan>();
        list.add(createCar("Honda", 1999));
        list.add(createCar("Honda", 2001));
        list.add(createCar("Toyota", 1999));

        ProtobufInspector<Car.Sedan> pi = new ProtobufInspector<>(list);

        assertTrue("Honda", pi.test(isHonda1999));
        assertTrue("1999", pi.test(isHonda1999));
    }

    <T> List<T> list(T ... ts) {
        List<T> result = new ArrayList<>();
        Collections.addAll(result, ts);
        return result;
    }
}
