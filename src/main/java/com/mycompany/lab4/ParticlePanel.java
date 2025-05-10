package com.mycompany.lab4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author nsoko
 */
public class ParticlePanel extends JPanel implements MouseMotionListener {
    private final ArrayList<Particle> particles = new ArrayList<>();
    private final Random random = new Random();

    public ParticlePanel() {
        setBackground(Color.WHITE); // Фон окна
        addMouseMotionListener(this); // Отслеживание движения мыши

        // Таймер для обновления анимации
        Timer timer = new Timer(30, e -> {
            updateParticles();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Отрисовка всех частиц
        for (Particle particle : particles) {
            particle.draw(g2d);
        }
    }

    private void updateParticles() {
        // Обновление состояния частиц
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle particle = particles.get(i);
            if (particle.update()) {
                particles.remove(i);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        spawnParticles(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        spawnParticles(e.getX(), e.getY());
    }

    private static int a = 0;
    // Создание новых частиц в месте курсора
    private void spawnParticles(int x, int y) {
        int count = 1 + random.nextInt(1);// Количество частиц
        a++;
        for (int i = 0; i < count; i++) {
            if (a % 3 == 1){
                particles.add(new Particle(x, y));
            }
        }
    }
}
