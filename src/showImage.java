import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//演示如何显示图像
class showImage {

    public static void main(String[] args) throws IOException {
        showImage.show("P:\\playground\\javaplay\\bookmanage\\bookmanage\\picture\\funhack.jpg");
    }
    public  static void show(String path) throws IOException {
        // method1:把图片文件读到缓存图像
        BufferedImage image = ImageIO.read(new File(path));
        ImageIcon icon = new ImageIcon(image); // 创建一个图标
//	    method2:
//		URL url = new URL("file:///E:/apple.png"); // 创建一个本地路径的URL对象
//		ImageIcon icon = new ImageIcon(url); // 创建一个来自网络图片的图标
        JFrame frame = new JFrame(path); // 创建一个窗口对象
        frame.setSize(icon.getIconWidth(), icon.getIconHeight());// 必须设置宽高，否则没有窗体
        frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序
        JLabel label = new JLabel(icon);
        frame.add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true); // 必须设置为true，否则看不见
    }
}