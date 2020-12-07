package com.company.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.company.math.Vector3;
import com.company.third.IModel;
import com.company.third.PolyLine3D;

/**
 * Описывает трёхмерный отрезок
 * @author Alexey
 */
public class Line3D implements IModel {
    private Vector3 p1, p2;

    public Line3D(Vector3 p1, Vector3 p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public List<PolyLine3D> getLines() {
        return Collections.singletonList(new PolyLine3D(
                Arrays.asList(p1, p2)
                , false));
    }

}