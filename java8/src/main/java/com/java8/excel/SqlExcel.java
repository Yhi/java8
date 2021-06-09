package com.java8.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.List;
import java.util.Map;

public class SqlExcel {

    public static void main(String[] args) {
        //ExcelReader reader = ExcelUtil.getReader(FileUtil.file("F:\\Slow_SQL.xlsx"));


        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("F:/Slow_SQL.xlsx"), "Slow SQL");
        List<Map<String, Object>> readAll = reader.readAll();
        List<String> result = CollUtil.newArrayList();
        for (Map<String, Object> map : readAll) {
            for (String s : map.keySet()) {
                //System.out.print(s+"------"+map.get(s) + "  ");
                if (s.equals("SQLText")) {
                    String sql = map.get(s).toString();
                    try {
                        result.add(sql.substring(0, sql.indexOf("where")));
                    } catch (Exception e) {
                        try {
                            //   System.out.println(sql.substring(0,sql.indexOf("WHERE")));
                            result.add(sql.substring(0, sql.indexOf("WHERE")));
                        } catch (Exception ex) {
                            // System.out.println(sql);
                            result.add(sql);
                        }

                    }

                }
            }

        }

        ExcelWriter writer = ExcelUtil.getWriter("F:/Slow_SQL_Result.xlsx");
        writer.passCurrentRow();
        //writer.merge(result.size() - 1, "sql");
        writer.write(result, true);
        writer.close();
    }
}
