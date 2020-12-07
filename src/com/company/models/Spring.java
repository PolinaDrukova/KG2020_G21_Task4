package com.company.models;

import com.company.math.Vector3;
import com.company.third.IModel;
import com.company.third.PolyLine3D;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Spring implements IModel {
    private float radius;
    private float step;
    private int turns;
    private int frequency;
    private boolean direction;
    private Vector3 centre;


    public Spring(float radius, Vector3 centre, float step, int turns, int frequency, boolean direction) {
        this.radius = radius;
        this.centre = centre;
        this.step = step;
        this.turns = turns;
        this.frequency = frequency;
        this.direction = direction;
    }

    @Override
    public List<PolyLine3D> getLines() {
        LinkedList<PolyLine3D> lines = new LinkedList<>();

        double da = 0;
        float stepOfRad = (float) (2 * Math.PI / frequency);
        float stepOfZ = step / frequency;
        float dx;
        float dy;
        float dz = 0;
        Vector3 vector = new Vector3(radius, 0, dz);
        Vector3 previousVector;

        for (int j = 0; j < turns; j++) {
            for (int i = 0; i < frequency; i++) {
                dx = (float) (radius * Math.cos(da));
                dy = (float) (radius * Math.sin(da));
                dz += stepOfZ;
                previousVector = vector;
                vector = new Vector3(centre.getX() + dx, centre.getY() + dz, centre.getZ() + dy);

                lines.add(new PolyLine3D(Arrays.asList(previousVector, vector), true));

                if (direction) {
                    da -= stepOfRad;
                } else {

                    da += stepOfRad;
                }
            }
        }
        return lines;
    }
}
