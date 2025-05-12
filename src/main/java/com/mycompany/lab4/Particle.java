/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author nsoko
 */
public class Particle {
    private float x, y;
    private float vx, vy; 
    private float size;
    private Color color;
    private int life;

    public Particle(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        Random random = new Random();
        this.vx = (random.nextFloat() - 0.5f) * 6;
        this.vy = (random.nextFloat() - 0.5f) * 6;
        this.size = 5 + random.nextFloat() * 10;
        this.color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256), 150); 
        this.life = 30 + random.nextInt(60);
    }

    public boolean update() {
        x += vx;
        y += vy;
        life--;
        vx *= 0.95f;
        vy *= 0.95f;
        return life <= 0;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval((int) x, (int) y, (int) size, (int) size);
    }
}
