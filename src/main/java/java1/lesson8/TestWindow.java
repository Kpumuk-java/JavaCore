package java1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TestWindow extends JFrame {
    /*
     * Тип int используется чаще при работе с целочисленными данными,
     * нежели short, даже если их диапазона хватает.
     * Это происходит потому, что при указании значений типа  byte и short
     * в выражениях, их тип все равно автоматически повышается до int при вычислении
     * */
    private int sizeFrameWidth;
    private int sizeFrameHeight;
    private JPanel panel;

    public TestWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.sizeFrameWidth = (int) ((screenSize.getWidth() * 2) / 3);
        this.sizeFrameHeight = (int) ((screenSize.getHeight() * 2) / 3);
        setTitle("Догони кнопку");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(sizeFrameWidth / 4, sizeFrameHeight / 4, sizeFrameWidth, sizeFrameHeight);
        this.panel = new JPanel();
        panel.setLayout(null);
        add(panel, BorderLayout.CENTER);
        setVisible(true);

    }

    /*
     * Создается кнопка, с размером 120 на 40, находящееся в середине поля (на глазок), добавляется на панель panel.
     * к кнопке вешается слушатель мышки, который при наведении рандомно передвигает кнопку по панели, размер панели
     * равен размеру окна JFrame, в теории кнопка не должна выходить за размеры sizeFrameWidth, sizeFrameHeight
     * */
    public void ghostButton() {
        JButton button = new JButton("Нажми меня");
        button.setSize(120, 40);
        button.setLocation((sizeFrameWidth - button.getWidth()) / 2, (sizeFrameHeight / 2) - button.getHeight());
        panel.add(button);
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.exit(0);

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            //При наведении на кнопку рандомно меняются координаты кнопки
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                int x, y;
                do {
                    x = (int) (Math.random() * (sizeFrameWidth - button.getWidth()));
                    y = (int) (Math.random() * (sizeFrameHeight - button.getHeight() * 2));
                } while (checkFrame(x, y));

                button.setLocation(x, y);

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });


    }

    // Логика проверки минимальна, поймать кнопку все же можно
    public boolean checkFrame(int x, int y) {
        if ((x < 0) && (y < 0)) {
            return true;
        }
        return false;
    }

}
