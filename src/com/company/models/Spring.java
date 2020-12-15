package com.company.models;

import com.company.math.Matrix3;
import com.company.math.Vector3;
import com.company.third.IModel;
import com.company.third.PolyLine3D;

import java.util.*;


public class Spring implements IModel {
    private float radius;
    private float step;
    private float section;
    private int turns;
    private int frequency;
    private boolean direction;
    private Vector3 centre;


    public Spring(float radius, float section, Vector3 centre, float step, int turns, int frequency, boolean direction) {
        this.radius = radius;
        this.centre = centre;
        this.step = step;
        this.turns = turns;
        this.frequency = frequency;
        this.direction = direction;
        this.section = section;
    }

    @Override
    public List<PolyLine3D> getLines() {
        LinkedList<PolyLine3D> lines = new LinkedList<>();
        List<Vector3> spring = new ArrayList<>();
        double da = 0;
        float stepOfRadius = (float) (2 * Math.PI / frequency);
        float stepOfY = step / frequency;
        float dx;
        float dy = 0;
        float dz;

        for (int j = 0; j < turns; j++) {
            for (int i = 0; i < frequency; i++) {
                dx = (float) (radius * Math.cos(da));
                dy += stepOfY;
                dz = (float) (radius * Math.sin(da));
                spring.add(new Vector3(centre.getX() + dx, centre.getY() + dy, centre.getZ() + dz));
                if (direction) {
                    da -= stepOfRadius;
                } else {
                    da += stepOfRadius;
                }
            }
        }
        lines.add(new PolyLine3D((spring), false));


        Vector3[][] circle = new Vector3[spring.size()][frequency];

        for (int i = 0; i < circle.length - 1; i++) {
            da = 0;
            for (int j = 0; j < frequency; j++) {
                dx = spring.get(i).getX() + (float) (section * Math.cos(da)) ;
                dy = spring.get(i).getY() + (float) (section * Math.sin(da));
                dz = spring.get(i).getZ();

                circle[i][j] =  new Vector3(dx, dy, dz);
                da += stepOfRadius;
            }



            lines.add(new PolyLine3D(Arrays.asList(circle[i]), true));
        }

        return lines;
    }
}
