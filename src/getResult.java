package Controller;

import Dao.businessLogicLayer;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 表示层，接收业务逻辑
 */
public class getResult extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public getResult() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestName = request.getParameter("Selection");
        //System.out.println(requestName);
        List<Double> list = null;
        try {
            Class<?> g = Class.forName("Dao.businessLogicLayer");
            businessLogicLayer getM = (businessLogicLayer) g.newInstance();
            if (requestName.equals("maxValue")) {
                list = getM.getRes(1);
                request.setAttribute("list", list);
                request.getRequestDispatcher("/maxValue.jsp").forward(request, response);
            } else if (requestName.equals("minValue")) {
                list = getM.getRes(2);
                request.setAttribute("list", list);
                request.getRequestDispatcher("/minValue.jsp").forward(request, response);
            } else if (requestName.equals("finalValue")) {
                list = getM.getRes(3);
                request.setAttribute("list", list);
                request.getRequestDispatcher("/finalValue.jsp").forward(request, response);
            } else if (requestName.equals("midValue")) {
                list = getM.getRes(4);
                request.setAttribute("list", list);
                request.getRequestDispatcher("/midValue.jsp").forward(request, response);
                BufferedImage image= ImageIO.read(new FileInputStream("src/Sources/midPrice.png"));
                ServletOutputStream sos = response.getOutputStream();
                ImageIO.write(image, "png", sos);
                sos.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.print(list.size());
        response.getWriter().append("Served at: ").append(request.getContextPath());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
