package com.sinocontact.app.utils;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码生成器工具类
 * 使用例子：struts2.x
 * String verifyCode = VerifyCodeUtil.getRandomVerifyCode();
 * ByteArrayInputStream verifyCodeInputStream = VerifyCodeUtil.getImageAsInputStream(verifyCode);
 *
 *
 * 使用例子：springboot1.x
 * String verifyCode = VerifyCodeUtil.getRandomVerifyCode();
 * VerifyCodeUtil.outputImage(verifyCode,response.getOutputStream());
 */
public class VerifyCodeUtil {

    private static final Logger logger = Logger.getLogger(VerifyCodeUtil.class);

    //验证码图片的高和宽
    private static int WIDTH = 100;
    private static int HEIGHT = 40;

    /**
     * 生成4个字符的随机验证码
     * @return String 4个字符的随机验证码字符串
     * @author sam
     * @since 2018年5月21日
     */
    public static String getRandomVerifyCode() {
        String str = "3456789abcdefhjkmnpqrstuvwxyz";
        char[] rands = new char[4];
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            rands[i] = str.charAt(random.nextInt(29));
        }
        return new String(rands);
    }
    /**
     * 向OutputStream中输出图片
     * @param verifyCode	//验证码
     * @param os
     * @author sam
     * @since 2018年12月13日
     */
    public static void outputImage(String verifyCode,OutputStream os){
        //生成内存验证码图片
        BufferedImage image = createImage(verifyCode);
        try{
            ImageIO.write(image, "jpeg", os);
        }catch(Exception e){
            logger.error("",e);
        }
    }
    /**
     * 根据验证码字符串生成图片验证码，并转换为ByteArrayInputStream供struts2的action使用
     * @param verifyCode 验证码字符串
     * @return ByteArrayInputStream 图片输入流
     * @author sam
     * @since 2018年5月21日
     */
    public static ByteArrayInputStream getImageAsInputStream(String verifyCode){

        //生成内存验证码图片
        BufferedImage image = createImage(verifyCode);

        //把内存图片转换为ByteArrayInputStream
        return convertImageToStream(image);
    }



    /**
     * 把图片转换为ByteArrayInputStream
     * @param image 待转换的图片
     * @return ByteArrayInputStream null表示出现异常，其他表示正常
     * @author sam
     * @since 2018年5月21日
     */
    private static ByteArrayInputStream convertImageToStream(BufferedImage image){
        ByteArrayOutputStream outputStream = null;
        ByteArrayInputStream input = null;
        try {
            //把图片数据写入到临时输出流
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", outputStream);

            //从输出流读入到输入流中
            input = new ByteArrayInputStream(outputStream.toByteArray());

            return input;
        } catch (Exception e) {
            logger.error("",e);
        } finally {
            try {
                input.close();
            } catch (Exception e1) {
                logger.error("",e1);
            }

            try {
                outputStream.close();
            } catch (Exception e2) {
                logger.error("",e2);
            }
        }
        return null;
    }
    /**
     * 把验证码字符串生成到图片
     * @return BufferedImage 内存图片
     * @author sam
     * @since 2018年5月21日
     */
    private static BufferedImage createImage(String verifyCode){
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        //画出背景图像
        drawBackground(g);

        //画出随机字符串
        drawRands(g, verifyCode);

        // 结束图像 的绘制 过程， 完成图像
        g.dispose();

        return image;
    }
    /**
     * 在图片上画出背景
     * @param g 图片画布
     * @author sam
     * @since 2018年5月21日
     */
    private static void drawBackground(Graphics g) {
        // 画背景
        g.setColor(new Color(0xDCDCDC));

        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 随机产生 120 个干扰点

        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * WIDTH);

            int y = (int) (Math.random() * HEIGHT);

            int red = (int) (Math.random() * 255);

            int green = (int) (Math.random() * 255);

            int blue = (int) (Math.random() * 255);

            g.setColor(new Color(red, green, blue));

            g.drawOval(x, y, 1, 0);
        }
    }

    /**
     * 在图片上画出验证码
     * @param g 图片画布
     * @param rands
     * @author sam
     * @since 2018年5月21日
     */
    private static void drawRands(Graphics g, String rands) {
        g.setColor(Color.BLACK);

        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 25));

        // 在不同的高度上输出验证码的每个字符

        g.drawString("" + rands.charAt(0), 1, 27);

        g.drawString("" + rands.charAt(1), 26, 19);

        g.drawString("" + rands.charAt(2), 51, 30);

        g.drawString("" + rands.charAt(3), 76, 26);

    }
}
