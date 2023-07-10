package tools;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

//演示如何显示图像
public class showImage {
    public static void show(String path) {
        // method1:把图片文件读到缓存图像
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        ImageIcon icon = new ImageIcon(image); // 创建一个图标
//	    method2:
//		URL url = new URL("file:///E:/apple.png"); // 创建一个本地路径的URL对象
//		ImageIcon icon = new ImageIcon(url); // 创建一个来自网络图片的图标
        JFrame frame = new JFrame(path); // 创建一个窗口对象
        frame.setSize(icon.getIconWidth(), icon.getIconHeight());// 必须设置宽高，否则没有窗体
        frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序

        JLabel label = new JLabel(icon);
        frame.add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true); // 必须设置为true，否则看不见
    }

    public static String chooseImage() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        String filePath = null;

        int returnValue = jfc.showOpenDialog(null);
//          returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            filePath = selectedFile.getPath();
            filePath = filePath.replaceAll("\\\\", "/");
            //  System.out.println(filePath);
        }
        return filePath;
    }
}