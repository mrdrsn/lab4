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
    private float x, y; // Координаты
    private float vx, vy; // Скорость
    private float size; // Размер
    private Color color; // Цвет
    private int life; // Время жизни

    public Particle(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        Random random = new Random();
        this.vx = (random.nextFloat() - 0.5f) * 6; // Случайная скорость по X
        this.vy = (random.nextFloat() - 0.5f) * 6; // Случайная скорость по Y
        this.size = 5 + random.nextFloat() * 10; // Случайный размер
        this.color = new Color(random.nextInt(1), random.nextInt(1), random.nextInt(256 - 100) + 100, 150); // Полупрозрачный цвет
        this.life = 30 + random.nextInt(60); // Случайное время жизни
    }

    public boolean update() {
        x += vx;
        y += vy;
        life--;

        // Замедление и исчезновение частицы
        vx *= 0.95f;
        vy *= 0.95f;
        return life <= 0;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval((int) x, (int) y, (int) size, (int) size);
    }
}
