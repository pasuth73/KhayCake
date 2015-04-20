package sit.khaycake.servlet.Province;

import com.google.gson.Gson;
import sit.khaycake.database.SQL;
import sit.khaycake.model.Province;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pasuth on 19/4/2558.
 */
public class PatternProvinceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resource = request.getRequestURI().substring(request.getRequestURI().indexOf("/",1));

        if(resource.indexOf("delete")>=0){
            resource = request.getRequestURI().substring(0, request.getRequestURI().indexOf("/",1));
            SQL sql = new SQL();
            try {
                int a = sql
                        .delete(Province.TABLE_NAME)
                        .where(Province.COLUMN_ID, SQL.WhereClause.Operator.EQ, resource)
                        .exec();
                if(a<0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

        }else{
            Province province = null;
            try {
                province = (Province)SQL.findById(Province.class,Integer.parseInt(resource));

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            if(province!=null){
                Gson gson = new Gson();
                response.getWriter().print(gson.toJson(province));
            }else{
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resource = request.getRequestURI().substring(request.getRequestURI().indexOf("/",1));
        Province province = null;
        try {
            province = (Province) SQL.findById(Province.class, Integer.parseInt(resource));
        }catch(Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        if(province!=null){
            province.setName(request.getParameter("name"));

            SQL sql = new SQL();
            try {
                sql
                        .update(Province.TABLE_NAME)
                        .set(Province.COLUMN_NAME, province.getName())
                        .where(Province.COLUMN_ID, SQL.WhereClause.Operator.EQ, province.getId())
                        .exec();

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }

}
