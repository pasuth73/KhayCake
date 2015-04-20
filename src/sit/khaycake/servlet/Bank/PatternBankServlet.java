package sit.khaycake.servlet.Bank;

import com.google.gson.Gson;
import sit.khaycake.database.SQL;
import sit.khaycake.model.Bank;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pasuth on 19/4/2558.
 */
public class PatternBankServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resource = request.getRequestURI().substring(request.getRequestURI().indexOf("/",1));

        if(resource.indexOf("delete")>=0){
            resource = request.getRequestURI().substring(0, request.getRequestURI().indexOf("/",1));
            SQL sql = new SQL();
            try {
                int a = sql
                        .delete(Bank.TABLE_NAME)
                        .where(Bank.COLUMN_ID, SQL.WhereClause.Operator.EQ, resource)
                        .exec();
                if(a<0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

        }else{
            Bank bank = null;
            try {
                bank = (Bank)SQL.findById(Bank.class,Integer.parseInt(resource));

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            if(bank!=null){
                Gson gson = new Gson();
                response.getWriter().print(gson.toJson(bank));
            }else{
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resource = request.getRequestURI().substring(request.getRequestURI().indexOf("/",1));
        Bank bank = null;
        try {
            bank = (Bank) SQL.findById(Bank.class, Integer.parseInt(resource));
        }catch(Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        if(bank!=null){
            bank.setName(request.getParameter("nameEn"));

            SQL sql = new SQL();
            try {
                sql
                        .update(Bank.TABLE_NAME)
                        .set(Bank.COLUMN_NAME_EN, bank.getName())
                        .set(Bank.COLUMN_NAME_TH, request.getParameter("nameTh"))
                        .where(Bank.COLUMN_ID, SQL.WhereClause.Operator.EQ, bank.getId())
                        .exec();

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

        }else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
