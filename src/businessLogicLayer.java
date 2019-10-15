package Dao;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务逻辑处理，查询股票最高价，被表示层调用
 */
public class businessLogicLayer {
    public static List<Double> getRes(int quest) {
        List<Double> ans = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataAccessLayer.getLink();
            String sql = null;
            if (quest == 1) {
                sql = "SELECT [最高价(元)] FROM dbo.experimentalData";
               // paint("maxPrice",sql);
            } else if (quest == 2) {
                sql = "SELECT [最低价(元)] FROM dbo.experimentalData";
               // paint("minPrice",sql);
            } else if (quest == 3) {
                sql = "SELECT [收盘价(元)] FROM dbo.experimentalData";
               // paint("finalPrice",sql);
            } else if (quest == 4) {
                sql = "SELECT [均价(元)] FROM dbo.experimentalData";
               // paint("midPrice",sql);
            }
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double temMax = resultSet.getDouble(1);
                ans.add(temMax);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static void paint(String tableName,String sql) {
        CategoryDataset mDataset = getDataSet(sql);
        JFreeChart mChart = ChartFactory.createLineChart(tableName,// 图名字
                "day",// 横坐标
                "price",// 纵坐标
                mDataset,// 数据集
                PlotOrientation.VERTICAL, true, // 显示图例
                true, // 采用标准生成器
                false);// 是否生成超链接
        CategoryPlot mPlot = (CategoryPlot) mChart.getPlot();
        mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
        mPlot.setRangeGridlinePaint(Color.BLUE);// 背景底部横虚线
        mPlot.setOutlinePaint(Color.black);// 边界线
        saveAsFile(mChart,"src/Sources/"+tableName+".png",600,400);

    }

    public static DefaultCategoryDataset getDataSet(String sql) {
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
        List<Double> ans = new ArrayList<>();
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = dataAccessLayer.getLink();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double price = resultSet.getDouble(1);
                ans.add(price);
            }
            for (int i = 0; i < ans.size(); i++) {
                mDataset.addValue(ans.get(i), "", i + "d");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDataset;
    }

    // 保存为文件


    public static void saveAsFile(JFreeChart chart, String outputPath, int weight, int height) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            // 保存为PNG
            ChartUtilities.writeChartAsPNG(out, chart, weight, height);
            // 保存为JPEG
            // ChartUtilities.writeChartAsJPEG(out, chart, weight, height);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

   /*
   	测试查询
   	public static void main(String[] args) {
        businessLogicLayer g = new businessLogicLayer();
        List<Double> list = g.getMax();
        for (Double d : list) {
            System.out.println(d);
        }

    }*/
}
